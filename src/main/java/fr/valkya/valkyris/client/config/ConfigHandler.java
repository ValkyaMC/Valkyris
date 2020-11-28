package fr.valkya.valkyris.client.config;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.valkya.valkyris.References;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	public static String macro1, macro2, macro3, macro4, macro5;
	
	public static ConfigHandler INSTANCE = new ConfigHandler();
	public Configuration configFile;
	public String[] usedCategories = { Configuration.CATEGORY_GENERAL };

	private String configString(String macroname, String command) {
		return configString(macroname, command);
	}

	public void init(File file) {
		configFile = new Configuration(file);

		syncConfigs();
	}

	private void syncConfigs() {
		
		macro1 = configString("Macro 1", "/spawn");
		macro1 = configString("Macro 2", "/rtp");
		macro1 = configString("Macro 3", "/tpyes");
		macro1 = configString("Macro 4", "/tpa monamis");
		macro1 = configString("Macro 5", "/shop");


		if (configFile.hasChanged())
			configFile.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (References.MODID.equals(eventArgs.modID))
			syncConfigs();
	}

}
