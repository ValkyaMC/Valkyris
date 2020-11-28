package fr.valkya.valkyris.client.gui.market.element.button.impl;

import fr.valkya.valkyris.client.gui.market.element.Element;
import fr.valkya.valkyris.client.gui.market.element.button.Button;
import fr.valkya.valkyris.client.gui.market.element.popup.impl.ListingPopup;
import fr.valkya.valkyris.client.gui.popup.GuiPopup;
import fr.valkya.valkyris.common.market.product.Product;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ListingButton extends Button {

	private Product product;
	
	public ListingButton(Element parent, int width, int height, String text, Product product) {
		super(parent, width, height, text);
		this.product = product;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		Gui.drawRect(x, y, x + width, y + height, 0xAA000000);
		if(isHovered(mouseX, mouseY)) 
			Gui.drawRect(x, y, x + width, y + height, 0xAA000000);
		
		fr.drawStringWithShadow(product.getDisplayName(), x + width / 2 - fr.getStringWidth(product.getDisplayName()) / 2, y + height - fr.FONT_HEIGHT, -1);
	}
	
	@Override
	public void click() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiPopup(Minecraft.getMinecraft().currentScreen, new ListingPopup(product)));
	}

}
