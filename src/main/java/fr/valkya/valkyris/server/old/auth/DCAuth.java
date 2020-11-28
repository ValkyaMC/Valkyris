package fr.valkya.valkyris.server.old.auth;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class DCAuth {

	private EntityPlayerMP player;
	private long timer;
	
	private Stage stage;
	
	public DCAuth(EntityPlayerMP player) {
		this.player = player;
		this.stage = Stage.IDLE;
	}

	public void start() {
		System.out.println("Started authentication of " + player.getCommandSenderName() + "...");
		this.timer = MinecraftServer.getSystemTimeMillis() + 2000L;
//		Valkyris.getValorion().getProxy().getNetwork().sendTo(new PacketRequestMachine(), player);
		this.stage = Stage.STARTED;
	}
	
	public void cancel() {
		if(this.stage == Stage.STARTED) {
			System.out.println(player.getCommandSenderName() + " authentified!");
			player.addChatMessage(new ChatComponentText("Vous êtes maintenant identifié " + player.getCommandSenderName() + " !").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED).setBold(true)));
			this.stage = Stage.AUTHENTIFIED;
		}
	}

	public EntityPlayerMP getPlayer() {
		return player;
	}

	public long getTimer() {
		return timer;
	}

	public Stage getStage() {
		return stage;
	}

}
