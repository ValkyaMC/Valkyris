package fr.valkya.valkyris.common.items.keys;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.items.ValoItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemDailyKey extends ValoItem {
	
	public ItemDailyKey(String name) {
		super(name, Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsUtils, 1);
		this.setMaxDamage(1);
		this.setTextureName(References.MODID + ":dailykey");
		this.setUnlocalizedName("dailykey");
	}
	
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.GRAY + "Shift pour plus d'informations...");
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "Daily Loot Key");
			p_77624_3_.add("Loots Possibles :");
			p_77624_3_.add("");
			p_77624_3_.add("- 3 Seed Accelerator (10%)");
			p_77624_3_.add("- 10 Iron Seeds (10%)");
			p_77624_3_.add("- 1 Césium Hoe (10%)");
			p_77624_3_.add("- 1 Kit daily (10%)");
			p_77624_3_.add("- 2 Césium Chest (10%)");
			p_77624_3_.add("- 1 Vote Loot Key (10%)");
			p_77624_3_.add("- 32 Bottle XP (10%)");
			p_77624_3_.add("- 1000$ (10%)");
			p_77624_3_.add("- 64 Bottle XP (5%)");
			p_77624_3_.add("- 5 Random Ore (5%)");
			p_77624_3_.add("- 1 Livre P4 (5%)");
			p_77624_3_.add("- 1 Livre U3 (5%)");
			p_77624_3_.add("- 1 Daily Loot Key (5%)");
			p_77624_3_.add("- 2500$ (5%)");
			p_77624_3_.add("- 5000$ (2%)");
			p_77624_3_.add("- 2 Daily Loot Key (2%)");
			p_77624_3_.add("- 3 Césium Seeds (2%)");
			p_77624_3_.add("- 3 Zirconium Seeds (1%)");
			p_77624_3_.add("");
			p_77624_3_.add("Clique sur l'item pour gagner un des loots !");
		}
		super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack item) {
		return true;
	}

}
