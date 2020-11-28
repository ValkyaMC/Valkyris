package fr.valkya.valkyris.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class ValoCrops {
	
	@SubscribeEvent
	public void onRightClickOnThemCropsssssssssssssssssssssssssss(PlayerInteractEvent event) {
		if(event.entityPlayer.inventory.getCurrentItem() == null) return;
		
		if (event.action != Action.RIGHT_CLICK_BLOCK) return;
		
		if (event.entityPlayer.inventory.getCurrentItem().getItem() == Items.dye) {
			Block b = event.world.getBlock(event.x, event.y, event.z);
            if (b.getClass().getName().toLowerCase().contains("valkya")) {
                event.setCanceled(true);
            }
        }
	}
}
