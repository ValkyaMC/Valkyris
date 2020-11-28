package fr.valkya.valkyris.client.armor;

import fr.valkya.valkyris.client.models.armor.ModelCesiumArmor;
import fr.valkya.valkyris.common.armor.ItemCommonCesiumArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class ItemClientCesiumArmor extends ItemCommonCesiumArmor {
	
	public ItemClientCesiumArmor(ArmorMaterial mat, int type, String name, String texture) {
		super(mat, type, name, texture);
	}
	
	public ModelBiped model;
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
		if (model == null) {
			if (armorType == 0)
				model = new ModelCesiumArmor(0.9F, true, false, false, false);
			else if (armorType == 1)
				model = new ModelCesiumArmor(1F, false, true, false, false);
			else if (armorType == 2)
				model = new ModelCesiumArmor(1F, false, false, true, false);
			else
				model = new ModelCesiumArmor(1F, false, false, false, true);

			this.model.bipedHead.showModel = (armorType == 0);
			this.model.bipedHeadwear.showModel = (armorType == 0);
			this.model.bipedBody.showModel = ((armorType == 1) || (armorType == 2));
			this.model.bipedLeftArm.showModel = (armorType == 1);
			this.model.bipedRightArm.showModel = (armorType == 1);
			this.model.bipedLeftLeg.showModel = (armorType == 2 || armorType == 3);
			this.model.bipedRightLeg.showModel = (armorType == 2 || armorType == 3);
		}

		if (entityLiving == null)
			return model;

		this.model.isSneak = entityLiving.isSneaking();
		this.model.isRiding = entityLiving.isRiding();
		this.model.isChild = entityLiving.isChild();
		this.model.aimedBow = false;
		this.model.heldItemRight = (entityLiving.getHeldItem() != null ? 1 : 0);

		if ((entityLiving instanceof EntityPlayer)) {
			if (((EntityPlayer) entityLiving).getItemInUseDuration() > 0) {
				EnumAction enumaction = ((EntityPlayer) entityLiving).getItemInUse().getItemUseAction();
				if (enumaction == EnumAction.block) {
					this.model.heldItemRight = 3;
				} else if (enumaction == EnumAction.bow) {
					this.model.aimedBow = true;
				}
			}
		}
		return model;
	}
	
	

}

