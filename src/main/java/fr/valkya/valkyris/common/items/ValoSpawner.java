package fr.valkya.valkyris.common.items;

import fr.valkya.valkyris.Valkyris;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class ValoSpawner extends ValoIdentifiableItem {
	
	private Class<?> class1;
	
	public ValoSpawner(String name, Class<?> class1) {
		super(name, Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsSpawner, 1);
		this.class1 = class1;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		--stack.stackSize;
		player.inventoryContainer.detectAndSendChanges();
		TileEntityMobSpawner tileEntityMobSpawner = new TileEntityMobSpawner();
		
		switch(side) {
			case 0:
				world.setBlock(x, y - 1, z, Blocks.mob_spawner);
				world.setTileEntity(x, y - 1, z, tileEntityMobSpawner);
				break;
			case 1:
				world.setBlock(x, y + 1, z, Blocks.mob_spawner);
				world.setTileEntity(x, y + 1, z, tileEntityMobSpawner);
				break;
			case 2:
				world.setBlock(x, y, z - 1, Blocks.mob_spawner);
				world.setTileEntity(x, y, z - 1, tileEntityMobSpawner);
				break;
			case 3:
				world.setBlock(x, y, z + 1, Blocks.mob_spawner);
				world.setTileEntity(x, y, z + 1, tileEntityMobSpawner);
				break;
			case 4:
				world.setBlock(x - 1, y, z, Blocks.mob_spawner);
				world.setTileEntity(x - 1, y, z, tileEntityMobSpawner);
				break;
			case 5:
				world.setBlock(x + 1, y, z, Blocks.mob_spawner);
				world.setTileEntity(x + 1, y, z, tileEntityMobSpawner);
				break;
			default:
				world.setBlock(x, y, z, Blocks.mob_spawner);
				world.setTileEntity(x, y, z, tileEntityMobSpawner);
				break;
		}
		tileEntityMobSpawner.func_145881_a().setEntityName(this.getEntityString(class1));
		return true;
	}
	

	private String getEntityString(Class<?> class12) {
		return (String) EntityList.classToStringMapping.get(class12);
	}

}
