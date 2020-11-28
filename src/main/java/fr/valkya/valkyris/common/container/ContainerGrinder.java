package fr.valkya.valkyris.common.container;

import fr.valkya.valkyris.common.tile.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGrinder extends Container {

    private final TileEntityGrinder tileGrinder;

    public ContainerGrinder(TileEntityGrinder tile, InventoryPlayer inventory) {
        this.tileGrinder = tile;
        
        this.addSlotToContainer(new Slot(tile, 0, 18, 27));
        this.addSlotToContainer(new Slot(tile, 2, 80, 42));
        this.addSlotToContainer(new Slot(tile, 1, 142, 27));
        
        this.bindPlayerInventory(inventory);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileGrinder.isUseableByPlayer(player);
    }

    private void bindPlayerInventory(InventoryPlayer inventory) {
        int i;
        for(i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 130 + 12));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int quantity) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(quantity);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(quantity < this.tileGrinder.getSizeInventory()) {
                if(!this.mergeItemStack(itemstack1, this.tileGrinder.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if(!this.mergeItemStack(itemstack1, 0, this.tileGrinder.getSizeInventory(), false)) {
                return null;
            }

            if(itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        
        this.tileGrinder.closeInventory();
    }

}
