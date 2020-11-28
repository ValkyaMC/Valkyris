package fr.valkya.valkyris.common.network.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.valkya.valkyris.api.APIProvider;
import io.netty.buffer.ByteBuf;

public class PacketBroadcast implements IMessage {

	public String text;
	
	public PacketBroadcast() {}
	public PacketBroadcast(String text) {
		this.text = text;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.text = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, text);
	}
	
	public static class Handler implements IMessageHandler<PacketBroadcast, IMessage> {
		@Override
		public IMessage onMessage(PacketBroadcast message, MessageContext ctx) {
			APIProvider.provideAPI().getRenderer().sendBroadcast(message.text);
			return null;
		}
	}

}
