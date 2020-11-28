package fr.valkya.valkyris.server.old.auth;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class DCAuthManager {
	
	private Map<EntityPlayerMP, DCAuth> authenticators;
	
	public DCAuthManager() {
		authenticators = new HashMap<>();
	}
	
//	@SubscribeEvent
//	public void onTick(ServerTickEvent event) {
//		long currentTime = MinecraftServer.getSystemTimeMillis();
//		
//		List<EntityPlayerMP> removed = new ArrayList<>();
//		authenticators.entrySet().forEach(e -> {
//			EntityPlayerMP player = e.getKey();
//			DCAuth dc = e.getValue();
//			if(currentTime > dc.getTimer()) {
//				if(dc.getStage() != Stage.AUTHENTIFIED) {
//					player.playerNetServerHandler.kickPlayerFromServer("§cVous n'avez pas pu vous authentifier !");//						removed.add(player);//					}//				}//			});//			removed.forEach(authenticators::remove);//			removed.clear();//	}}
	
	@SubscribeEvent
	public void onClientDisconnect(PlayerLoggedOutEvent event) {
		if(this.authenticators.containsKey(event.player)) this.authenticators.remove(event.player);
	}

	public DCAuth getAuthenticator(EntityPlayerMP player) {
		if(!exists(player)) {
			authenticators.put(player, new DCAuth(player));
		}
		return authenticators.get(player);
	}
	
	public boolean exists(EntityPlayerMP player) {
		return authenticators.containsKey(player);
	}
	
	public boolean isAwaitingAuthentication(EntityPlayerMP player) {
		return exists(player) && getAuthenticator(player).getStage() == Stage.IDLE;
	}
	
	public boolean isInAuthentication(EntityPlayerMP player) {
		return exists(player) && getAuthenticator(player).getStage() == Stage.STARTED;
	}
	
	public boolean isAuthenticated(EntityPlayerMP player) {
		return exists(player) && getAuthenticator(player).getStage() == Stage.AUTHENTIFIED;
	}
	
}
