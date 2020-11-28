package fr.valkya.valkyris.server.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.entity.EntitySize;
import fr.valkya.valkyris.common.network.packets.PacketUpdateSize;
import fr.valkya.valkyris.server.utils.PlayerUtils;
import net.minecraft.entity.CommonSizeManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ServerSizeManager {
	
	private final Map<UUID, Long> players;
	
	public ServerSizeManager() {
		players = new HashMap<UUID, Long>();
	}

	public boolean add(EntityPlayerMP player, EntitySize size, int time) {
		if(!players.containsKey(player.getUniqueID())) {
			players.put(player.getUniqueID(), System.currentTimeMillis() + time * 1000);
			updateSize(player, size);
			return true;
		}else {
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Tu ne peux pas changer plus d'une fois de taille !"));
			return false;
		}
	}
	
	public void remove(EntityPlayerMP player) {
		if(players.containsKey(player.getUniqueID())) {
			updateSize(player, EntitySize.NORMAL);
			players.remove(player.getUniqueID());
		}
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.WorldTickEvent e) {
		for(EntityPlayerMP player : PlayerUtils.getAllPlayers()) {
			if(players.containsKey(player.getUniqueID()) && players.get(player.getUniqueID()) < System.currentTimeMillis()) {
				updateSize(player, EntitySize.NORMAL);
				players.remove(player.getUniqueID());
			}
		}
	}
	
	private void updateSize(EntityPlayerMP player, EntitySize size) {
		CommonSizeManager.INSTANCE.setSize(player, size); // set those values for the server
		Valkyris.getValkyris().getProxy().getNetwork().sendToDimension(new PacketUpdateSize(player, size), player.dimension);
	}
	
}
