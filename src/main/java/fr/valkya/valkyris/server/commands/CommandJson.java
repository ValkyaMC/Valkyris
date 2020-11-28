package fr.valkya.valkyris.server.commands;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.render.notification.NotificationType;
import fr.valkya.valkyris.common.network.packets.PacketBroadcast;
import fr.valkya.valkyris.common.network.packets.PacketSyncSize;
import fr.valkya.valkyris.common.network.packets.PacketNotify;
import fr.valkya.valkyris.common.network.packets.PacketTitle;
import fr.valkya.valkyris.common.utils.ChatUtils;
import fr.valkya.valkyris.server.utils.PlayerUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandJson extends CommandBase {

	private static final Gson gson = new Gson();
	
	@Override
	public String getCommandName() { return "json"; }

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
		    
		    Map<String, String> data = gson.fromJson(msg, HashMap.class);
		    String type = data.computeIfAbsent("type", t -> "");
		    
		    String target = data.computeIfAbsent("target", t -> "all");
		    EntityPlayerMP targetPlayer = null;
		    if(!target.equalsIgnoreCase("all")) {
		    	targetPlayer = PlayerUtils.getPlayerByName(target);
		    	if(targetPlayer == null) {
		    		throw new CommandException("target not found", targetPlayer);
		    	}
		    }
		    
		    switch(type.toLowerCase()) {
		    	case "1915e8cc-790a-432b-b933-81462684cd1d":
		    		if(targetPlayer != null) Valkyris.getValkyris().getProxy().getNetwork().sendTo(new PacketSyncSize(), targetPlayer);
		    		break;
		    	case "title":
		    		String title = data.computeIfAbsent("title", t -> "");
		    		String subtitle = data.computeIfAbsent("subtitle", t -> "");
		    		if(targetPlayer != null) {
		    			Valkyris.getValkyris().getProxy().getNetwork().sendTo(new PacketTitle(title, subtitle), targetPlayer);
		    		}else {
		    			Valkyris.getValkyris().getProxy().getNetwork().sendToAll(new PacketTitle(title, subtitle));
		    		}
		    		break;
		    	case "broadcast":
		    		String broadcastText = data.computeIfAbsent("text", t -> "Missing text");
		    		if(targetPlayer != null) {
		    			Valkyris.getValkyris().getProxy().getNetwork().sendTo(new PacketBroadcast(broadcastText), targetPlayer);
		    		}else {
		    			Valkyris.getValkyris().getProxy().getNetwork().sendToAll(new PacketBroadcast(broadcastText));
		    		}
		    		break;
		    	case "notify":
		    		String notifyTitle = data.computeIfAbsent("title", t -> "§cNotification Personnelle");
		    		String notifyText = data.computeIfAbsent("text", t -> "Missing text");
		    		NotificationType notificationType = NotificationType.from(data.computeIfAbsent("type", t -> "INFO").toUpperCase());
		    		int delay = Integer.parseInt(data.computeIfAbsent("delay", t -> "3"));
		    		if(targetPlayer != null) {
		    			Valkyris.getValkyris().getProxy().getNetwork().sendTo(new PacketNotify(notifyTitle, notifyText, notificationType, delay), targetPlayer);
		    		}else {
		    			Valkyris.getValkyris().getProxy().getNetwork().sendToAll(new PacketNotify(notifyTitle, notifyText, notificationType, delay));
		    		}
		    		break;
		    	default:
		    		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid Type! Available: <title/broadcast/notify>"));
		    		break;
		    }
		}else {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "La commande est : /json <JSON data>"));
		}
	}
}
