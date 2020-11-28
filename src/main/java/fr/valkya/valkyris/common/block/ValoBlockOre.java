package fr.valkya.valkyris.common.block;

import java.util.Random;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;

public class ValoBlockOre extends BlockOre{
	
	public ValoBlockOre() {
		this.setBlockName("valorion_ore");
		this.setBlockTextureName(References.MODID + ":" + "valorion_ore");
		
	}
	
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return VItems.valo_fragment;
    }
	

	
//	@SideOnly(Side.CLIENT)
//    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
//    {
//         this.func_150186_m(p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_);
//    }
//
//    private void func_150186_m(World p_150186_1_, int p_150186_2_, int p_150186_3_, int p_150186_4_)
//    {
//        Random random = p_150186_1_.rand;
//        double d0 = 0.0625D;
//
//        for (int l = 0; l < 6; ++l)
//        {
//            double d1 = (double)((float)p_150186_2_ + random.nextFloat());
//            double d2 = (double)((float)p_150186_3_ + random.nextFloat());
//            double d3 = (double)((float)p_150186_4_ + random.nextFloat());
//
//            if (l == 0 && !p_150186_1_.getBlock(p_150186_2_, p_150186_3_ + 1, p_150186_4_).isOpaqueCube())
//            {
//                d2 = (double)(p_150186_3_ + 1) + d0;
//            }
//
//            if (l == 1 && !p_150186_1_.getBlock(p_150186_2_, p_150186_3_ - 1, p_150186_4_).isOpaqueCube())
//            {
//                d2 = (double)(p_150186_3_ + 0) - d0;
//            }
//
//            if (l == 2 && !p_150186_1_.getBlock(p_150186_2_, p_150186_3_, p_150186_4_ + 1).isOpaqueCube())
//            {
//                d3 = (double)(p_150186_4_ + 1) + d0;
//            }
//
//            if (l == 3 && !p_150186_1_.getBlock(p_150186_2_, p_150186_3_, p_150186_4_ - 1).isOpaqueCube())
//            {
//                d3 = (double)(p_150186_4_ + 0) - d0;
//            }
//
//            if (l == 4 && !p_150186_1_.getBlock(p_150186_2_ + 1, p_150186_3_, p_150186_4_).isOpaqueCube())
//            {
//                d1 = (double)(p_150186_2_ + 1) + d0;
//            }
//
//            if (l == 5 && !p_150186_1_.getBlock(p_150186_2_ - 1, p_150186_3_, p_150186_4_).isOpaqueCube())
//            {
//                d1 = (double)(p_150186_2_ + 0) - d0;
//            }
//
//            if (d1 < (double)p_150186_2_ || d1 > (double)(p_150186_2_ + 1) || d2 < 0.0D || d2 > (double)(p_150186_3_ + 1) || d3 < (double)p_150186_4_ || d3 > (double)(p_150186_4_ + 1))
//            {
//                p_150186_1_.spawnParticle("portal", d1, d2, d3, 0.0D, 0.0D, 0.0D);
//            }
//        }
//    }

}
