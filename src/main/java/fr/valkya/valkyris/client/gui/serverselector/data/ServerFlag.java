package fr.valkya.valkyris.client.gui.serverselector.data;

import fr.valkya.valkyris.References;
import net.minecraft.util.ResourceLocation;

public enum ServerFlag {
	
	PVP(new ResourceLocation(References.MODID, "icons/serverselector/" + "pvp" + ".png")),
	END(new ResourceLocation(References.MODID, "icons/serverselector/" + "end" + ".png")),
	FACTIONS(new ResourceLocation(References.MODID, "icons/serverselector/" + "faction" + ".png")),
	MINE(new ResourceLocation(References.MODID, "icons/serverselector/" + "minage" + ".png")),
	NETHER(new ResourceLocation(References.MODID, "icons/serverselector/" + "nether" + ".png")),
	SKY(new ResourceLocation(References.MODID, "icons/serverselector/" + "sky" + ".png"));
	
	private ResourceLocation resLoc;
	
	private ServerFlag(ResourceLocation icon) {
		this.resLoc = icon;
	}
	
	public ResourceLocation getIcon() {
		return resLoc;
	}
	
}
