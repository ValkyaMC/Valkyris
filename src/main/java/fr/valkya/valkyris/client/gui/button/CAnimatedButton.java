package fr.valkya.valkyris.client.gui.button;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.client.utils.RenderHelper;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;

public class CAnimatedButton extends GuiButton implements IAnimatedButton {

	private Animation translateAnimation;
	private boolean right;
	
	public CAnimatedButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, boolean right) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.right = right;
	}
	
	public CAnimatedButton(int buttonId, int x, int y, String buttonText, boolean right) {
		this(buttonId, x, y, 200, 200, buttonText, right);
	}	
	
	public boolean animationStarted() {
		return translateAnimation != null;
	}
	
	private boolean skip;
	
	@Override
	public void skipAnimation() {
		skip = true;
	}
	
	public void performAnimation() {
		translateAnimation = new Animation(skip ? 400 : 0, 0);
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(translateAnimation == null) return;
		
		double percentage = Math.min(translateAnimation.getX(), 400) / 400;
		
		if(!enabled) {
			Gui.drawRect((int) (this.xPosition + (right ? 0 : (this.width - (this.width * percentage)))), this.yPosition, (int) (this.xPosition + (this.width * percentage)), this.yPosition + this.height, 0xDD000000);
		} else {
			field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseY <= this.yPosition + this.height && mouseX <= this.xPosition + width;
			
			if(right) {
				Gui.drawRect(this.xPosition, this.yPosition, (int) (this.xPosition + (this.width * percentage)), this.yPosition + this.height, 0x8A000000);
				RenderHelper.scissor(this.xPosition, this.yPosition, (int) (this.xPosition + (this.width * percentage)), this.yPosition + this.height);
			}else {
				Gui.drawRect((int) (this.xPosition + this.width - (this.width * percentage)), this.yPosition, (int) (this.xPosition + this.width), this.yPosition + this.height, 0x8A000000);
				RenderHelper.scissor((int) (this.xPosition + this.width - (this.width * percentage)), this.yPosition, (int) (this.xPosition + this.width), this.yPosition + this.height);
			}
			
			GL11.glPushMatrix();
			{
				GL11.glEnable(GL11.GL_SCISSOR_TEST);
				drawCenteredString(mc.fontRenderer, this.displayString, (int) (this.xPosition + (right ? (this.width * percentage / 2) : (this.width - (this.width * percentage / 2)))), this.yPosition + this.height / 2 - 4, -1);
				GL11.glDisable(GL11.GL_SCISSOR_TEST);
			}
			GL11.glPopMatrix();
			
			double percentageHoveranim = Math.max(0, translateAnimation.getX() - 400) / 200;
			Gui.drawRect((int)(this.xPosition + this.width / 2 - (this.width / 2 * percentageHoveranim)), this.yPosition + this.height - 1, (int)(this.xPosition + this.width / 2 + (this.width / 2 * percentageHoveranim)), this.yPosition + this.height, 0xFFFFB011);
		}
		
		translateAnimation.interpolate(400 + (field_146123_n ? 200 : 0), 0, 3);
	}
	
	@Override
	public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
		if(!animationStarted()) return false;
		return super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_);
	}

}
