package fr.valkya.valkyris.server.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import net.minecraft.server.MinecraftServer;

public class ServerConfig {
	
	public String serverName;
	public String protocole;
	public String ip;
	public String port;
	public String dataBaseName;
	public String user;
	public String password;
	public boolean auth;
	public boolean activeOre;
	public int maxAlts;
	public int cesiumPercentage, zircoPercentage, randomPercentage; 
	
	public ServerConfig() {
		try {
			File parent = new File(MinecraftServer.field_152367_a.getParentFile(), "config");
			File f = new File(parent, "config.properties");
			if(!parent.exists())
				parent.mkdir();
			if(!f.exists()) {
				f.createNewFile(); 
				
				Properties props = new Properties();
				props.setProperty("serverName", "ServerName");
				props.setProperty("dbProtocole", "jdbc:mysql:");
		        props.setProperty("dbIp", "ip");
		        props.setProperty("dbPort", "3306");
		        props.setProperty("dbName", "dnname");
		        props.setProperty("dbUser", "dbuser");
		        props.setProperty("dbPassword", "mdp");
		        props.setProperty("auth", "true");
		        props.setProperty("acceptedNbAccount", "2");
//		        props.setProperty("faction", "true");
//		        props.setProperty("dcauth", "true");
		        props.setProperty("activeOre", "true");
		        props.setProperty("cesiumOre", "80");
		        props.setProperty("zirconiumOre", "65");
		        props.setProperty("randomOre", "65");
		        
		        OutputStream out = new FileOutputStream(f);
		        props.store(out, "This is all Valorion Properties");
			}
			
			InputStream is = new FileInputStream(f);
			Properties prop = new Properties();
			
			prop.load(is);
			
			if(!prop.containsKey("serverName"))
				System.out.println("y'a pas le nom du serv. \"serverName=Faction/Minage/\" à rajouter dans config.properties");
			
			serverName = prop.getProperty("serverName");
			protocole = prop.getProperty("dbProtocole");
			ip = prop.getProperty("dbIp");
			port = prop.getProperty("dbPort");
			dataBaseName = prop.getProperty("dbName");
			user = prop.getProperty("dbUser");
			password = prop.getProperty("dbPassword");
			auth = prop.getProperty("auth").contains("true") ? true : false;
			maxAlts = Integer.parseInt(prop.getProperty("acceptedNbAccount", "-1"));
			activeOre = Boolean.parseBoolean(prop.getProperty("activeOre"));
			cesiumPercentage = Integer.parseInt(prop.getProperty("cesiumOre"));
			zircoPercentage = Integer.parseInt(prop.getProperty("zirconiumOre"));
			randomPercentage = Integer.parseInt(prop.getProperty("randomOre"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
