package fr.valkya.valkyris.common.network.packets.market;

import java.util.List;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.market.product.Product;
import io.netty.buffer.ByteBuf;

public class PacketRequestListings implements IMessage {

	@Override public void fromBytes(ByteBuf buf) {}
	@Override public void toBytes(ByteBuf buf) {}
	
	public static class Handler implements IMessageHandler<PacketRequestListings, IMessage>{
		@Override
		public IMessage onMessage(PacketRequestListings message, MessageContext ctx) {
			SimpleNetworkWrapper network = Valkyris.getValkyris().getProxy().getNetwork();
			List<Product> listings = Valkyris.getValkyris().getProxy().getMarketManager().getProducts();
			if(!listings.isEmpty()) {
				for(int i = 0; i < listings.size() - 1; i++) {
					network.sendTo(new PacketProduct(listings.get(i), false), ctx.getServerHandler().playerEntity);
				}
				network.sendTo(new PacketProduct(listings.get(listings.size() - 1), true), ctx.getServerHandler().playerEntity);
			}
			return null;
		}
	}

}
