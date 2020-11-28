package fr.valkya.valkyris.common.armor;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCommonAquaArmor extends ItemArmor{
	
	public ItemCommonAquaArmor(ArmorMaterial mat, int type, String name, String texture) {
		super(mat, 0, type);
		this.setUnlocalizedName(name);
		this.setTextureName(References.MODID + texture);
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().armorCreativeTabs);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {		
		return null;
	}

	public ItemCommonAquaArmor register() {
		GameRegistry.registerItem(this, getUnlocalizedName().substring("tamer".length()));
		return this;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return References.MODID + ":textures/models/armor/dive.png";
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
    {
		ItemStack boots = player.getEquipmentInSlot(1);
        ItemStack leggings = player.getEquipmentInSlot(2);
        ItemStack chestPlate = player.getEquipmentInSlot(3);
        ItemStack helmet = player.getEquipmentInSlot(4);
        
		if(boots != null && boots.getItem() == VItems.valoArmors.travelBoots && leggings != null && leggings.getItem() == VItems.valoArmors.travelLeggings && chestPlate != null && chestPlate.getItem() == VItems.valoArmors.travelChestplate && helmet != null && helmet.getItem() == VItems.valoArmors.travelHelmet)
	    {
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 500, 0));
			player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 20, 0));
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 20, 1));
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20, 1));
	    }
    }
}
