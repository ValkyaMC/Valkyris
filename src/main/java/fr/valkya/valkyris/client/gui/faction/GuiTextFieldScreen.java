package fr.valkya.valkyris.client.gui.faction;

import org.lwjgl.input.Keyboard;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.network.Network;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public abstract class GuiTextFieldScreen extends GuiScreen {
	
	protected GuiTextField text;
	
	private String title;

	protected Network network;
	
	public GuiTextFieldScreen(String title) {
		this.title = title;
		this.network = Valkyris.getValkyris().getProxy().getNetwork();
	}
	
	@Override
	public void initGui() {
		this.text = new GuiTextField(fontRendererObj, 0, 15, 200, 35);
		this.text.setMaxStringLength(12);
		this.text.setFocused(true);
		this.buttonList.add(new GuiButton(0, 0, 35, "Ok"));
		
	}
	
	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
		if(p_73869_2_ == Keyboard.KEY_SPACE) return;
		super.keyTyped(p_73869_1_, p_73869_2_);
		this.text.textboxKeyTyped(p_73869_1_, p_73869_2_);
	}
	
	@Override
	public void onGuiClosed() {		
		this.text.setFocused(false);
		
		super.onGuiClosed();
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		if(this.text.getText().contains(" ")) {
			this.text.setText(this.text.getText().replaceAll(" ", ""));
		}
		this.text.updateCursorCounter();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			if(this.text.getText() == null || this.text.getText() == "") return;
			doButton(this.text.getText());
			mc.displayGuiScreen(null);
			break;
		default:
			break;
		}
	}
	
	protected abstract void doButton(String text);

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		this.drawDefaultBackground();
		this.drawString(fontRendererObj, title, 0, 0, -1);
		this.text.drawTextBox();
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
		super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		this.text.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}
}
