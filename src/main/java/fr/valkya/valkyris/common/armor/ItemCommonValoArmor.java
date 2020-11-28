package fr.valkya.valkyris.common.armor;

import java.util.List;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.config.ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCommonValoArmor extends ItemArmor {

	public ItemCommonValoArmor(ArmorMaterial mat, int type, String name, String texture) {
		super(mat, 0, type);
		this.setUnlocalizedName(name);
		this.setTextureName(References.MODID + texture);
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().armorCreativeTabs);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {		
		return null;
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

		if (armorType == 2) {
			return References.MODID + ":textures/models/armor/valkyrite_layer_2.png";
		} else {
			return References.MODID + ":textures/models/armor/valkyrite_layer_1.png";
		}
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
					((EntityPlayer) e).getDisplayName() + ":::" + ((EntityPlayer) e).getUniqueID());
		}
		if (!it.stackTagCompound.hasKey("players")) {
			it.stackTagCompound.setTag("players", new NBTTagCompound());
		} else if (e instanceof EntityPlayer) {
			NBTTagCompound tag = it.stackTagCompound.getCompoundTag("players");
			if (tag.hasKey(((EntityPlayer) e).getDisplayName() + ":::" + ((EntityPlayer) e).getUniqueID()))
				return;
			tag.setString(((EntityPlayer) e).getDisplayName() + ":::" + ((EntityPlayer) e).getUniqueID(), "");
		}
	}

	@Override
	public void addInformation(ItemStack it, EntityPlayer player, List list, boolean p_77624_4_) {
		if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			if (it.hasTagCompound()) {
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
	
	public ItemCommonValoArmor register() {
		GameRegistry.registerItem(this, getUnlocalizedName().substring("tamer".length()));
		return this;
	}
	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return false;
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		ItemStack helmet = player.getEquipmentInSlot(4);


		if (helmet != null && helmet.getItem() == this) {
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 20 * 15, 0));
		}
	}

}
