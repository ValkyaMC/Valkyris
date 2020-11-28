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

public class ItemVoteKey extends ValoItem {
	
	public ItemVoteKey(String name) {
		super(name, Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsUtils, 1);
		this.setMaxDamage(16);
		this.setTextureName(References.MODID + ":votekey");
		this.setUnlocalizedName("votekey");
	}
	
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.GRAY + "Shift pour plus d'informations...");
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "Vote Loot Key");
			p_77624_3_.add("Loots Possibles :");
			p_77624_3_.add("");
			p_77624_3_.add("- 2 Kit Food (20%)");
			p_77624_3_.add("- 1 Arrosoir Niv1 (10%)");
			p_77624_3_.add("- 2 Césium Chest (10%");
			p_77624_3_.add("- 1 Daily Loot Key (10%");
			p_77624_3_.add("- 1 Iron Hammer (5%)");
			p_77624_3_.add("- 5 Pommes D'or (5%)");
			p_77624_3_.add("- 10 Pommes D'or (2%)");
			p_77624_3_.add("- 1 Zirconium Chest (1%)");
			p_77624_3_.add("- 2 Zirconium Ingot (1%)");
			p_77624_3_.add("- 2500$ (1%)");
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
