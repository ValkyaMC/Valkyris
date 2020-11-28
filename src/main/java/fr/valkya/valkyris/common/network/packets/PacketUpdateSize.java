package fr.valkya.valkyris.common.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.valkya.valkyris.client.authentication.VNetHandlerPlayClient;
import fr.valkya.valkyris.client.manager.ClientSizeManager;
import fr.valkya.valkyris.common.entity.EntitySize;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class PacketUpdateSize implements IMessage {

	private int entityId;
	private EntitySize size;
	
	public PacketUpdateSize() {}
	public PacketUpdateSize(Entity entity, EntitySize size) {
		this.entityId = entity.getEntityId();
		this.size = size;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityId = buf.readInt();
		this.size = EntitySize.values()[buf.readInt()];
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityId);
		buf.writeInt(this.size.ordinal());
	}
	
	public static class Handler implements IMessageHandler<PacketUpdateSize, IMessage>{
		@Override
		public IMessage onMessage(PacketUpdateSize message, MessageContext ctx) {
			Entity entity = ((VNetHandlerPlayClient)ctx.getClientHandler()).clientWorldController.getEntityByID(message.entityId);
			if(entity != null && entity instanceof EntityLivingBase) {
				ClientSizeManager.INSTANCE.updateEntity((EntityLivingBase)entity, message.size);
			}
			return null;
		}
	}

}
