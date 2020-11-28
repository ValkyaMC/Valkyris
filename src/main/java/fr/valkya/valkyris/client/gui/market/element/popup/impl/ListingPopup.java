package fr.valkya.valkyris.client.gui.market.element.popup.impl;

import java.util.Arrays;

import fr.valkya.valkyris.client.gui.popup.Popup;
import fr.valkya.valkyris.common.market.product.Product;

public class ListingPopup extends Popup {

	private Product product;
	
	public ListingPopup(Product product) {
		super("Market", Arrays.asList());
		this.product = product;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		super.render(mouseX, mouseY, partialTicks);
		
	}
	
	@Override
	public int getHeight() {
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		return super.getWidth();
	}
}
