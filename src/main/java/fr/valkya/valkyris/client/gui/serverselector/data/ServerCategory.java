package fr.valkya.valkyris.client.gui.serverselector.data;

public enum ServerCategory {
	ALL("Tous"),
	FACTION("Factions"),
	RESOURCE("Ressource"),
	OTHER("Autres");
	
	private String str;
	
	private ServerCategory(String str) {
		this.str = str;
	}
	
	public String getName() {
		return str;
	}
}
