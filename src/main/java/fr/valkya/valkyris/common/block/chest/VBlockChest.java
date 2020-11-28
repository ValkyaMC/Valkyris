package fr.valkya.valkyris.common.block.chest;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

import java.util.Iterator;
import java.util.Random;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.tile.chest.VTileEntityChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class VBlockChest<T extends VTileEntityChest> extends BlockContainer {

	private static final Random random = new Random();
	private final Class<T> tileEntityClass;

	public VBlockChest(String name, Class<T> tileEntityClass) {
		super(Material.iron);
		this.tileEntityClass = tileEntityClass;

		// BlockChest

		this.setBlockName(name);
		this.setBlockTextureName(References.MODID + ":" + name);
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().machineCreativeTabs);
		
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 22;
	}

	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_,
			EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		byte b0 = 0;
		int l = MathHelper.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0)
			b0 = 2;
		if (l == 1)
			b0 = 5;
		if (l == 2)
			b0 = 3;
		if (l == 3)
			b0 = 4;

		p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, b0, 3);

		if (p_149689_6_.hasDisplayName()) {
			((T) p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_))
					.func_145976_a(p_149689_6_.getDisplayName());
		}
	}

	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_,
			int p_149719_4_) {
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_,
			Block p_149695_5_) {
		T tileentitychest = (T) p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_, p_149695_4_);

		if (tileentitychest != null) {
			tileentitychest.updateContainingBlockInfo();
		}
	}

	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_,
			int p_149749_6_) {
		T tileentitychest = (T) p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

		if (tileentitychest != null) {
			for (int i1 = 0; i1 < tileentitychest.getSizeInventory(); ++i1) {
				ItemStack itemstack = tileentitychest.getStackInSlot(i1);

				if (itemstack != null) {
					float f = random.nextFloat() * 0.8F + 0.1F;
					float f1 = random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; p_149749_1_
							.spawnEntityInWorld(entityitem)) {
						int j1 = random.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						entityitem = new EntityItem(p_149749_1_, (double) ((float) p_149749_2_ + f),
								(double) ((float) p_149749_3_ + f1), (double) ((float) p_149749_4_ + f2),
								new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) random.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) random.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) random.nextGaussian() * f3);

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem()
									.setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
					}
				}
			}

			p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
		}

		p_149749_1_.removeTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_,
			float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (world.isRemote) {
			return true;
		} else {
			IInventory iinventory = this.func_149951_m(world, x, y, z);

			if (iinventory != null) {
				player.openGui(Valkyris.getValkyris(), 0, world, x, y, z);
			}

			return true;
		}
	}

	public IInventory func_149951_m(World p_149951_1_, int p_149951_2_, int p_149951_3_, int p_149951_4_) {
		T object = (T) p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_);

		if (object == null) {
			return null;
		} else if (p_149951_1_.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_, DOWN)) {
			return null;
		} else if (func_149953_o(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_)) {
			return null;
		} else {
			return (IInventory) object;
		}
	}

	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		try {
			return tileEntityClass.getConstructor().newInstance();
		} catch (ReflectiveOperationException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_,
			int p_149709_5_) {
		if (!this.canProvidePower()) {
			return 0;
		} else {
			int i1 = ((T) p_149709_1_.getTileEntity(p_149709_2_, p_149709_3_, p_149709_4_)).numPlayersUsing;
			return MathHelper.clamp_int(i1, 0, 15);
		}
	}

	public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_,
			int p_149748_5_) {
		return p_149748_5_ == 1
				? this.isProvidingWeakPower(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_)
				: 0;
	}

	/**
	 * If this returns true, then comparators facing away from this block will use
	 * the value from getComparatorInputOverride instead of the actual redstone
	 * signal strength.
	 */
	public boolean hasComparatorInputOverride() {
		return true;
	}

	/**
	 * If hasComparatorInputOverride returns true, the return value from this is
	 * used instead of the redstone signal strength when this block inputs to a
	 * comparator.
	 */
	public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_,
			int p_149736_5_) {
		return Container
				.calcRedstoneFromInventory(this.func_149951_m(p_149736_1_, p_149736_2_, p_149736_3_, p_149736_4_));
	}

	private static boolean func_149953_o(World p_149953_0_, int p_149953_1_, int p_149953_2_, int p_149953_3_) {
		Iterator iterator = p_149953_0_.getEntitiesWithinAABB(EntityOcelot.class,
				AxisAlignedBB.getBoundingBox((double) p_149953_1_, (double) (p_149953_2_ + 1), (double) p_149953_3_,
						(double) (p_149953_1_ + 1), (double) (p_149953_2_ + 2), (double) (p_149953_3_ + 1)))
				.iterator();
		EntityOcelot entityocelot;

		do {
			if (!iterator.hasNext()) {
				return false;
			}

			Entity entity = (Entity) iterator.next();
			entityocelot = (EntityOcelot) entity;
		} while (!entityocelot.isSitting());

		return true;
	}

	public VBlockChest register() {
		GameRegistry.registerBlock(this, getUnlocalizedName().substring("tile.".length()));
		return this;
	}

	public void registerTileEntity() {
		GameRegistry.registerTileEntity(tileEntityClass,
				"valkyris." + getUnlocalizedName().substring("tile.".length()));
	}

	@SideOnly(Side.CLIENT)
	public void bindTileEntity() {
		T test = (T) createNewTileEntity(null, 0);
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, test.getRenderer());
	}

}
