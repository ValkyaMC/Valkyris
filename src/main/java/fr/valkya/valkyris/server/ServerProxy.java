package fr.valkya.valkyris.server;

import java.io.File;
import java.io.IOException;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.common.CommonProxy;
import fr.valkya.valkyris.server.commands.CommandBroadcast;
import fr.valkya.valkyris.server.commands.CommandJson;
import fr.valkya.valkyris.server.commands.CommandNotify;
import fr.valkya.valkyris.server.commands.CommandNotifyPlayer;
import fr.valkya.valkyris.server.commands.CommandSize;
import fr.valkya.valkyris.server.commands.CommandTitle;
import fr.valkya.valkyris.server.commands.CommandTitlePlayer;
import fr.valkya.valkyris.server.config.ServerConfig;
import fr.valkya.valkyris.server.manager.ServerSizeManager;
import fr.valkya.valkyris.server.sql.SQL;
import fr.valkya.valkyris.server.world.VWorldGenerator;
import net.minecraft.server.MinecraftServer;

public class ServerProxy extends CommonProxy {
	
	private SQL sql;
	private File dataFile;
	private File dataDir;
	private ServerConfig serverCfg;
	private ServerSizeManager sizeManager;
	
	public ServerProxy() {
		dataDir = new File(MinecraftServer.field_152367_a.getParentFile(), "faction");
		dataFile = new File(dataDir, "claims.data");
		
		if(!dataDir.exists()) dataDir.mkdirs();
		if(!dataFile.exists()) try { dataFile.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		this.serverCfg = new ServerConfig();
	
		this.sql = new SQL(serverCfg);
		this.sizeManager = new ServerSizeManager();
		
		FMLCommonHandler.instance().bus().register(this.sizeManager);
		
		super.preInit(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
		GameRegistry.registerWorldGenerator(VWorldGenerator.INSTANCE, 0);
	}

	public void serverStarting(FMLServerStartingEvent e) {
		e.registerServerCommand(new CommandJson());
		e.registerServerCommand(new CommandNotify());
		e.registerServerCommand(new CommandNotifyPlayer());
		e.registerServerCommand(new CommandSize());
		e.registerServerCommand(new CommandBroadcast());
		e.registerServerCommand(new CommandTitle());
		e.registerServerCommand(new CommandTitlePlayer());
	}

	public void serverStoping(FMLServerStoppingEvent e) {
	}

	public SQL getSQL() { return sql; }
	public ServerSizeManager getSizeManager() { return sizeManager; }
	public ServerConfig getServerConfig() { return serverCfg; }
	
}
