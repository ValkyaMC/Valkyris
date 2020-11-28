package fr.valkya.valkyris.client.gui.market.element.button.impl;

import fr.valkya.valkyris.client.gui.market.element.Element;
import fr.valkya.valkyris.client.gui.market.element.button.Button;
import fr.valkya.valkyris.client.gui.market.element.window.panel.impl.FilterPanel;
import fr.valkya.valkyris.client.gui.market.element.window.panel.impl.ListingsPanel;
import net.minecraft.client.gui.Gui;

public class FilterButton extends Button {

	private FilterPanel filter;
	private ListingsPanel panel;
	
	public FilterButton(FilterPanel parent, int width, int height, String text, ListingsPanel panel) {
		super(parent, width, height, text);
		this.filter = parent;
		this.panel = panel;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		Gui.drawRect(x, y, x + width, y + height, 0xff0ac21f); 
		if(isHovered(mouseX, mouseY)) 
			Gui.drawRect(x, y, x + width, y + height, 0xAA000000);
		fr.drawStringWithShadow(text, x + width / 2 - fr.getStringWidth(text) / 2, y + height / 2 - fr.FONT_HEIGHT / 2, -1);
	}
	
	@Override
	public void click() {
		panel.refresh(filter);
	}

}
