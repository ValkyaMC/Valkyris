package fr.valkya.valkyris.client.gui.market.element.window.panel.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import fr.valkya.valkyris.client.gui.market.element.Element;
import fr.valkya.valkyris.client.gui.market.element.button.impl.FilterButton;
import fr.valkya.valkyris.client.gui.market.element.checkbox.Checkbox;
import fr.valkya.valkyris.client.gui.market.element.window.panel.Panel;
import fr.valkya.valkyris.common.market.product.ProductCategory;

public class FilterPanel extends Panel {

	public Map<ProductCategory, Checkbox> categories;
	
	@Override
	public void updateCoords() {
		this.x = parent.x + 4;
		this.y = parent.y + 30 + 4;
		this.height = parent.height - 30 - 4 - 4;
		
		AtomicInteger yPos = new AtomicInteger(0);
		this.childs.forEach(e -> {
			e.x = this.x + 2;
			e.y = this.y + 2 + yPos.getAndAdd(e.height);
		});
	}
	
	public FilterPanel(Element parent, int width, int height, ListingsPanel panel) {
		super(parent, width, height);
		
		categories = new HashMap<>();
		for(ProductCategory cat : ProductCategory.values()) {
			categories.put(cat, new Checkbox(this, width - 4, 16, cat.getName()));
		}
		
		categories.values().forEach(childs::add);
		
		this.childs.add(new FilterButton(this, width - 4, 20, "Filtrer", panel));
	}

	public List<ProductCategory> getEnabledCategories() {
		return categories.keySet().stream().filter(c -> categories.get(c).value).collect(Collectors.toList());
	}
}
