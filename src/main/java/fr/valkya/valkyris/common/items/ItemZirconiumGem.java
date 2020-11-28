package fr.valkya.valkyris.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemZirconiumGem extends Item {

	public ItemZirconiumGem() {
		this.setUnlocalizedName("zirconium_gem");
		this.setTextureName(References.MODID + ":zirconium_gem");
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().materialCreativeTabs);
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack item) {
		return true;
	}

}