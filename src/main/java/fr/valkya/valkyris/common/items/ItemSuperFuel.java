package fr.valkya.valkyris.common.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.IFuelHandler;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemSuperFuel extends Item implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		return 36000;
	}
	
	public ItemSuperFuel() {
		this.setUnlocalizedName("superfuel");
		this.setTextureName(References.MODID + ":superfuel");
		this.setMaxStackSize(16);
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().materialCreativeTabs);
	}
	
	
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add("Shift pour plus d'informations...");
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "SuperFuel");
			p_77624_3_.add("Utilisable pour  : ");
			p_77624_3_.add("- Four / Smoker / BlastFurnace");
			p_77624_3_.add("- GodForge");
		}
		super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}

}
