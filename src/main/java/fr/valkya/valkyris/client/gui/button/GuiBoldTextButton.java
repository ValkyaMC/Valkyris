package fr.valkya.valkyris.client.gui.button;

import net.minecraft.util.EnumChatFormatting;

public class GuiBoldTextButton extends GuiTextButton {

	public GuiBoldTextButton(int id, int x, int y, int w, int h, int color, int color_hover, String text) {
		super(id, x, y, w, h, color, color_hover, EnumChatFormatting.BOLD + text);
	}

}
