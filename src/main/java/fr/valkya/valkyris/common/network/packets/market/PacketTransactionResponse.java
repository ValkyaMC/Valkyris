package fr.valkya.valkyris.common.network.packets.market;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.valkya.valkyris.References;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;

public class PacketTransactionResponse implements IMessage {

	public ResponseType type;
	public List<String> details;
	
	public PacketTransactionResponse() {}
	public PacketTransactionResponse(ResponseType type, List<String> responseDetails) {
		this.type = type;
		this.details = responseDetails;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.type = ResponseType.fromID(buf.readInt());
		this.details = new ArrayList<>();
		int max = buf.readInt();
		for(int i = 0; i < max; i++) {
			this.details.add(ByteBufUtils.readUTF8String(buf));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.type.getID());
		buf.writeInt(details.size());
		this.details.forEach(s -> ByteBufUtils.writeUTF8String(buf, s));
	}
	
	public static class Handler implements IMessageHandler<PacketTransactionResponse, IMessage>{
		@Override
		public IMessage onMessage(PacketTransactionResponse message, MessageContext ctx) {
			return null; //TODO gui
		}
	}
	
	public enum ResponseType {
		SUCCESS(0, new ResourceLocation(References.MODID + ":textures/gui/market/success.png")), 
		ERROR(1, new ResourceLocation(References.MODID + ":textures/gui/market/error.png"));
		
		int id;
		ResourceLocation rl;
		ResponseType(int id, ResourceLocation rl){
			this.id = id;
			this.rl = rl;
		}
		
		public int getID() {
			return id;
		}
		
		public ResourceLocation getIcon() {
			return rl;
		}
		
		public static ResponseType fromID(int id) {
			for (ResponseType rt : values())
				if (rt.id == id)
					return rt;
			return null;
		}
	}

}
