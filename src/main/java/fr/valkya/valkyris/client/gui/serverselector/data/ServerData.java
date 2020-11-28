package fr.valkya.valkyris.client.gui.serverselector.data;

import java.util.List;

public class ServerData {
	
	public String displayName, internalName;
	public ServerCategory category;
	
	public List<ServerFlag> flags;
	
	public ServerData(String displayName, String internalName, ServerCategory cat, List<ServerFlag> flags) {
		this.displayName = displayName;
		this.internalName = internalName;
		this.category = cat;
		this.flags = flags;
	}

}
