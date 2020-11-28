package fr.valkya.valkyris.client.command;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class MarketCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "market";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return getCommandName();
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		Minecraft.getMinecraft().func_152344_a(() -> {
			Minecraft.getMinecraft().displayGuiScreen(((ClientProxy)Valkyris.getValkyris().getProxy()).getMarketGui());
		});
	}

}
