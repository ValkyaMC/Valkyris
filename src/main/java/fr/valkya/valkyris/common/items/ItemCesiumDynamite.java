package fr.valkya.valkyris.common.items;

import fr.valkya.valkyris.common.entity.EntityDynamiteCesium;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCesiumDynamite extends Item {

	public ItemCesiumDynamite() {
		this.setMaxStackSize(64);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote) {
			world.spawnEntityInWorld(new EntityDynamiteCesium(world, player));
			stack.stackSize--;
		}
		return stack;
	}

}
