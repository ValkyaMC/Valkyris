package fr.valkya.valkyris.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.valkya.valkyris.common.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class LightShot {

//	@SubscribeEvent
//	public void onLivingHurt(LivingHurtEvent event) {
//		if(!(event.source instanceof EntityDamageSource)) return;
//		if(event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer && ((EntityPlayer)event.source.getEntity()).getHeldItem() != null) {
//			EntityPlayer ep = (EntityPlayer)event.source.getEntity();
//			int j = EnchantmentHelper.getEnchantmentLevel(CommonProxy.enchantLightShot.effectId, ep.getHeldItem());
//			if(j > 0) {
//				if(event.entity.worldObj.canLightningStrikeAt((int)event.entity.posX, (int)event.entity.posY, (int)event.entity.posZ)) {
//					event.entity.worldObj.spawnEntityInWorld(new EntityLightningBolt(event.entity.worldObj, (int)event.entity.posX, (int)event.entity.posY, (int)event.entity.posZ));
//				}
//				event.setCanceled(true);
//			}
//			
//	    }
//	}

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent e) {
		if (!(e.source instanceof EntityDamageSource))
			return;

		if (e.source.getEntity() != null && e.source.getEntity() instanceof EntityPlayer && ((EntityPlayer) e.source.getEntity()).getHeldItem() != null) {
			EntityPlayer ep = (EntityPlayer) e.source.getEntity();
			int j = EnchantmentHelper.getEnchantmentLevel(CommonProxy.enchantLightShot.effectId, ep.getHeldItem());
			if (j > 0) {
				e.entity.worldObj.spawnEntityInWorld(new EntityLightningBolt(e.entity.worldObj, (int) e.entity.posX, (int) e.entity.posY, (int) e.entity.posZ));
				e.setCanceled(true);

			}
		}
	}

}
