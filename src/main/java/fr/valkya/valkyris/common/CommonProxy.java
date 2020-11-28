package fr.valkya.valkyris.common;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.command.MacrosCommand;
import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.common.block.chest.VBlockChest;
import fr.valkya.valkyris.common.crash.StacktraceDeobfuscator;
import fr.valkya.valkyris.common.creativetabs.ValoCreativeTabs;
import fr.valkya.valkyris.common.enchant.EnchantmentLightshot;
import fr.valkya.valkyris.common.enchant.EnchantmentTelekinesis;
import fr.valkya.valkyris.common.entity.EntityDynamiteCesium;
import fr.valkya.valkyris.common.event.BucketCesiumFill;
import fr.valkya.valkyris.common.event.LightShot;
import fr.valkya.valkyris.common.event.ValoCrops;
import fr.valkya.valkyris.common.event.ValoEnchantment;
import fr.valkya.valkyris.common.event.ValoExplosiveArrow;
import fr.valkya.valkyris.common.event.ValoStructureCesium;
import fr.valkya.valkyris.common.event.ValoStructureZirconium;
import fr.valkya.valkyris.common.fluid.VFluids;
import fr.valkya.valkyris.common.items.VItems;
import fr.valkya.valkyris.common.manager.FallManager;
import fr.valkya.valkyris.common.market.MarketManager;
import fr.valkya.valkyris.common.network.Network;
import fr.valkya.valkyris.common.recipes.ValoRecipes;
import fr.valkya.valkyris.common.tile.TileEntityGrinder;
import fr.valkya.valkyris.common.tile.TileEntityObsidianReinforced;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class CommonProxy {
	
	public File dataDir;
	
	public long startTimestamp;
	
	private Network network;
	public ValoCreativeTabs tabs;
	
	private FallManager fallManager;
	
	public static Enchantment enchantTelekinesis;
	public static Enchantment enchantLightShot;
	public static int tesrRenderId;
	
	private MarketManager marketManager;
	
	public CommonProxy() {}

	public void preInit(FMLPreInitializationEvent event) {		
		
			
		dataDir = event.getModConfigurationDirectory();
		StacktraceDeobfuscator.init(new File(dataDir, "mappings.csv"));
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Valkyris.getValkyris(), new ValoGuiHandler());
		
//		EntityRegistry.registerGlobalEntityID(EntityStagBeetles.class, "Beetle", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
//		EntityRegistry.registerModEntity(EntityBeetle.class, "Beetle", 420, Valkyris.getValorion(), 40, 1, true);    
//		
//		EntityRegistry.registerGlobalEntityID(EntityStagBeetles.class, "StagBeetle", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
//        EntityRegistry.registerModEntity(EntityStagBeetles.class, "StagBeetle", 420, Valkyris.getValorion(), 40, 1, true);        
//        
//		EntityRegistry.registerGlobalEntityID(EntityButterflies.class, "Butterflies", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
//        EntityRegistry.registerModEntity(EntityButterflies.class, "Butterflies", 420, Valkyris.getValorion(), 40, 1, true); 
//        
//		EntityRegistry.registerGlobalEntityID(EntityStick.class, "Stick", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
//        EntityRegistry.registerModEntity(EntityStick.class, "Stick", 420, Valkyris.getValorion(), 40, 1, true);
//        
//		EntityRegistry.registerGlobalEntityID(EntityBees.class, "Bees", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
//        EntityRegistry.registerModEntity(EntityBees.class, "Bees", 420, Valkyris.getValorion(), 40, 1, true);  
//        
//		EntityRegistry.registerGlobalEntityID(EntityCarp.class, "Carp", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
//        EntityRegistry.registerModEntity(EntityCarp.class, "Carp", 420, Valkyris.getValorion(), 40, 1, true); 
//        
//		EntityRegistry.registerGlobalEntityID(EntitySeaBream.class, "SeaBream", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
//        EntityRegistry.registerModEntity(EntitySeaBream.class, "SeaBream", 420, Valkyris.getValorion(), 40, 1, true);
//        
//		EntityRegistry.registerGlobalEntityID(EntityTuna.class, "Tuna", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
//        EntityRegistry.registerModEntity(EntityTuna.class, "Tuna", 420, Valkyris.getValorion(), 40, 1, true); 
//        
//		EntityRegistry.registerGlobalEntityID(EntityMagalodon.class, "Megalodon", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
//        EntityRegistry.registerModEntity(EntityMagalodon.class, "Megalodon", 420, Valkyris.getValorion(), 40, 1, true);
		
		EntityRegistry.registerModEntity(EntityDynamiteCesium.class, "EntityDynamiteCesium", 420, Valkyris.instance, 32, 20, false);
		
		tabs = new ValoCreativeTabs();
				
		VFluids.init();
		VBlocks.init();	
		VItems.init();
		
		regListeners(fallManager = new FallManager());
//		regListeners(new ValoEasterEggColorant());
//		regListeners(new BimBamBoomHandle());
		regListeners(new ValoStructureZirconium());
		regListeners(new ValoStructureCesium());
		regListeners(new ValoExplosiveArrow());
		regListeners(new ValoEnchantment());
		regListeners(new ValoCrops());
		regListeners(new BucketCesiumFill());
		regListeners(new LightShot());
		
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("cesium_liquid", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(VItems.cesiumBucket), FluidContainerRegistry.EMPTY_BUCKET);

		enchantTelekinesis = new EnchantmentTelekinesis();
		enchantLightShot = new EnchantmentLightshot();
	}

	public void init(FMLInitializationEvent event) {		
		network = new Network();
		
		ValoRecipes.init();
		
		GameRegistry.registerTileEntity(TileEntityObsidianReinforced.class, References.MODID + ":reinforced_obsi");
		GameRegistry.registerTileEntity(TileEntityGrinder.class, "valorion:TileEntityGrinder");
//		GameRegistry.registerTileEntity(TileEntityValoChest.class, References.MODID + ".valo_chest");
//		GameRegistry.registerTileEntity(TileEntityZircoChest.class, References.MODID + ".zirco_chest");
//		GameRegistry.registerTileEntity(TileEntityCesiumChest.class, References.MODID + ".cesium_chest");
		
		for(Field field : VBlocks.class.getDeclaredFields()) {
			if(Modifier.isStatic(field.getModifiers())) {
				field.setAccessible(true);
				
				try {
					Block block = (Block) field.get(null);
					if(block instanceof VBlockChest) {
						((VBlockChest)block).registerTileEntity();
					}
				} catch(Exception ignored) {
				}
			}
		}
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		marketManager = new MarketManager();
		marketManager.init(); 
		
		startTimestamp = System.currentTimeMillis();
	}
	
	protected void regListeners(Object o) {
		FMLCommonHandler.instance().bus().register(o);
		MinecraftForge.EVENT_BUS.register(o);
	}
	
	public Network getNetwork() {
		return network;
	}

	public ValoCreativeTabs getCreativesTabs() { return tabs; }

	public FallManager getFallManger() { return fallManager; }
	public MarketManager getMarketManager() { return marketManager; }
	
	public void serverStarting(FMLServerStartingEvent e) {
		e.registerServerCommand(new MacrosCommand());
	}

	public void serverStoping(FMLServerStoppingEvent e) {}
	
}
