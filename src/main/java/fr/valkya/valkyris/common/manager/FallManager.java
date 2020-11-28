package fr.valkya.valkyris.common.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class FallManager {
	
	private Map<UUID, Long> players;
	
	public FallManager() {
		players = new HashMap<UUID, Long>();
	}

	public void add(EntityPlayer p, int i) {
		if(!players.containsKey(p.getUniqueID())) {
			players.put(p.getUniqueID(), System.currentTimeMillis() + i * 1000);
		}
	}
	
	@SubscribeEvent
	public void onFalling(LivingAttackEvent e) {
		if(!e.entity.worldObj.isRemote) {
			if(e.entity instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) e.entity;
				if(e.source == DamageSource.fall) {
					if(players.containsKey(p.getUniqueID())) {
						if(System.currentTimeMillis() <= players.get(p.getUniqueID())) {
							e.setCanceled(true);
						}
						players.remove(p.getUniqueID());
					}
				}
			}
		}
	}
}
