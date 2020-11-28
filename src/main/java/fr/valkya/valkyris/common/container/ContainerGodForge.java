package fr.valkya.valkyris.common.container;

import fr.valkya.valkyris.common.container.slot.SlotResult;
import fr.valkya.valkyris.common.tile.TileEntityGodForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGodForge extends Container {
	
	private TileEntityGodForge tileEntityGodForge;

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileEntityGodForge.isUseableByPlayer(player);
	}
	
	public ContainerGodForge(TileEntityGodForge tile, InventoryPlayer inventory) {
		this.tileEntityGodForge = tile;
		this.addSlotToContainer(new Slot(tile, 0, 38, 128));
		
		this.addSlotToContainer(new Slot(tile, 1, 41, 8));
		this.addSlotToContainer(new Slot(tile, 2, 73, 8));
		
		this.addSlotToContainer(new Slot(tile, 3, 18, 32));
		this.addSlotToContainer(new Slot(tile, 4, 97, 31));
		
		this.addSlotToContainer(new Slot(tile, 5, 18, 60));
		this.addSlotToContainer(new Slot(tile, 6, 97, 61));
		
		this.addSlotToContainer(new Slot(tile, 7, 42, 83));
		this.addSlotToContainer(new Slot(tile, 8, 73, 83));
		
		this.addSlotToContainer(new Slot(tile, 9, 57, 46));
		
		this.addSlotToContainer(new SlotResult(tile, 10, 203, 46));
		
		this.bindPlayerInventory(inventory);
	}

	
	public void bindPlayerInventory(InventoryPlayer inventory) {
		
		int i;
		for( i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 53 + j * 18, 166 + i * 18));
			}
		}
		  
        for (i = 0; i < 9; ++i){
            this.addSlotToContainer(new Slot(inventory, i, 53 + i * 18, 224));
        }
	}
	
	public ItemStack transferStackInSlot(EntityPlayer p, int quantity) {
		ItemStack itemStack = null;
		Slot slot = (Slot) this.inventorySlots.get(quantity);
		if(slot != null && slot.getHasStack()) {
			ItemStack itemStack1 = slot.getStack();
			itemStack = itemStack1.copy();
			if(quantity < this.tileEntityGodForge.getSizeInventory()) {
				if(!this.mergeItemStack(itemStack1, this.tileEntityGodForge.getSizeInventory(), this.inventorySlots.size(), true)) {
					return null;
				}
			}else if(!this.mergeItemStack(itemStack1, 0, this.tileEntityGodForge.getSizeInventory(), false)) {
				return null;
			}
			
			if(itemStack1.stackSize == 0) {
				slot.putStack((ItemStack)null);
			}else {
				slot.onSlotChanged();
			}
		}
		
		return itemStack;
		
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.tileEntityGodForge.closeInventory();
	}
}
