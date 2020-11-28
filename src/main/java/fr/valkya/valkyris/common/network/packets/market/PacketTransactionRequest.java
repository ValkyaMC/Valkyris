package fr.valkya.valkyris.common.network.packets.market;

import java.util.Arrays;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.market.MarketManager;
import fr.valkya.valkyris.common.market.exception.ShoppingException.ShoppingError;
import fr.valkya.valkyris.common.market.pricing.PricingCurrency;
import fr.valkya.valkyris.common.market.product.Product;
import fr.valkya.valkyris.common.market.transaction.TransactionType;
import fr.valkya.valkyris.common.network.packets.market.PacketTransactionResponse.ResponseType;
import io.netty.buffer.ByteBuf;
	
public class PacketTransactionRequest implements IMessage {

	public String internal_name;
	public TransactionType type;
	public PricingCurrency currency;
	public int ammount;
	
	public PacketTransactionRequest() {}
	public PacketTransactionRequest(String internal_name, TransactionType type, PricingCurrency currency, int ammount) {
		this.internal_name = internal_name;
		this.type = type;
		this.currency = currency;
		this.ammount = ammount;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.internal_name = ByteBufUtils.readUTF8String(buf);
		this.type = TransactionType.fromID(buf.readInt());
		this.currency = PricingCurrency.fromID(buf.readInt());
		this.ammount = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, internal_name);
		buf.writeInt(type.getID());
		buf.writeInt(currency.getID());
		buf.writeInt(ammount);
	}

	public static class Handler implements IMessageHandler<PacketTransactionRequest, PacketTransactionResponse>{
		@Override
		public PacketTransactionResponse onMessage(PacketTransactionRequest message, MessageContext ctx) {
			MarketManager market = Valkyris.getValkyris().getProxy().getMarketManager();
			Product product = market.getProductByInternalName(message.internal_name);

			if(!product.allowsMultiplePurchases() && message.ammount > 1) {
				return new PacketTransactionResponse(ResponseType.ERROR, Arrays.asList(ShoppingError.NO_MULTIPLE_PURCHASES.getMessage()));
			}
			
			return null;
		}
	}
}
