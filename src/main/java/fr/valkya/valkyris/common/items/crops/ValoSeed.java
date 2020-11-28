package fr.valkya.valkyris.common.items.crops;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class ValoSeed extends ItemSeeds {
	
	private String name;
	
	public ValoSeed(String name, Block block) {
		super(block, Blocks.farmland);
		this.name = name;
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().cropsCreativeTabs);
		this.setUnlocalizedName(this.name);
		this.setTextureName(References.MODID + ":seeds/" + this.name);
	}
	
	public Item register() {
		GameRegistry.registerItem(this, name);
		return this;
	}
}
