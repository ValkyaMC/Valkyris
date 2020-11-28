package fr.valkya.valkyris.client.gui.market.element;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public class Element {

	protected static Minecraft mc = Minecraft.getMinecraft();
	protected static FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	
	public Element parent;
	public List<Element> childs = new ArrayList<>();
	
	public int x, y, width, height;
	
	public Element(Element parent, int width, int height) {
		this.parent = parent;
		this.width = width;
		this.height = height;
	}
	
	public void updateCoords() {
		childs.forEach(Element::updateCoords);
	}
	
	public void render(int mouseX, int mouseY, float partialTicks) {
		childs.forEach(e -> e.render(mouseX, mouseY, partialTicks));
	}
	
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		childs.forEach(e -> e.mouseClicked(mouseX, mouseY, mouseButton));
	}
	
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		childs.forEach(e -> e.mouseReleased(mouseX, mouseY, mouseButton));
	}
	
	public void keyTyped(int keyCode, char character) {
		childs.forEach(e -> e.keyTyped(keyCode, character));
	}
	
	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height - 1;
	}
	
	protected ScaledResolution res() {
		return new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
	}
}
