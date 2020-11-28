package fr.valkya.valkyris.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.armor.ValoArmors;
import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.common.creativetabs.ValoCreativeTabs;
import fr.valkya.valkyris.common.items.crops.ValoSeed;
import fr.valkya.valkyris.common.items.keys.ItemArgentKey;
import fr.valkya.valkyris.common.items.keys.ItemBronzeKey;
import fr.valkya.valkyris.common.items.keys.ItemDailyKey;
import fr.valkya.valkyris.common.items.keys.ItemGoldKey;
import fr.valkya.valkyris.common.items.keys.ItemVoteKey;
import fr.valkya.valkyris.common.items.kits.impl.ItemAttackKit;
import fr.valkya.valkyris.common.items.kits.impl.ItemDailyKit;
import fr.valkya.valkyris.common.items.kits.impl.ItemDefenceKit;
import fr.valkya.valkyris.common.items.kits.impl.ItemEnchantKit;
import fr.valkya.valkyris.common.items.kits.impl.ItemFarmerKit;
import fr.valkya.valkyris.common.items.kits.impl.ItemFoodKit;
import fr.valkya.valkyris.common.items.kits.impl.ItemPillageKit;
import fr.valkya.valkyris.common.items.kits.impl.ItemTravelKit;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class VItems {
	
	public static Item zirconiumIngot, zirconiumSeed;
	public static Item zircoSword, zircoPickaxe, zircoAxe, zircoShovel, zircoHoe;
	
	public static Item cesiumIngot, cesiumSeed;
	public static Item cesiumSword, cesiumPickaxe, cesiumAxe, cesiumShovel, cesiumHoe;
	
	public static Item ironSeed, diamondSeed, goldenSeed, obsidianSeed, quartzSeed, netherSeed, emeraldSeed;
	
	public static Item spongeStick;
	
	public static Item esp_helmet;
	
	public static Item cesiumBucket, cesiumDynamite;
	
	public static Item valoIngot, valo_fragment;
	public static Item valoSword, valoPickaxe, valoAxe, valoShovel;
	
	public static Item hand, corne, wing, skull, valkyaxe, spiderleg, mermaidtail, hammer, gobelin, motherboard, barbare, carnivore, ghost, titanhead, fish, infinite_fruit, superfuel, werewolf;
	
	public static Item spawnerzombie, spawnerskeleton, spawnerpigmen, spawnerirongolem, spawnerspider, spawnerchicken, spawnercow;
	public static Item sizeDownStick, sizeUpStick;
	public static Item chargedCreeperStick, creeperStick, portableFurnace;
	
	public static Item iron_fragment, gold_fragment, obsidian_fragment, netherstars_fragment, quartz_fragment, diamond_fragment, emerald_fragment, cesium_fragment, zirconium_fragment;
	
	public static Item valomanager;
	
	public static Item dailyKit, attackKit, defenceKit, enchantKit, farmerKit, foodKit, travelKit, pillageKit;
	
	public static Item wateringCan1, wateringCan2, wateringCan3;
	
	public static Item cesium_gem, zirconium_gem, cesium_activator, zirconium_activator;
	
	public static Item cesiumArrow, cesiumBow;
	
	public static Item wings, mandibles, magic_powder, polen, royal_wax, camouflage;
//	public static Item ailes, mandibules, poudre_magique, polen, cire_royale, camouflage, filet_a_insectes;

	public static Item scales, fins, aqua_ingot;	
	
	public static Item cesiumNugget, zircoNugget;
	
	public static Item point_classement;
	
	public static Item argentkey, bronzekey, goldkey, dailykey, votekey;
	
	public static Item emerauldHammer, ironHammer;
	
	public static ToolMaterial valoTool = EnumHelper.addToolMaterial("valoTool", 5, 8000, 22.0F, 6F, 15);
	public static ToolMaterial zircoTool = EnumHelper.addToolMaterial("zircroTool", 5, 4500, 20.0F, 5F, 13);
	public static ToolMaterial cesiumTool = EnumHelper.addToolMaterial("cesiumTool", 5, 3000, 18.0F, 4.0F, 11);	
	
	public static ToolMaterial emerauldHammerMat = EnumHelper.addToolMaterial("emerauldhammer", 4, 5000, 6.0F, 3.0F, 15);
	public static ToolMaterial ironHammerMat = EnumHelper.addToolMaterial("ironhammer", 4, 500, 2.0F, 3.0F, 15);
	
	public static ArmorMaterial nvmat = EnumHelper.addArmorMaterial("specialArmor", 15, new int[] {1, 4, 3, 2}, 30);

	public static Item itemObsidianDoor;
	
	public static ValoArmors valoArmors = new ValoArmors();
	
	public static void init() {
		final ValoCreativeTabs ct = Valkyris.getValkyris().getProxy().getCreativesTabs();
		
		emerauldHammer = new EmerauldHammer(emerauldHammerMat).setUnlocalizedName("emerald_hammer").setTextureName(References.MODID + ":emerauld_hammer").setCreativeTab(ct.valoCreativeTabsUtils);
    	GameRegistry.registerItem(emerauldHammer, "emerauld_hammer");
    	
    	ironHammer = new IronHammer(ironHammerMat).setUnlocalizedName("iron_hammer").setTextureName(References.MODID + ":iron_hammer").setCreativeTab(ct.valoCreativeTabsUtils);
    	GameRegistry.registerItem(ironHammer, "iron_hammer");
		
		cesiumIngot = new ValoItem("cesium_ingot").register();		
		zirconiumIngot = new ValoItem("zirconium_ingot").register();
		
		zirconiumSeed = new ValoSeed("zirconium_seed", VBlocks.zirconiumCrop).register();
		cesiumSeed = new ValoSeed("cesium_seed", VBlocks.cesiumCrop).register();
		
		ironSeed = new ValoSeed("iron_seed", VBlocks.ironCrop).register();
		diamondSeed = new ValoSeed("diamond_seed", VBlocks.diamondCrop).register();
		goldenSeed = new ValoSeed("golden_seed", VBlocks.goldenCrop).register();
		obsidianSeed = new ValoSeed("obsidian_seed", VBlocks.obsidianCrop).register();
		quartzSeed = new ValoSeed("quartz_seed", VBlocks.quartzCrop).register();
		netherSeed = new ValoSeed("nether_seed", VBlocks.netherCrop).register();
		emeraldSeed = new ValoSeed("emerald_seed", VBlocks.emeraldCrop).register();
		
		iron_fragment = new Item().setTextureName(References.MODID + ":iron_fragment").setUnlocalizedName("iron_fragment").setCreativeTab(ct.cropsCreativeTabs);
		gold_fragment = new Item().setTextureName(References.MODID + ":gold_fragment").setUnlocalizedName("gold_fragment").setCreativeTab(ct.cropsCreativeTabs);
		obsidian_fragment = new Item().setTextureName(References.MODID + ":obsidian_fragment").setUnlocalizedName("obsidian_fragment").setCreativeTab(ct.cropsCreativeTabs);
		netherstars_fragment = new Item().setTextureName(References.MODID + ":netherstar_fragment").setUnlocalizedName("netherstar_fragment").setCreativeTab(ct.cropsCreativeTabs);
		quartz_fragment = new Item().setTextureName(References.MODID + ":quartz_fragment").setUnlocalizedName("quartz_fragment").setCreativeTab(ct.cropsCreativeTabs);
		diamond_fragment = new Item().setTextureName(References.MODID + ":diamond_fragment").setUnlocalizedName("diamond_fragment").setCreativeTab(ct.cropsCreativeTabs);
		emerald_fragment = new Item().setTextureName(References.MODID + ":emerald_fragment").setUnlocalizedName("emerald_fragment").setCreativeTab(ct.cropsCreativeTabs);
		cesium_fragment = new Item().setTextureName(References.MODID + ":cesium_fragment").setUnlocalizedName("cesium_fragment").setCreativeTab(ct.cropsCreativeTabs);
		zirconium_fragment = new Item().setTextureName(References.MODID + ":zirconium_fragment").setUnlocalizedName("zirconium_fragment").setCreativeTab(ct.cropsCreativeTabs);
		
		valoIngot = new ValoIdentifiableItem("valo_ingot", ct.materialCreativeTabs, 1).register();
		valo_fragment = new Item().setTextureName(References.MODID + ":valorion_fragment").setUnlocalizedName("valo_fragment").setCreativeTab(ct.materialCreativeTabs);
		
		valoSword = new ItemValorionSword(valoTool).setUnlocalizedName("valoSword").setTextureName(References.MODID + ":valo_sword").setCreativeTab(ct.armorCreativeTabs);
		valoPickaxe = new ItemValorionPickaxe(valoTool).setUnlocalizedName("valoPickaxe").setTextureName(References.MODID + ":valo_pickaxe").setCreativeTab(ct.armorCreativeTabs);
		valoAxe = new ItemValorionAxe(valoTool).setUnlocalizedName("valoAxe").setTextureName(References.MODID + ":valo_axe").setCreativeTab(ct.armorCreativeTabs);
		valoShovel = new ItemValorionShovel(valoTool).setUnlocalizedName("valoShovel").setTextureName(References.MODID + ":valo_shovel").setCreativeTab(ct.armorCreativeTabs);
		
		zircoSword = new ItemZircoSword(zircoTool).setUnlocalizedName("zircoSword").setTextureName(References.MODID + ":zirco_sword").setCreativeTab(ct.armorCreativeTabs);
		zircoPickaxe = new ItemZircoPickaxe(zircoTool).setUnlocalizedName("zircoPickaxe").setTextureName(References.MODID + ":zirco_pickaxe").setCreativeTab(ct.armorCreativeTabs);
		zircoAxe = new ItemZircoAxe(zircoTool).setUnlocalizedName("zircoAxe").setTextureName(References.MODID + ":zirco_axe").setCreativeTab(ct.armorCreativeTabs);
		zircoShovel = new ItemZircoShovel(zircoTool).setUnlocalizedName("zircoShovel").setTextureName(References.MODID + ":zirco_shovel").setCreativeTab(ct.armorCreativeTabs);
		zircoHoe = new ItemZircoHoe(zircoTool).setUnlocalizedName("zircoHoe").setTextureName(References.MODID + ":zirco_hoe").setCreativeTab(ct.armorCreativeTabs);
		
		cesiumSword = new ItemCesiumSword(cesiumTool).setUnlocalizedName("cesiumSword").setTextureName(References.MODID + ":cesium_sword").setCreativeTab(ct.armorCreativeTabs);
		cesiumPickaxe = new ItemCesiumPickaxe(cesiumTool).setUnlocalizedName("cesiumPickaxe").setTextureName(References.MODID + ":cesium_pickaxe").setCreativeTab(ct.armorCreativeTabs);
		cesiumAxe = new ItemCesiumAxe(cesiumTool).setUnlocalizedName("cesiumAxe").setTextureName(References.MODID + ":cesium_axe").setCreativeTab(ct.armorCreativeTabs);
		cesiumShovel = new ItemCesiumShovel(cesiumTool).setUnlocalizedName("cesiumShovel").setTextureName(References.MODID + ":cesium_shovel").setCreativeTab(ct.armorCreativeTabs);
		cesiumHoe = new ItemCesiumHoe(cesiumTool).setUnlocalizedName("cesiumHoe").setTextureName(References.MODID + ":cesium_hoe").setCreativeTab(ct.armorCreativeTabs);
		
		cesiumNugget = new ValoItem("cesium_nugget", Valkyris.getValkyris().getProxy().getCreativesTabs().materialCreativeTabs).register();
		zircoNugget = new ValoItem("zirconium_nugget", Valkyris.getValkyris().getProxy().getCreativesTabs().materialCreativeTabs).register();
		
		cesiumBucket = new ItemCesiumLiquid(VBlocks.cesiumliquidb).setUnlocalizedName("cesium_bucket").setCreativeTab(ct.valoCreativeTabsUtils).setContainerItem(Items.bucket).setMaxStackSize(1).setTextureName(References.MODID + ":cesium_bucket");
		GameRegistry.registerItem(cesiumBucket, "cesium_bucket");
		
		cesiumDynamite = new ItemCesiumDynamite().setUnlocalizedName("cesium_dynamite").setTextureName(References.MODID + ":dyna_cesium").setCreativeTab(ct.valoCreativeTabsUtils);
		GameRegistry.registerItem(cesiumDynamite, "dynamite_tuto");
		
		esp_helmet = new ESPHelmet(nvmat, 0).setUnlocalizedName("esp_helmet").setTextureName(References.MODID + ":esp_helmet").setCreativeTab(ct.valoCreativeTabsUtils);
		GameRegistry.registerItem(esp_helmet, "esp_helmet");
		
		spongeStick = new ItemSpongeStick("sponge_stick").register();
		
		bronzekey = new ItemBronzeKey("bronzekey").register();
		argentkey = new ItemArgentKey("argentkey").register();
		goldkey = new ItemGoldKey("goldkey").register();
		dailykey = new ItemDailyKey("dailykey").register();
		votekey = new ItemVoteKey("votekey").register();
		
		GameRegistry.registerItem(valoSword, "valo_sword");
		GameRegistry.registerItem(valoPickaxe, "valo_pickaxe");
		GameRegistry.registerItem(valoAxe, "valo_axe");
		GameRegistry.registerItem(valoShovel, "valo_shovel");
		
		GameRegistry.registerItem(zircoSword, "zirco_sword");
		GameRegistry.registerItem(zircoPickaxe, "zirco_pickaxe");
		GameRegistry.registerItem(zircoAxe, "zirco_axe");
		GameRegistry.registerItem(zircoShovel, "zirco_shovel");
		GameRegistry.registerItem(zircoHoe, "zirco_hoe");
		
		GameRegistry.registerItem(cesiumSword, "cesium_sword");
		GameRegistry.registerItem(cesiumPickaxe, "cesium_pickaxe");
		GameRegistry.registerItem(cesiumAxe, "cesium_axe");
		GameRegistry.registerItem(cesiumShovel, "cesium_shovel");
		GameRegistry.registerItem(cesiumHoe, "cesium_hoe");
		
		GameRegistry.registerItem(iron_fragment, "iron_fragment");
		GameRegistry.registerItem(gold_fragment, "gold_fragment");
		GameRegistry.registerItem(obsidian_fragment, "obsidian_fragment");
		GameRegistry.registerItem(netherstars_fragment, "netherstars_fragment");
		GameRegistry.registerItem(quartz_fragment, "quartz_fragment");
		GameRegistry.registerItem(diamond_fragment, "diamond_fragment");
		GameRegistry.registerItem(emerald_fragment, "emerald_fragment");
		GameRegistry.registerItem(cesium_fragment, "cesium_fragment");
		GameRegistry.registerItem(zirconium_fragment, "zirconium_fragment");
		
		GameRegistry.registerItem(valo_fragment, "valo_fragment");
		
		valoArmors.init();
		
		hand = new ValoItem("hand", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		corne = new ValoItem("corne", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		wing = new ValoItem("wing", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		skull = new ValoItem("skull", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		valkyaxe = new ValoItem("valkyaxe", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		spiderleg = new ValoItem("spiderleg", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		mermaidtail = new ValoItem("mermaidtail", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		
		hammer = new ValoItem("hammer", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		gobelin = new ValoItem("gobelin", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		motherboard = new ValoItem("motherboard", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		barbare = new ValoItem("barbare", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		carnivore = new ValoItem("carnivore", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		fish = new ValoItem("fish", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		ghost = new ValoItem("ghost", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		titanhead = new ValoItem("titanhead", 1).register().setCreativeTab(ct.valoCreativeTabsCrafts);
		infinite_fruit = new ItemInfiniteFruit();
		superfuel = new ItemSuperFuel();
		
		valomanager = new ItemManager();
		GameRegistry.registerItem(valomanager, "valomanager");
		
//		enriched_cesium = new ValoItem("enriched_cesium", 64).register().setCreativeTab(ct.materialCreativeTabs);
//		enriched_zirconium = new ValoItem("enriched_zirconium", 64).register().setCreativeTab(ct.materialCreativeTabs);
		
		cesium_gem = new ItemCesiumGem();
		zirconium_gem = new ItemZirconiumGem();
		
		cesium_activator = new ItemCesiumActivator();
		zirconium_activator = new ItemZirconiumActivator();
		
		point_classement = new ValoItem("point_classement", 1).register().setCreativeTab(ct.valoCreativeTabsUtils);
		
		GameRegistry.registerItem(cesium_gem, "cesium_gem");
		GameRegistry.registerItem(zirconium_gem, "zirconium_gem");
		GameRegistry.registerItem(cesium_activator, "cesium_activator");
		GameRegistry.registerItem(zirconium_activator, "zirconium_activator");
		
		GameRegistry.registerItem(infinite_fruit, "infinite_fruit");
		GameRegistry.registerItem(superfuel, "superfuel");
		
		spawnerzombie = new ValoSpawner("spawner_zombie", EntityZombie.class).register();
		spawnerskeleton = new ValoSpawner("spawner_skeleton", EntitySkeleton.class).register();
		spawnerpigmen = new ValoSpawner("spawner_pigmen", EntityPigZombie.class).register();
		spawnerirongolem = new ValoSpawner("spawner_irongolem", EntityIronGolem.class).register();
		spawnerspider = new ValoSpawner("spawner_spider", EntitySpider.class).register();
		spawnercow = new ValoSpawner("spawner_cow", EntityCow.class).register();
		spawnerchicken = new ValoSpawner("spawner_chicken", EntityChicken.class).register();
			
		pillageKit = new ItemPillageKit().register();
		dailyKit = new ItemDailyKit().register();
		attackKit = new ItemAttackKit().register(); 
		defenceKit = new ItemDefenceKit().register();
		enchantKit = new ItemEnchantKit().register(); 
		farmerKit = new ItemFarmerKit().register();
		foodKit = new ItemFoodKit().register();
		travelKit = new ItemTravelKit().register();
		
		wateringCan1 = new ItemWateringCan1().setUnlocalizedName("watering_can_1").setTextureName(References.MODID + ":watering_can").setCreativeTab(ct.valoCreativeTabsUtils);
		wateringCan2 = new ItemWateringCan2().setUnlocalizedName("watering_can_2").setTextureName(References.MODID + ":watering_can").setCreativeTab(ct.valoCreativeTabsUtils);
		wateringCan3 = new ItemWateringCan3().setUnlocalizedName("watering_can_3").setTextureName(References.MODID + ":watering_can").setCreativeTab(ct.valoCreativeTabsUtils);
		
		GameRegistry.registerItem(wateringCan1, "watering_can_1");
		GameRegistry.registerItem(wateringCan2, "watering_can_2");
		GameRegistry.registerItem(wateringCan3, "watering_can_3");
		
		wings = new ValoItem("wings").setCreativeTab(ct.valoCreativeTabsCrafts);
		mandibles = new ValoItem("mandibles").setCreativeTab(ct.valoCreativeTabsCrafts);
		magic_powder = new ValoItem("magic_powder").setCreativeTab(ct.valoCreativeTabsCrafts);
		polen = new ValoItem("polen").setCreativeTab(ct.valoCreativeTabsCrafts);
		royal_wax = new ValoItem("royal_wax").setCreativeTab(ct.valoCreativeTabsCrafts);
		scales = new ValoItem("scales").setCreativeTab(ct.valoCreativeTabsCrafts);
		fins = new ValoItem("fins").setCreativeTab(ct.valoCreativeTabsCrafts);
		
		GameRegistry.registerItem(wings, "wings");
		GameRegistry.registerItem(mandibles, "mandibles");
		GameRegistry.registerItem(magic_powder, "magic_powder");
		GameRegistry.registerItem(polen, "polen");
		GameRegistry.registerItem(royal_wax, "royal_wax");
		GameRegistry.registerItem(scales, "scales");
		GameRegistry.registerItem(fins, "fins");
		
		
		camouflage = new ValoItem("camouflage").register();
		aqua_ingot = new ValoItem("aqua_ingot").register();
		
		cesiumArrow = new ValoItem("cesium_arrow").setTextureName(References.MODID + ":cesium_arrow").setCreativeTab(ct.valoCreativeTabsUtils);
		cesiumBow = new ItemCesiumBow().setUnlocalizedName("cesium_bow").setTextureName(References.MODID + ":cesium_bow").setCreativeTab(ct.valoCreativeTabsUtils);
		GameRegistry.registerItem(cesiumBow, "cesium_bow");
		GameRegistry.registerItem(cesiumArrow, "cesium_arrow");
		
		sizeUpStick = ((ValoItem) new ItemUpSizeStick("size_up_stick", ct.valoCreativeTabsUtils, 1).setMaxDamage(3)).register();
		sizeDownStick = ((ValoItem) new ItemDownSizeStick("size_down_stick", ct.valoCreativeTabsUtils, 1).setMaxDamage(3)).register();
		
		chargedCreeperStick = new ItemCreeperStick("charged_creeper_stick", ct.valoCreativeTabsUtils, 1, true).register();
		creeperStick = new ItemCreeperStick("creeper_stick", ct.valoCreativeTabsUtils, 1, false).register();
		
		itemObsidianDoor = new ItemObsidianDoor().register();		
	}

}
