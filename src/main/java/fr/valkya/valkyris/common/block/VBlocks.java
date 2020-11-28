package fr.valkya.valkyris.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.block.bone.BoneBlock1;
import fr.valkya.valkyris.common.block.bone.BoneBlock2;
import fr.valkya.valkyris.common.block.bone.BoneBlock3;
import fr.valkya.valkyris.common.block.bone.BoneBlock4;
import fr.valkya.valkyris.common.block.bone.BoneBlock5;
import fr.valkya.valkyris.common.block.bone.BoneBlock6;
import fr.valkya.valkyris.common.block.chest.VBlockCesiumChest;
import fr.valkya.valkyris.common.block.chest.VBlockChest;
import fr.valkya.valkyris.common.block.chest.VBlockValkyriteChest;
import fr.valkya.valkyris.common.block.chest.VBlockZircoChest;
import fr.valkya.valkyris.common.block.crops.BlockSeedAccelerator;
import fr.valkya.valkyris.common.block.crops.ValoCrop;
import fr.valkya.valkyris.common.creativetabs.ValoCreativeTabs;
import fr.valkya.valkyris.common.fluid.VFluids;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class VBlocks {
	
	public static final Block.SoundType soundTypeGrass = new Block.SoundType("grass", 1.0F, 1.0F);
	public static Block barrier;
	public static Block seedAccelerator;
	public static Block zirconiumBlock, chargedzirconiumBlock, zirconiumOre, zirconiumCrop;
	public static Block cesiumBlock, chargedcesiumBlock, cesiumOre, cesiumCrop;
	public static Block godForge, valoChest, zircoChest, cesiumChest;
	public static Block randomOre;
	public static Block bone1, bone2, bone3, bone4, bone5, bone6;
	public static Block cobble1, cobble2, cobble3, cobble4, cobble5, cobble6;
	
	public static Block cesiumliquidb;

	public static Block ironCrop, diamondCrop, goldenCrop, obsidianCrop, quartzCrop, netherCrop, emeraldCrop;
	public static Block obsidianReinforced, obsidianDoor, obsidianTrapdoor, obsidianLadder, obsidianPane;
	
	public static Block grinder;
	
	public static Block blackGrass, redGrass, greenGrass, brownGrass, blueGrass, purpleGrass, cyanGrass, light_grayGrass, grayGrass, pinkGrass,
	limeGrass, yellowGrass, light_blueGrass, magentaGrass, orangeGrass, whiteGrass;
	
	public static void init() {
		final ValoCreativeTabs ct = Valkyris.getValkyris().getProxy().getCreativesTabs();
		
		barrier = new BlockBarrier().setBlockName("invisible_barrier").setCreativeTab(ct.staffCreativeTabs);
		GameRegistry.registerBlock(barrier, "invisible_barrier");
		
		seedAccelerator = new BlockSeedAccelerator().setHardness(2).setCreativeTab(ct.cropsCreativeTabs);
		GameRegistry.registerBlock(seedAccelerator, "seed_accelerator");
		
		zirconiumBlock = new VBlock(Material.iron, "zirconium_block").setHardness(2).setCreativeTab(ct.materialCreativeTabs);
		zirconiumOre = new VBlock(Material.rock, "zirconium_ore").setHardness(3).setCreativeTab(ct.materialCreativeTabs);
		
		GameRegistry.registerBlock(zirconiumBlock, ((VBlock) zirconiumBlock).getName());
		GameRegistry.registerBlock(zirconiumOre, ((VBlock) zirconiumOre).getName());
		
		
		cesiumBlock = new ValoCesiumBlock(Material.iron, "cesium_block").setHardness(2).setCreativeTab(ct.materialCreativeTabs);
		cesiumOre = new VBlock(Material.rock, "cesium_ore").setHardness(3).setCreativeTab(ct.materialCreativeTabs);
		GameRegistry.registerBlock(cesiumBlock, ((VBlock) cesiumBlock).getName());
		GameRegistry.registerBlock(cesiumOre, ((VBlock) cesiumOre).getName());
		
		chargedcesiumBlock = new ValoChargedCesiumBlock(Material.iron, "charged_cesium_block").setBlockTextureName(References.MODID + ":cesium_block_charged").setHardness(2).setCreativeTab(ct.materialCreativeTabs);
		chargedzirconiumBlock = new ValoChargedZirconiumBlock(Material.iron, "charged_zirconium_block").setBlockTextureName(References.MODID + ":zirconium_block_charged").setHardness(2).setCreativeTab(ct.materialCreativeTabs);
		
		GameRegistry.registerBlock(chargedcesiumBlock, ((VBlock) chargedcesiumBlock).getName());
		GameRegistry.registerBlock(chargedzirconiumBlock, ((VBlock) chargedzirconiumBlock).getName());

		randomOre = new RandomBlockOre().setHardness(3).setCreativeTab(ct.materialCreativeTabs);
		GameRegistry.registerBlock(randomOre, "random_ore");
		
		grinder = new BlockGrinder().setHardness(3).setCreativeTab(ct.machineCreativeTabs).setBlockName("compresseur").setBlockTextureName(References.MODID + ":compressor");
		GameRegistry.registerBlock(grinder, "compresseur");
		
		
		godForge = new GodForge().setBlockTextureName(References.MODID + ":god_forge");
		GameRegistry.registerBlock(godForge, "god_forge");
		
//		valoChest = new BlockValoChest(Material.wood).setHardness(1.5F).setResistance(10.0F).setBlockName("valo_chest").setBlockTextureName(References.MODID + ":valo_chest").setCreativeTab(ct.machineCreativeTabs);
//		GameRegistry.registerBlock(valoChest, "valo_chest");
//		
//		zircoChest = new BlockZircoChest(Material.wood).setHardness(1.5F).setResistance(10.0F).setBlockName("zirco_chest").setBlockTextureName(References.MODID + ":zirco_chest").setCreativeTab(ct.machineCreativeTabs);
//		GameRegistry.registerBlock(zircoChest, "zirco_chest");
//		
//		cesiumChest = new BlockCesiumChest(Material.wood).setHardness(1.5F).setResistance(10.0F).setBlockName("cesium_chest").setBlockTextureName(References.MODID + ":cesium_chest").setCreativeTab(ct.machineCreativeTabs);
//		GameRegistry.registerBlock(cesiumChest, "cesium_chest");
		
//		cesiumChest = ((VBlockChest)new VBlockChest<VTileEntityChest>("cesium_chest", VTileEntityChest.class, new VChestRenderer(VBlockChest.class, new ResourceLocation(References.MODID + ":textures/gui/cesium_chest"))).setHardness(1.5F).setResistance(10.0F).setBlockName("cesium_chest").setBlockTextureName(References.MODID + ":cesium_chest").setCreativeTab(ct.machineCreativeTabs)).register();

		cesiumChest = ((VBlockChest) new VBlockCesiumChest().setHardness(1.5F).setResistance(10.0F)
				.setBlockName("cesium_chest").setBlockTextureName(References.MODID + ":cesium_block")
				.setCreativeTab(ct.machineCreativeTabs)).register();
		zircoChest = ((VBlockChest) new VBlockZircoChest().setHardness(1.5F).setResistance(10.0F)
				.setBlockName("zirco_chest").setBlockTextureName(References.MODID + ":zirconium_block")
				.setCreativeTab(ct.machineCreativeTabs)).register();
		valoChest = ((VBlockChest) new VBlockValkyriteChest().setHardness(1.5F).setResistance(10.0F)
				.setBlockName("valkyrite_chest").setBlockTextureName(References.MODID + ":valo_ingot")
				.setCreativeTab(ct.machineCreativeTabs)).register();
		
		cesiumliquidb = new BlockFluidCesium(VFluids.cesium_liquid, Material.water).setBlockTextureName(References.MODID + ":cesiumliquidb_still");
		GameRegistry.registerBlock(cesiumliquidb, cesiumliquidb.getUnlocalizedName().substring(5));
		
//		blackGrass = new VBlock(Material.grass, "black_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		redGrass = new VBlock(Material.grass, "red_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		greenGrass = new VBlock(Material.grass, "green_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		brownGrass = new VBlock(Material.grass, "brown_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		blueGrass = new VBlock(Material.grass, "blue_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);		
//		purpleGrass = new VBlock(Material.grass, "purple_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		cyanGrass = new VBlock(Material.grass, "cyan_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		light_grayGrass = new VBlock(Material.grass, "light_gray_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		grayGrass = new VBlock(Material.grass, "gray_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		pinkGrass = new VBlock(Material.grass, "pink_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);		
//		limeGrass = new VBlock(Material.grass, "lime_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		yellowGrass = new VBlock(Material.grass, "yellow_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		light_blueGrass = new VBlock(Material.grass, "light_blue_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		magentaGrass = new VBlock(Material.grass, "magenta_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		orangeGrass = new VBlock(Material.grass, "orange_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
//		whiteGrass = new VBlock(Material.grass, "white_grass").setHardness(0.6F).setStepSound(soundTypeGrass).setCreativeTab(CreativeTabs.tabBlock);
		
//		GameRegistry.registerBlock(blackGrass, "black_grass"); //
//		GameRegistry.registerBlock(redGrass, "red_grass"); //
//		GameRegistry.registerBlock(greenGrass, "green_grass"); // 
//		GameRegistry.registerBlock(brownGrass, "brown_grass"); //
//		GameRegistry.registerBlock(blueGrass, "blue_grass"); //
//		GameRegistry.registerBlock(purpleGrass, "purple_grass"); // 
//		GameRegistry.registerBlock(cyanGrass, "cyan_grass"); //
//		GameRegistry.registerBlock(light_grayGrass, "light_gray_grass"); //
//		GameRegistry.registerBlock(grayGrass, "gray_grass"); //
//		GameRegistry.registerBlock(pinkGrass, "pink_grass"); //
//		GameRegistry.registerBlock(limeGrass, "lime_grass"); //
//		GameRegistry.registerBlock(yellowGrass, "yellow_grass"); //
//		GameRegistry.registerBlock(light_blueGrass, "light_blue_grass"); //
//		GameRegistry.registerBlock(magentaGrass, "magenta_grass"); 
//		GameRegistry.registerBlock(orangeGrass, "orange_grass"); //
//		GameRegistry.registerBlock(whiteGrass, "white_grass"); //
		
		bone1 = new BoneBlock1(Material.rock).setBlockName("boneblock1").setCreativeTab(ct.materialCreativeTabs);
		bone2 = new BoneBlock2(Material.rock).setBlockName("boneblock2").setCreativeTab(ct.materialCreativeTabs);
		bone3 = new BoneBlock3(Material.rock).setBlockName("boneblock3").setCreativeTab(ct.materialCreativeTabs);
		bone4 = new BoneBlock4(Material.rock).setBlockName("boneblock4").setCreativeTab(ct.materialCreativeTabs);
		bone5 = new BoneBlock5(Material.rock).setBlockName("boneblock5").setCreativeTab(ct.materialCreativeTabs);
		bone6 = new BoneBlock6(Material.rock).setBlockName("boneblock6").setCreativeTab(ct.materialCreativeTabs);
		
		// Block
		
		cobble1 = new VBlock(Material.rock, "cobble1").setHardness(5.0F).setResistance(2000.0F).setBlockName("cobbleblock1").setBlockTextureName(References.MODID + ":cobbleblock1").setCreativeTab(ct.materialCreativeTabs);
		cobble2 = new VBlock(Material.rock, "cobble2").setHardness(15.0F).setResistance(2000.0F).setBlockName("cobbleblock2").setBlockTextureName(References.MODID + ":cobbleblock2").setCreativeTab(ct.materialCreativeTabs);
		cobble3 = new VBlock(Material.rock, "cobble3").setHardness(30.0F).setResistance(2000.0F).setBlockName("cobbleblock3").setBlockTextureName(References.MODID + ":cobbleblock3").setCreativeTab(ct.materialCreativeTabs);
		cobble4 = new VBlock(Material.rock, "cobble4").setHardness(50.0F).setResistance(2000.0F).setBlockName("cobbleblock4").setBlockTextureName(References.MODID + ":cobbleblock4").setCreativeTab(ct.materialCreativeTabs);
		cobble5 = new VBlock(Material.rock, "cobble5").setHardness(75.0F).setResistance(2000.0F).setBlockName("cobbleblock5").setBlockTextureName(References.MODID + ":cobbleblock5").setCreativeTab(ct.materialCreativeTabs);
		cobble6 = new VBlock(Material.rock, "cobble6").setHardness(100.0F).setResistance(2000.0F).setBlockName("cobbleblock6").setBlockTextureName(References.MODID + ":cobbleblock6").setCreativeTab(ct.materialCreativeTabs);
		
		GameRegistry.registerBlock(bone1, "bone1");
		GameRegistry.registerBlock(bone2, "bone2");
		GameRegistry.registerBlock(bone3, "bone3");
		GameRegistry.registerBlock(bone4, "bone4");
		GameRegistry.registerBlock(bone5, "bone5");
		GameRegistry.registerBlock(bone6, "bone6");
		
		GameRegistry.registerBlock(cobble1, "cobble1");
		GameRegistry.registerBlock(cobble2, "cobble2");
		GameRegistry.registerBlock(cobble3, "cobble3");
		GameRegistry.registerBlock(cobble4, "cobble4");
		GameRegistry.registerBlock(cobble5, "cobble5");
		GameRegistry.registerBlock(cobble6, "cobble6");
		
		ironCrop = ((ValoCrop) new ValoCrop("iron_crop", 5F) {
			@Override protected Item getSeed() { return VItems.ironSeed; }
			@Override protected Item getCrop() { return VItems.iron_fragment; }
		}.setBlockTextureName(References.MODID + ":iron_crops/iron_crop")).register();
		
		goldenCrop = ((ValoCrop) new ValoCrop("golden_crop", 4F) {
			@Override protected Item getSeed() { return VItems.goldenSeed; }
			@Override protected Item getCrop() { return VItems.gold_fragment; }
		}.setBlockTextureName(References.MODID + ":golden_crops/golden_crop")).register();
		
		diamondCrop = ((ValoCrop) new ValoCrop("diamond_crop", 3F) {
			@Override protected Item getSeed() { return VItems.diamondSeed; }
			@Override protected Item getCrop() { return VItems.diamond_fragment; }
		}.setBlockTextureName(References.MODID + ":diamond_crops/diamond_crop")).register();
		
		obsidianCrop = ((ValoCrop) new ValoCrop("obsidian_crop", 2F) {
			@Override protected Item getSeed() { return VItems.obsidianSeed;	}
			@Override protected Item getCrop() { return VItems.obsidian_fragment;	}
		}.setBlockTextureName(References.MODID + ":obsidian_crops/obsidian_crop")).register();
		
		quartzCrop = ((ValoCrop) new ValoCrop("quartz_crop", 4F) {
			@Override protected Item getSeed() { return VItems.quartzSeed; }
			@Override protected Item getCrop() { return VItems.quartz_fragment; }
		}.setBlockTextureName(References.MODID + ":quartz_crops/quartz_crop")).register();
		
		netherCrop = ((ValoCrop) new ValoCrop("nether_crop", 0.5F) {
			@Override protected Item getSeed() { return VItems.netherSeed; }
			@Override protected Item getCrop() { return VItems.netherstars_fragment; }
		}.setBlockTextureName(References.MODID + ":nether_crops/nether_crop")).register();
		
		emeraldCrop = ((ValoCrop) new ValoCrop("emerald_crop", 0.5F) {
			@Override protected Item getSeed() { return VItems.emeraldSeed; }
			@Override protected Item getCrop() { return VItems.emerald_fragment; }
		}.setBlockTextureName(References.MODID + ":emerald_crops/emerald_crop")).register();

		zirconiumCrop = ((ValoCrop) new ValoCrop("zirconium_crop", 0.8F) {
			@Override protected Item getSeed() { return VItems.zirconiumSeed; }
			@Override protected Item getCrop() { return VItems.zirconium_fragment; }			
		}.setBlockTextureName(References.MODID + ":zirconium_crops/zirconium_crop")).register();
		
		cesiumCrop = ((ValoCrop) new ValoCrop("cesium_crop", 0.5F) {
			@Override protected Item getSeed() { return VItems.cesiumSeed; }
			@Override protected Item getCrop() { return VItems.cesium_fragment; }
		}.setBlockTextureName(References.MODID + ":cesium_crops/cesium_crop")).register();
		
		obsidianReinforced = new ObsidianReinforced().register();
		obsidianTrapdoor = new ObsidianTrapdoor().register();
		obsidianLadder = new ObsidianLadder().register();
		obsidianDoor = new ObsidianDoor().register();
		obsidianPane = new ObsidianGlassPane().register();
	}

}
