package fr.valkya.valkyris.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

public class ObsidianGlassPane extends BlockPane {

	protected ObsidianGlassPane() {
		super(References.MODID + ":obsidian/obsidian_glass_pane", References.MODID + ":obsidian/obsidian_glass_pane_top", Material.rock, true);
		this.setBlockName("obsidian_glass_pan");
		this.setResistance(2000.0F);
		this.setHardness(50.0F);
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsObsidian);
	}
	
	public Block register() {
		GameRegistry.registerBlock(this, getUnlocalizedName().substring(5));
		return this;
	}

}
