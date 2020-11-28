package fr.valkya.valkyris.common.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SerializableItemStack {
	
	private String itemName;
	private int meta;
	private int ammount;
	
	public SerializableItemStack(ItemStack is) {
		this.itemName = Item.itemRegistry.getNameForObject(is.getItem());
		this.meta = is.getItemDamage();
		this.ammount = is.stackSize;
	}
	
	public Item getItem() {
		return (Item) Item.itemRegistry.getObject(itemName);
	}
	
	public int getMeta() {
		return meta;
	}
	
	public int getAmmount() {
		return ammount;
	}
	
	public ItemStack getItemStack() {
		return new ItemStack(getItem(), ammount, meta);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + meta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SerializableItemStack)) {
			return false;
		}
		SerializableItemStack other = (SerializableItemStack) obj;
		if (itemName == null) {
			if (other.itemName != null) {
				return false;
			}
		} else if (!itemName.equals(other.itemName)) {
			return false;
		}
		if (meta != other.meta) {
			return false;
		}
		return true;
	}

}
