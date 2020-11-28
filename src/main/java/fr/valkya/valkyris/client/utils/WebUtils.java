package fr.valkya.valkyris.client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WebUtils {

	public static String readUrl(URL url) throws IOException {
        url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        String str = sb.toString();
        str = str.substring(0, str.lastIndexOf("\n"));
		return str;
	}
	
}
