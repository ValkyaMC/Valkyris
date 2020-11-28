package fr.valkya.valkyris.client.gui.market.element.window;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.client.gui.market.element.DraggableElement;
import fr.valkya.valkyris.client.gui.market.element.Element;
import fr.valkya.valkyris.client.gui.market.element.window.panel.impl.FilterPanel;
import fr.valkya.valkyris.client.gui.market.element.window.panel.impl.ListingsPanel;
import fr.valkya.valkyris.client.utils.GuiUtils;
import net.minecraft.client.gui.Gui;

public class Window extends DraggableElement {
	
	public int x2, y2;
	public ListingsPanel listings;
	public FilterPanel filter;
	
	public Window(Element parent, int width, int height) {
		super(parent, width, height);
		this.childs.add(listings = new ListingsPanel(this, width / 4, height));
		this.childs.add(filter = new FilterPanel(this, width / 4, height, listings));
		listings.refresh(filter);
	}
	
	@Override
	public void updateCoords() {
		super.updateCoords();
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		Gui.drawRect(x, y, x + width, y + height, 0xAA000000);
		Gui.drawRect(x, y, x + width, y + 30, 0xAA000000);
		GuiUtils.getInstance().drawBorderedRect(x, y, x + width, y + 30, 0xFFFFB011, 0);
		GuiUtils.getInstance().drawBorderedRect(x, y, x + width, y + height, 0xAA000000, 0);
		
		GL11.glPushMatrix();
		GL11.glTranslated(x + width / 2, y + 30 / 2, 0);
		GL11.glTranslated(-fr.getStringWidth("§lValkya Market"), -fr.FONT_HEIGHT, 0);
		GL11.glScaled(2, 2, 1);
		fr.drawStringWithShadow("§6§lValkya Market", 0, 0, -1);
		GL11.glScaled(0.5, 0.5, 1);
		GL11.glPopMatrix();
		
		super.render(mouseX, mouseY, partialTicks);
	}

}
