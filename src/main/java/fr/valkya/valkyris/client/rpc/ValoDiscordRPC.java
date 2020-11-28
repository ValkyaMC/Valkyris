package fr.valkya.valkyris.client.rpc;

import org.apache.logging.log4j.LogManager;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordEventHandlers.OnReady;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordUser;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.ServerRegistry;
import net.minecraft.client.Minecraft;

public class ValoDiscordRPC {
	
	public void init() { 
		try {
			final DiscordRPC lib = DiscordRPC.INSTANCE;
			final Minecraft mc = Minecraft.getMinecraft();
			final DiscordRichPresence presence = new DiscordRichPresence();
			
			String applicationID = "700277951845105695";
			
			DiscordEventHandlers handlers = new DiscordEventHandlers();
			
			handlers.ready = new OnReady() {
				@Override
				public void accept(DiscordUser user) {
					LogManager.getLogger().info("[Valkya] Discord RPC Ready !");
				}				
			};
			
			lib.Discord_Initialize(applicationID, handlers, true, "");
			
			presence.startTimestamp = System.currentTimeMillis() / 1000L;
			presence.details = "En ligne : ";
			presence.state = "Connecté sur : " + ServerRegistry.serverName;
			presence.largeImageText = References.SITE;
			presence.smallImageText = Minecraft.getMinecraft().getSession().getUsername();
			
			presence.smallImageKey = "head";
			presence.largeImageKey = "small";
			
			lib.Discord_UpdatePresence(presence);
			
			Thread t = new Thread(() -> {
				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(2000);
					} catch(InterruptedException e) {}
					
			    	lib.Discord_RunCallbacks();
			    	
			    	if(mc.theWorld != null && mc.getNetHandler() != null) {
			    		if(mc.isSingleplayer()) {
		    				presence.state = "Joue en solo... hein?";	
		    			}else{		    				
		    				presence.state = "Connecté sur Valkya";			    						    						    			
		    			}
		    		}else {
		    			presence.state = "Sur le menu principal";
		    		}
			    	
			    	presence.details = "https://www.valkya.fr/"; 
			    	
			    	lib.Discord_UpdatePresence(presence);	        
			    }
			}, "RPC-Callback");
			t.setDaemon(true);
			t.start();
			
		} catch(UnsatisfiedLinkError e) { e.printStackTrace(); }
	}

}
