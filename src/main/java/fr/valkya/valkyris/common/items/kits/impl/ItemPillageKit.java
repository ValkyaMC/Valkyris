package fr.valkya.valkyris.common.items.kits.impl;

import fr.valkya.valkyris.common.items.VItems;
import fr.valkya.valkyris.common.items.kits.ItemKit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemPillageKit extends ItemKit {

	public ItemPillageKit() {
		super("pillage");
	}

	@Override
	protected void initKit() {
		this.kitItems.add(new ItemStack(Items.ender_pearl, 16));
		this.kitItems.add(new ItemStack(VItems.cesiumDynamite, 16));
		this.kitItems.add(new ItemStack(Blocks.tnt, 32));
		this.kitItems.add(new ItemStack(Items.flint_and_steel, 1));
		this.kitItems.add(new ItemStack(Items.cooked_beef, 16));

		ItemStack camouflageHelmet = new ItemStack(VItems.valoArmors.camouflageHelmet);
		this.kitItems.add(camouflageHelmet);

		ItemStack camouflageChestPlate = new ItemStack(VItems.valoArmors.camouflageChestplate);
		this.kitItems.add(camouflageChestPlate);

		ItemStack camouflageLeggins = new ItemStack(VItems.valoArmors.camouflageLeggins);
		this.kitItems.add(camouflageLeggins);

		ItemStack camouflageBoots = new ItemStack(VItems.valoArmors.camouflageBoots);
		this.kitItems.add(camouflageBoots);

	}

}
