package fr.valkya.valkyris.common.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import fr.valkya.valkyris.common.block.VBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EmerauldHammer extends ItemPickaxe {

	protected EmerauldHammer(ToolMaterial p_i45347_1_) {
		super(VItems.emerauldHammerMat);
		this.canRepair = false;
		this.maxStackSize = 1;
	}

	private static final Material[] MATS = {Material.rock, Material.iron, Material.ice, Material.glass, Material.piston, Material.anvil, Material.snow, Material.craftedSnow, Material.clay };
	public static Block[] mineral = { Blocks.iron_ore, Blocks.gold_ore, VBlocks.cesiumOre, VBlocks.randomOre, VBlocks.zirconiumOre, Blocks.coal_ore, Blocks.redstone_ore, Blocks.diamond_ore, Blocks.emerald_ore, Blocks.quartz_ore, Blocks.iron_ore, Blocks.lapis_ore, Blocks.gold_ore, Blocks.lit_redstone_ore };


	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
		MovingObjectPosition raycast = raytraceFromEntity(player.worldObj, player, true, 10.0D);
		if(raycast != null) {
			breakHammer(player, itemstack, x, y, z, x, y, z, raycast.sideHit);
			itemstack.damageItem(1, player);
		}
		return false;
	}

	private MovingObjectPosition raytraceFromEntity(World world, EntityPlayer player, boolean par3, double range) {
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * f;
		if (!world.isRemote && player instanceof EntityPlayer) d1 += ((EntityPlayer)player).eyeHeight; 
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;
		Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
		return world.rayTraceBlocks(vec3, vec31, par3);
	}

	private void breakHammer(EntityPlayer player, ItemStack itemstack, int x, int y, int z, int x2, int y2, int z2,	int sideHit) {
		World world = player.worldObj;		
		ForgeDirection direction = ForgeDirection.getOrientation(sideHit);
		int range = Math.max(0, 1);
		int rangeY = Math.max(1, range);
		   
		boolean doX = (direction.offsetX == 0);
		boolean doY = (direction.offsetY == 0);
		boolean doZ = (direction.offsetZ == 0);
		
		if(world.isAirBlock(x, y, z)) return;
		
		removeBlocks(player, itemstack, world, x, y, z, doX ? -range : 0, doY ? -1 : 0, doZ ? -range : 0, doX ? (range + 1) : 1, doY ? (rangeY * 2) : 1, doZ ? (range + 1) : 1, null, MATS, false, 0, true, false);
		
	}

	private void removeBlocks(EntityPlayer player, ItemStack itemstack, World world, int x, int y, int z, int xs, int ys, int zs, int xe, int ye, int ze, Block block, Material[] materialsListing, boolean smelt, int fortune, boolean dispose, boolean obsidian) {
		float blockHardness = (block == null) ? 1.0F : block.getBlockHardness(world, x, y, z);
		for (int x1 = xs; x1 < xe; x1++) {
			for (int y1 = ys; y1 < ye; y1++) {
				for (int z1 = zs; z1 < ze; z1++) {
					if (world.getBlock(x1 + x, y1 + y, z1 + z).isToolEffective("pickaxe", 3) || world.getBlock(x1 + x, y1 + y, z1 + z) instanceof BlockRedstoneOre || (obsidian && world.getBlock(x1 + x, y1 + y, z1 + z) instanceof BlockObsidian)) {
						removeBlockWithDrops(player, itemstack, world, x1 + x, y1 + y, z1 + z, x, y, z, world.getBlock(x1 + x, y1 + y, z1 + z), materialsListing, blockHardness);
					}
					
					if (world.getBlock(x1 + x, y1 + y, z1 + z) == VBlocks.cesiumOre) {
						removeBlockWithDrops(player, new ItemStack(VItems.cesiumIngot), world, x1 + x, y1 + y, z1 + z, x, y, z, world.getBlock(x1 + x, y1 + y, z1 + z), materialsListing, blockHardness);
					}
					
					if (world.getBlock(x1 + x, y1 + y, z1 + z) == VBlocks.zirconiumOre) {
						removeBlockWithDrops(player, new ItemStack(VItems.zirconiumIngot), world, x1 + x, y1 + y, z1 + z, x, y, z, world.getBlock(x1 + x, y1 + y, z1 + z), materialsListing, blockHardness);
					}
					
					if (world.getBlock(x1 + x, y1 + y, z1 + z) == Blocks.iron_ore) {
						removeBlockWithDrops(player, new ItemStack(Items.iron_ingot), world, x1 + x, y1 + y, z1 + z, x, y, z, world.getBlock(x1 + x, y1 + y, z1 + z), materialsListing, blockHardness);
					}
					
					if (world.getBlock(x1 + x, y1 + y, z1 + z) == Blocks.gold_ore) {
						removeBlockWithDrops(player, new ItemStack(Items.gold_ingot), world, x1 + x, y1 + y, z1 + z, x, y, z, world.getBlock(x1 + x, y1 + y, z1 + z), materialsListing, blockHardness);
					}
				} 
			} 
		} 
	}
	
	
	public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, float blockHardness) {
		
		if (!world.blockExists(x, y, z)) {
			return;
		}
		Block blk = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		
		if (block != null && blk != block) {
			return;
		}
		if (!world.isRemote && blk != null && !blk.isAir(world, x, y, z) && blk.getPlayerRelativeBlockHardness(player, world, x, y, z) > 0.0F) {
			if (!blk.canHarvestBlock(player, meta) || !isRightMaterial(blk, materialsListing) || blk == Blocks.obsidian) return;
			if (!player.capabilities.isCreativeMode) {
				int localMeta = world.getBlockMetadata(x, y, z);
				blk.onBlockHarvested(world, x, y, z, localMeta, player);
			    if (blk.removedByPlayer(world, player, x, y, z, true)) {
			    	blk.onBlockDestroyedByPlayer(world, x, y, z, localMeta);
			    }
			    blk.dropBlockAsItem(world, x, y, z, localMeta, 1);
				} else {
					int localMeta = world.getBlockMetadata(x, y, z);
					blk.dropBlockAsItem(world, x, y, z, localMeta, 1);
				} 
				int xpDrop = blk.getExpDrop(world, blk.getDamageValue(world, x, y, z), 1);
				EntityXPOrb xp = new EntityXPOrb(world, x, y, z, xpDrop);
				if (xpDrop > 0)
					world.spawnEntityInWorld(xp); 
				} else {
					world.setBlockToAir(x, y, z);
				}  
			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(blk) + (meta << 12));
			world.setBlockToAir(x, y, z);
	}
	
	public static boolean isRightMaterial(Block block, Material[] materialsListing) {
		Material material = block.getMaterial();
	    for (Material mat : materialsListing) {
	       if (material == mat) return true; 
	    } 
	    return false;
	}
	
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.GRAY + "Shift pour plus d'informations...");
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "Emerald Hammer");
			p_77624_3_.add("Fonctionne en : 3x3");
			p_77624_3_.add("Plus rapide et durable que IronHammer");
			p_77624_3_.add("Durabilitée : " +  (p_77624_1_.getMaxDamage() - p_77624_1_.getItemDamage()) + " / " + p_77624_1_.getMaxDamage());
		}
		super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}
}