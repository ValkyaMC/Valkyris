package fr.valkya.valkyris.server.commands;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.network.packets.PacketTitle;
import fr.valkya.valkyris.common.utils.ChatUtils;
import fr.valkya.valkyris.server.utils.PlayerUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandTitlePlayer extends CommandBase {

	@Override
	public String getCommandName() { return "titleplayer"; }

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) { return null; }

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length > 1) {
			String playerStr = args[0];
			StringBuffer sb = new StringBuffer();
			int id = 0;
			for(String arg : args) {
				if(id == 0) {
					id++;
					continue;
				}
				sb.append(arg);
				if(id + 1 != args.length) {
		    		sb.append(" ");
		    	}
				id++;
			}
			
			if(PlayerUtils.getPlayerByName(playerStr) != null) {
				String str = sb.toString();
			    String msg = ChatUtils.getColoredText(str);
			    
				EntityPlayer target = PlayerUtils.getPlayerByName(playerStr);
				Valkyris.getValkyris().getProxy().getNetwork().sendTo(new PacketTitle(msg), (EntityPlayerMP) target);
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Message envoyé!"));
			} else {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Joueur " + playerStr + " introuvable!"));
			}
		} else {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "La commande est : /titleplayer <player> <message>"));
		}
	}
}
