package fr.valkya.valkyris.common.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class EnchantmentTelekinesis extends Enchantment {

	public EnchantmentTelekinesis() {
		super(121, 0, EnumHelper.addEnchantmentType("tools"));
		setName("telekinesis");
	}
	
	public int getMinEnchantability(int p_77321_1_)
    {
        return 30;
    }

    public int getMaxEnchantability(int p_77317_1_)
    {
        return 50;
    }
    
    
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
	public boolean canApply(ItemStack stack) {
		if(stack == null || stack.getItem() == null) return false;
		Item item = stack.getItem();
		if(item instanceof ItemSword || item instanceof ItemTool || item instanceof ItemBow || item instanceof ItemFishingRod) {
			return true;
		}
		return false;
	}

}
