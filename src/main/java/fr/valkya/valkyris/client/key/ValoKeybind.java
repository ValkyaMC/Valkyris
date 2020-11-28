package fr.valkya.valkyris.client.key;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class ValoKeybind {
	
	private KeyCategory category;
	private String name;
	private int key;
	private Runnable callback;
	
	private KeyBinding wrappedKeyBinding;
	
	public ValoKeybind(KeyCategory category, String name, int default_key, Runnable callback) {
		this.category = category;
		this.name = name;
		this.key = default_key;
		this.callback = callback;
	}
	
	public enum KeyCategory {
		GENERAL;
	}
	
	public void register() {
		ClientRegistry.registerKeyBinding(wrappedKeyBinding = new KeyBinding("valorion.keybind." + name.toLowerCase(), key, "valorion.keybinds." + category.name().toLowerCase()));
	}
	
	public boolean isPressed() {
		return wrappedKeyBinding.isPressed();
	}
	
	public void execute() {
		callback.run();
	}

}
