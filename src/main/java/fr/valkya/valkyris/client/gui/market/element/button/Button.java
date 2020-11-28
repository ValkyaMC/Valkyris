package fr.valkya.valkyris.client.gui.market.element.button;

import fr.valkya.valkyris.client.gui.market.element.Element;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public abstract class Button extends Element {

	protected String text;
	
	public abstract void click();
	
	public Button(Element parent, int width, int height, String text) {
		super(parent, width, height);
		this.text = text;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		Gui.drawRect(x, y, x + width, y + height, 0xAA000000);
		if(isHovered(mouseX, mouseY)) 
			Gui.drawRect(x, y, x + width, y + height, 0xAA000000);
		fr.drawStringWithShadow(text, x + width / 2 - fr.getStringWidth(text) / 2, y + height / 2 - fr.FONT_HEIGHT / 2, -1);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(isHovered(mouseX, mouseY) && mouseButton == 0) {
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			click();
		}
	}

	@Override public void mouseReleased(int mouseX, int mouseY, int mouseButton) {}
	@Override public void keyTyped(int keyCode, char character) {}
	
	public String getText() {
		return this.text;
	}

}
