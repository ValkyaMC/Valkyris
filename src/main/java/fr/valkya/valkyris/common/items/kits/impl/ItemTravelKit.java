package fr.valkya.valkyris.common.items.kits.impl;

import fr.valkya.valkyris.common.items.VItems;
import fr.valkya.valkyris.common.items.kits.ItemKit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemTravelKit extends ItemKit {

	public ItemTravelKit() {
		super("travel");
	}

	@Override
	protected void initKit() {
		ItemStack travelHelmet = new ItemStack(VItems.valoArmors.travelHelmet);
		travelHelmet.addEnchantment(Enchantment.unbreaking, 3); 
		this.kitItems.add(travelHelmet);
		
		ItemStack travelchesplate = new ItemStack(VItems.valoArmors.travelChestplate);
		travelchesplate.addEnchantment(Enchantment.unbreaking, 3); 
		this.kitItems.add(travelchesplate);
		
		ItemStack travelleggins = new ItemStack(VItems.valoArmors.travelLeggings);
		travelleggins.addEnchantment(Enchantment.unbreaking, 3); 
		this.kitItems.add(travelleggins);
		
		ItemStack travelboots = new ItemStack(VItems.valoArmors.travelBoots);
		travelboots.addEnchantment(Enchantment.unbreaking, 3); 
		this.kitItems.add(travelboots);
		
		this.kitItems.add(new ItemStack(VItems.esp_helmet, 1));
		this.kitItems.add(new ItemStack(Items.cooked_beef, 64));
		this.kitItems.add(new ItemStack(Items.ender_pearl, 16));
	}

}
