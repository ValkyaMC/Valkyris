package fr.valkya.valkyris.common.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemManager extends Item {
	
	public ItemManager() {
		this.setUnlocalizedName("valomanager");
		this.setTextureName(References.MODID + ":valomanager");
		this.setMaxStackSize(1);
		this.setMaxDamage(1);
		this.setNoRepair();
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().machineCreativeTabs);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			((ClientProxy)Valkyris.getValkyris().getProxy()).showValoManager();
		}
		return itemStack;
	}

}
