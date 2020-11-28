package fr.valkya.valkyris.common.block;

import fr.valkya.valkyris.References;
import net.minecraft.block.material.Material;

public class ValoChargedCesiumBlock extends VBlock {
	
	public ValoChargedCesiumBlock(Material mat, String name) {
		super(mat, name);
		this.setBlockTextureName(References.MODID + ":cesium_block_charged");
	}
}
