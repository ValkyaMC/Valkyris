package fr.valkya.valkyris.common.items.kits;

import java.util.ArrayList;
import java.util.List;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.render.notification.NotificationType;
import fr.valkya.valkyris.common.items.ValoIdentifiableItem;
import fr.valkya.valkyris.common.network.packets.PacketNotify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public abstract class ItemKit extends ValoIdentifiableItem {
	
	protected List<ItemStack> kitItems;
	
	public ItemKit(String kitName) {
		super(kitName + "_kit", Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsUtils, 1);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(this.kitItems == null) {
			this.kitItems = new ArrayList<>();
			initKit();
		}

		ItemStack[] inv = player.inventory.mainInventory;
		int placesVides = 0;
		for (int i = 0; i < inv.length; i++) {
			if (inv[i] == null || inv[i].getItem() == null) {
				placesVides++;
			}
		}

		int itemNbr = kitItems.size();
		if (placesVides < itemNbr) {
			if (!world.isRemote) { 
				int missingSlots = (itemNbr - placesVides);
				Valkyris.getValkyris().getProxy().getNetwork() .sendTo(new PacketNotify(EnumChatFormatting.GOLD + "Kits", "Ton inventaire est plein ! Il te manque " + missingSlots + " place" + (missingSlots != 1 ? "s" : "") + " !", NotificationType.WARNING, 3), (EntityPlayerMP) player);
			}
		} else {
			stack.stackSize--;
			
			for(ItemStack is : kitItems) {
				int size = is.stackSize;
				player.inventory.addItemStackToInventory(is);
				is.stackSize = size;
			}
		}

		return stack;
	}
	
	protected abstract void initKit();
		
		// template
//		ItemStack zircoSword = new ItemStack(ValoItems.zircoSword);
//		zircoSword.addEnchantment(Enchantment.sharpness, 2); 
//		this.kitItems.add(zircoSword);
//
//		this.kitItems.add(new ItemStack(Items.cooked_beef, 16));	

}
