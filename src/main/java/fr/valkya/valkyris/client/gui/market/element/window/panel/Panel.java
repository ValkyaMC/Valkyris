package fr.valkya.valkyris.client.gui.market.element.window.panel;

import fr.valkya.valkyris.client.gui.market.element.Element;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.gui.Gui;

public class Panel extends Element {

	private Animation scrollAnimation;
	private int scrollTarget;
	private int maxScrolling;
	
	public Panel(Element parent, int width, int height) {
		super(parent, width, height);
	}
	
	@Override
	public void updateCoords() {
		
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		Gui.drawRect(x, y, x + width, y + height, 0x80000000);
		super.render(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(isHovered(mouseX, mouseY)) {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}

}
