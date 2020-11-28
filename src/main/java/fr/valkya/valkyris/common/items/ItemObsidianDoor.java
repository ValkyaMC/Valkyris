package fr.valkya.valkyris.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.block.VBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemObsidianDoor extends ItemDoor {

	public ItemObsidianDoor() {
		super(Material.rock);
		this.setUnlocalizedName("obsidian_door_item");
		this.setTextureName(References.MODID + ":obsidian_door");
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsObsidian);
	}

	public Item register() {
		GameRegistry.registerItem(this, getUnlocalizedName().substring(5));
		return this;
	}

	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if (p_77648_7_ != 1) return false;

		++p_77648_5_;
		Block block = VBlocks.obsidianDoor;
		if (p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_ + 1, p_77648_6_, p_77648_7_, p_77648_1_)) {
			if (!block.canPlaceBlockAt(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_))	return false;
			
			int i1 = MathHelper.floor_double((double) ((p_77648_2_.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
			
			placeDoorBlock(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, i1, block);
			
			--p_77648_1_.stackSize;
			return true;
		}
		return false;
	}
}
