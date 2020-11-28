package fr.valkya.valkyris.common.market.transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.annotations.Expose;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.market.exception.MarketException;
import fr.valkya.valkyris.common.market.exception.ShoppingException;
import fr.valkya.valkyris.common.market.exception.ShoppingException.ShoppingError;
import fr.valkya.valkyris.common.market.pricing.Pricing;
import fr.valkya.valkyris.common.market.pricing.PricingCurrency;
import fr.valkya.valkyris.common.market.product.Product;
import fr.valkya.valkyris.common.utils.SerializableItemStack;
import fr.valkya.valkyris.server.ServerProxy;
import fr.valkya.valkyris.server.sql.SQL;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TransactionAction {

	@Expose(deserialize = false, serialize = false)
	private static Logger logger = LogManager.getLogger("Market");
	
	private TransactionActionType actionType;
	private String[] serverCommands;
	private List<SerializableItemStack> items;
	private String javaCallbackClass;

	public TransactionAction(String[] serverCommands) {
		this.actionType = TransactionActionType.SERVER_COMMANDS;
		this.serverCommands = serverCommands;
		
		// fixing shit
		for(int i = 0; i < serverCommands.length; i++) {
			String str = this.serverCommands[i];
			if(str.startsWith("/")) {
				this.serverCommands[i] = str.substring(1);
			}
		}
	}
	
	public TransactionAction(boolean sell, ItemStack... items) {
		if(sell && items.length != 1) {
			throw new UnsupportedOperationException("Cannot sell more than 1 item type!");
		}
		this.actionType = sell ? TransactionActionType.ITEM_SELL : TransactionActionType.ITEM_GIVE;
		
		this.items = new ArrayList<>();
		for(ItemStack is : items) {
			this.items.add(new SerializableItemStack(is));
		}
	}
	
	public TransactionAction(BiConsumer<Product, TransactionAction> callback) {
		this.actionType = TransactionActionType.JAVA_CALLBACK;
		this.javaCallbackClass = callback.getClass().getName();
	}

	@SideOnly(Side.SERVER)
	public void execute(TransactionData data, Consumer<TransactionData> successCallback, BiConsumer<TransactionData, Throwable> errorCallback) {
		EntityPlayerMP player = data.getPlayer();
		Product product = data.getProduct();
		TransactionType transactionType = data.getType();
		PricingCurrency currency = data.getCurrency();
		int ammount = data.getTransactionAmmount();
		
		logger.info("Preparing purchase, PurchaseData: {}", data.toString());

		Pricing prices = product.getPricing(transactionType);
		if(prices == null) {
			errorCallback.accept(data, new ShoppingException(ShoppingError.TRANSACTION_NOT_SUPPORTED));
			return;
		}
		
		Number nbr = prices.getPricing(currency);
		if(nbr == null) {
			errorCallback.accept(data, new ShoppingException(ShoppingError.CURRENCY_NOT_SUPPORTED));
			return;
		}
		double pricing = nbr.doubleValue();
		
		SQL sql = ((ServerProxy)Valkyris.getValkyris().getProxy()).getSQL();
		
		int currencyAmmount = -1;
		try {
			PreparedStatement statement = sql.getConnection().prepareStatement("SELECT * FROM `users` WHERE `pseudo` == ?");
			statement.setString(1, player.getCommandSenderName());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				currencyAmmount = result.getInt(currency.getDatabaseColumn());
			} else {
				logger.info("Player {} wasn't found in the database... wtf", player.getCommandSenderName());
				errorCallback.accept(data, new ShoppingException(ShoppingError.INTERNAL_ERROR, "Joueur introuvable dans la base de données."));
				return;
			}
		} catch(Exception e) {
			logger.info("Error during database access. player=" + player.getCommandSenderName() + ", currency=" + currency.name());
			e.printStackTrace();
			errorCallback.accept(data, new ShoppingException(ShoppingError.INTERNAL_ERROR, "Erreur durant l'accès à la base de données: " + e.getMessage()));
			return;
		}
		
		if(currencyAmmount == -1) {
			errorCallback.accept(data, new ShoppingException(ShoppingError.INTERNAL_ERROR, "Impossible d'obtenir votre solde."));
			return;
		}
		
		switch (actionType) {
			case SERVER_COMMANDS:
			case ITEM_GIVE:
			case JAVA_CALLBACK:
				if(currencyAmmount < pricing) {
					errorCallback.accept(data, new ShoppingException(ShoppingError.NOT_ENOUGH_MONEY));
					return;
				}
				
				double newBalance = currencyAmmount - pricing;
				
				try {
					PreparedStatement statement = sql.getConnection().prepareStatement("UPDATE `users` SET `" + currency.getDatabaseColumn() + "` = ? WHERE `pseudo` == ?");
					statement.setInt(1, (int) Math.floor(newBalance)); // i guess fuck you then
					statement.setString(2, player.getCommandSenderName());
					boolean result = statement.execute();
					if(!result) {
						errorCallback.accept(data, new ShoppingException(ShoppingError.INTERNAL_ERROR, "Erreur durant la mise à jour de la base de données."));
						return;	
					}
				} catch(Exception e) {
					logger.info("Error during database access. player=" + player.getCommandSenderName() + ", currency=" + currency.name());
					e.printStackTrace();
					errorCallback.accept(data, new ShoppingException(ShoppingError.INTERNAL_ERROR, "Erreur durant l'accès à la base de données: " + e.getMessage()));
					return;
				}
				break;
			case ITEM_SELL:
				int remaining = ammount;
				for(Object o : player.inventoryContainer.getInventory()) {
					ItemStack is = (ItemStack)o;
					if(items.contains(new SerializableItemStack(is))) {
						if(remaining - is.stackSize < 0) {
							is.stackSize -= remaining;
							break;
						} else if(remaining - is.stackSize > 0){
							remaining -= is.stackSize;
							is.stackSize = 0;
							continue;
						}else {
							break;
						}
					}
				}
				
				if(remaining == ammount) {
					errorCallback.accept(data, new ShoppingException(ShoppingError.NO_ITEMS));
					return;
				}
				
				if(remaining > 0) {
					errorCallback.accept(data, new ShoppingException(ShoppingError.NOT_ENOUGH_ITEMS));
					return;
				}
				break;
			default:
				break;
		}
		
		if(actionType == TransactionActionType.ITEM_GIVE) {
			int itemsAmount = -1;
			for(SerializableItemStack sis : this.items) {
				itemsAmount += sis.getAmmount();
			}
			int available = 0;
			for(ItemStack is : player.inventory.mainInventory) {
				if(is == null) {
					available += 64;
					continue;
				}
				if(is.getItem() == null) {
					available += 64;
					continue;
				}
				if(is.getItem() == Item.getItemFromBlock(Blocks.air)) {
					available += 0;
					continue;
				}
			}
			if(available < itemsAmount) {
				errorCallback.accept(data, new ShoppingException(ShoppingError.NOT_ENOUGH_INVENTORY_SPACE));
				return;
			}
		}
		
		switch (actionType) {
			case SERVER_COMMANDS:
				for(int i = 0; i < ammount; i++) {
					for(String command : this.serverCommands) {
						String cmd = command
								.replace("%player%", player.getCommandSenderName())
								.replace("%product_name%", product.getDisplayName())
								.replace("%product_internal_name%", product.getInternalName())
								.replace("%ammout%", String.valueOf(ammount))
								.replace("%price%", String.valueOf(pricing))
								.replace("%currency%", (pricing == 1) ? currency.getNameSingular() : currency.getNamePlural())
								.replace("%currency_abreviated%", (pricing == 1) ? currency.getAbreviatedSingular() : currency.getAbreviatedPlural());

						logger.debug("Executing command \"" + command + "\"");
						int returnValue = FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager().executeCommand(FMLCommonHandler.instance().getMinecraftServerInstance(), cmd);
						logger.debug("Command returned " + returnValue);
					}
				}
				
				successCallback.accept(data);
				break;
			case ITEM_GIVE:
				for(int i = 0; i < ammount; i++) {
					for(SerializableItemStack pi : items) {
						ItemStack is = pi.getItemStack();
						
						EntityItem entityitem = player.dropPlayerItemWithRandomChoice(is, false);
			            entityitem.delayBeforeCanPickup = 0;
			            entityitem.func_145797_a(player.getCommandSenderName());
					}
				}
				successCallback.accept(data);
				break;
			case ITEM_SELL:
				double newBalance = currencyAmmount + pricing;
				
				try {
					PreparedStatement statement = sql.getConnection().prepareStatement("UPDATE `users` SET `" + currency.getDatabaseColumn() + "` = ? WHERE `pseudo` == ?");
					statement.setInt(1, (int) Math.floor(newBalance)); // i guess fuck you then
					statement.setString(2, player.getCommandSenderName());
					boolean result = statement.execute();
					if(!result) {
						errorCallback.accept(data, new ShoppingException(ShoppingError.INTERNAL_ERROR, "Erreur durant la mise à jour de la base de données."));
						return;	
					}
				} catch(Exception e) {
					logger.info("Error during database access. player=" + player.getCommandSenderName() + ", currency=" + currency.name());
					e.printStackTrace();
					errorCallback.accept(data, new ShoppingException(ShoppingError.INTERNAL_ERROR, "Erreur durant l'accès à la base de données: " + e.getMessage()));
					return;
				}
				successCallback.accept(data);
				break;
			case JAVA_CALLBACK:				
				TransactionCustomCallback callback = null; 
				try {
					Class<? extends TransactionCustomCallback> callbackClazz = (Class<? extends TransactionCustomCallback>) Class.forName(javaCallbackClass);
					callback = callbackClazz.getConstructor().newInstance();
				} catch(Exception e) {
					logger.error("Error while getting callback class!");
					errorCallback.accept(data, e);
					return;
				}
				
				if(callback == null) {
					logger.error("Couldn't find callback class!");
					errorCallback.accept(data, new MarketException("Couldn't find callback class!"));
					return;
				}
				
				boolean goThrough = false;
				try {
					goThrough = callback.execute(data);
				} catch(MarketException e) {
					errorCallback.accept(data, e);
					return;
				} catch(Throwable t) {
					logger.error("Error while executing market callback: " + callback + " @ " + callback.getClass().getName());
					errorCallback.accept(data, t);
					return;
				}
				if(!goThrough) {
					errorCallback.accept(data, new MarketException("Callback denied"));
					return;
				}
				successCallback.accept(data);
				break;
			default:
				logger.error("Wrongly configured product, action type: " + actionType);
				errorCallback.accept(data, new ShoppingException(ShoppingError.INTERNAL_ERROR, "Transaction Action: " + actionType));
				return;
		}
	}

}
