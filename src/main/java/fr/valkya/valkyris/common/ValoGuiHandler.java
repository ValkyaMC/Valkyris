package fr.valkya.valkyris.common;

import cpw.mods.fml.common.network.IGuiHandler;
import fr.valkya.valkyris.client.gui.GuiGodForge;
import fr.valkya.valkyris.client.gui.GuiGrinder;
import fr.valkya.valkyris.common.container.ContainerGodForge;
import fr.valkya.valkyris.common.container.ContainerGrinder;
import fr.valkya.valkyris.common.tile.TileEntityGodForge;
import fr.valkya.valkyris.common.tile.TileEntityGrinder;
import fr.valkya.valkyris.common.tile.chest.VTileEntityChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ValoGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		
		if(tile instanceof TileEntityGodForge) {
			return new ContainerGodForge((TileEntityGodForge) tile, player.inventory);
		}
		
		if(tile instanceof VTileEntityChest) {
			VTileEntityChest tileEntityChest = (VTileEntityChest)tile;
			return tileEntityChest.newContainer(player.inventory, 0, 0);
		}
		
		if(tile instanceof TileEntityGrinder)
        {
            return new ContainerGrinder((TileEntityGrinder)tile, player.inventory);
        }
		return null;
		
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		
		if(tile instanceof TileEntityGodForge) {
			return new GuiGodForge((TileEntityGodForge) tile, player.inventory);
		}

		if(tile instanceof VTileEntityChest) {
			return ((VTileEntityChest)tile).newGui(player.inventory);
		}
		
        if(tile instanceof TileEntityGrinder)
        {
            return new GuiGrinder((TileEntityGrinder)tile, player.inventory);
        }
		return null;
	}
	
	

}
