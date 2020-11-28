package fr.valkya.valkyris.server.commands;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.render.notification.NotificationType;
import fr.valkya.valkyris.common.network.packets.PacketNotify;
import fr.valkya.valkyris.common.utils.ChatUtils;
import fr.valkya.valkyris.server.utils.PlayerUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandNotifyPlayer extends CommandBase {

	@Override
	public String getCommandName() { return "notifyplayer"; }

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) { return null; }

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length > 1) {
			String playerStr = args[0];
			StringBuffer sb = new StringBuffer();
			int id = 0;
			for(String arg : args) {
				if(id != 0) sb.append(arg + " ");
				id++;
			}
			if(PlayerUtils.getPlayerByName(playerStr) != null) {
				EntityPlayer target = PlayerUtils.getPlayerByName(playerStr);
				Valkyris.getValkyris().getProxy().getNetwork().sendTo(new PacketNotify(EnumChatFormatting.RED + "Notification Personnelle", ChatUtils.getColoredText(sb.toString()), NotificationType.INFO, 7), (EntityPlayerMP) target);
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Message envoyé!"));
			} else {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Joueur " + playerStr + " introuvable!"));
			}
		} else {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "La commande est : /notifyplayer <player> <message>"));
		}
	}
}
