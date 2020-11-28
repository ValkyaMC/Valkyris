package fr.valkya.valkyris.client.gui.faction;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiFaction extends GuiScreen {
	
	public GuiFaction() {

	}
	
	@Override
	public void initGui() {
		int i = 0;
		int j = 0;
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Créer une faction"));
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Informations"));
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Claim"));
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Unclaim"));
		
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Inviter un joueur"));
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Kick un joueur"));
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Promote un joueur"));
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Demote un joueur"));
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Passer Leader un joueur"));
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Disband la Faction"));
		
		this.buttonList.add(new GuiButton(j++, 220, 0, "Debug Faction"));
		
		this.buttonList.add(new GuiButton(j++, 0, i++ * 30, "Quitter"));
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
			case 0:
				mc.displayGuiScreen(new GuiTextFieldScreen("Créer une faction :") {
					@Override
					protected void doButton(String text) {
						//network.sendToServer(new PacketCreateFaction(text));
					}
				});
				break;
			case 1:
				mc.displayGuiScreen(new GuiFacInfos());
				break;
			case 2: 
				//Valorion.getValorion().getProxy().getNetwork().sendToServer(new PacketClaimFaction());
				mc.displayGuiScreen(null);
				break;
			case 3:
				//Valorion.getValorion().getProxy().getNetwork().sendToServer(new PacketUnclaimFaction());
				mc.displayGuiScreen(null);
				break;
			case 4:
				mc.displayGuiScreen(new GuiTextFieldScreen("Inviter un joueur :") {
					@Override
					protected void doButton(String text) {
						//network.sendToServer(new PacketInvitePlayer(text));
					}
				});
				break;
			case 5:
				mc.displayGuiScreen(new GuiTextFieldScreen("Kick un joueur de la faction :") {
					@Override
					protected void doButton(String text) {
						//network.sendToServer(new PacketKickPlayer(text));
					}
				});
				break;
			case 6:
				mc.displayGuiScreen(new GuiTextFieldScreen("Promote un membre :") {
					@Override
					protected void doButton(String text) {
						//network.sendToServer(new PacketPromotePlayer(text));
					}
				});
				break;
			case 7:
				mc.displayGuiScreen(new GuiTextFieldScreen("Demote un joueur") {
					@Override
					protected void doButton(String text) {
						//network.sendToServer(new PacketDemotePlayer(text));
					}
				});
				break;
			case 8:
				mc.displayGuiScreen(new GuiTextFieldScreen("Passer Leader un joueur :") {
					@Override
					protected void doButton(String text) {
						//network.sendToServer(new PacketLeadPlayer(text));
					}
				});
				break;
			case 9:
				//Valorion.getValorion().getProxy().getNetwork().sendToServer(new PacketDisbandFaction());
				mc.displayGuiScreen(null);
				break;
			case 10:
				//Valorion.getValorion().getProxy().getNetwork().sendToServer(new PacketDebugFaction());
			default:
				mc.displayGuiScreen(null);
				break;
		}
	}
	
	

}
