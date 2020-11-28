package fr.valkya.valkyris.common.block;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ObsidianDoor extends BlockDoor {

	protected ObsidianDoor() {
		super(Material.rock);
		this.setBlockName("obsidian_door");
		this.setBlockTextureName(References.MODID + ":obsidian/obsidian_door");
		this.setResistance(2000.0F);
		this.setHardness(50.0F);
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsObsidian);
	}

	public Block register() {
		GameRegistry.registerBlock(this, getUnlocalizedName().substring(5));
		return this;
	}
	
	@Override
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return VItems.itemObsidianDoor;
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		if((p_149650_1_ & 8) != 0) return null;
		return VItems.itemObsidianDoor;
	}
}
