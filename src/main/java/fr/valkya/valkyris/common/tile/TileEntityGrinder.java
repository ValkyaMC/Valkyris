package fr.valkya.valkyris.common.tile;

import fr.valkya.valkyris.common.recipes.GrinderRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGrinder extends TileEntity implements IInventory {

	private ItemStack[] contents = new ItemStack[4];
	private int workingTime = 0;
	private int workingTimeNeeded = 200;

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.contents.length; ++i) {
			if (this.contents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.contents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		compound.setTag("Items", nbttaglist);
		compound.setShort("workingTime", (short) this.workingTime);
		compound.setShort("workingTimeNeeded", (short) this.workingTimeNeeded);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.contents = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.contents.length) {
				this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.workingTime = compound.getShort("workingTime");
		this.workingTimeNeeded = compound.getShort("workingTimeNeeded");
	}

	@Override
	public int getSizeInventory() {
		return this.contents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		return this.contents[slotIndex];
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int amount) {
		if (this.contents[slotIndex] != null) {
			ItemStack itemstack;

			if (this.contents[slotIndex].stackSize <= amount) {
				itemstack = this.contents[slotIndex];
				this.contents[slotIndex] = null;
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.contents[slotIndex].splitStack(amount);

				if (this.contents[slotIndex].stackSize == 0) {
					this.contents[slotIndex] = null;
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		if (this.contents[slotIndex] != null) {
			ItemStack itemstack = this.contents[slotIndex];
			this.contents[slotIndex] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack stack) {
		this.contents[slotIndex] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	@Override
	public String getInventoryName() {
		return "tile.grinder";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
			: player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 3 ? false : true;
	}

	public boolean isBurning() {
		return this.workingTime > 0;
	}

	private boolean canSmelt() {
		if (this.contents[0] == null || this.contents[1] == null || this.contents[2] == null) {
			return false;
		} else {
			ItemStack itemstack = GrinderRecipes.smelting().getSmeltingResult(new ItemStack[] { this.contents[0], this.contents[1], this.contents[2] });
			
			if (itemstack == null)
				return false;

			int result = itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.contents[2].getMaxStackSize();
		}
	} 

	@Override
	public void updateEntity() {
		if (this.isBurning() && this.canSmelt()) {
			++this.workingTime;
		}
		if (this.canSmelt() && !this.isBurning()) {
			this.workingTime = 1;
		}
		if (this.canSmelt() && this.workingTime == this.workingTimeNeeded) {
			this.smeltItem();
			this.workingTime = 0;
		}
		if (!this.canSmelt()) {
			this.workingTime = 0;
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = GrinderRecipes.smelting()
				.getSmeltingResult(new ItemStack[] { this.contents[0], this.contents[1], this.contents[2] });

			this.contents[2] = itemstack.copy();

			--this.contents[0].stackSize;
			--this.contents[1].stackSize;

			if (this.contents[0].stackSize <= 0) {
				this.contents[0] = null;
			}
			if (this.contents[1].stackSize <= 0) {
				this.contents[1] = null;
			}
		}
	}
}
