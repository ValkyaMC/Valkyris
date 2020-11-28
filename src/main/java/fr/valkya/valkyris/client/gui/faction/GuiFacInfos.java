package fr.valkya.valkyris.client.gui.faction;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;

public class GuiFacInfos extends GuiScreen {
	
	public static List<String> infos;
	
	public GuiFacInfos() {
		infos = new ArrayList<String>();
	}
	
	@Override
	public void initGui() {
//		PacketRequestFactionInfos pkt = new PacketRequestFactionInfos();
//		Valorion.getValorion().getProxy().getNetwork().sendToServer(pkt);
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		int height = 0;
		for(String str : infos) {
			drawString(fontRendererObj, str, 0, height, -1);
			height+=11;
		}
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

}
