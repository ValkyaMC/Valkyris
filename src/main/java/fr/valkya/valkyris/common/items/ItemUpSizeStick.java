package fr.valkya.valkyris.common.items;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.entity.EntitySize;
import fr.valkya.valkyris.server.ServerProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUpSizeStick extends ValoStickItem {

	public ItemUpSizeStick(String name, CreativeTabs tab, int maxStackSize) {
		super(name, tab, maxStackSize, 3, 1200);
	}

	@Override
	public void doEffect(ItemStack it, World w, EntityPlayer player) {
		if(!w.isRemote) {
			((ServerProxy)Valkyris.getValkyris().getProxy()).getSizeManager().add((EntityPlayerMP)player, EntitySize.GIANT, 15);
		}
	}
}
