package fr.valkya.valkyris.common.items;

import java.util.List;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;

public class ValoIdentifiableItem extends ValoItem {

	public ValoIdentifiableItem(String name, CreativeTabs tab, int maxStackSize) {
		super(name, tab, maxStackSize);
	}

	@Override
	public void onUpdate(ItemStack it, World w, Entity e, int slot, boolean inHand) {
		if (!it.hasTagCompound()) {
			it.setTagCompound(new NBTTagCompound());
		}
		if (!it.stackTagCompound.hasKey("uuid")) {
			it.stackTagCompound.setString("uuid", UUID.randomUUID().toString());
		}
		if (!it.stackTagCompound.hasKey("owner") && e instanceof EntityPlayer) {
			it.stackTagCompound.setString("owner",
					((EntityPlayer) e).getDisplayName() + ":" + ((EntityPlayer) e).getUniqueID());
		}
		if (!it.stackTagCompound.hasKey("players")) {
			it.stackTagCompound.setTag("players", new NBTTagCompound());
		} else if (e instanceof EntityPlayer) {
			NBTTagCompound tag = it.stackTagCompound.getCompoundTag("players");
			if (tag.hasKey(((EntityPlayer) e).getDisplayName() + ":" + ((EntityPlayer) e).getUniqueID()))
				return;
			tag.setString(((EntityPlayer) e).getDisplayName() + ":" + ((EntityPlayer) e).getUniqueID(), "");
		}
	}

	@Override
	public void addInformation(ItemStack it, EntityPlayer player, List list, boolean p_77624_4_) {
		GameType gm = ObfuscationReflectionHelper.getPrivateValue(PlayerControllerMP.class,
				Minecraft.getMinecraft().playerController, "currentGameType", "field_78779_k");
		if (gm.isCreative() && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			if (it != null && it.stackTagCompound != null) {
				if (it.stackTagCompound.hasKey("uuid")) {
					list.add("§cUUID : §r" + it.stackTagCompound.getString("uuid"));
				}
				if (it.stackTagCompound.hasKey("owner")) {
					list.add("§cOwner : §r" + it.stackTagCompound.getString("owner"));
				}
				if (it.stackTagCompound.hasKey("players") && it.stackTagCompound.getTag("players") != null) {
					NBTTagCompound tag = (NBTTagCompound) it.stackTagCompound.getTag("players");
					list.add("§cPlayers :§r");
					int in = 0;
					for (Object i : tag.func_150296_c()) {
						list.add("§6" + in + ".§r " + i);
						in++;
					}
				}
			}
		}
	}

}
