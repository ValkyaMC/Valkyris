package fr.valkya.valkyris.common.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotResult extends Slot {

	public SlotResult(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		return false;
	}
	
	@Override
	public ItemStack decrStackSize(int amount) {
		return super.decrStackSize(amount);
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack) {
		super.onCrafting(itemStack);
		super.onPickupFromSlot(player, itemStack);
	}

}
