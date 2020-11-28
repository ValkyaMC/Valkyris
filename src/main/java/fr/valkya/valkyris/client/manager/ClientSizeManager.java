package fr.valkya.valkyris.client.manager;

import java.util.HashMap;
import java.util.Map;

import fr.valkya.valkyris.api.APIProvider;
import fr.valkya.valkyris.common.entity.EntitySize;
import net.minecraft.entity.CommonSizeManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public enum ClientSizeManager {
	INSTANCE;
	
	private final Map<Entity, EntitySize> sizes;
	
	private ClientSizeManager() {
		this.sizes = new HashMap<>();
	}
	
	public void updateEntity(EntityLivingBase entity, EntitySize size) {
		APIProvider.provideAPI().getRenderer().sendInfoNotification("size debug", entity.getCommandSenderName() + " -> " + size.name(), 1);
		CommonSizeManager.INSTANCE.setSize(entity, size);
		this.sizes.put(entity, size);
	}

	public EntitySize getSize(EntityLivingBase entity) {
		return entity == null ? EntitySize.NORMAL : sizes.computeIfAbsent(entity, e -> EntitySize.NORMAL);
	}
}
