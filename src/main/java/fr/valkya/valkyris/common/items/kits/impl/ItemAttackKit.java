package fr.valkya.valkyris.common.items.kits.impl;

import fr.valkya.valkyris.common.items.VItems;
import fr.valkya.valkyris.common.items.kits.ItemKit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAttackKit extends ItemKit {

	public ItemAttackKit() {
		super("attack");
	}

	@Override
	protected void initKit() {
		ItemStack is = new ItemStack(VItems.valoArmors.zircoHelmet);
		is.addEnchantment(Enchantment.protection, 2);
		is.addEnchantment(Enchantment.unbreaking, 2);
		this.kitItems.add(is);
		
		is = new ItemStack(VItems.valoArmors.zircoChestplate);
		is.addEnchantment(Enchantment.protection, 2);
		is.addEnchantment(Enchantment.unbreaking, 2);
		this.kitItems.add(is);
		
		is = new ItemStack(VItems.valoArmors.zircoLeggins);
		is.addEnchantment(Enchantment.protection, 2);
		is.addEnchantment(Enchantment.unbreaking, 2);
		this.kitItems.add(is);
		
		is = new ItemStack(VItems.valoArmors.zircoBoots);
		is.addEnchantment(Enchantment.protection, 2);
		is.addEnchantment(Enchantment.unbreaking, 2);
		this.kitItems.add(is);
		
		ItemStack zircoSword = new ItemStack(VItems.zircoSword);
		zircoSword.addEnchantment(Enchantment.sharpness, 4); 
		zircoSword.addEnchantment(Enchantment.unbreaking, 2);
		this.kitItems.add(zircoSword);
		
		this.kitItems.add(new ItemStack(Items.golden_apple, 32));
		this.kitItems.add(new ItemStack((Item)Item.itemRegistry.getObject("totem_of_undying"), 1));
		
//		this.kitItems.add(new ItemStack(Items.popo.splah.heal2, 6));
//		this.kitItems.add(new ItemStack(Items.popo.splah.regen, 3));
//		this.kitItems.add(new ItemStack(Items.popo.splah.nightvision, 6));
//		this.kitItems.add(new ItemStack(Items.popo.splah.speed2, 3));
//		this.kitItems.add(new ItemStack(Items.popo.splah.poison, 2));
//		this.kitItems.add(new ItemStack(Items.popo.splah.slowness, 2));
		
	}

}
