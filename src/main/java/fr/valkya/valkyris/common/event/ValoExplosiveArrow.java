package fr.valkya.valkyris.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ValoExplosiveArrow {

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event) {
		if(event.source.getDamageType() == "arrow" && event.source.getSourceOfDamage().getEntityData().getBoolean("isExplosive")) {
//			event.entity.worldObj.newExplosion((Entity)null, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, 3, true, event.entity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
			if(event.entity.worldObj.canLightningStrikeAt((int)event.entity.posX, (int)event.entity.posY, (int)event.entity.posZ)) {
				event.entity.worldObj.spawnEntityInWorld(new EntityLightningBolt(event.entity.worldObj, (int)event.entity.posX, (int)event.entity.posY, (int)event.entity.posZ));
			}
	    }
	}

}
