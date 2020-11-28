package fr.valkya.valkyris.common.block.bone;

import fr.valkya.valkyris.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BoneBlock4 extends Block {

	private IIcon top;

	public BoneBlock4(Material mat) {
		super(mat);
	}

	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		this.blockIcon = register.registerIcon(References.MODID + ":bone_block_side4");
		this.top = register.registerIcon(References.MODID + ":bone_block_top4");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		switch(side) {
		case 0:
			return this.top;
		case 1:
			return this.top;
		default:
			return this.blockIcon;
		}
	}
	
	
}
