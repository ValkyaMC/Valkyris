package fr.valkya.valkyris.client.render.notification;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class Notification {
    
	private static final Minecraft mc = Minecraft.getMinecraft();
    private NotificationType type;
    private String title;
    private String messsage;
    private long start;

    private long fadedIn;
    private long fadeOut;
    private long end;

    public Notification(NotificationType type, String title, String messsage, int length) {
        this.type = type;
        this.title = title;
        this.messsage = messsage;

        fadedIn = 200 * length;
        fadeOut = fadedIn + 500 * length;
        end = fadeOut + fadedIn;
    }

    public void show() {
        start = System.currentTimeMillis();
    }

    public boolean isShown() {
        return getTime() <= end;
    }

    private long getTime() {
        return System.currentTimeMillis() - start;
    }

    public void render() {
        ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        double offset;
        int width = Math.max(mc.fontRenderer.getStringWidth(messsage), mc.fontRenderer.getStringWidth(title)) + 12;
        int height = 35;
        long time = getTime();
        
        int yOffset = 0;
        
        int hotbarEnd = res.getScaledWidth() / 2 + 100;
        if(res.getScaledWidth() - width < hotbarEnd) {
        	yOffset += 37;
        }

        if (time < fadedIn) {
            offset = Math.tanh(time / (double) (fadedIn) * 3.0) * width;
        } else if (time > fadeOut) {
            offset = (Math.tanh(3.0 - (time - fadeOut) / (double) (end - fadeOut) * 3.0) * width);
        } else {
            offset = width;
        }

        Color color = new Color(0, 0, 0, 220);
        Color color2 = new Color(0xFFFFB011);

        if (type == NotificationType.ERROR) {
            int i = Math.max(0, Math.min(255, (int) (Math.sin(time / 100.0) * 255.0 / 2 + 127.5)));
            color = new Color(i, 0, 0, 220);
        }

        Gui.drawRect((int) (res.getScaledWidth() - offset), res.getScaledHeight() - 5 - height - yOffset, (int) (res.getScaledWidth() + width - offset - 5), res.getScaledHeight() - 5 - yOffset, color.getRGB());
        Gui.drawRect((int) (res.getScaledWidth() - offset), res.getScaledHeight() - 6 - yOffset, (int) ((int) (res.getScaledWidth() - offset) + (width * Math.min(1, (((double)getTime())/((double)fadeOut)))) - 5), res.getScaledHeight() - 5 - yOffset, color2.getRGB());
        
        mc.fontRenderer.drawStringWithShadow(title, (int) (res.getScaledWidth() - offset + 3), res.getScaledHeight() - 2 - height - yOffset, -1);
        mc.fontRenderer.drawStringWithShadow(messsage, (int) (res.getScaledWidth() - offset + 3), res.getScaledHeight() - 15 - 5 - yOffset, -1);
    }

}