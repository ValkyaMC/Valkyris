package fr.valkya.valkyris.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCreeperStick extends ValoStickItem {

	private boolean isCharged;

	public ItemCreeperStick(String name, CreativeTabs tab, int maxStackSize, boolean isCharged) {
		super(name, tab, maxStackSize, isCharged ? 4 : 8, 200);
		this.isCharged = isCharged;
	}

	@Override
	public void doEffect(ItemStack it, World w, EntityPlayer p) {
		if(!w.isRemote) {
			EntityCreeper ec = new EntityCreeper(w);
			ec.setLocationAndAngles(p.posX, p.posY, p.posZ, p.cameraYaw, p.cameraPitch);
			if(isCharged) {
				ec.getDataWatcher().updateObject(17, Byte.valueOf((byte)1));
			}
			w.spawnEntityInWorld(ec);
		}
	}

}
