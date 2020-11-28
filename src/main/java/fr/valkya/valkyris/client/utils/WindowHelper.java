package fr.valkya.valkyris.client.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.apache.commons.compress.utils.IOUtils;
import org.lwjgl.opengl.Display;

import net.minecraft.client.Minecraft;

public class WindowHelper {

	public static void initializeWindow() {
		Display.setTitle("Valkya - " + Minecraft.getMinecraft().getSession().getUsername());
        
		InputStream inputstream1 = null, inputstream2 = null, inputstream3 = null, inputstream4 = null;

        try {
        	inputstream2 = WindowHelper.class.getResourceAsStream("/assets/valkyris/icons/icon_64.png");
        	inputstream3 = WindowHelper.class.getResourceAsStream("/assets/valkyris/icons/icon_32.png");
        	inputstream4 = WindowHelper.class.getResourceAsStream("/assets/valkyris/icons/icon_16.png");
        	
            if (inputstream2 != null && inputstream3 != null && inputstream4 != null) {
                Display.setIcon(new ByteBuffer[] { ImageHelper.readImageToBuffer(inputstream4), ImageHelper.readImageToBuffer(inputstream3), ImageHelper.readImageToBuffer(inputstream2) });
            }
        } catch (IOException ioexception) {
        	ioexception.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputstream1);
            IOUtils.closeQuietly(inputstream2);
            IOUtils.closeQuietly(inputstream3);
        }
	}
	
}
