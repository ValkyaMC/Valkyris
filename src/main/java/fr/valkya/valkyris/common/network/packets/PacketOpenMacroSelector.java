package fr.valkya.valkyris.common.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.client.gui.macros.MacrosSelector;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketOpenMacroSelector implements IMessage
{
	   public PacketOpenMacroSelector()
	   {}
	 
	   @Override
	   public void fromBytes(ByteBuf buf)
	   {}
	 
	   @Override
	   public void toBytes(ByteBuf buf)
	   {}
	 
	   public static class Handler implements IMessageHandler <PacketOpenMacroSelector, IMessage>{
	   @SideOnly(Side.CLIENT)
	       @Override
	       public IMessage onMessage(PacketOpenMacroSelector message, MessageContext ctx)
	       {
	           Minecraft.getMinecraft().displayGuiScreen(new MacrosSelector());
	           return null;
	       }
	   }
	}
