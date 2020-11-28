package fr.valkya.valkyris.client.utils;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.lwjgl.opengl.GL11;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;

public class RenderHelper {

	public static void scissor(float x, float y, float x2, float y2) {
    	ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
	    int factor = sr.getScaleFactor();
	    GL11.glScissor((int) (x * factor), (int) ((sr.getScaledHeight() - y2) * factor), (int) ((x2 - x) * factor), (int) ((y2 - y) * factor));
	} 

	private static final Minecraft mc = Minecraft.getMinecraft();
	
    public static Map<String, ResourceLocation> cache2;
    public static ResourceLocation loadHead() {
    	return loadHead(mc.getSession().func_148256_e().getId());
	}
    
    public static ResourceLocation loadHead(UUID uuid) {
    	return loadHead(uuid.toString());
    }
    
    public static ResourceLocation loadHead(String string) {
    	if(cache2 == null)
    		cache2 = new HashMap<String, ResourceLocation>();
		if(cache2.containsKey(string))
			return cache2.get(string);
		
		ResourceLocation head = new ResourceLocation("heads/" + string);
		ThreadDownloadImageData textureHead = new ThreadDownloadImageData(null, String.format("https://minotar.net/helm/%s/64.png", string), null, null);
		Minecraft.getMinecraft().getTextureManager().loadTexture(head, textureHead);
		cache2.put(string, head);
		return head;
	}
    
    public static Map<String, ResourceLocation> cache = new HashMap<>();
    public static ResourceLocation loadFullBody(String uuid) {
    	if(cache.containsKey(uuid))
    		return cache.get(uuid);
    	
    	ResourceLocation head = new ResourceLocation("body/" + uuid);
		ThreadDownloadImageData textureHead = new ThreadDownloadImageData(null, String.format("https://visage.surgeplay.com/full/%s", uuid), null, null);
		Minecraft.getMinecraft().getTextureManager().loadTexture(head, textureHead);
		cache.put(uuid, head);
		return head;
    }
    
    private static Map<String, String> uuids = new HashMap<>();
    public static String getUUID(String username) {
    	if(uuids.containsKey(username)) {
    		return uuids.get(username);
    	}
    	
    	uuids.put(username, "c06f89064c8a49119c29ea1dbd1aab82");
    	
    	Thread t = new Thread( () -> {
    		try {
				String json = WebUtils.readUrl(new URL("https://api.mojang.com/users/profiles/minecraft/" + username));
				Gson gson = new GsonBuilder().create();
				JsonObject owo = gson.fromJson(json, JsonObject.class);
				uuids.put(username, owo.get("id").getAsString());
			} catch (IOException e) {
				e.printStackTrace();
			}
    	});
    	t.setDaemon(true);
    	t.start();
    	
    	return "c06f89064c8a49119c29ea1dbd1aab82";
    }
	
}
