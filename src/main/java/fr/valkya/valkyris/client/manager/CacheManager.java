package fr.valkya.valkyris.client.manager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import net.minecraft.client.Minecraft;

public class CacheManager {
	
	private final File cacheDir;
	public final HashMap<String, File> cacheFiles = new HashMap<String, File>();
	
	public CacheManager() {
		cacheDir = new File(Minecraft.getMinecraft().mcDataDir, "cache");
	}
	
	public void downloadAndStockInCache(String strUrl, String name) throws IOException {
		File file = new File(cacheDir, name);
		URL url = new URL(strUrl);
		URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
        conn.connect();
		FileUtils.copyInputStreamToFile(conn.getInputStream(), file);
		cacheFiles.put(name, file);
	}
	
	public File getCachedFileFromName(String name) throws Exception{
		if(isCacheExist(name)) 
			return cacheFiles.get(name);
		return null;
	}
	
	public boolean isCacheExist(String name) {
		return cacheFiles.containsKey(name);
	}
	
	public File getCacheFile() {
		return this.cacheDir;
	}
}
