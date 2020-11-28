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

public class ItemArgentKey extends ValoItem {
	
	public ItemArgentKey(String name) {
		super(name, Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsUtils, 1);
		this.setMaxDamage(16);
		this.setTextureName(References.MODID + ":argentkey");
		this.setUnlocalizedName("argentkey");
	}
	
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.GRAY + "Shift pour plus d'informations...");
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "Zirconium Loot Key");
			p_77624_3_.add("Loots Possibles :");
			p_77624_3_.add("");
			p_77624_3_.add("- 1 Kit Farmeur (10%)");
			p_77624_3_.add("- 3 Valkyrie Chest (10%)");
			p_77624_3_.add("- 10 Zirconium Chest (10%)");
			p_77624_3_.add("- 2 Bronze Loot Key (10%)");
			p_77624_3_.add("- 5 Enchant télékinésis (10%)");
			p_77624_3_.add("- 1 Ziconium Loot Key (5%)");
			p_77624_3_.add("- Chat Color (a vie) (5%)");
			p_77624_3_.add("- 1 Golem Spawners (1%)");
			p_77624_3_.add("- 1 Gold Loot Key (1%)");
			p_77624_3_.add("- /craft (a vie) (1%)");
			p_77624_3_.add("- 2 Ziconium Loot Key (1%)");
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
