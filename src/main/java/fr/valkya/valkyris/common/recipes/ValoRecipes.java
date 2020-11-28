package fr.valkya.valkyris.common.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.api.APIProvider;
import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.common.items.ItemSuperFuel;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ValoRecipes {

	public static void init() {
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumIngot), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.cesiumNugget
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zirconiumIngot), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.zircoNugget
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumNugget), new Object[] {
				"XX",
				"XX",
				'X', VItems.cesium_fragment
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zircoNugget), new Object[] {
				"XX",
				"XX",
				'X', VItems.zirconium_fragment
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.emerauldHammer), new Object[] {
				"XXX",
				"XXX",
				" Y ",
				'X', Blocks.emerald_block,
				'Y', Items.stick
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.ironHammer), new Object[] {
				"XXX",
				"XXX",
				" Y ",
				'X', Blocks.iron_block,
				'Y', Items.stick
		});		
		
		GameRegistry.addRecipe(new ItemStack(VBlocks.godForge), new Object[] {
				"ZAZ",
				"XYX",
				"ZXZ",
				'X', Blocks.quartz_block,
				'Z', Blocks.gold_block,
				'Y', Blocks.furnace,
				'A', Blocks.anvil
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zirconiumSeed), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', VItems.zirconiumIngot,
				'Y', Items.wheat_seeds
		});
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumSeed), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', VItems.cesiumIngot,
				'Y', Items.wheat_seeds
		});
		GameRegistry.addRecipe(new ItemStack(VItems.ironSeed), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', Items.iron_ingot,
				'Y', Items.wheat_seeds
		});
		GameRegistry.addRecipe(new ItemStack(VItems.diamondSeed), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', Items.diamond,
				'Y', Items.wheat_seeds
		});
		GameRegistry.addRecipe(new ItemStack(VItems.goldenSeed), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', Items.gold_ingot,
				'Y', Items.wheat_seeds
		});
		GameRegistry.addRecipe(new ItemStack(VItems.obsidianSeed), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', Blocks.obsidian,
				'Y', Items.wheat_seeds
		});
		GameRegistry.addRecipe(new ItemStack(VItems.quartzSeed), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', Items.quartz,
				'Y', Items.wheat_seeds
		});
		GameRegistry.addRecipe(new ItemStack(VItems.netherSeed), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', Items.nether_star,
				'Y', Items.wheat_seeds
		});
		GameRegistry.addRecipe(new ItemStack(VItems.emeraldSeed), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', Items.emerald,
				'Y', Items.wheat_seeds
		});
		GameRegistry.addRecipe(new ItemStack(VBlocks.seedAccelerator), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', Items.bone,
				'Y', Blocks.diamond_block
		});
		GameRegistry.addRecipe(new ItemStack(VBlocks.zirconiumBlock), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VBlocks.cesiumBlock), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.cesiumIngot,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumIngot, 9), new Object[] {
				"X",
				'X', VBlocks.cesiumBlock,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.zirconiumIngot, 9), new Object[] {
				"X",
				'X', VBlocks.zirconiumBlock,
		});
		
		GameRegistry.addSmelting(VBlocks.zirconiumOre, new ItemStack(VItems.zirconiumIngot, 1), 20F);
		GameRegistry.addSmelting(VBlocks.cesiumOre, new ItemStack(VItems.cesiumIngot, 1), 15F);
		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cesiumBlock), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.cesiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.itemObsidianDoor), new Object[] {
				"XX",
				"XX",
				"XX",
				'X', Blocks.obsidian
		});
		GameRegistry.addRecipe(new ItemStack(VBlocks.obsidianLadder, 8), new Object[] {
				"X X",
				"XYX",
				"X X",
				'X', Blocks.obsidian,
				'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(VBlocks.obsidianPane, 8), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', Blocks.obsidian,
				'Y', Blocks.glass_pane
		});
		GameRegistry.addRecipe(new ItemStack(VBlocks.obsidianReinforced), new Object[] {
				"X",
				'X', Blocks.obsidian
		});
		GameRegistry.addRecipe(new ItemStack(VBlocks.obsidianTrapdoor, 3), new Object[] {
				"XXX",
				"XXX",
				'X', Blocks.obsidian
		});
//		GameRegistry.addRecipe(new ItemStack(VItems.sizeDownStick), new Object[] {
//				"YXY",
//				"X X",
//				"X X",
//				'X', VItems.cesiumIngot,
//				'Y', VItems.zirconium_gem
//		});
//		GameRegistry.addRecipe(new ItemStack(VItems.sizeUpStick), new Object[] {
//				"YXY",
//				"X X",
//				"X X",
//				'X', VItems.zirconiumIngot,
//				'Y', VItems.cesium_gem
//		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.wateringCan1), new Object[] {"  X", "YX ", "ZX ", 'X', Blocks.iron_block, 'Y', Items.water_bucket, 'Z', VBlocks.bone1});
		GameRegistry.addRecipe(new ItemStack(VItems.wateringCan2), new Object[] {"  X", "YX ", "ZX ", 'X', Blocks.iron_block, 'Y', Items.water_bucket, 'Z', VBlocks.bone3});
		GameRegistry.addRecipe(new ItemStack(VItems.wateringCan3), new Object[] {"  X", "YX ", "ZX ", 'X', Blocks.iron_block, 'Y', Items.water_bucket, 'Z', VBlocks.bone5});
		
//		GameRegistry.addRecipe(new ItemStack(VItems.creeperStick), new Object[] {
//				"ZXZ",
//				" Y ",
//				"YAY",
//				'X', Blocks.tnt,
//				'Y', Items.stick,
//				'Z', new ItemStack(Items.dye, 1, 10),
//				'A', VItems.cesiumIngot
//		});
//		GameRegistry.addRecipe(new ItemStack(VItems.chargedCreeperStick), new Object[] {
//				"DDD",
//				"ZYZ",
//				"YAY",
//				'D', VItems.zirconiumIngot,
//				'Y', VBlocks.cesiumBlock,
//				'Z', new ItemStack(Items.dye, 1, 12),
//				'A', VItems.cesiumIngot
//		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.creeperStick), new Object[] {
				"TCT",
				"G#G",
				"G#G",
				'#', Items.stick,
				'C', VItems.cesium_gem,
				'G', Items.gunpowder,
				'T', Blocks.tnt
		});
		GameRegistry.addRecipe(new ItemStack(VItems.chargedCreeperStick), new Object[] {
				"BFB",
				"GSG",
				"TGT",
				'S', VItems.creeperStick,
				'G', VItems.zirconium_gem,
				'B', Blocks.iron_bars,
				'F', Items.flint_and_steel,
				'T', Blocks.tnt
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumSword), new Object[] {
				"Y",
				"Y",
				"X",
				'X', Items.stick,
				'Y', VItems.cesiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumPickaxe), new Object[] {
				"YYY",
				" X ",
				" X ",
				'X', Items.stick,
				'Y', VItems.cesiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumAxe), new Object[] {
				"YY",
				"YX",
				" X",
				'X', Items.stick,
				'Y', VItems.cesiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumShovel), new Object[] {
				"Y",
				"X",
				"X",
				'X', Items.stick,
				'Y', VItems.cesiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumHoe), new Object[] {
				"YY",
				"X ",
				"X ",
				'X', Items.stick,
				'Y', VItems.cesiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumHoe), new Object[] {
				"YY",
				" X",
				" X",
				'X', Items.stick,
				'Y', VItems.cesiumIngot,
		});
		
//		GameRegistry.addRecipe(new ItemStack(VItems.cesiumBow), new Object[] {
//				" XY",
//				"X Y",
//				" XY",
//				'Y', Items.string,
//				'X', VItems.cesiumIngot,
//		});
//		GameRegistry.addRecipe(new ItemStack(VItems.cesiumBow), new Object[] {
//				"YX ",
//				"Y X",
//				"YX",
//				'Y', Items.string,
//				'X', VItems.cesiumIngot,
//		});
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumArrow), new Object[] {
				"A",
				"B",
				"C",
				'A', VItems.cesiumIngot,
				'B', Items.stick,
				'C', Items.feather,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zircoSword), new Object[] {
				"Y",
				"Y",
				"X",
				'X', Items.stick,
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zircoPickaxe), new Object[] {
				"YYY",
				" X ",
				" X ",
				'X', Items.stick,
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zircoAxe), new Object[] {
				"YXY",
				"YXY",
				" X ",
				'X', Items.stick,
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zircoShovel), new Object[] {
				"Y",
				"X",
				"X",
				'X', Items.stick,
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zircoHoe), new Object[] {
				"YY ",
				"X  ",
				"X  ",
				'X', Items.stick,
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zircoHoe), new Object[] {
				" YY",
				" X ",
				" X ",
				'X', Items.stick,
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zircoHoe), new Object[] {
				"YY ",
				" X ",
				" X ",
				'X', Items.stick,
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.zircoHoe), new Object[] {
				" YY",
				"  X",
				"  X",
				'X', Items.stick,
				'Y', VItems.zirconiumIngot,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.cesiumHelmet), new Object[] {
				"YYY",
				"Y Y",
				'Y', VItems.cesiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.cesiumChestplate), new Object[] {
				"Y Y",
				"YYY",
				"YYY",
				'Y', VItems.cesiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.cesiumLeggins), new Object[] {
				"YYY",
				"Y Y",
				"Y Y",
				'Y', VItems.cesiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.cesiumBoots), new Object[] {
				"   ",
				"Y Y",
				"Y Y",
				'Y', VItems.cesiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.cesiumBoots), new Object[] {
				"Y Y",
				"Y Y",
				"   ",
				'Y', VItems.cesiumIngot,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.zircoHelmet), new Object[] {
				"YYY",
				"Y Y",
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.zircoChestplate), new Object[] {
				"Y Y",
				"YYY",
				"YYY",
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.zircoLeggins), new Object[] {
				"YYY",
				"Y Y",
				"Y Y",
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.zircoBoots), new Object[] {
				"   ",
				"Y Y",
				"Y Y",
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.zircoBoots), new Object[] {
				"Y Y",
				"Y Y",
				"   ",
				'Y', VItems.zirconiumIngot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.infinite_fruit), new Object[] {
				"YYY",
				"YXY",
				"YYY",
				'Y', VBlocks.zirconiumBlock,
				'X', Items.golden_apple,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.superfuel), new Object[] {
				"ABC",
				"DEF",
				"GHI",
				'A', VItems.zirconiumIngot,
				'B', Items.diamond,
				'C', VItems.cesiumIngot,
				'D', Items.iron_ingot,
				'E', Items.coal,
				'F', Items.gold_ingot,
				'G', new ItemStack(Items.dye, 1, 4),
				'H', Items.redstone,
				'I', Items.emerald				
		});
		GameRegistry.addRecipe(new ItemStack(Items.iron_ingot), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.iron_fragment,
		});
		GameRegistry.addRecipe(new ItemStack(Items.gold_ingot), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.gold_fragment,
		});
		GameRegistry.addRecipe(new ItemStack(Blocks.obsidian), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.obsidian_fragment,
		});
		GameRegistry.addRecipe(new ItemStack(Items.nether_star), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.netherstars_fragment,
		});
		GameRegistry.addRecipe(new ItemStack(Items.quartz), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.quartz_fragment,
		});
		GameRegistry.addRecipe(new ItemStack(Items.diamond), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.diamond_fragment,
		});
		GameRegistry.addRecipe(new ItemStack(Items.emerald), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.emerald_fragment,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoIngot), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VItems.valo_fragment,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.cesium_gem), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VBlocks.cesiumBlock,
		});
			
		GameRegistry.addRecipe(new ItemStack(VItems.zirconium_gem), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				'X', VBlocks.zirconiumBlock,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoSword), new Object[] {
				"X",
				"X",
				"Y",
				'X', VItems.valoIngot,
				'Y', Items.stick,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoPickaxe), new Object[] {
				"XXX",
				" Y ",
				" Y ",
				'X', VItems.valoIngot,
				'Y', Items.stick,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoAxe), new Object[] {
				"XYX",
				"XYX",
				" Y ",
				'X', VItems.valoIngot,
				'Y', Items.stick,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoShovel), new Object[] {
				"X",
				"Y",
				"Y",
				'X', VItems.valoIngot,
				'Y', Items.stick,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.valoHelmet), new Object[] {
				"XXX",
				"X X",
				'X', VItems.valoIngot,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.valoChestplate), new Object[] {
				"X X",
				"XXX",
				"XXX",
				'X', VItems.valoIngot,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.valoLeggins), new Object[] {
				"XXX",
				"X X",
				"X X",
				'X', VItems.valoIngot,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.valoBoots), new Object[] {
				"X X",
				"X X",
				'X', VItems.valoIngot,
		});
		
		
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.camouflageHelmet), new Object[] {
				"YYY",
				"Y Y",
				'Y', VItems.camouflage,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.camouflageChestplate), new Object[] {
				"Y Y",
				"YYY",
				"YYY",
				'Y', VItems.camouflage,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.camouflageLeggins), new Object[] {
				"YYY",
				"Y Y",
				"Y Y",
				'Y', VItems.camouflage,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.camouflageBoots), new Object[] {
				"Y Y",
				"Y Y",
				'Y', VItems.camouflage,
		});

		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.travelHelmet), new Object[] {
				"YYY",
				"Y Y",
				'Y', VItems.aqua_ingot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.travelChestplate), new Object[] {
				"Y Y",
				"YYY",
				"YYY",
				'Y', VItems.aqua_ingot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.travelLeggings), new Object[] {
				"YYY",
				"Y Y",
				"Y Y",
				'Y', VItems.aqua_ingot,
		});
		GameRegistry.addRecipe(new ItemStack(VItems.valoArmors.travelBoots), new Object[] {
				"Y Y",
				"Y Y",
				'Y', VItems.aqua_ingot,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.camouflage), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X', Items.leather,
				'Y', new ItemStack(APIProvider.provideAPI().getRegistry().getItem("rabbit_foot"), 1),
		});
		
		
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumBucket), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'Y', Items.bucket,
				'X', VItems.cesiumIngot,
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.cesiumDynamite), new Object[] {
				"YXY",
				"YXY",
				"YXY",
				'Y', VItems.cesiumIngot,
				'X', Blocks.tnt,
		});
	
		GameRegistry.addRecipe(new ItemStack(VItems.esp_helmet), new Object[] {
				"IAI",
				"AWA",
				"XFX",
				'X', VBlocks.cesiumBlock,
				'W', Items.compass,
				'A', VItems.cesium_gem,
				'I', Blocks.redstone_torch,
				'F', Blocks.glass_pane
		});
		
		GameRegistry.addRecipe(new ItemStack(VBlocks.grinder), new Object[] {
				"ARA",
				"YWY",
				"AAA",
				'Y', Blocks.piston,
				'W', Blocks.furnace,
				'A', Blocks.iron_block,
				'R', Items.redstone
		});
		
		GameRegistry.addRecipe(new ItemStack(VItems.spongeStick), new Object[] {
				"A",
				"B",
				"B",
				'B', Items.stick,
				'A', Blocks.sponge
		});
		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cesiumChest), new Object[] {
				"FFF",
				"FCF",
				"FFF",
				'F', VBlocks.cesiumBlock,
				'C', Blocks.chest,
		});
		
		GameRegistry.addRecipe(new ItemStack(VBlocks.zircoChest), new Object[] {
				"FFF",
				"FCF",
				"FFF",
				'F', VBlocks.zirconiumBlock,
				'C', VBlocks.cesiumChest,
		});
		
		GameRegistry.addRecipe(new ItemStack(VBlocks.valoChest), new Object[] {
				"FFF",
				"FCF",
				"FFF",
				'F', VItems.valo_fragment,
				'C', VBlocks.zircoChest
		});
		
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone1), new Object[] {"XXX","XXX","XXX",'X', new ItemStack(Items.dye, 1, 15)});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone2), new Object[] {"XXX","XXX","XXX",'X', VBlocks.bone1});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone3), new Object[] {"XXX","XXX","XXX",'X', VBlocks.bone2});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone4), new Object[] {"XXX","XXX","XXX",'X', VBlocks.bone3});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone5), new Object[] {"XXX","XXX","XXX",'X', VBlocks.bone4});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone6), new Object[] {"XXX","XXX","XXX",'X', VBlocks.bone5});
		
		GameRegistry.addRecipe(new ItemStack(Items.dye, 9, 15), new Object[] {"X",'X', VBlocks.bone1});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone1, 9), new Object[] {"X",'X', VBlocks.bone2});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone2, 9), new Object[] {"X",'X', VBlocks.bone3});
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone3, 9), new Object[] {"X",'X', VBlocks.bone4});
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone4, 9), new Object[] {"X",'X', VBlocks.bone5});
		GameRegistry.addRecipe(new ItemStack(VBlocks.bone5, 9), new Object[] {"X",'X', VBlocks.bone6});
		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble1), new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack(Blocks.cobblestone, 1)});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble2), new Object[] {"XXX","XXX","XXX",'X', VBlocks.cobble1});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble3), new Object[] {"XXX","XXX","XXX",'X', VBlocks.cobble2});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble4), new Object[] {"XXX","XXX","XXX",'X', VBlocks.cobble3});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble5), new Object[] {"XXX","XXX","XXX",'X', VBlocks.cobble4});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble6), new Object[] {"XXX","XXX","XXX",'X', VBlocks.cobble5});		
		
		GameRegistry.addRecipe(new ItemStack(Blocks.cobblestone, 9), new Object[] {"X",'X', VBlocks.cobble1});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble1, 9), 	 new Object[] {"X",'X', VBlocks.cobble2});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble2, 9), 	 new Object[] {"X",'X', VBlocks.cobble3});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble3, 9), 	 new Object[] {"X",'X', VBlocks.cobble4});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble4, 9), 	 new Object[] {"X",'X', VBlocks.cobble5});		
		GameRegistry.addRecipe(new ItemStack(VBlocks.cobble5, 9), 	 new Object[] {"X",'X', VBlocks.cobble6});	

		GameRegistry.addRecipe(new ItemStack(VItems.wateringCan1), new Object[] {"  X", "YX ", "ZX ", 'X', Blocks.iron_block, 'Y', Items.water_bucket, 'Z', VBlocks.bone1});
		GameRegistry.addRecipe(new ItemStack(VItems.wateringCan2), new Object[] {"  X", "YX ", "ZX ", 'X', Blocks.iron_block, 'Y', Items.water_bucket, 'Z', VBlocks.bone3});
		GameRegistry.addRecipe(new ItemStack(VItems.wateringCan3), new Object[] {"  X", "YX ", "ZX ", 'X', Blocks.iron_block, 'Y', Items.water_bucket, 'Z', VBlocks.bone5});
		
		GameRegistry.registerFuelHandler(new ItemSuperFuel());
		
//		for(ArmorMaterial material : ArmorMaterial.values()) {
//			Item baseItem = material.func_151685_b();
//			
//			Iterator<Item> items = Item.itemRegistry.iterator();
//			while(items.hasNext()) {
//				Item item = items.next();
//				if(item.getClass().getName().toLowerCase().contains("noppes")) continue;
//				
//				if(item instanceof ESPHelmet) continue;
//				
//				if(item instanceof ItemArmor) {
//					ItemArmor itemArmor = (ItemArmor) item;
//					int stackSize = 1;
//					
//					if(itemArmor.getArmorMaterial() == material) {
//						for(Object o : CraftingManager.getInstance().getRecipeList()) {
////							if (o instanceof ShapelessRecipes) {
////								ShapelessRecipes sr = (ShapelessRecipes) o;
////								if (sr.getRecipeOutput().getItem() == itemArmor) {
////									stackSize = sr.recipeItems.size();
////									if (baseItem == null) {
////										baseItem = (Item) sr.recipeItems.get(0);
////									}
////									break;
////								}
////							}
//							if(o instanceof ShapedRecipes) {
//								ShapedRecipes sr = (ShapedRecipes)o;
//								if(sr.getRecipeOutput().getItem() == itemArmor) {
//									for(ItemStack is : sr.recipeItems) {
//										if(is == null || is.getItem() == null) 
//											continue;
//										stackSize++;
//										
//										if(baseItem == null) {
//											baseItem = is.getItem(); 
//										}
//									}
//									break;
//								}
//							}
//						}
//						
//						GameRegistry.addSmelting(new ItemStack(itemArmor, 1), new ItemStack(baseItem, Math.max(1, (int) (stackSize * 0.2))), 15F);
//					}
//				}
//			}
//		}	
	}
	
}
