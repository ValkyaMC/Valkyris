package fr.valkya.valkyris.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;

public class ItemValoCD extends ItemRecord {

	protected ItemValoCD() {
		super(References.MODID + ":hey");
		this.setUnlocalizedName("mistery_hey");
	}
	
	public Item register() {
		GameRegistry.registerItem(this, getUnlocalizedName().substring(5));
		return this;
	}
	
	@Override
	public ResourceLocation getRecordResource(String name) {
		return new ResourceLocation(References.MODID, ":record.mistery");
	}
	
	@Override
	public String getRecordNameLocal() { return ""; }

}
