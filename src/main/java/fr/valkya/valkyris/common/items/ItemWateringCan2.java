package fr.valkya.valkyris.common.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemWateringCan2 extends Item {

	public ItemWateringCan2() {
		this.setMaxDamage(2000);
		this.setNoRepair();
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block1 = world.getBlock(x, y, z);
		Block block2 = world.getBlock(x + 1, y, z);
		Block block3 = world.getBlock(x - 1, y, z);
		Block block4 = world.getBlock(x, y, z + 1);
		Block block5 = world.getBlock(x, y, z - 1);
		Block block6 = world.getBlock(x + 1, y, z + 1);
		Block block7 = world.getBlock(x - 1, y, z - 1);
		Block block8 = world.getBlock(x - 1, y, z + 1);
		Block block9 = world.getBlock(x + 1, y, z - 1);

		if (block1 instanceof IGrowable) {
			IGrowable iGrowable = (IGrowable) block1;
			if (iGrowable.func_149851_a(world, x, y, z, world.isRemote)) {
				if (!world.isRemote) {
					if (world.rand.nextInt(100) <= 5) {
						if (iGrowable.func_149852_a(world, world.rand, x, y, z)) {
							iGrowable.func_149853_b(world, world.rand, x, y, z);
						}
						is.damageItem(1, player);
					}
				}
			}
		}

		if (block2 instanceof IGrowable) {
			IGrowable iGrowable = (IGrowable) block2;
			if (iGrowable.func_149851_a(world, x + 1, y, z, world.isRemote)) {
				if (!world.isRemote) {
					if (world.rand.nextInt(100) <= 5) {
						if (iGrowable.func_149852_a(world, world.rand, x + 1, y, z)) {
							iGrowable.func_149853_b(world, world.rand, x + 1, y, z);
						}
						is.damageItem(1, player);
					}
				}
			}
		}

		if (block3 instanceof IGrowable) {
			IGrowable iGrowable = (IGrowable) block3;
			if (iGrowable.func_149851_a(world, x - 1, y, z, world.isRemote)) {
				if (!world.isRemote) {
					if (world.rand.nextInt(100) <= 5) {
						if (iGrowable.func_149852_a(world, world.rand, x - 1, y, z)) {
							iGrowable.func_149853_b(world, world.rand, x - 1, y, z);
						}
						is.damageItem(1, player);
					}
				}
			}
		}

		if (block4 instanceof IGrowable) {
			IGrowable iGrowable = (IGrowable) block4;
			if (iGrowable.func_149851_a(world, x, y, z + 1, world.isRemote)) {
				if (!world.isRemote) {
					if (world.rand.nextInt(100) <= 5) {
						if (iGrowable.func_149852_a(world, world.rand, x, y, z + 1)) {
							iGrowable.func_149853_b(world, world.rand, x, y, z + 1);
						}
						is.damageItem(1, player);
					}
				}
			}
		}

		if (block5 instanceof IGrowable) {
			IGrowable iGrowable = (IGrowable) block5;
			if (iGrowable.func_149851_a(world, x, y, z - 1, world.isRemote)) {
				if (!world.isRemote) {
					if (world.rand.nextInt(100) <= 5) {
						if (iGrowable.func_149852_a(world, world.rand, x, y, z - 1)) {
							iGrowable.func_149853_b(world, world.rand, x, y, z - 1);
						}
						is.damageItem(1, player);
					}
				}
			}
		}

		if (block6 instanceof IGrowable) {
			IGrowable iGrowable = (IGrowable) block6;
			if (iGrowable.func_149851_a(world, x + 1, y, z + 1, world.isRemote)) {
				if (!world.isRemote) {
					if (world.rand.nextInt(100) <= 5) {
						if (iGrowable.func_149852_a(world, world.rand, x + 1, y, z + 1)) {
							iGrowable.func_149853_b(world, world.rand, x + 1, y, z + 1);
						}
						is.damageItem(1, player);
					}
				}
			}
		}

		if (block7 instanceof IGrowable) {
			IGrowable iGrowable = (IGrowable) block7;
			if (iGrowable.func_149851_a(world, x - 1, y, z - 1, world.isRemote)) {
				if (!world.isRemote) {
					if (world.rand.nextInt(100) <= 5) {
						if (iGrowable.func_149852_a(world, world.rand, x - 1, y, z - 1)) {
							iGrowable.func_149853_b(world, world.rand, x - 1, y, z - 1);
						}
						is.damageItem(1, player);
					}
				}
			}
		}

		if (block8 instanceof IGrowable) {
			IGrowable iGrowable = (IGrowable) block8;
			if (iGrowable.func_149851_a(world, x - 1, y, z + 1, world.isRemote)) {
				if (!world.isRemote) {
					if (world.rand.nextInt(100) <= 5) {
						if (iGrowable.func_149852_a(world, world.rand, x - 1, y, z + 1)) {
							iGrowable.func_149853_b(world, world.rand, x - 1, y, z + 1);
						}
						is.damageItem(1, player);
					}
				}
			}
		}

		if (block9 instanceof IGrowable) {
			IGrowable iGrowable = (IGrowable) block9;
			if (iGrowable.func_149851_a(world, x + 1, y, z - 1, world.isRemote)) {
				if (!world.isRemote) {
					if (world.rand.nextInt(100) <= 5) {
						if (iGrowable.func_149852_a(world, world.rand, x + 1, y, z - 1)) {
							iGrowable.func_149853_b(world, world.rand, x + 1, y, z - 1);
						}
						is.damageItem(1, player);
					}
				}
			}
		}

		return super.onItemUse(is, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

	@Override
	public boolean isRepairable() {
		return false;
	}

	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.GRAY + "Shift pour plus d'informations...");
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "Arrosoir Niv2");
			p_77624_3_.add("Une portée de : 3x3");
			p_77624_3_.add("Durabilitée : " +  (p_77624_1_.getMaxDamage() - p_77624_1_.getItemDamage()) + " / " + p_77624_1_.getMaxDamage());
		}
		super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}

}
