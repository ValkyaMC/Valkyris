package fr.valkya.valkyris.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;

public class ObsidianLadder extends BlockLadder {
	
	public ObsidianLadder() {
		this.setBlockName("obsidian_ladder");
		this.setBlockTextureName(References.MODID + ":obsidian/obsidian_ladder");
		this.setResistance(2000F);
		this.setHardness(50F);
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsObsidian);
	}

	public Block register() {
		GameRegistry.registerBlock(this, getUnlocalizedName().substring(5));
		return this;
	}

}
