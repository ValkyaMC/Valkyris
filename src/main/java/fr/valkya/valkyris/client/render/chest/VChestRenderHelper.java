package fr.valkya.valkyris.client.render.chest;

import java.util.Map;

import com.google.common.collect.Maps;

import fr.valkya.valkyris.common.block.chest.VBlockChest;
import fr.valkya.valkyris.common.tile.chest.VTileEntityChest;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class VChestRenderHelper extends TileEntityRendererChestHelper {

	private final Map<VBlockChest, VTileEntityChest> cached = Maps.newHashMap();
	
	@Override
    public void renderChest(Block p_147715_1_, int p_147715_2_, float p_147715_3_) {
    	if(p_147715_1_ instanceof VBlockChest) {
    		if(!cached.containsKey(p_147715_1_)) {
    			cached.put((VBlockChest)p_147715_1_, (VTileEntityChest)((VBlockChest) p_147715_1_).createNewTileEntity(null, 0));
    		}
    		TileEntityRendererDispatcher.instance.renderTileEntityAt(cached.get(p_147715_1_), 0.0D, 0.0D, 0.0D, 0.0F);
    	}else {
    		super.renderChest(p_147715_1_, p_147715_2_, p_147715_3_);
    	}
    }
	
}
