package fr.valkya.valkyris.client;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import api.player.render.RenderPlayerAPI;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.armor.ClientValoArmors;
import fr.valkya.valkyris.client.command.MacrosCommand;
import fr.valkya.valkyris.client.config.ClientConfig;
import fr.valkya.valkyris.client.config.ConfigHandler;
import fr.valkya.valkyris.client.gui.GuiHandler;
import fr.valkya.valkyris.client.gui.macros.MacrosSelector;
import fr.valkya.valkyris.client.gui.market.MarketGUI;
import fr.valkya.valkyris.client.gui.serverselector.GuiServerSelector;
import fr.valkya.valkyris.client.gui.serverselector.data.ServerCategory;
import fr.valkya.valkyris.client.key.Keybindings;
import fr.valkya.valkyris.client.manager.CacheManager;
import fr.valkya.valkyris.client.render.ClientRenderer;
import fr.valkya.valkyris.client.render.ESPlayer;
import fr.valkya.valkyris.client.render.PlayerRenderer;
import fr.valkya.valkyris.client.render.RenderTileGodForge;
import fr.valkya.valkyris.client.render.blocks.RenderItemGodForge;
import fr.valkya.valkyris.client.render.blocks.RenderTileGrinder;
import fr.valkya.valkyris.client.render.chest.VChestRenderHelper;
import fr.valkya.valkyris.client.render.valkyrite.RenderValorionAxe;
import fr.valkya.valkyris.client.render.valkyrite.RenderValorionPickaxe;
import fr.valkya.valkyris.client.render.valkyrite.RenderValorionShovel;
import fr.valkya.valkyris.client.render.valkyrite.RenderValorionSword;
import fr.valkya.valkyris.client.render.zirconium.RenderZirconiumAxe;
import fr.valkya.valkyris.client.render.zirconium.RenderZirconiumHoe;
import fr.valkya.valkyris.client.render.zirconium.RenderZirconiumPickaxe;
import fr.valkya.valkyris.client.render.zirconium.RenderZirconiumShovel;
import fr.valkya.valkyris.client.render.zirconium.RenderZirconiumSword;
import fr.valkya.valkyris.client.rpc.ValoDiscordRPC;
import fr.valkya.valkyris.client.utils.WindowHelper;
import fr.valkya.valkyris.common.CommonProxy;
import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.common.block.chest.VBlockChest;
import fr.valkya.valkyris.common.entity.EntityDynamiteCesium;
import fr.valkya.valkyris.common.items.VItems;
import fr.valkya.valkyris.common.tile.TileEntityGodForge;
import fr.valkya.valkyris.common.tile.TileEntityGrinder;
import fr.valkya.valkyris.server.config.ServerConfig;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	
	private CacheManager cacheManager;
	private MarketGUI marketGUI;
	private MacrosSelector macroselector;
	private ValoDiscordRPC valoDiscordRPC;
	private ClientConfig clientcfg;
		
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		this.clientcfg = new ClientConfig();
		
//		ConfigHandler.INSTANCE.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + "Macros" + ".cfg"));
		WindowHelper.initializeWindow();
		
		cacheManager = new CacheManager();
		VItems.valoArmors = new ClientValoArmors();
		
		valoDiscordRPC = new ValoDiscordRPC();
		valoDiscordRPC.init();
		super.preInit(event);
	}
	
	@Override
	public void init(FMLInitializationEvent event) {		
		super.init(event);
		RenderPlayerAPI.register(References.MODID, PlayerRenderer.class);
		
		MinecraftForgeClient.registerItemRenderer(VItems.valoSword, new RenderValorionSword());
		MinecraftForgeClient.registerItemRenderer(VItems.valoPickaxe, new RenderValorionPickaxe());
		MinecraftForgeClient.registerItemRenderer(VItems.valoAxe, new RenderValorionAxe());
		MinecraftForgeClient.registerItemRenderer(VItems.valoShovel, new RenderValorionShovel());
		
		MinecraftForgeClient.registerItemRenderer(VItems.zircoSword, new RenderZirconiumSword());
		MinecraftForgeClient.registerItemRenderer(VItems.zircoPickaxe, new RenderZirconiumPickaxe());
		MinecraftForgeClient.registerItemRenderer(VItems.zircoAxe, new RenderZirconiumAxe());
		MinecraftForgeClient.registerItemRenderer(VItems.zircoShovel, new RenderZirconiumShovel());
		MinecraftForgeClient.registerItemRenderer(VItems.zircoHoe, new RenderZirconiumHoe());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(VBlocks.godForge), new RenderItemGodForge());
				
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(VBlocks.godForge), new RenderTileGodForge());
		
//		RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, new RenderEntityBeetle(new ModelBiped(), 0.5F));
//		RenderingRegistry.registerEntityRenderingHandler(EntityStagBeetles.class, new RenderEntityStagBeetles(new ModelBiped(), 0.5F));
//		RenderingRegistry.registerEntityRenderingHandler(EntityButterflies.class, new RenderEntityButterflies(new ModelBiped(), 0.5F));
//		RenderingRegistry.registerEntityRenderingHandler(EntityBees.class, new RenderEntityBees(new ModelBiped(), 0.5F));
//		RenderingRegistry.registerEntityRenderingHandler(EntityStick.class, new RenderEntityStick(new ModelBiped(), 0.5F));
		
//		RenderingRegistry.registerEntityRenderingHandler(EntityCarp.class, new RenderEntityCarp(new ModelBiped(), 0.5F));
//		RenderingRegistry.registerEntityRenderingHandler(EntitySeaBream.class, new RenderEntitySeaBream(new ModelBiped(), 0.5F));
//		RenderingRegistry.registerEntityRenderingHandler(EntityTuna.class, new RenderEntityTuna(new ModelBiped(), 0.5F));
//		RenderingRegistry.registerEntityRenderingHandler(EntityMagalodon.class, new RenderEntityMegalodon(new ModelBiped(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDynamiteCesium.class, new RenderSnowball(VItems.cesiumDynamite));
		
		GameRegistry.registerTileEntity(TileEntityGodForge.class, "tilegodforge");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGodForge.class, new RenderTileGodForge());
		
		GameRegistry.registerTileEntity(TileEntityGrinder.class, "tilegrinder");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrinder.class, new RenderTileGrinder());
		
		ClientCommandHandler.instance.registerCommand(new MacrosCommand());
		
		regListeners(new ClientRenderer());
		regListeners(new GuiHandler());
		regListeners(new ESPlayer());
		
		tesrRenderId = RenderingRegistry.getNextAvailableRenderId();

		TileEntityRendererChestHelper.instance = new VChestRenderHelper();
		
        for(Field field : VBlocks.class.getDeclaredFields()) {
        	field.setAccessible(true);
        	
        	try {
        		Block block = (Block)field.get(null);
        		if(block instanceof VBlockChest) {
        			((VBlockChest)block).bindTileEntity();
        		}
        	} catch(Exception ignored) {
        	}
        }
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
		Keybindings.INSTANCE.register();

		for(Field f : ClientValoArmors.class.getDeclaredFields()) {
			if(!Modifier.isStatic(f.getModifiers())) {
				f.setAccessible(true);
				
				try {
					Object owo = f.get(VItems.valoArmors);
					if(owo instanceof ItemArmor) {
						((ItemArmor) owo).getArmorModel(null, null, 0);
					}
				} catch(Exception ignored) {
				}
			}
		}
	}

	public void showValoManager() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiServerSelector(ServerCategory.ALL));
	}
	
	public MarketGUI getMarketGui() {
		if(marketGUI == null) marketGUI = new MarketGUI();
		return marketGUI;
	}
	
	public CacheManager getCacheManager() { return cacheManager; }

}
