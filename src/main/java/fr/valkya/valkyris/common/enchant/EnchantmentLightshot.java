package fr.valkya.valkyris.common.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;

public class EnchantmentLightshot extends Enchantment {

	public EnchantmentLightshot() {
		super(122, 0, EnumEnchantmentType.bow);
		setName("lightshot");
	}
	
	public int getMinEnchantability(int p_77321_1_)
    {
        return 20;
    }

    public int getMaxEnchantability(int p_77317_1_)
    {
        return 50;
    }

    public int getMaxLevel()
    {
        return 1;
    }
	
	@Override
	public boolean canApply(ItemStack stack) {
		return stack != null && stack.getItem() != null && stack.getItem() instanceof ItemBow;
	}
	
}
