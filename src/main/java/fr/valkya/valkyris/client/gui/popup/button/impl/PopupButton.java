package fr.valkya.valkyris.client.gui.popup.button.impl;

import fr.valkya.valkyris.client.gui.market.element.button.Button;
import fr.valkya.valkyris.client.gui.popup.Popup;

public class PopupButton extends Button {

	private Popup popup;
	private int action;

	public PopupButton(int width, int height, String text, Popup popup, int action) {
		super(popup, width, height, text);
		this.action = action;
		this.popup = popup;
	}
	
	@Override
	public void updateCoords() {
		super.updateCoords();
		this.x = parent.x + (parent.width / 2) - this.width / 2;
		this.y = parent.y + parent.height - this.height - 6;
	}
	
	@Override
	public void click() {
		popup.action(action);
	}

}
