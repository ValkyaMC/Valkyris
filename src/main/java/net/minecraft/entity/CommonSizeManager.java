package net.minecraft.entity;

import fr.valkya.valkyris.common.entity.EntitySize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public enum CommonSizeManager {
	INSTANCE;
	
	private static final float defaultWidth = 0.6F;
	private static final float defaultHeight = 1.8F;
	private static final float eyeHeightMultiplier = 0.85F;
	
	public void setSize(EntityLivingBase entity, EntitySize size) {
		//											 wtf \/
		if(size == EntitySize.SMALL) entity.setSize(defaultHeight * size.getSize(), defaultHeight * size.getSize());
		else entity.setSize(defaultWidth * size.getSize(), defaultHeight * size.getSize());
		
		switch (size) {
			case GIANT:
				entity.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20, 3));
				entity.addPotionEffect(new PotionEffect(Potion.jump.id, 20, 2));
				break;
			case SMALL:
				entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 2));
				break;
			default:
				break;
		}
		
		entity.getEntityData().setFloat("smallWidth", entity.width);
		entity.getEntityData().setFloat("smallHeight", entity.height);

		if ((entity instanceof EntityPlayer)) {
			EntityPlayer player = (EntityPlayer) entity;
			if (size != EntitySize.NORMAL) {
				player.eyeHeight = (player.height * eyeHeightMultiplier);
			} else {
				player.eyeHeight = player.getDefaultEyeHeight();
			}
		}
	}

}
