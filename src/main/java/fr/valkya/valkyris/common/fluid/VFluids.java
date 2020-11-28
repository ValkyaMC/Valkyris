
package fr.valkya.valkyris.common.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class VFluids {

	public static Fluid cesium_liquid;

	public static void init() {

		cesium_liquid = new Fluid("cesium_liquid").setDensity(4000).setViscosity(500).setTemperature(286)
				.setLuminosity(10).setUnlocalizedName("cesium_liquid");
		FluidRegistry.registerFluid(cesium_liquid);
		cesium_liquid = FluidRegistry.getFluid("cesium_liquid");
	}

}
