package fr.valkya.valkyris.common.block.crops;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ValoCrop extends BlockCrops {

	private float chance = 1F;
	private String name;
	private boolean hasSeedAccelerator;
	
	public ValoCrop(String name, float chance) {
		setTickRandomly(false);
		setBlockBounds(0F, 0F, 0F, 1F, 0.5F, 1F);
		setStepSound(soundTypeGrass);
		disableStats();
		setHardness(0.0F);
		setBlockName(name);
		
		this.name = name;
		this.chance = chance / 2;
	}
	
	@Override
	public int getRenderType() { return 1; }
	
	@Override
	public void updateTick(World w, int x, int y, int z, Random r) {				
		if (w.getBlockLightValue(x, y + 1, z) >= 9) {
            int l = w.getBlockMetadata(x, y, z);
                        
            if (l < 7) {
                if(w.getBlock(x, y - 2, z) instanceof BlockSeedAccelerator) hasSeedAccelerator = true;
                else hasSeedAccelerator = false;
                
                if (r.nextFloat() <= (hasSeedAccelerator ? chance * 2 : chance) / 100) {
                    w.setBlockMetadataWithNotify(x, y, z, l + 1, 2);
                }
            }
        }
		w.scheduleBlockUpdate(x, y, z, this, this.tickRate(w));
	}
	
	@Override
	public void onBlockAdded(World w, int x, int y, int z) {
		w.scheduleBlockUpdate(x, y, z, this, this.tickRate(w));
		super.onBlockAdded(w, x, y, z);
	}
	
	@Override
	public int tickRate(World w) {
		return 30;
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(!w.isRemote) {
			int meta = w.getBlockMetadata(x, y, z);
			boolean flag = false;
			for(ItemStack it : p.inventory.mainInventory) {
				if(it == null || (it.getItem() == this.func_149865_P() && it.stackSize < it.getMaxStackSize())) {
					flag = true;
					break;
				}
			}
			if(meta >= 7) {
				if(flag) {
					p.inventory.addItemStackToInventory(new ItemStack(func_149865_P()));
					((EntityPlayerMP) p).sendContainerToPlayer(p.inventoryContainer);
				}else {
					EntityItem ei;
					w.spawnEntityInWorld(ei = new EntityItem(w, x, y, z, new ItemStack(func_149865_P())));
					ei.delayBeforeCanPickup = 0;
					ei.func_145797_a(p.getCommandSenderName());
				}
				w.setBlockMetadataWithNotify(x, y, z, 0, 2);
			}
		}
		return false;
	}
	
	@Override
	protected Item func_149866_i() {
		return getSeed();
	}
	
	@Override
	protected Item func_149865_P() {
		return getCrop();
	}

	protected abstract Item getCrop();
	protected abstract Item getSeed();
	
	@Override
	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
		return super.func_149851_a(p_149851_1_, p_149851_2_, p_149851_3_, p_149851_4_, p_149851_5_);
	}
	
	@Override
	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return super.func_149852_a(p_149852_1_, p_149852_2_, p_149852_3_, p_149852_4_, p_149852_5_);
	}
	
	@Override
	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
		super.func_149853_b(p_149853_1_, p_149853_2_, p_149853_3_, p_149853_4_, p_149853_5_);
	}
	
	public Block register() {
		GameRegistry.registerBlock(this, name);
		return this;
	}
	
//	public void onFallenUpon(World w, int x, int y, int z, Entity e, float fallHeight) {
//	    if (!w.isRemote && w.rand.nextFloat() < fallHeight - 0.5F) {
//	        if (!(e instanceof EntityPlayer) && !w.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
//	            return;
//	        }
//	        w.setBlock(x, y+1, z, null);
//	    }
//	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
		ArrayList<ItemStack> ret = new ArrayList<>();
		ret.add(new ItemStack(this.func_149866_i(), 1, 0));
		
        if (metadata >= 7)
        {
            for (int i = 0; i < 1 + (fortune > 0 ? 1 : 0); ++i)
            {
            	ret.add(new ItemStack(this.func_149865_P(), 1, 0));
            }
        }

        return ret;
    }

}
