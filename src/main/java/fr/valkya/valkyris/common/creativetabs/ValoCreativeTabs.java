package fr.valkya.valkyris.common.creativetabs;

import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class ValoCreativeTabs {
	
	public CreativeTabs cropsCreativeTabs = new CreativeTabs("crops_creative_tab") {
		@Override
		public Item getTabIconItem() { 
			return Item.getItemFromBlock(VBlocks.seedAccelerator);
		}
	};
	
	public CreativeTabs materialCreativeTabs = new CreativeTabs("materials_creative_tab") {
		@Override
		public Item getTabIconItem() { 
			return Item.getItemFromBlock(VBlocks.zirconiumBlock);
		}
	};
	
	public CreativeTabs staffCreativeTabs = new CreativeTabs("staff_creative_tab") {
		@Override
		public Item getTabIconItem() { 
			return Item.getItemFromBlock(VBlocks.barrier);
		}
	};

	public CreativeTabs machineCreativeTabs = new CreativeTabs("machine_creative_tab") {
		@Override
		public Item getTabIconItem() { 
			return Item.getItemFromBlock(VBlocks.godForge);
		}
	};

	public CreativeTabs armorCreativeTabs = new CreativeTabs("armor_creative_tabs") {
		@Override
		public Item getTabIconItem() { 
			return VItems.valoArmors.valoChestplate;
		}
	};

	public CreativeTabs valoCreativeTabsSpawner = new CreativeTabs("spawner_creative_tabs") {
		@Override
		public Item getTabIconItem() {
			return VItems.spawnercow;
		}
	};
	
	public CreativeTabs valoCreativeTabsObsidian = new CreativeTabs("obsidian_creative_tabs") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(VBlocks.obsidianReinforced);
		}
	};
	
	public CreativeTabs valoCreativeTabsUtils = new CreativeTabs("utils_creative_tabs") {
		@Override
		public Item getTabIconItem() {
			return VItems.wateringCan1;
		}
	};
	
	public CreativeTabs valoCreativeTabsCrafts = new CreativeTabs("crafts_creative_tabs") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.crafting_table);
		}
	};

}
