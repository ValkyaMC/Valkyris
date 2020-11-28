package fr.valkya.valkyris.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;

public class ObsidianTrapdoor extends BlockTrapDoor {

	protected ObsidianTrapdoor() {
		super(Material.rock);
		this.setHardness(50F);
		this.setResistance(2000F);
		this.setStepSound(soundTypePiston);
		this.setBlockName("obsidian_trapdoor");
		this.disableStats();
		this.setBlockTextureName(References.MODID + ":obsidian/obsidian_trapdoor");
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsObsidian);
	}

	public Block register() {
		GameRegistry.registerBlock(this, getUnlocalizedName().substring(5, getUnlocalizedName().length()));
		return this;
	}

}
