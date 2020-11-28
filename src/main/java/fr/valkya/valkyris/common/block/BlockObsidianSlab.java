package fr.valkya.valkyris.common.block;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockObsidianSlab extends BlockSlab {

	private boolean isDouble;

	public BlockObsidianSlab(boolean isDouble) {
		super(isDouble, Material.rock);
		this.isDouble = isDouble;
		this.useNeighborBrightness = true;
		if(!this.field_150004_a) this.setLightOpacity(0);
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
		this.setStepSound(soundTypePiston);
		this.setBlockName("obsidian_slab");
		this.setBlockTextureName("obsidian");
	}
	
	@Override
	public MapColor getMapColor(int a) {
		return MapColor.obsidianColor;
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(this);
	}

	public Block register() {
		GameRegistry.registerBlock(this, getUnlocalizedName().substring(5) + (this.isDouble ? "_double" : "_single"));
		return this;
	}

	@Override
	public String func_150002_b(int meta) { return null; }
	
	@Override
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return Item.getItemFromBlock(this);
	}

}
