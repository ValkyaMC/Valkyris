package fr.valkya.valkyris.common.items.kits.impl;

import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.common.items.VItems;
import fr.valkya.valkyris.common.items.kits.ItemKit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemFarmerKit extends ItemKit {

	public ItemFarmerKit() {
		super("farmer");
	}

	@Override
	protected void initKit() {
		ItemStack cesiulPickaxe = new ItemStack(VItems.cesiumPickaxe);
		cesiulPickaxe.addEnchantment(Enchantment.efficiency, 4); 
		cesiulPickaxe.addEnchantment(Enchantment.unbreaking, 3); 
		this.kitItems.add(cesiulPickaxe);
		
		ItemStack cesiumAxe = new ItemStack(VItems.cesiumAxe);
		cesiumAxe.addEnchantment(Enchantment.efficiency, 4); 
		cesiumAxe.addEnchantment(Enchantment.unbreaking, 3); 
		this.kitItems.add(cesiumAxe);
		
		ItemStack cesiumShovel = new ItemStack(VItems.cesiumShovel);
		cesiumShovel.addEnchantment(Enchantment.efficiency, 4); 
		cesiumShovel.addEnchantment(Enchantment.unbreaking, 3); 
		this.kitItems.add(cesiumShovel);
		
		ItemStack cesiumHoe = new ItemStack(VItems.cesiumHoe);
		cesiumHoe.addEnchantment(Enchantment.unbreaking, 3); 
		this.kitItems.add(cesiumHoe);
		
		ItemStack owo = new ItemStack(Items.enchanted_book);
		this.kitItems.add(owo);
		
		this.kitItems.add(new ItemStack(VItems.wateringCan2, 1));
		this.kitItems.add(new ItemStack(VBlocks.seedAccelerator, 16));
		
		this.kitItems.add(new ItemStack(VItems.cesiumSeed, 16));
		this.kitItems.add(new ItemStack(VItems.zirconiumSeed, 8));
	}

}
