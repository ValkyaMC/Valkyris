package fr.valkya.valkyris.client.gui.market.element;

public class DraggableElement extends Element {

	public int x2, y2;
	public boolean dragging;
	
	public DraggableElement(Element parent, int width, int height) {
		super(parent, width, height);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		if(dragging) {
			this.x = x2 + mouseX;
			this.y = y2 + mouseY;
		}
		super.render(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if((mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + 30 - 1) && mouseButton == 0) {
			this.x2 = this.x - mouseX;
			this.y2 = this.y - mouseY;
			this.dragging = true;
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		dragging = false;
		super.mouseReleased(mouseX, mouseY, mouseButton);
	}
	
}
