package fr.valkya.valkyris.common.items.kits.impl;

import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.common.items.VItems;
import fr.valkya.valkyris.common.items.kits.ItemKit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ItemDefenceKit extends ItemKit {

	public ItemDefenceKit() {
		super("defence");
	}

	@Override
	protected void initKit() {
		this.kitItems.add(new ItemStack(Blocks.obsidian, 576));
		this.kitItems.add(new ItemStack(VBlocks.obsidianLadder, 64));
		this.kitItems.add(new ItemStack(VItems.itemObsidianDoor, 16));
		this.kitItems.add(new ItemStack(VBlocks.obsidianTrapdoor, 16));
		this.kitItems.add(new ItemStack(VItems.cesiumBucket, 16));		
//		this.kitItems.add(new ItemStack(VBlocks.cobblecompressniv4, 64));
//		this.kitItems.add(new ItemStack(VBlocks.cobblecompressniv5, 32));
		
	}

}
