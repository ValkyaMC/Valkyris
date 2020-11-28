package fr.valkya.valkyris.common.items;

import java.util.List;

import fr.valkya.valkyris.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ESPHelmet extends ItemArmor {

	public ESPHelmet(ArmorMaterial mat, int side) {
		super(mat, side, 0);
		this.setMaxDamage(50);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (stack.getItem() == VItems.esp_helmet) {
			return References.MODID + ":textures/models/armor/nv.png";
		}
		return super.getArmorTexture(stack, entity, slot, type);
	}
		
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		if (p_77624_1_.getItemDamage() >= 0) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "ESP Helmet");
			p_77624_3_.add(EnumChatFormatting.GREEN + "Durabilitée : "
					+ (p_77624_1_.getMaxDamage() - p_77624_1_.getItemDamage()) + " / " + p_77624_1_.getMaxDamage());
			super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
		}
		if (p_77624_1_.getItemDamage() >= 30) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "ESP Helmet");
			p_77624_3_.add(EnumChatFormatting.GOLD + "Durabilitée : "
					+ (p_77624_1_.getMaxDamage() - p_77624_1_.getItemDamage()) + " / " + p_77624_1_.getMaxDamage());
			super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
		}

		if (p_77624_1_.getItemDamage() >= 45) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "ESP Helmet");
			p_77624_3_.add(EnumChatFormatting.RED + "Durabilitée : "
					+ (p_77624_1_.getMaxDamage() - p_77624_1_.getItemDamage()) + " / " + p_77624_1_.getMaxDamage());
			super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
		}

		if (p_77624_1_.getItemDamage() >= 49) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "ESP Helmet");
			p_77624_3_.add(EnumChatFormatting.RED + "Durabilitée : "
					+ (p_77624_1_.getMaxDamage() - p_77624_1_.getItemDamage()) + " / " + p_77624_1_.getMaxDamage());
			p_77624_3_.add(EnumChatFormatting.RED + "Recharge ton casque !");
			super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
		}
	}

}
