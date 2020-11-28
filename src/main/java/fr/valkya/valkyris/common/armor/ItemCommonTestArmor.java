package fr.valkya.valkyris.common.armor;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCommonTestArmor extends ItemArmor {

	public ItemCommonTestArmor() {
		super(ArmorMaterial.GOLD, 0, 0);
		setUnlocalizedName("owoMask");
		setTextureName(References.MODID + ":owo");
	}
	
	public ItemCommonTestArmor register() {
		GameRegistry.registerItem(this, getUnlocalizedName().substring("tamer".length()));
		return this;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return References.MODID + ":textures/models/armor/naming_conventions_lmao.png";
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		ItemStack helmet = player.getEquipmentInSlot(4);

		if (helmet != null && helmet.getItem() == this) {
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 20 * 15, 0));
		}
	}
}
