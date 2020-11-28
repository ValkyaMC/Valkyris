package fr.valkya.valkyris.common.items.kits.impl;

import fr.valkya.valkyris.common.items.kits.ItemKit;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemFoodKit extends ItemKit {

	public ItemFoodKit() {
		super("food");
	}

	@Override
	protected void initKit() {
		this.kitItems.add(new ItemStack(Items.cooked_beef, 16));
	}

}
