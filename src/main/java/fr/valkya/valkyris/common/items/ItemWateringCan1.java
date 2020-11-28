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

public class ItemWateringCan1 extends Item {
	
	public ItemWateringCan1() {
		this.setMaxDamage(50);
		this.setNoRepair();
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		
		if(block instanceof IGrowable) {
			IGrowable iGrowable = (IGrowable) block;
			if(iGrowable.func_149851_a(world, x, y, z, world.isRemote)) {
				if(!world.isRemote) {
					if(world.rand.nextInt(100) <= 5) {
						if(iGrowable.func_149852_a(world, world.rand, x, y, z)) {
							iGrowable.func_149853_b(world, world.rand, x, y, z);
						}
					}
					is.damageItem(1, player);
				}
				return true;
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
			p_77624_3_.add(EnumChatFormatting.WHITE + "Arrosoir Niv1");
			p_77624_3_.add("Une portée de : 1x1");
			p_77624_3_.add("Durabilitée : " +  (p_77624_1_.getMaxDamage() - p_77624_1_.getItemDamage()) + " / " + p_77624_1_.getMaxDamage());
		}
		super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}
	
}