package fr.valkya.valkyris.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ValoStructureCesium {
	
	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent event) {
		if (event.world.getBlock(event.x, event.y, event.z) == VBlocks.cesiumBlock) {
			if (event.world.getBlock(event.x, event.y-1, event.z) == Blocks.cobblestone_wall //couche 6
			 && event.world.getBlock(event.x, event.y-2, event.z) == VBlocks.cesiumBlock //couche 5
			 
			 && event.world.getBlock(event.x+1, event.y-3, event.z) == VBlocks.cesiumBlock //couche 4
			 && event.world.getBlock(event.x-1, event.y-3, event.z) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x, event.y-3, event.z+1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x, event.y-3, event.z+1) == VBlocks.cesiumBlock

			 && event.world.getBlock(event.x+2, event.y-4, event.z) == VBlocks.cesiumBlock //couche 3
			 && event.world.getBlock(event.x-2, event.y-4, event.z) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x, event.y-4, event.z+2) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x, event.y-4, event.z-2) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+1, event.y-4, event.z-1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-1, event.y-4, event.z-1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+1, event.y-4, event.z+1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-1, event.y-4, event.z+1) == VBlocks.cesiumBlock
			 
			 && event.world.getBlock(event.x+3, event.y-5, event.z) == VBlocks.cesiumBlock //couche 2
			 && event.world.getBlock(event.x-3, event.y-5, event.z) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x, event.y-5, event.z+3) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x, event.y-5, event.z-3) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+2, event.y-5, event.z-1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+2, event.y-5, event.z+1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-2, event.y-5, event.z-1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-2, event.y-5, event.z+1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+1, event.y-5, event.z-2) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-1, event.y-5, event.z-2) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+1, event.y-5, event.z+2) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-1, event.y-5, event.z+2) == VBlocks.cesiumBlock
			 
			 && event.world.getBlock(event.x+4, event.y-6, event.z) == VBlocks.cesiumBlock //couche 1
			 && event.world.getBlock(event.x-4, event.y-6, event.z) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x, event.y-6, event.z+4) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x, event.y-6, event.z-4) == VBlocks.cesiumBlock
			 
			 && event.world.getBlock(event.x+3, event.y-6, event.z-1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+3, event.y-6, event.z+1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-3, event.y-6, event.z-1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-3, event.y-6, event.z+1) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+1, event.y-6, event.z-3) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-1, event.y-6, event.z-3) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+1, event.y-6, event.z+3) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-1, event.y-6, event.z+3) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+2, event.y-6, event.z-2) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-2, event.y-6, event.z-2) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x+2, event.y-6, event.z+2) == VBlocks.cesiumBlock
			 && event.world.getBlock(event.x-2, event.y-6, event.z+2) == VBlocks.cesiumBlock) {
				
				if (event.entityPlayer.inventory.getCurrentItem().getItem() == VItems.cesium_activator) {
					
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.chargedcesiumBlock);
					
					if (event.world.isRemote) {
						event.world.spawnEntityInWorld(new EntityLightningBolt(event.world, event.x, event.y, event.z)); 							
					}					
				}				
			}
		}
	}

}
