package fr.valkya.valkyris.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public abstract class ValoStickItem extends ValoIdentifiableItem {
	
	private int cooldown;

	public ValoStickItem(String name, CreativeTabs tab, int maxStackSize, int maxDamage, int cooldown) {
		super(name, tab, maxStackSize);
		this.setMaxDamage(maxDamage);
		this.cooldown = cooldown;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack it, World w, EntityPlayer p) {
		if(!it.hasTagCompound()) {
			it.setTagCompound(new NBTTagCompound());
			it.stackTagCompound.setInteger("cooldown", 0);
		}
		
		if(it.stackTagCompound.getInteger("cooldown") == 0) {
			it.stackTagCompound.setInteger("cooldown", this.cooldown);
			it.damageItem(1, p);

			doEffect(it, w, p);
		}else if(w.isRemote) p.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Tu dois encore attendre " + it.stackTagCompound.getInteger("cooldown") / 20 + " sec(s) !"));
		
		return it;
	}
	
	public abstract void doEffect(ItemStack it, World w, EntityPlayer p);

	@Override
	public void onUpdate(ItemStack it, World w, Entity e, int itemSlot, boolean isSelected) {
		if(it.hasTagCompound()) {
			if(it.stackTagCompound.getInteger("cooldown") > 0) {
				it.stackTagCompound.setInteger("cooldown", it.stackTagCompound.getInteger("cooldown") - 1);
			}
		}
		super.onUpdate(it, w, e, itemSlot, isSelected);
	}
	
	@Override
	public boolean hasEffect(ItemStack it, int pass) {
		return (it.hasTagCompound() && it.stackTagCompound.getInteger("cooldown") == 0) || !it.hasTagCompound();
	}

}
