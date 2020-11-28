package fr.valkya.valkyris.common.block;

import java.util.Random;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.block.BlockOre;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class RandomBlockOre extends BlockOre {

	public RandomBlockOre() {
		this.setBlockName("random_ore");
		this.setBlockTextureName(References.MODID + ":" + "random_ore");
	}

	public Item getItemDropped(int metadata, Random random, int fortune) {
		switch (random.nextInt(6)) {
			case 0:
				return Items.iron_ingot;
			case 1:
				return Items.gold_ingot;
			case 2:
				return Items.diamond;
			case 3:
				return Items.emerald;
			case 4:
				return VItems.cesiumIngot;
			case 5:
				return VItems.zirconiumIngot;

			default:
				return Items.iron_ingot;
		}

	}
	
	private static Random rand = new Random();
	
	@Override
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_) {
        if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) != Item.getItemFromBlock(this)) {
        	return MathHelper.getRandomIntegerInRange(rand, 7, 14);
        }
		return p_149690_7_;
    }
	
}
