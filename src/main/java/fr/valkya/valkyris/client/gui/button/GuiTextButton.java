package fr.valkya.valkyris.client.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;

public class GuiTextButton extends GuiButton {
	
	protected int color, color_hover;	

	public GuiTextButton(int id, int x, int y, int w, int h, int color, int color_hover, String text) {
		super(id, x, y, w, h, text);
		this.color = color;
		this.color_hover = color_hover;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(this.visible) {
			this.field_146123_n = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
			
			Gui.drawRect(xPosition, yPosition, xPosition + width, yPosition + height, 0xFF000000);
			mc.fontRenderer.drawStringWithShadow(this.displayString, xPosition + width / 2 - mc.fontRenderer.getStringWidth(displayString) / 2, yPosition + height / 2 - mc.fontRenderer.FONT_HEIGHT / 2, this.field_146123_n ? color_hover : color);
		}
	}
}
