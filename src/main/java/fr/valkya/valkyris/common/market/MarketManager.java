package fr.valkya.valkyris.common.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.market.pricing.Pricing;
import fr.valkya.valkyris.common.market.pricing.PricingCurrency;
import fr.valkya.valkyris.common.market.product.Product;
import fr.valkya.valkyris.common.market.product.ProductCategory;
import fr.valkya.valkyris.common.market.product.ProductRenderer;
import fr.valkya.valkyris.common.market.transaction.TransactionAction;
import fr.valkya.valkyris.common.market.transaction.TransactionType;
import fr.valkya.valkyris.common.network.packets.market.PacketRequestListings;
import net.minecraft.util.ResourceLocation;

public class MarketManager {
	
	private List<Product> listings;
	private Side side;
	private boolean ready;
	
	public MarketManager() {
		this.listings = new ArrayList<>();
		this.side = FMLCommonHandler.instance().getSide();
	}
	
	public void init() {		
		if(side.isServer()) {
			// Creating some test products 
			Map<String, Object> marketProperties = new HashMap<>();
			
//			File file = new File("market.json");
//			if(!file.exists()) {
//				throw new RuntimeException(file.getAbsolutePath() + " not found!");
//			}
			
//			Gson gson = new Gson();
//			try {
//				FileReader fr = new FileReader(file);
//				marketProperties = gson.fromJson(fr, Map.class);
//			} catch(IOException e) {
//				e.printStackTrace();
//			}
			
//			Map<TransactionType, Pricing> pricings2 = new HashMap<>();
//			pricings2.put(TransactionType.BUY, Pricing.of(PricingCurrency.TOKENS, 500));
//			Map<TransactionType, TransactionAction> callbacks2 = new HashMap<>();
//			callbacks2.put(TransactionType.BUY, new TransactionAction(new String[] {"pex user %player% group set arcange", "notifyplayer %player% §6Boutique » §eMerci pour ton achat !", "vbroadcast §6Valkya » §eMerci à §6%player% §epour son achat de §6%amount%x %product_name% §e!"}));
//			listings.add(new Product("arcange_rank", "Grade Arcange", 
//					"Archange:\n-4 lignes EC\n-10 homes\n-3 Kits offert(Utilisable 1 fois)\n-/craft\n-/xpbottle\n-/ec\n-/ptime - /pweather\n-/feed - /eat\n-Chat Color\n-Garde XP quand meurt\n-Accès serveur plein\n-Accès channel spécial discord", 
//					ProductCategory.GRADES, new ProductRenderer(new ResourceLocation(References.MODID + ":textures/gui/market/ranks/arcange.png")), pricings2, false, true, false, callbacks2));
//			
//			Map<TransactionType, Pricing> pricings3 = new HashMap<>();
//			pricings3.put(TransactionType.BUY, Pricing.of(PricingCurrency.TOKENS, 350));
//			Map<TransactionType, TransactionAction> callbacks3 = new HashMap<>();
//			callbacks3.put(TransactionType.BUY, new TransactionAction(new String[] {"pex user %player% group set ange", "notifyplayer %player% §6Boutique » §eMerci pour ton achat !", "vbroadcast §6Valkya » §eMerci à §6%player% §epour son achat de §6%amount%x %product_name% §e!"}));
//			listings.add(new Product("ange_rank", "Grade Ange", 
//					"Ange:\n-3 lignes EC\n-8 homes\n-2 Kits offert(Utilisable 1 fois)\n-/craft\n-/xpbottle\n-/ec\n-/ptime - /pweather\n-/feed - /eat\n-Chat Color\n-Garde XP quand meurt\n-Accès serveur plein\n-Accès channel spécial discord", 
//					ProductCategory.GRADES, new ProductRenderer(new ResourceLocation(References.MODID + ":textures/gui/market/ranks/ange.png")), pricings3, false, true, false, callbacks3));
//			
//			Map<TransactionType, Pricing> pricings4 = new HashMap<>();
//			pricings4.put(TransactionType.BUY, Pricing.of(PricingCurrency.TOKENS, 250));
//			Map<TransactionType, TransactionAction> callbacks4 = new HashMap<>();
//			callbacks4.put(TransactionType.BUY, new TransactionAction(new String[] {"pex user %player% group set séraphin","notifyplayer %player% §6Boutique » §eMerci pour ton achat !", "vbroadcast §6Valkya » §eMerci à §6%player% §epour son achat de §6%amount%x %product_name% §e!"}));
//			listings.add(new Product("seraphin_rank", "Grade Séraphin", 
//					"Séraphin:\n-2 lignes EC\n-5 homes\n-1 Kits offert(Utilisable 1 fois)\n-/craft\n-/ec\n-/feed - /eat\n-Chat Color\n-Accès serveur plein\n-Accès channel spécial discord",
//					ProductCategory.GRADES, new ProductRenderer(new ResourceLocation(References.MODID + ":textures/gui/market/ranks/seraphin.png")), pricings4, false, true, false, callbacks4));
//			
//			Map<TransactionType, Pricing> pricings5 = new HashMap<>();
//			pricings5.put(TransactionType.BUY, Pricing.of(PricingCurrency.TOKENS, 100));
//			Map<TransactionType, TransactionAction> callbacks5 = new HashMap<>();
//			callbacks5.put(TransactionType.BUY, new TransactionAction(new String[] {"pex user %player% group set chérubin", "notifyplayer %player% §6Boutique » §eMerci pour ton achat !", "vbroadcast §6Valkya » §eMerci à §6%player% §epour son achat de §6%amount%x %product_name% §e!"}));
//			listings.add(new Product("cherubin_rank", "Grade Chérubin", 
//					"Chérubin:\n-2 lignes EC\n-3 homes\n-1 Kits offert(Utilisable 1 fois)\n-/craft\n-/ec\n-Accès serveur plein\n-Accès channel spécial discord", 
//					ProductCategory.GRADES, new ProductRenderer(new ResourceLocation(References.MODID + ":textures/gui/market/ranks/cherubin.png")), pricings5, false, true, false, callbacks5));
		}
		
		Valkyris.getValkyris().getProxy().getNetwork().registerMarketMessages();
		
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent e) {
		if(!side.isClient()) return;
		clearListings();
		
		Valkyris.getValkyris().getProxy().getNetwork().sendToServer(new PacketRequestListings());
	}
	
	public void registerProduct(Product p) {
		this.listings.add(p);
	}
	
	public void clearListings() {
		this.listings.clear();
		ready = false;
	}

	public boolean hasReceivedListings() {
		return side.isServer() || (side.isClient() && !listings.isEmpty());
	}
	
	public List<Product> getProducts() {
		return listings;
	}
	
	public List<Product> getProductsInCategory(ProductCategory cat) {
		return listings.stream().filter(p -> p.getCategory() == cat).collect(Collectors.toList());
	}
	
	public Product getProductByInternalName(String internalName) {
		return listings.stream().filter(p -> p.getInternalName().equalsIgnoreCase(internalName)).findFirst().orElse(null);
	}
	
	public void setReady(boolean state) {
		this.ready = state;
	}
	
	public boolean isReady() {
		return ready;
	}
}
