package fr.valkya.valkyris.common.items;

import java.util.List;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemInfiniteFruit extends ItemFood {

	public ItemInfiniteFruit() {
		super(20, 1.2F, false);
		this.setMaxDamage(64);
		this.setUnlocalizedName("infinite_fruit");
		this.setTextureName(References.MODID + ":infinite_fruit");
		this.setMaxStackSize(1);
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsUtils);
	}
	
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
			p_77624_3_.add("Durabilitée : " + (p_77624_1_.getMaxDamage() - p_77624_1_.getItemDamage()) + " / " + p_77624_1_.getMaxDamage());
			super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}
	
	@Override
	public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
		p_77654_3_.getFoodStats().func_151686_a(this, p_77654_1_);
        p_77654_2_.playSoundAtEntity(p_77654_3_, "random.burp", 0.5F, p_77654_2_.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(p_77654_1_, p_77654_2_, p_77654_3_);
        p_77654_1_.damageItem(1, p_77654_3_);
        return p_77654_1_;
	}

}
