package fr.valkya.valkyris.client.gui.popup;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.client.gui.market.element.Element;
import fr.valkya.valkyris.client.gui.popup.button.impl.PopupButton;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class Popup extends Element {

	private String title;
	private List<String> contents;
	private IPopupCallback callback;
	
	public Popup(String title, List<String> contents) {
		super(null, 0, 0);
		
		this.title = title;
		this.contents = contents;
		this.childs.add(new PopupButton(60, 40, "OK", this, 0));
	}
	
	public void initCallback(IPopupCallback callback) {
		this.callback = callback;
	}
	
	@Override
	public void updateCoords() {
		ScaledResolution sr = res();
		this.x = sr.getScaledWidth() / 2 - getWidth() / 2;
		this.y = sr.getScaledHeight() / 2 - getHeight() / 2;
		this.width = getWidth();
		this.height = getHeight();
		super.updateCoords();
	}
	
	public void render(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = res();
		Gui.drawRect(x, y, x + width, y + height, 0xCC000000);
		Gui.drawRect(x, y, x + width, y + 30, 0xCC000000);
		
		GL11.glPushMatrix();
		{
			GL11.glTranslated(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, 0);
			GL11.glTranslated(-fr.getStringWidth(getTitle()), -fr.FONT_HEIGHT, 0);
			GL11.glTranslated(0, -this.height / 2 + 30 / 2, 0);
			GL11.glScaled(2, 2, 1);
			fr.drawStringWithShadow(getTitle(), 0, 0, -1);
			GL11.glScaled(0.5, 0.5, 1);
			
//			GL11.glScaled(2, 2, 1);
//			GL11.glTranslated(-sr.getScaledWidth() / 4, -sr.getScaledHeight() / 4, 0);
//			fr.drawStringWithShadow(getTitle(), x + width / 2 - fr.getStringWidth(getTitle()) / 2, y + (33) - (fr.FONT_HEIGHT / 2), -1);
//			GL11.glTranslated(sr.getScaledWidth() / 4, sr.getScaledHeight() / 4, 0);
//			GL11.glScaled(0.5, 0.5, 1);
		}
		GL11.glPopMatrix();
		
		for(int i = 0; i < contents.size(); i++) {
			String txt = contents.get(i);
			fr.drawStringWithShadow(txt, x + width / 2 - fr.getStringWidth(txt) / 2, y + 36 + (i * 11), -1);
		}
// GuiButton		
		super.render(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void keyTyped(int keyCode, char character) {
		if(keyCode == Keyboard.KEY_ESCAPE) {
			callback.action(PopupAction.CLOSE, this);
		}
	}
	
	public void action(int action) {
		callback.action(action, this);
	}
	
	public String getTitle() {
		return title;
	}
	
	public List<String> getContents() {
		return contents;
	}
	
	public int getWidth() {
		int w = fr.getStringWidth(getTitle()) * 2;
		for(String str : contents) {
			int w2 = fr.getStringWidth(str);
			if(w2 > w) w = w2;
		}
		for(Element child : childs) {
			if(child.width > w) w = child.width;
		}
		return w + 20;
	}
	
	public int getHeight() {
		AtomicInteger ai = new AtomicInteger(30);
		this.childs.stream().map(e -> e.height).collect(Collectors.summingInt(ai::addAndGet));
		ai.addAndGet(this.childs.size() * 12);
		this.getContents().forEach(s -> ai.addAndGet(11));
		ai.addAndGet(12);
		return ai.get();
	}
	
	public class PopupAction {
		public static final int CLOSE = 0;
	}
}
