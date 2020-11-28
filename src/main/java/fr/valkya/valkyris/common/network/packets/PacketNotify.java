package fr.valkya.valkyris.common.network.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.valkya.valkyris.client.render.notification.Notification;
import fr.valkya.valkyris.client.render.notification.NotificationManager;
import fr.valkya.valkyris.client.render.notification.NotificationType;
import io.netty.buffer.ByteBuf;

public class PacketNotify implements IMessage {

	public String title;
    public String msg;
    public NotificationType type;
    public int length;
	
	public PacketNotify() {}
	public PacketNotify(String title, String msg, NotificationType type, int length) {
		this.title = title;
		this.msg = msg;
		this.type = type;
		this.length = length;
	}
	
	@Override 
	public void fromBytes(ByteBuf buf) { 
		this.title = ByteBufUtils.readUTF8String(buf);
		this.msg = ByteBufUtils.readUTF8String(buf);
		this.type = NotificationType.from(ByteBufUtils.readUTF8String(buf));
		this.length = buf.readInt();
	}
	
	@Override 
	public void toBytes(ByteBuf buf) { 
		ByteBufUtils.writeUTF8String(buf, title);
		ByteBufUtils.writeUTF8String(buf, msg);
		ByteBufUtils.writeUTF8String(buf, this.type.getName());
		buf.writeInt(length);
	}
	
	public static class Handler implements IMessageHandler<PacketNotify, IMessage>{
		@Override
		public IMessage onMessage(PacketNotify message, MessageContext ctx) {
			NotificationManager.show(new Notification(message.type, message.title, message.msg, message.length));
			return null;
		}
	}

}
