package fr.valkya.valkyris.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;

public class GuiNormalizedScreen extends GuiScreen implements GuiYesNoCallback {

	private int lastGuiScale = -69;
	
	@Override
	public void initGui() {
		lastGuiScale = mc.gameSettings.guiScale;
		if(mc.gameSettings.guiScale != 2) {
			mc.gameSettings.guiScale = 2;
			
			ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			this.width = sr.getScaledWidth();
	        this.height = sr.getScaledHeight();
		}
		
		super.initGui();
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		
		if(lastGuiScale != -69) {
			mc.gameSettings.guiScale = lastGuiScale;
		}
	}
	
	protected ScaledResolution res() {
		return new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
	}
	
}
