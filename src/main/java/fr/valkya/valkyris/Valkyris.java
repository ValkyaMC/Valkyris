package fr.valkya.valkyris;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.common.CommonProxy;
import net.minecraft.launchwrapper.Launch;

@Mod(modid = References.MODID, name = References.MODNAME, version = References.VERSION, acceptedMinecraftVersions = References.MCVERSION)
public class Valkyris {
	
	@Instance
	public static Valkyris instance;
	
	@SidedProxy(clientSide = References.CLIENTPROXY, serverSide = References.SERVERPROXY)
	private static CommonProxy proxy;
	
	@EventHandler public void preInit(FMLPreInitializationEvent e) {proxy.preInit(e);}
	@EventHandler public void init(FMLInitializationEvent e) { proxy.init(e);}
	@EventHandler public void postInit(FMLPostInitializationEvent e) { proxy.postInit(e); }
	
	@SideOnly(Side.SERVER) @EventHandler public void onServerStarting(FMLServerStartingEvent e) { proxy.serverStarting(e); }
	@SideOnly(Side.SERVER) @EventHandler public void onServerStopped(FMLServerStoppingEvent e) { proxy.serverStoping(e); }
	
	public static Valkyris getValkyris() { return instance; }
	public CommonProxy getProxy() { return proxy; }
	
	public static boolean isDevEnv() { return (boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment"); }
}
