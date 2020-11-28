package fr.valkya.valkyris.api.impl;

import fr.valkya.valkyris.api.IRenderer;
import fr.valkya.valkyris.client.render.ClientRenderer;
import fr.valkya.valkyris.client.render.notification.Notification;
import fr.valkya.valkyris.client.render.notification.NotificationManager;
import fr.valkya.valkyris.client.render.notification.NotificationType;

public class RendererImpl implements IRenderer {

	@Override
    public void sendTitle(String title, String text) {
    	ClientRenderer.title(title, text);
    }
    
	@Override
    public void sendBroadcast(String text) {
    	ClientRenderer.broadcast(text);
    }
    
	@Override
    public void sendInfoNotification(String title, String text, int delay) {
    	NotificationManager.show(new Notification(NotificationType.INFO, title, text, delay));
    }
    
	@Override
    public void sendWarnNotification(String title, String text, int delay) {
    	NotificationManager.show(new Notification(NotificationType.WARNING, title, text, delay));
    }
    
	@Override
    public void sendErrorNotification(String title, String text, int delay) {
    	NotificationManager.show(new Notification(NotificationType.ERROR, title, text, delay));
    }
	
}
