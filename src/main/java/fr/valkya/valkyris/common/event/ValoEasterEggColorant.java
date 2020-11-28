package fr.valkya.valkyris.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.valkya.valkyris.common.block.VBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ValoEasterEggColorant {
	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent event) {
		if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
			if(event.entityPlayer.inventory.getCurrentItem() == null) return;
			
			if (event.world.getBlock(event.x, event.y, event.z) == Blocks.grass) {
				if (event.entityPlayer.inventory.getCurrentItem().getItem() != Items.dye) return;
				
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 0) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.blackGrass);
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 1) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.redGrass);
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 2) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.greenGrass);
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 3) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.brownGrass);
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 4) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.blueGrass);					
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 5) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.purpleGrass);					
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 6) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.cyanGrass);					
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 7) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.light_grayGrass);					
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 8) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.grayGrass);					
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 9) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.pinkGrass);					
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 10) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.limeGrass);					
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 11) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.yellowGrass);					
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 12) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.light_blueGrass);					
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 13) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.magentaGrass);					
				}
				if (event.entityPlayer.inventory.getCurrentItem().getItemDamage() == 14) {
					event.entityPlayer.inventory.getCurrentItem().stackSize--;
					event.world.setBlock(event.x, event.y, event.z, VBlocks.orangeGrass);					
				}
			}
		}
	}
}
