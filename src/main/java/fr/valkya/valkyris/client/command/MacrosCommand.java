package fr.valkya.valkyris.client.command;

import java.util.Arrays;

import fr.valkya.valkyris.client.gui.popup.GuiPopup;
import fr.valkya.valkyris.client.gui.popup.Popup;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class MacrosCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "macros";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "Usage: /macros";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
	}
}