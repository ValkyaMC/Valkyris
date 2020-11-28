package fr.valkya.valkyris.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.valkya.valkyris.common.CommonProxy;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;

public class ValoEnchantment {
	
	@SubscribeEvent
	public void ntm(LivingDropsEvent e) {
		if(!(e.source instanceof EntityDamageSource)) return;
		
		if(e.source.getEntity() != null && e.source.getEntity() instanceof EntityPlayer && ((EntityPlayer)e.source.getEntity()).getHeldItem() != null) {
			EntityPlayer ep = (EntityPlayer)e.source.getEntity();
			int j = EnchantmentHelper.getEnchantmentLevel(CommonProxy.enchantTelekinesis.effectId, ep.getHeldItem());
			if(j > 0) {
				e.drops.stream().map(EntityItem::getEntityItem).forEach(ep.inventory::addItemStackToInventory);
				if(!e.entityLiving.worldObj.isRemote) {
					((EntityPlayerMP) ep).sendContainerToPlayer(ep.inventoryContainer);
				}
				
				e.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void owo(BlockEvent.HarvestDropsEvent e) {
		if(e.harvester != null && e.harvester.getHeldItem() != null) {
			int j = EnchantmentHelper.getEnchantmentLevel(CommonProxy.enchantTelekinesis.effectId, e.harvester.getHeldItem());
			if(j > 0) {
				EntityPlayer ep = e.harvester;
				
				e.drops.forEach(ep.inventory::addItemStackToInventory);
				if(!e.world.isRemote) {
					((EntityPlayerMP) ep).sendContainerToPlayer(ep.inventoryContainer);
				}
				
				e.drops.clear();
			}
		}
	}
}