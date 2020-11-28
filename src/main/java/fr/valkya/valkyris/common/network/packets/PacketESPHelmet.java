package fr.valkya.valkyris.common.network.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.valkya.valkyris.common.items.ESPHelmet;
import fr.valkya.valkyris.common.items.VItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class PacketESPHelmet implements IMessage {

	public ItemStack is;
	
	public PacketESPHelmet() {}
	public PacketESPHelmet(ItemStack is) {
		this.is = is;
	}
	
	@Override 
	public void fromBytes(ByteBuf buf) {
		this.is = ByteBufUtils.readItemStack(buf);
	}
	
	@Override 
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeItemStack(buf, is);
	}
	
	public static class Handler implements IMessageHandler<PacketESPHelmet, IMessage> {

		@Override
		public IMessage onMessage(PacketESPHelmet message, MessageContext ctx) {
			ItemStack is = message.is;
			
			if(is == null) {
				return null;
			}
			if(is.getItem() != VItems.esp_helmet) {
				ctx.getServerHandler().kickPlayerFromServer(EnumChatFormatting.RED + "Invalid data.");
				return null;
			}
			
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			ItemStack helmet = null;
			for (ItemStack stacc : player.inventory.armorInventory) {
				if (stacc != null && stacc.getItem() != null && stacc.getItem() instanceof ESPHelmet) {
					helmet = stacc;
					break;
				}
			}
			
			if (helmet == null) {
				return null;
			}
			
			helmet.damageItem(1, player);
			
			return null;
		}
		
	}

}
