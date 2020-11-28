package fr.valkya.valkyris.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class ValoChargedZirconiumBlock extends VBlock {
	
	public ValoChargedZirconiumBlock(Material mat, String name) {
		super(mat, name);
		this.setBlockTextureName(References.MODID + ":zirconium_block_charged");
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack item) {
		return true;
	}
}
