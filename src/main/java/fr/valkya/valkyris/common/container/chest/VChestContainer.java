package fr.valkya.valkyris.common.container.chest;

import fr.valkya.valkyris.common.tile.chest.VTileEntityChest;
import fr.valkya.valkyris.common.tile.chest.VTileEntityZircoChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class VChestContainer extends Container {

	public VTileEntityChest chest;
	private EntityPlayer player;

	public VChestContainer(IInventory playerInventory, VTileEntityChest type, int xSize, int ySize) {
		chest = type;
		chest.openInventory();
		player = ((InventoryPlayer) playerInventory).player;
		layoutContainer(xSize, ySize);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return chest.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer p, int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i < chest.getSize()) {
				if (!mergeItemStack(itemstack1, chest.getSize(), inventorySlots.size(), true)) {
					return null;
				}
			} else if (!mergeItemStack(itemstack1, 0, chest.getSize(), false)) {
				return null;
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}

	@Override
	public void onContainerClosed(EntityPlayer entityplayer) {
		super.onContainerClosed(entityplayer);
		chest.closeInventory();
	}

	protected void layoutContainer(int xSize, int ySize) {
		for (int chestRow = 0; chestRow < chest.getRowCount(); chestRow++) {
			for (int chestCol = 0; chestCol < chest.getRowLength(); chestCol++) {
				addSlotToContainer(new Slot(this.chest, chestCol + chestRow * chest.getRowLength(), 12 + chestCol * 18,
						8 + chestRow * 18));
			}
		}

		int offset = 0;
		if(chest instanceof VTileEntityZircoChest) { // WHYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY
			offset -= 18;
		}
		
		int leftCol = (xSize - 162) / 2 + 1;
		for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
			for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
				addSlotToContainer(new Slot(this.player.inventory, playerInvCol + playerInvRow * 9 + 9,
						leftCol + playerInvCol * 18, offset + (ySize - (4 - playerInvRow) * 18 - 10)));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlotToContainer(new Slot(this.player.inventory, hotbarSlot, leftCol + hotbarSlot * 18, offset + ySize - 24));
		}
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public int getNumColumns() {
		return chest.getRowLength();
	}
}