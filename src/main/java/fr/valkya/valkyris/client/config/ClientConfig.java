package fr.valkya.valkyris.client.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ClientConfig {

	public static String macro1, macro2, macro3, macro4, macro5;
	public static Boolean activem1, activem2, activem3, activem4, activem5, hidef3, armortoggle;
	static String activestr1, activestr2, activestr3, activestr4, activestr5, hidef3str, armortogglesrtr;

	public ClientConfig() {
		try {
			File f = new File("vconfig.properties");
			if (!f.exists()) {
				f.createNewFile();

				Properties props = new Properties();
				props.setProperty("Macro1", "/command1");
				props.setProperty("Macro2", "/command2");
				props.setProperty("Macro3", "/command3");
				props.setProperty("Macro4", "/command4");
				props.setProperty("Macro5", "/command5");

				props.setProperty("ActiveMacro1", "false");
				props.setProperty("ActiveMacro2", "false");
				props.setProperty("ActiveMacro3", "false");
				props.setProperty("ActiveMacro4", "false");
				props.setProperty("ActiveMacro5", "false");
				props.setProperty("HideF3", "false");
				props.setProperty("ArmorToggle", "false");

				OutputStream out = new FileOutputStream(f);
				props.store(out, "This is Valkya Properties");
			}

			InputStream is = new FileInputStream(f);
			Properties prop = new Properties();

			prop.load(is);

			macro1 = prop.getProperty("Macro1");
			macro2 = prop.getProperty("Macro2");
			macro3 = prop.getProperty("Macro3");
			macro4 = prop.getProperty("Macro4");
			macro5 = prop.getProperty("Macro5");
			activem1 = prop.getProperty("ActiveMacro1").contains("true") ? true : false;
			activem2 = prop.getProperty("ActiveMacro2").contains("true") ? true : false;
			activem3 = prop.getProperty("ActiveMacro3").contains("true") ? true : false;
			activem4 = prop.getProperty("ActiveMacro4").contains("true") ? true : false;
			activem5 = prop.getProperty("ActiveMacro5").contains("true") ? true : false;
			hidef3 = prop.getProperty("HideF3").contains("true") ? true : false;
			armortoggle = prop.getProperty("ArmorToggle").contains("true") ? true : false;


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			File f = new File("vconfig.properties");
			Properties prop = new Properties();

			f.delete();
			
			prop.setProperty("Macro1", macro1);
			prop.setProperty("Macro2", macro2);
			prop.setProperty("Macro3", macro3);
			prop.setProperty("Macro4", macro4);
			prop.setProperty("Macro5", macro5);

			if(activem1)activestr1="true"; else activestr1="false";
			if(activem2)activestr2="true"; else activestr2="false";
			if(activem3)activestr3="true"; else activestr3="false";
			if(activem4)activestr4="true"; else activestr4="false";
			if(activem5)activestr5="true"; else activestr5="false";
			if(hidef3)hidef3str="true"; else hidef3str="false";
			if(armortoggle)armortogglesrtr="true"; else armortogglesrtr="false";

			prop.setProperty("ActiveMacro1", activestr1);
			prop.setProperty("ActiveMacro2", activestr2);
			prop.setProperty("ActiveMacro3", activestr3);
			prop.setProperty("ActiveMacro4", activestr4);
			prop.setProperty("ActiveMacro5", activestr5);
			prop.setProperty("HideF3", hidef3str);
			prop.setProperty("ArmorToggle", armortogglesrtr);
			
			OutputStream out = new FileOutputStream(f);
			prop.store(out, "This is Valkya Properties");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
