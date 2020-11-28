package fr.valkya.valkyris.common.block;

import fr.valkya.valkyris.References;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class VBlockGrass extends VBlock {

	private IIcon bottom, top;
	
	public VBlockGrass(Material mat, String name) {
		super(mat, name);
		this.setBlockTextureName(References.MODID + ":grass/" + name);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		bottom = iconRegister.registerIcon("minecraft:");
		top = iconRegister.registerIcon(References.MODID + ":grass/" + getName() + "_top");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 0 ? bottom : side == 1 ? top : blockIcon;
	}
}
