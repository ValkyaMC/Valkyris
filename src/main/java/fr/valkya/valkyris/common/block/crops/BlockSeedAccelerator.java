package fr.valkya.valkyris.common.block.crops;

import fr.valkya.valkyris.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockSeedAccelerator extends Block {

	private IIcon top_bottom;
	
	public BlockSeedAccelerator() {
		super(Material.rock);
		this.setBlockName("seed_accelerator");
		this.setBlockTextureName(References.MODID + ":" + "seed_accelerator");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconregister) {
		this.top_bottom = iconregister.registerIcon(References.MODID + ":seed_accelerator_top");
		super.registerBlockIcons(iconregister);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 0 || side == 1) return top_bottom;
		return this.blockIcon;
	}

}
