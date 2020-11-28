package fr.valkya.valkyris.common.tile.chest;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.common.block.VBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class VTileEntityValkyriteChest extends VTileEntityChest {

	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(References.MODID + ":" + "textures/models/block/model_valkyrite_chest.png");
	private static final ResourceLocation GUI_TEXTURE_LOCATION = new ResourceLocation(References.MODID + ":" + "textures/gui/container/valkyrite_chest.png");
	
	@Override
	public Block getBlock() {
		return VBlocks.valoChest;
	}
	
	@Override
	public int getRowCount() {
		return 9;
	}

	@Override
	public int getRowLength() {
		return 12;
	}

	@Override
	public ResourceLocation getModelTexture() {
		return TEXTURE_LOCATION;
	}
	
	@Override
	public ResourceLocation getGuiTexture() {
		return GUI_TEXTURE_LOCATION;
	}

	@Override
	public int[] getTextureSizes() {
		return new int[] { 238, 256 };
	}

}
