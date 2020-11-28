package fr.valkya.valkyris.common.network.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.valkya.valkyris.api.APIProvider;
import io.netty.buffer.ByteBuf;

public class PacketTitle implements IMessage {

	public String title, subtitle;
	
	public PacketTitle() {}
	public PacketTitle(String subtitle) { this("null", subtitle); }
	public PacketTitle(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.title = ByteBufUtils.readUTF8String(buf);
		this.subtitle = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.title);
		ByteBufUtils.writeUTF8String(buf, this.subtitle);
	}
	
	public static class Handler implements IMessageHandler<PacketTitle, IMessage> {
		@Override
		public IMessage onMessage(PacketTitle message, MessageContext ctx) {
			if(message.title == null || message.title.isEmpty() || message.title == "null")
				APIProvider.provideAPI().getRenderer().sendTitle(message.subtitle);
			else
				APIProvider.provideAPI().getRenderer().sendTitle(message.title, message.subtitle);
			return null;
		}
	}

}
