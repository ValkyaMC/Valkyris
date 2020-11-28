package fr.valkya.valkyris.common.armor;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ValoArmors {
	
	public ItemArmor valoHelmet, valoChestplate, valoLeggins, valoBoots;
	public ItemArmor zircoHelmet, zircoChestplate, zircoLeggins, zircoBoots;
	public ItemArmor cesiumHelmet, cesiumChestplate, cesiumLeggins, cesiumBoots;
	public ItemArmor camouflageHelmet, camouflageChestplate, camouflageLeggins, camouflageBoots;
	public ItemArmor travelHelmet, travelChestplate, travelLeggings, travelBoots;

	public static ArmorMaterial valoArmor = EnumHelper.addArmorMaterial("valoArmor", 80, new int[] {6, 9, 6, 5}, 10);
	public static ArmorMaterial zircoArmor = EnumHelper.addArmorMaterial("zircoArmor", 65, new int[] {5, 9, 6, 4}, 10);
	public static ArmorMaterial cesiumArmor = EnumHelper.addArmorMaterial("cesiumArmor", 45, new int[] {4, 8, 6, 4}, 10);
	public static ArmorMaterial aquaArmor = EnumHelper.addArmorMaterial("aquaArmor", 15, new int[] {2, 5, 4, 1}, 6);
	public static ArmorMaterial camouflageArmor = EnumHelper.addArmorMaterial("camouflageArmor", 10, new int[] {2, 6, 4, 2}, 5);
	
	public void init() {
		valoHelmet = new ItemCommonValoArmor(valoArmor, 0, "valoHelmet", ":valo_helmet").register();
		valoChestplate = new ItemCommonValoArmor(valoArmor, 1, "valoChestplate", ":valo_chestplate").register();
		valoLeggins = new ItemCommonValoArmor(valoArmor, 2, "valoLeggings", ":valo_leggings").register();
		valoBoots = new ItemCommonValoArmor(valoArmor, 3, "valoBoots", ":valo_boots").register();
		
		zircoHelmet = new ItemCommonZircoArmor(zircoArmor, 0, "zircoHelmet", ":zirconium_helmet").register();
		zircoChestplate = new ItemCommonZircoArmor(zircoArmor, 1, "zircoChestplate", ":zirconium_chestplate").register();
		zircoLeggins = new ItemCommonZircoArmor(zircoArmor, 2, "zircoLeggings", ":zirconium_leggings").register();
		zircoBoots = new ItemCommonZircoArmor(zircoArmor, 3, "zircoBoots", ":zirconium_boots").register();
		
		cesiumHelmet = new ItemCommonCesiumArmor(cesiumArmor, 0, "cesiumHelmet", ":cesium_helmet").register();
		cesiumChestplate = new ItemCommonCesiumArmor(cesiumArmor, 1, "cesiumChestplate", ":cesium_chestplate").register();
		cesiumLeggins = new ItemCommonCesiumArmor(cesiumArmor, 2, "cesiumLeggings", ":cesium_leggings").register();
		cesiumBoots = new ItemCommonCesiumArmor(cesiumArmor, 3, "cesiumBoots", ":cesium_boots").register();
		
		travelHelmet = new ItemCommonAquaArmor(aquaArmor, 0, "aquahelmet", ":aqua_helmet").register();
		travelChestplate = new ItemCommonAquaArmor(aquaArmor, 1, "aquachestplate", ":aqua_chestplate").register();
		travelLeggings = new ItemCommonAquaArmor(aquaArmor, 2, "aqualeggings", ":aqua_leggings").register();
		travelBoots = new ItemCommonAquaArmor(aquaArmor, 3, "aquaboots", ":aqua_boots").register();
		
		camouflageHelmet = new ItemCommonCamouflageArmor(camouflageArmor, 0, "camouflagehelmet", ":camouflage_helmet").register();
		camouflageChestplate = new ItemCommonCamouflageArmor(camouflageArmor, 1, "camouflagechestplate", ":camouflage_chestplate").register();
		camouflageLeggins = new ItemCommonCamouflageArmor(camouflageArmor, 2, "camouflageleggings", ":camouflage_leggings").register();
		camouflageBoots = new ItemCommonCamouflageArmor(camouflageArmor, 3, "camouflageboots", ":camouflage_boots").register();
		
		new ItemCommonTestArmor().register();
	}

}
