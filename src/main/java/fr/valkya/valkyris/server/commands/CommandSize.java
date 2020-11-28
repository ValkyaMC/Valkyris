package fr.valkya.valkyris.server.commands;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.entity.EntitySize;
import fr.valkya.valkyris.common.network.packets.PacketUpdateSize;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

public class CommandSize extends CommandBase {

	@Override
	public String getCommandName() { return "size"; }

	@Override
	public String getCommandUsage(ICommandSender sender) { return ""; }

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(sender instanceof EntityPlayerMP) {
			Valkyris.getValkyris().getProxy().getNetwork().sendToDimension(new PacketUpdateSize(((Entity) sender), EntitySize.NORMAL), ((EntityPlayerMP) sender).dimension);
		}else {
			sender.addChatMessage(new ChatComponentText("tu es la console !"));
		}
	}
}
