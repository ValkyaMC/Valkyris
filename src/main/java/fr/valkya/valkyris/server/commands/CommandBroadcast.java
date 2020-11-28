package fr.valkya.valkyris.server.commands;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.network.packets.PacketBroadcast;
import fr.valkya.valkyris.common.utils.ChatUtils;
import fr.valkya.valkyris.server.utils.PlayerUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandBroadcast extends CommandBase {

	@Override
	public String getCommandName() { return "vbroadcast"; }

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) { return null; }

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length >= 1) {
			StringBuffer sb = new StringBuffer();
		    for(int i = 0; i < args.length; i++) {
		    	sb.append(args[i]);
		    	if(i + 1 != args.length) {
		    		sb.append(" ");
		    	}
		    }
		    String str = sb.toString();
		    String msg = ChatUtils.getColoredText(str);
		    for(EntityPlayer player : PlayerUtils.getAllPlayers()) {
				Valkyris.getValkyris().getProxy().getNetwork().sendTo(new PacketBroadcast(msg), (EntityPlayerMP) player);
			}
		    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Message envoyé!"));
		}else {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "La commande est : /vbroadcast <message>"));
		}
	}
}
