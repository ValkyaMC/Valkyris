package fr.valkya.valkyris.client.gui.button;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.client.utils.RenderHelper;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;

public class CFatButton extends GuiButton implements IAnimatedButton {

	private Animation translateAnimation;
	private boolean right;
	
	public CFatButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, boolean right) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.right = right;
	}
	
	public CFatButton(int buttonId, int x, int y, String buttonText, boolean right) {
		this(buttonId, x, y, 200, 20, buttonText, right);
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
			
			double percentageHoveranim = Math.max(0, translateAnimation.getX() - 400) / 200;
			Gui.drawRect((int)(this.xPosition + 2), this.yPosition + 2, (int)(this.xPosition + 2 + ((this.width - 4) * percentageHoveranim)), this.yPosition + this.height - 2, 0xFFFFB011);
			
			GL11.glPushMatrix();
			{
				GL11.glEnable(GL11.GL_SCISSOR_TEST);
				GL11.glScaled(1.75, 1.75, 1);
				mc.fontRenderer.drawStringWithShadow(this.displayString, (int) (this.xPosition / 1.75 + this.width / (1.75 * 2) - mc.fontRenderer.getStringWidth(displayString) / 2 - Math.round(5 * percentageHoveranim)), (int) (this.yPosition / 1.75 + height / (1.75 * 2) - mc.fontRenderer.FONT_HEIGHT / 2), -1);
				RenderHelper.scissor((int)(this.xPosition + 2), this.yPosition + 2, (int)(this.xPosition + 2 + ((this.width - 4) * percentageHoveranim)), this.yPosition + this.height - 2);
			    mc.fontRenderer.drawStringWithShadow(">>", (int) (this.xPosition / 1.75 + this.width / (1.75 * 2) - Math.round(5 * percentageHoveranim)) + mc.fontRenderer.getStringWidth(this.displayString + "  ") / 2 - 1, (int) (this.yPosition / 1.75 + height / (1.75 * 2) - mc.fontRenderer.FONT_HEIGHT / 2), -1);
				GL11.glDisable(GL11.GL_SCISSOR_TEST);
			}
			GL11.glPopMatrix();
		}
		
		translateAnimation.interpolate(400 + (field_146123_n ? 200 : 0), 0, 3);
	}

	@Override
	public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
		if(!animationStarted()) return false;
		return super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_);
	}
}
