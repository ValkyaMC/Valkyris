package fr.valkya.valkyris.common.items;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.valkya.valkyris.api.APIProvider;
import fr.valkya.valkyris.client.render.notification.NotificationManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public abstract class VItemExecutor extends ValoIdentifiableItem {

	public VItemExecutor(String name, CreativeTabs tab, int maxStackSize) {
		super(name, tab, maxStackSize);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		if(FMLCommonHandler.instance().getSide().isServer()) {
			FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager().executeCommand(FMLCommonHandler.instance().getMinecraftServerInstance(), getCommand(p_77659_1_, p_77659_2_, p_77659_3_));
		} else {
			if(NotificationManager.currentNotification != null) {
				APIProvider.provideAPI().getRenderer().sendInfoNotification(EnumChatFormatting.RED + "Notification Personnelle", "Ta faction gagne 1 point de classement supplémentaire!", 4);
			}
		}
		p_77659_1_.stackSize--;
		return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
	}
	
	public abstract String getCommand(ItemStack stack, World world, EntityPlayer player);
}
