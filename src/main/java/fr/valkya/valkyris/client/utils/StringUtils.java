package fr.valkya.valkyris.client.utils;

import fr.valkya.valkyris.References;
import net.minecraft.client.Minecraft;

public class StringUtils {
	
	/**
	 * %VERSION% = Version du Mod
	 * %MCVERSION% = Version de MC
	 * %PLAYERNAME% = Pseudo du Player
	 * */
	
	public static String getTransformedString(String s) {
		String ss = s.replaceAll("%VERSION%", References.VERSION).replaceAll("%MCVERSION%", References.MCVERSION).replaceAll("%PLAYERNAME%", Minecraft.getMinecraft().getSession().getUsername());
		return ss;
	}

}
