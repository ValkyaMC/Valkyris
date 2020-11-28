package fr.valkya.valkyris.client.gui;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.valkya.valkyris.client.gui.overrides.GuiIngameMenuValorion;
import fr.valkya.valkyris.client.gui.overrides.GuiNewMainMenu;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GuiHandler	{
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onGui(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			event.gui = new GuiNewMainMenu();
		}
		
		if (event.gui instanceof GuiIngameMenu) {
			event.gui = new GuiIngameMenuValorion();
		}
	}
}
