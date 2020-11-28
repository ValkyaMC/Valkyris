package fr.valkya.valkyris.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemZirconiumActivator extends Item {
	
	public ItemZirconiumActivator() {
		this.setUnlocalizedName("zirconium_activator");
		this.setTextureName(References.MODID + ":zirconium_activator");
		this.setMaxStackSize(1);
		this.setMaxDamage(1);
		this.setNoRepair();
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().staffCreativeTabs);
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack item) {
		return true;
	}

}
