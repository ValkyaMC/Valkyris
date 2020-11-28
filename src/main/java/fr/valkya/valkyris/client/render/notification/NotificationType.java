package fr.valkya.valkyris.client.render.notification;

import java.util.stream.Stream;

public enum NotificationType {
    INFO("Info"), 
    WARNING("Warning"), 
    ERROR("Error");
	
	private String name;
	
	private NotificationType(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public static NotificationType from(String value) {
		return Stream.of(values()).filter(n -> n.getName().equalsIgnoreCase(value)).findFirst().orElse(null);
	}
}