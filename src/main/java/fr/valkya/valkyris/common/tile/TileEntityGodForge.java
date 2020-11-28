package fr.valkya.valkyris.common.tile;

import fr.valkya.valkyris.common.items.VItems;
import fr.valkya.valkyris.common.recipes.GodForgeRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGodForge extends TileEntity implements IInventory {

	private ItemStack[] contents = new ItemStack[11]; // 0 = four, 1,2,3,4,5,6,7,8,9 = input des items, 10 = Output

	private double charge = 0;

	public float rotation = 0;
	public float scale = 0.1F;

	@Override
	public void updateEntity() {
		if (charge >= 200)
			charge = 200;
		charge();

//		System.out.println(canCraft());
		
		if (this.charge >= 100 && this.canCraft()) {
			this.craftItem();
			charge = charge - 100;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		NBTTagList nbtTagList = new NBTTagList();
		for (int i = 0; i < contents.length; i++) {
			if (this.contents[i] != null) {
				NBTTagCompound nbtTagCompound1 = new NBTTagCompound();
				nbtTagCompound1.setByte("slot", (byte) i);
				this.contents[i].writeToNBT(nbtTagCompound1);
				nbtTagList.appendTag(nbtTagCompound1);
			}
		}
		compound.setTag("items", nbtTagList);
		compound.setDouble("charge", this.charge);
		super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbtTagList = compound.getTagList("items", 10);
		this.contents = new ItemStack[this.getSizeInventory()];
		this.charge = compound.getDouble("charge");

		for (int i = 0; i < nbtTagList.tagCount(); i++) {
			NBTTagCompound nbtTagCompound1 = nbtTagList.getCompoundTagAt(i);
			int j = nbtTagCompound1.getByte("slot") & 255;

			if (j >= 0 && j < this.contents.length) {
				this.contents[j] = ItemStack.loadItemStackFromNBT(nbtTagCompound1);
			}
		}
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
			ItemStack itemStack;

			if (this.contents[slotIndex].stackSize <= amount) {
				itemStack = this.contents[slotIndex];
				this.contents[slotIndex] = null;
				this.markDirty();
				return itemStack;
			} else {
				itemStack = this.contents[slotIndex].splitStack(amount);

				if (this.contents[slotIndex].stackSize == 0) {
					this.contents[slotIndex] = null;
				}

				this.markDirty();
				return itemStack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		if (this.contents[slotIndex] != null) {
			ItemStack itemStack = this.contents[slotIndex];
			this.contents[slotIndex] = null;
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
		this.contents[slotIndex] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	public String getInventoryName() {
		return "Forge des dieux";
	}

	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		return slot == 10 ? false : true;
	}

	private boolean canCraft() {
		if (this.contents[1] == null || this.contents[2] == null || this.contents[3] == null || this.contents[4] == null || this.contents[5] == null || this.contents[6] == null || this.contents[7] == null || this.contents[8] == null || this.contents[9] == null)
			return false;
		else {
			ItemStack is = GodForgeRecipes.crafting().getCraftingResult(new ItemStack[] { this.contents[1], this.contents[2], this.contents[3], this.contents[4], this.contents[5], this.contents[6], this.contents[7], this.contents[8], this.contents[9] });
			if (is == null)
				return false;
//			System.out.println("good?");
			if (this.contents[10] == null)
				return true;
			if (!this.contents[10].isItemEqual(is))
				return false;
			if (charge <= 100)
				return false;

			int result = contents[10].stackSize + is.stackSize;
			if (result <= 0)
				return false;
			return result <= getInventoryStackLimit() && result <= this.contents[10].getMaxStackSize();
		}
	}

	public void craftItem() {
		if (this.canCraft()) {
			ItemStack itemStack = GodForgeRecipes.crafting().getCraftingResult(new ItemStack[] { this.contents[1], this.contents[2], this.contents[3], this.contents[4], this.contents[5], this.contents[6], this.contents[7], this.contents[8], this.contents[9] });
			if (this.contents[10] == null) {
				this.contents[10] = itemStack.copy();
			} else if (this.contents[10].getItem() == itemStack.getItem()) {
				this.contents[10].stackSize += itemStack.stackSize;
			}

			--this.contents[1].stackSize;
			--this.contents[2].stackSize;
			--this.contents[3].stackSize;
			--this.contents[4].stackSize;
			--this.contents[5].stackSize;
			--this.contents[6].stackSize;
			--this.contents[7].stackSize;
			--this.contents[8].stackSize;
			--this.contents[9].stackSize;

			if (this.contents[1].stackSize <= 0) {
				this.contents[1] = null;
			}
			if (this.contents[2].stackSize <= 0) {
				this.contents[2] = null;
			}
			if (this.contents[3].stackSize <= 0) {
				this.contents[3] = null;
			}
			if (this.contents[4].stackSize <= 0) {
				this.contents[4] = null;
			}
			if (this.contents[5].stackSize <= 0) {
				this.contents[5] = null;
			}
			if (this.contents[6].stackSize <= 0) {
				this.contents[6] = null;
			}
			if (this.contents[7].stackSize <= 0) {
				this.contents[7] = null;
			}
			if (this.contents[8].stackSize <= 0) {
				this.contents[8] = null;
			}
			if (this.contents[9].stackSize <= 0) {
				this.contents[9] = null;
			}
		}
	}

	public double getForgeCharge() {
		return charge;
	}

	public void setForgeCharge(double d) {
		this.charge = d;
	}

	public void charge() {
		ItemStack itemStack = contents[0];
		if (itemStack == null)
			return;
		
		double i = getForgeCharge();
		
		if (i < 200 && itemStack.getItem() == Items.lava_bucket) {
			setForgeCharge(getForgeCharge() + 50);
			decrStackSize(0, 1);
		}
		if (i < 200 && itemStack.getItem() == VItems.superfuel) {
			setForgeCharge(getForgeCharge() + 200);
			decrStackSize(0, 1);
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound compound = new NBTTagCompound();

		NBTTagList nbtTagList = new NBTTagList();
		for (int i = 0; i < contents.length; i++) {
			if (this.contents[i] != null) {
				NBTTagCompound nbtTagCompound1 = new NBTTagCompound();
				nbtTagCompound1.setByte("slot", (byte) i);
				this.contents[i].writeToNBT(nbtTagCompound1);
				nbtTagList.appendTag(nbtTagCompound1);
			}
		}
		compound.setTag("items", nbtTagList);
		compound.setDouble("charge", this.charge);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, compound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound compound = pkt.func_148857_g();
		this.charge = compound.getDouble("charge");

		NBTTagList nbtTagList = compound.getTagList("items", 10);
		this.contents = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbtTagList.tagCount(); i++) {
			NBTTagCompound nbtTagCompound1 = nbtTagList.getCompoundTagAt(i);
			int j = nbtTagCompound1.getByte("slot") & 255;

			if (j >= 0 && j < this.contents.length) {
				this.contents[j] = ItemStack.loadItemStackFromNBT(nbtTagCompound1);
			}
		}
	}
}
