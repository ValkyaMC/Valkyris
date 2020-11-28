package fr.valkya.valkyris.client.gui.popup;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.client.gui.GuiNormalizedScreen;
import fr.valkya.valkyris.client.gui.popup.Popup.PopupAction;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class GuiPopup extends GuiNormalizedScreen implements IPopupCallback {
	
	private GuiScreen parent;
	private Popup popup;
	
	private Animation fadeInAnimation;
	private int target = 100;
	
	public GuiPopup(GuiScreen parent, Popup popup) {
		this.parent = parent;
		this.popup = popup;
		this.popup.initCallback(this);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.fadeInAnimation = new Animation(0, 0);
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		fadeInAnimation.interpolate(target, 0, target == 0 ? 4 : 2.25);

		if(fadeInAnimation.getX() <= 2 && target == 0) {
			mc.getSoundHandler().stopSounds();
			mc.currentScreen = parent;
			return;
		}
		
		if(parent != null) parent.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		
		ScaledResolution sr = res();
		
		double percentage = fadeInAnimation.getX() / 100;
		// bg
		int col1 = new Color(0, 0, 0, (int)fadeInAnimation.getX()).getRGB();
		Gui.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), col1);
		
		GL11.glPushMatrix();
		GL11.glTranslated(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, 0);		
		GL11.glScaled(percentage, percentage, 1);
		GL11.glTranslated(-sr.getScaledWidth() / 2, -sr.getScaledHeight() / 2, 0);
		// update coords
		popup.updateCoords();
		
		popup.render(p_73863_1_, p_73863_2_, p_73863_3_);
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		
		GL11.glPopMatrix();		
	}
	
	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
		popup.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}
	
	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
		popup.keyTyped(p_73869_2_, p_73869_1_);
	}
	
	@Override
	public void action(int id, Popup popup) {
		if(id == PopupAction.CLOSE) {
			close();
		}
	}
	
	public void close() {
		target = 0;
	}

}
