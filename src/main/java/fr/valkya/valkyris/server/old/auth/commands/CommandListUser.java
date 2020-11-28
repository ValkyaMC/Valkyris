package fr.valkya.valkyris.server.old.auth.commands;

import java.util.List;

import fr.valkya.valkyris.server.utils.PlayerUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandListUser extends CommandBase {

	@Override
	public String getCommandName() {
		return "getusersmachine";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length > 0 && args.length < 2) {
			if(PlayerUtils.getPlayerByName(args[0]) != null) {
//				String machineId = ((ServerProxy) Valkyris.getValorion().getProxy()).getMachineHelper().getMachineFromName(args[0]);
//				try {
//					Map<String, String> accounts = ((ServerProxy) Valkyris.getValorion().getProxy()).getMachineHelper().getAllAccountFromMachine(machineId);
//					sender.addChatMessage(new ChatComponentText("§c-+- -- - -- + -- - -- -+-"));
//					sender.addChatMessage(new ChatComponentText("§eCompte(s) de §l§6" + args[0]));
//					for(String account : accounts.keySet()) {
//						sender.addChatMessage(new ChatComponentText(" §8- §d" + account + ", §5" + accounts.get(account)));
//					}
//					
//					sender.addChatMessage(new ChatComponentText("§c-+- -- - -- + -- - -- -+-"));
//					
//				} catch (SQLException e) { e.printStackTrace(); }
			}
		}else {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Vous avez mal utilisé cette commande ! : /getusersmachine <username>"));
		}
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] args) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
	}

}
