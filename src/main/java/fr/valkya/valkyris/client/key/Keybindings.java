package fr.valkya.valkyris.client.key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import fr.valkya.valkyris.api.APIProvider;
import fr.valkya.valkyris.client.config.ClientConfig;
import fr.valkya.valkyris.client.config.ConfigHandler;
import fr.valkya.valkyris.client.gui.popup.GuiPopup;
import fr.valkya.valkyris.client.gui.popup.Popup;
import fr.valkya.valkyris.client.key.ValoKeybind.KeyCategory;
import fr.valkya.valkyris.common.manager.ESPManager;
import net.minecraft.client.Minecraft;

public class Keybindings {

	
	public static Boolean sco = true;
	public static Boolean armr = true;

	public static final Keybindings INSTANCE = new Keybindings();

	private List<ValoKeybind> keybinds;

	private Keybindings() {
		this.keybinds = new ArrayList<>();

		FMLCommonHandler.instance().bus().register(this);
	}

	public void register() {
//		this.keybinds.add(new ValoKeybind(KeyCategory.GENERAL, "market", Keyboard.KEY_P, () -> {
//			Minecraft.getMinecraft().displayGuiScreen( new GuiPopup(null, new Popup("Market", Arrays.asList("Le market n'est pas encore disponible."))) );
//			Minecraft.getMinecraft().displayGuiScreen(((ClientProxy)Valkyris.getValkyris().getProxy()).getMarketGui());
//		}));
		this.keybinds.add(new ValoKeybind(KeyCategory.GENERAL, "esp_helmet", Keyboard.KEY_X, () -> ESPManager.start()));
			
		this.keybinds.add(new ValoKeybind(KeyCategory.GENERAL, "coordonate", Keyboard.KEY_M, () -> {
			if (sco) {
				sco = false;
				ClientConfig.hidef3 = true;
				ClientConfig.save();
				APIProvider.provideAPI().getRenderer().sendInfoNotification("§cNotification Personnelle", "§eLes coordonnées du F3 sont maintenant cachés !", 3);
			} 
			else if (sco == false) {
				sco = true;
				ClientConfig.hidef3 = false;				
				ClientConfig.save();
				APIProvider.provideAPI().getRenderer().sendInfoNotification("§cNotification Personnelle", "§eLes coordonnées du F3 sont maintenant visibles !", 3);
			}
		}));
				
		this.keybinds.add(new ValoKeybind(KeyCategory.GENERAL, "macro_1", Keyboard.KEY_NUMPAD1, () -> {
			if (ClientConfig.activem1) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage(ClientConfig.macro1);
			} else {return;}
		}));

		this.keybinds.add(new ValoKeybind(KeyCategory.GENERAL, "macro_2", Keyboard.KEY_NUMPAD2, () -> {
			if (ClientConfig.activem2) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage(ClientConfig.macro2);
			} else {return;}
		}));
		
		this.keybinds.add(new ValoKeybind(KeyCategory.GENERAL, "macro_3", Keyboard.KEY_NUMPAD3, () -> {
			if (ClientConfig.activem3) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage(ClientConfig.macro3);
			} else {return;}
		}));
		
		this.keybinds.add(new ValoKeybind(KeyCategory.GENERAL, "macro_4", Keyboard.KEY_NUMPAD4, () -> {
			if (ClientConfig.activem4) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage(ClientConfig.macro4);
			} else {return;}
		}));
		
		this.keybinds.add(new ValoKeybind(KeyCategory.GENERAL, "macro_5", Keyboard.KEY_NUMPAD5, () -> {
			if (ClientConfig.activem5) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage(ClientConfig.macro5);
			} else {return;}
		}));
//		this.keybinds.add(new ValoKeybind(KeyCategory.GENERAL, "TEST", Keyboard.KEY_P, () -> Minecraft.getMinecraft().func_152344_a(() -> {
//			Minecraft.getMinecraft().displayGuiScreen(new GuiPopup(Minecraft.getMinecraft().currentScreen, new Popup("Salut", Arrays.asList("awé", "je suce bien et toi?"))));
//		})));

//		this.keybinds.add(new ValoKeybind(KeyCategory.GENERAL, "reset", Keyboard.KEY_P, () -> {
//			((ItemClientCesiumArmor)VItems.valoArmors.cesiumBoots).model = null;
//			((ItemClientCesiumArmor)VItems.valoArmors.cesiumHelmet).model = null;
//			((ItemClientCesiumArmor)VItems.valoArmors.cesiumChestplate).model = null;
//			((ItemClientCesiumArmor)VItems.valoArmors.cesiumLeggins).model = null;
//			
//			((ItemClientZircoArmor)VItems.valoArmors.zircoBoots).model = null;
//			((ItemClientZircoArmor)VItems.valoArmors.zircoHelmet).model = null;
//			((ItemClientZircoArmor)VItems.valoArmors.zircoChestplate).model = null;
//			((ItemClientZircoArmor)VItems.valoArmors.zircoLeggins).model = null;
//		}));

		this.keybinds.forEach(ValoKeybind::register);
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent e) {
		this.keybinds.stream().filter(ValoKeybind::isPressed).forEach(ValoKeybind::execute);
	}

}
