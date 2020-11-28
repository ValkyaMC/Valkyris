package fr.valkya.valkyris.common.items.kits.impl;

import java.util.ArrayList;

import fr.valkya.valkyris.common.items.kits.ItemKit;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemDailyKit extends ItemKit {

	public ItemDailyKit() {
		super("daily");
	}

	@Override
	protected void initKit() {
		this.kitItems = new ArrayList<>();
		ItemStack ironSword = new ItemStack(Items.iron_sword);
		this.kitItems.add(ironSword);
		
		ItemStack ironPickaxe = new ItemStack(Items.iron_pickaxe);
		this.kitItems.add(ironPickaxe);
		
		ItemStack ironHelmet = new ItemStack(Items.iron_helmet);
		this.kitItems.add(ironHelmet);
		
		ItemStack ironChestplate = new ItemStack(Items.iron_chestplate);
		this.kitItems.add(ironChestplate);
		
		ItemStack ironLeggins = new ItemStack(Items.iron_leggings);
		this.kitItems.add(ironLeggins);
		
		ItemStack ironBoots = new ItemStack(Items.iron_boots);
		this.kitItems.add(ironBoots);
		
		this.kitItems.add(new ItemStack(Items.cooked_beef, 16));
	}

}
