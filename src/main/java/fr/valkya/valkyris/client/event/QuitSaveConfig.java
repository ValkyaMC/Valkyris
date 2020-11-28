//package fr.valkya.valkyris.client.event;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.util.Properties;
//
//import cpw.mods.fml.common.eventhandler.SubscribeEvent;
//import cpw.mods.fml.common.network.FMLNetworkEvent;
//import fr.valkya.valkyris.client.config.ClientConfig;
//import net.minecraftforge.client.event.RenderWorldLastEvent;
//
//public class QuitSaveConfig {
//	
//	@SubscribeEvent
//	public void clientdisconnected(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
//		
//		try {
//			File f = new File("macros.properties");
//			Properties prop = new Properties();
//
//			f.delete();
//			
//			prop.setProperty("Macro1", ClientConfig.macro1);
//			prop.setProperty("Macro2", ClientConfig.macro2);
//			prop.setProperty("Macro3", ClientConfig.macro3);
//			prop.setProperty("Macro4", ClientConfig.macro4);
//			prop.setProperty("Macro5", ClientConfig.macro5);
//
//			if(ClientConfig.activem1)ClientConfig.activestr1="true"; else ClientConfig.activestr1="false";
//			if(ClientConfig.activem2)ClientConfig.activestr2="true"; else ClientConfig.activestr2="false";
//			if(ClientConfig.activem3)ClientConfig.activestr3="true"; else ClientConfig.activestr3="false";
//			if(ClientConfig.activem4)ClientConfig.activestr4="true"; else ClientConfig.activestr4="false";
//			if(ClientConfig.activem5)ClientConfig.activestr5="true"; else ClientConfig.activestr5="false";
//
//			prop.setProperty("ActiveMacro1", ClientConfig.activestr1);
//			prop.setProperty("ActiveMacro2", ClientConfig.activestr2);
//			prop.setProperty("ActiveMacro3", ClientConfig.activestr3);
//			prop.setProperty("ActiveMacro4", ClientConfig.activestr4);
//			prop.setProperty("ActiveMacro5", ClientConfig.activestr5);
//			
//			OutputStream out = new FileOutputStream(f);
//			prop.store(out, "This is Valkya Properties");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//
//}
