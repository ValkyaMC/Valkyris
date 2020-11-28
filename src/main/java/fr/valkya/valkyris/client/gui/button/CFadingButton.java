package fr.valkya.valkyris.client.gui.button;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.client.utils.RenderHelper;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;

public class CFadingButton extends GuiButton implements IAnimatedButton {

	private Animation shadeAnim;
	
	public CFadingButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}
	
	public CFadingButton(int buttonId, int x, int y, String buttonText) {
		this(buttonId, x, y, 200, 20, buttonText);
	}	
	
	public boolean animationStarted() {
		return shadeAnim != null;
	}
	
	private boolean skip;
	
	@Override
	public void skipAnimation() {
		skip = true;
	}
	
	public void performAnimation() {
		shadeAnim = new Animation(skip ? 400 : 0, 0);
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(shadeAnim == null) return;
		
		double percentage = shadeAnim.getX() / 500;
		
		Color c = new Color(0, 0, 0, (int) (120 * percentage));
		Color cText = new Color(255, 255, 255, (int) Math.min(255, Math.max(25, (255 * percentage))));
		
		if(!enabled) {
			Gui.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, c.darker().darker().getRGB());
		}else {
			field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseY <= this.yPosition + this.height && mouseX <= this.xPosition + width;
			
			Gui.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, c.getRGB());
			RenderHelper.scissor(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height);
			
			GL11.glPushMatrix();
			{
				GL11.glEnable(GL11.GL_SCISSOR_TEST);
				drawCenteredString(mc.fontRenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + this.height / 2 - 4, cText.getRGB());
				GL11.glDisable(GL11.GL_SCISSOR_TEST);
			}
			GL11.glPopMatrix();
			
			double percentageHoveranim = Math.max(0, shadeAnim.getX() - 400) / 200;
			Gui.drawRect((int)(this.xPosition + this.width / 2 - (this.width / 2 * percentageHoveranim)), this.yPosition + this.height - 1, (int)(this.xPosition + this.width / 2 + (this.width / 2 * percentageHoveranim)), this.yPosition + this.height, 0xFFFFB011);
		}
		
		shadeAnim.interpolate(400 + (field_146123_n ? 200 : 0), 0, 3);
	}
	
	@Override
	public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
		if(!animationStarted()) return false;
		return super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_);
	}

}
