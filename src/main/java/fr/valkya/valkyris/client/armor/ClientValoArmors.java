package fr.valkya.valkyris.client.armor;

import fr.valkya.valkyris.client.config.ClientConfig;
import fr.valkya.valkyris.common.armor.ItemCommonAquaArmor;
import fr.valkya.valkyris.common.armor.ItemCommonCamouflageArmor;
import fr.valkya.valkyris.common.armor.ItemCommonCesiumArmor;
import fr.valkya.valkyris.common.armor.ItemCommonTestArmor;
import fr.valkya.valkyris.common.armor.ItemCommonValoArmor;
import fr.valkya.valkyris.common.armor.ItemCommonZircoArmor;
import fr.valkya.valkyris.common.armor.ValoArmors;

public class ClientValoArmors extends ValoArmors {
	
	@Override
	public void init() {
		
		if(ClientConfig.armortoggle) {
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
		} else {
			
			valoHelmet = new ItemClientValoArmor(valoArmor, 0, "valoHelmet", ":valo_helmet").register();
			valoChestplate = new ItemClientValoArmor(valoArmor, 1, "valoChestplate", ":valo_chestplate").register();
			valoLeggins = new ItemClientValoArmor(valoArmor, 2, "valoLeggings", ":valo_leggings").register();
			valoBoots = new ItemClientValoArmor(valoArmor, 3, "valoBoots", ":valo_boots").register();
			
			zircoHelmet = new ItemClientZircoArmor(zircoArmor, 0, "zircoHelmet", ":zirconium_helmet").register();
			zircoChestplate = new ItemClientZircoArmor(zircoArmor, 1, "zircoChestplate", ":zirconium_chestplate").register();
			zircoLeggins = new ItemClientZircoArmor(zircoArmor, 2, "zircoLeggings", ":zirconium_leggings").register();
			zircoBoots = new ItemClientZircoArmor(zircoArmor, 3, "zircoBoots", ":zirconium_boots").register();
			
			cesiumHelmet = new ItemClientCesiumArmor(cesiumArmor, 0, "cesiumHelmet", ":cesium_helmet").register();
			cesiumChestplate = new ItemClientCesiumArmor(cesiumArmor, 1, "cesiumChestplate", ":cesium_chestplate").register();
			cesiumLeggins = new ItemClientCesiumArmor(cesiumArmor, 2, "cesiumLeggings", ":cesium_leggings").register();
			cesiumBoots = new ItemClientCesiumArmor(cesiumArmor, 3, "cesiumBoots", ":cesium_boots").register();
			
		}
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
