package fr.valkya.valkyris.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class VBlock extends Block {
	
	private String name;
	
	public VBlock(Material mat, String name) { 
		super(mat); 
		this.name = name;
		this.setBlockName(name);
		this.setBlockTextureName(References.MODID + ":" + name);
	}
	
	public String getName() {
		return name;
	}
	
	public Block register() {
		GameRegistry.registerBlock(this, getName());
		return this;
	}
}
