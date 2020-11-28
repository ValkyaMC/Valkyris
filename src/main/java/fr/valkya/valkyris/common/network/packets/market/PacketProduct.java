package fr.valkya.valkyris.common.network.packets.market;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.market.product.Product;
import io.netty.buffer.ByteBuf;

public class PacketProduct implements IMessage {

	private static final Gson gson = new GsonBuilder().serializeNulls().create();
	
	public Product product;
	public boolean isFinal;
	
	public PacketProduct() {}
	public PacketProduct(Product product, boolean isFinal) {
		this.product = product;
		this.isFinal = isFinal;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.product = gson.fromJson(ByteBufUtils.readUTF8String(buf), Product.class);
		this.isFinal = buf.readBoolean();
		
//		String internalName = ByteBufUtils.readUTF8String(buf);
//		String displayName = ByteBufUtils.readUTF8String(buf);
//		String desc = ByteBufUtils.readUTF8String(buf);
//		ProductCategory pc = ProductCategory.fromID(buf.readInt());
//		RenderType type = RenderType.fromID(buf.readInt());
//		ProductRenderer renderer = null;
//		switch(type) {
//			case ITEM:
//				renderer = new ProductRenderer(ByteBufUtils.readItemStack(buf));
//				break;
//			case ICON:
//				renderer = new ProductRenderer(new ResourceLocation(ByteBufUtils.readUTF8String(buf)));
//				break;
//		}
//		
//		Map<TransactionType, Pricing> pricings = product.getPricings();
//		int pricingsSize = buf.readInt();
//		for(int i = 0; i < pricingsSize; i++) {
//			TransactionType tt = TransactionType.fromID(buf.readInt());
//			
//			Map<PricingCurrency, Number> prices = new HashMap<>();
//			int pricesSize = buf.readInt();
//			for(int j = 0; j < pricesSize; j++) {
//				PricingCurrency prc = PricingCurrency.fromID(buf.readInt());
//				int value = buf.readInt();
//				prices.put(prc, value);
//			}
//		}
//		
//		this.f
	}

	@Override
	public void toBytes(ByteBuf buf) {
		String json = gson.toJson(this.product);
		ByteBufUtils.writeUTF8String(buf, json);
		buf.writeBoolean(isFinal);
		
//		ByteBufUtils.writeUTF8String(buf, product.getInternalName());
//		ByteBufUtils.writeUTF8String(buf, product.getDisplayName());
//		ByteBufUtils.writeUTF8String(buf, product.getDescription());
//		buf.writeInt(product.getCategory().getID());
//		ProductRenderer renderer = product.getRenderer();
//		RenderType type = renderer.getType();
//		buf.writeInt(type.getID());
//		switch(type) {
//			case ITEM:
//				ByteBufUtils.writeItemStack(buf, renderer.getStack().getItemStack());
//				break;
//			case ICON:
//				ByteBufUtils.writeUTF8String(buf, renderer.getIcon().getResourceDomain() + ":" + renderer.getIcon().getResourcePath());
//				break;
//		}
//		
//		Map<TransactionType, Pricing> pricings = product.getPricings();
//		buf.writeInt(pricings.size());
//		for(TransactionType tt : pricings.keySet()) {
//			Pricing pricing = pricings.get(tt);
//			buf.writeInt(tt.getID());
//			
//			Map<PricingCurrency, Number> prices = pricing.getPrices();
//			buf.writeInt(prices.size());
//			for(PricingCurrency pc : prices.keySet()) {
//				buf.writeInt(pc.getID());
//				buf.writeInt(prices.get(pc).intValue());
//			}
//		}
//		
//		this.discounted;
//		this.available; 
//		this.allowsMultiplePurchases;
//
//		buf.writeBoolean(this.isFinal);
	}
	
	public static class Handler implements IMessageHandler<PacketProduct, IMessage>{
		@Override
		public IMessage onMessage(PacketProduct message, MessageContext ctx) {
			Valkyris.getValkyris().getProxy().getMarketManager().registerProduct(message.product);
			if(message.isFinal) {
				Valkyris.getValkyris().getProxy().getMarketManager().setReady(true);
			}else {
				Valkyris.getValkyris().getProxy().getMarketManager().setReady(false);
			}
			return null;
		}
	}

}
