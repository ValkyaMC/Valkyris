package fr.valkya.valkyris.common.items.kits.impl;

import fr.valkya.valkyris.common.items.kits.ItemKit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemEnchantKit extends ItemKit {

	public ItemEnchantKit() {
		super("enchant");
	}

	@Override
	protected void initKit() {
		
		this.kitItems.add(new ItemStack(Blocks.enchanting_table, 1));
		this.kitItems.add(new ItemStack(Blocks.bookshelf, 16));
		this.kitItems.add(new ItemStack(Items.book, 32));
		this.kitItems.add(new ItemStack(Items.dye, 64, 4));
		this.kitItems.add(new ItemStack(Items.experience_bottle, 128));
	}

}
