package fr.valkya.valkyris.client.gui.market.element.window.panel.impl;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.gui.market.element.Element;
import fr.valkya.valkyris.client.gui.market.element.button.impl.ListingButton;
import fr.valkya.valkyris.client.gui.market.element.window.panel.Panel;

public class ListingsPanel extends Panel {
	
	public ListingsPanel(Element parent, int width, int height) {
		super(parent, width, height);
	}

	@Override
	public void updateCoords() {
		this.x = this.parent.x + 4 + 4 + (parent.width / 4) + 4;
		this.y = this.parent.y + 30 + 4;
		this.width = this.parent.width - 4 - 4 - (parent.width / 4) - 4 - 4;
		this.height = parent.height - 30 - 4 - 4;
		
		int xPosition = this.x + 4;
		int yPosition = this.y + 4;
		for(Element listing : this.childs) {
			listing.x = xPosition;
			listing.y = yPosition;
			
			xPosition += listing.width + 4 + 4;
			if(xPosition > this.x + this.width) {
				xPosition = this.x + 4;
				yPosition += listing.height + 4 + 4;
			}
		}
	}
	
	public void refresh(FilterPanel filter) {
		this.childs.clear();

		int size = ((res().getScaledWidth() / 3) / 3);
		
		filter.getEnabledCategories().forEach(pc -> {
			Valkyris.getValkyris().getProxy().getMarketManager().getProductsInCategory(pc).forEach(p -> {
				this.childs.add(new ListingButton(this, size, size, p.getDisplayName(), p));
			});
		});
	}

}
