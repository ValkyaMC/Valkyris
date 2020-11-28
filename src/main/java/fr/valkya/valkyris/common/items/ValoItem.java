package fr.valkya.valkyris.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ValoItem extends Item {
	
	private String name;
	
	public ValoItem(String name, CreativeTabs tab, int maxStackSize) {
		this.name = name;
		
		setUnlocalizedName(this.name);
		setTextureName(References.MODID + ":" + this.name);
		setCreativeTab(tab);
		setMaxStackSize(maxStackSize);
	}
	
	public ValoItem(String name, int maxStackSize) {
		this(name, Valkyris.getValkyris().getProxy().getCreativesTabs().materialCreativeTabs, maxStackSize);
	}
	
	public ValoItem(String name, CreativeTabs tab) {
		this(name, tab, 64);
	}

	public ValoItem(String name) {
		this(name, Valkyris.getValkyris().getProxy().getCreativesTabs().materialCreativeTabs, 64);
	}
	
	public Item register() {
		GameRegistry.registerItem(this, name);
		return this;
	}

}
