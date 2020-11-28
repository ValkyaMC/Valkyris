package fr.valkya.valkyris.common.manager;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.api.APIProvider;
import fr.valkya.valkyris.client.utils.TimeHelper;
import fr.valkya.valkyris.common.items.ESPHelmet;
import fr.valkya.valkyris.common.network.packets.PacketESPHelmet;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ESPManager {

	public static TimeHelper timer = new TimeHelper();
	public static boolean espEnabled;

	private static TimeHelper notifTimer = new TimeHelper();
	public static final int COOLDOWN = 60_000;

	static {
		timer.reset(COOLDOWN);
	}
	
	public static void start() {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;

		ItemStack helmet = null;
		for (ItemStack is : player.inventory.armorInventory) {
			if (is != null && is.getItem() != null && is.getItem() instanceof ESPHelmet) {
				helmet = is;
				break;
			}
		}
		if (helmet == null)
			return;

		// has helmet
		if (timer.hasReached(COOLDOWN)) {
			APIProvider.provideAPI().getRenderer().sendInfoNotification("§cNotification Personnelle", "Activation du casque.", 3);
			Valkyris.getValkyris().getProxy().getNetwork().sendToServer(new PacketESPHelmet(helmet));
			helmet.damageItem(1, player);
			espEnabled = true;
			timer.reset();
		} else {
			if(notifTimer.hasReached(6_000)) {
				APIProvider.provideAPI().getRenderer().sendInfoNotification("§cNotification Personnelle", "Le casque est en cooldown! Merci de patienter " + ((int)Math.floor(((COOLDOWN - timer.getOffset()) / 1000)) + 1) + " secondes", 6);
				notifTimer.reset();
			}
			
		}
	}

}
