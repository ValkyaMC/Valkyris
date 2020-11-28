package fr.valkya.valkyris.client.gui.market.element.checkbox;

import java.awt.Color;

import fr.valkya.valkyris.client.gui.market.element.Element;
import fr.valkya.valkyris.client.utils.GuiUtils;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class Checkbox extends Element {

	private String name;
	public boolean value = true;
	
	public Checkbox(Element parent, int width, int height, String name) {
		super(parent, width, height);
		this.name = name;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		if(isHovered(mouseX, mouseY)) Gui.drawRect(x, y, x + width, y + height, 0xAA00000);
		
		Gui.drawRect(x + 2, y + 2, x + height - 2, y + height - 2, 0xAACFCFCF);
		if(value) GuiUtils.getInstance().drawCheck(x + 4, y + 4, new Color(255, 255, 255, 255));
		
		fr.drawStringWithShadow(name, x + height + 4, y + height / 2 - fr.FONT_HEIGHT / 2, -1);
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(isHovered(mouseX, mouseY) && mouseButton == 0) {
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			this.value ^= true;
		}
	}
	
}
