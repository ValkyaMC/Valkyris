package fr.valkya.valkyris.client.gui.market;

import java.util.Arrays;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.gui.GuiNormalizedScreen;
import fr.valkya.valkyris.client.gui.market.element.window.Window;
import fr.valkya.valkyris.client.gui.popup.GuiPopup;
import fr.valkya.valkyris.client.gui.popup.Popup;
import fr.valkya.valkyris.common.market.MarketManager;
import fr.valkya.valkyris.common.network.Network;
import fr.valkya.valkyris.common.network.packets.market.PacketRequestListings;
import net.minecraft.client.gui.ScaledResolution;

public class MarketGUI extends GuiNormalizedScreen {
	
	private MarketManager marketManager;
	private Network network;
	
	private Window marketWindow;
	
	public MarketGUI() {
		this.marketManager = Valkyris.getValkyris().getProxy().getMarketManager();
		this.network = Valkyris.getValkyris().getProxy().getNetwork();
	}
	
	@Override
	public void initGui() {
		if(!this.marketManager.isReady()) {
			network.sendToServer(new PacketRequestListings());
			mc.displayGuiScreen(new GuiPopup(null, new Popup("Market", Arrays.asList("Le Market est en cours de synchronisation, réessayez dans un petit moment..."))));
			return;
		}
		
		super.initGui();
		
		ScaledResolution sr = res();
		this.marketWindow = new Window(null, sr.getScaledWidth() / 2 + sr.getScaledWidth() / 3, sr.getScaledHeight() - sr.getScaledHeight() / 8);
		this.marketWindow.x = sr.getScaledWidth() / 2 - marketWindow.width / 2;
		this.marketWindow.y = sr.getScaledHeight() / 2 - marketWindow.height / 2;
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		marketWindow.updateCoords();
		marketWindow.render(p_73863_1_, p_73863_2_, p_73863_3_);
	}
	
	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
		if(marketWindow.isHovered(p_73864_1_, p_73864_2_)) {
			marketWindow.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		}
	}
	
	@Override
	protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
		if(p_146286_3_ != -1) {
			marketWindow.mouseReleased(p_146286_1_, p_146286_2_, p_146286_3_);
		}
	}
	
	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
		marketWindow.keyTyped(p_73869_2_, p_73869_1_);
		super.keyTyped(p_73869_1_, p_73869_2_);
	}
}
