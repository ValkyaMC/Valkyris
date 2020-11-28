package fr.valkya.valkyris.common.block;

import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.tile.TileEntityGodForge;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class GodForge extends BlockContainer {

	public GodForge() {
		super(Material.wood);
		
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().machineCreativeTabs);
		this.setBlockName("godforge");
	}
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
        if(world.isRemote){
            return true;
        }else{
            player.openGui(Valkyris.getValkyris(), 0, world, x, y, z);
            return true;
        }
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {    	
    	TileEntity te = world.getTileEntity(x, y, z);
    	if(te instanceof IInventory) {
    		IInventory inv = (IInventory) te;
    		
    		for(int i1 = 0; i1 < inv.getSizeInventory(); ++i1) {
    			ItemStack itemstack = inv.getStackInSlot(i1);
    			
    			if(itemstack != null) {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem = null;
                    
                    for(float f2 = world.rand.nextFloat() *0.8F +0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
                    	int j1 = world.rand.nextInt(21) + 10;
                    	
                    	if(j1 > itemstack.stackSize) {
                    		j1 = itemstack.stackSize;
                    	}
                    	
                    	itemstack.stackSize -= j1;
                    	entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                    	entityitem.motionX = (double)((float)world.rand.nextGaussian() * 3);
                    	entityitem.motionY = (double)((float)world.rand.nextGaussian() * 3 + 0.2F);
                    	entityitem.motionZ = (double)((float)world.rand.nextGaussian() * 3);
                    	
                    	if(itemstack.hasTagCompound()) {
                    		entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                    	}
                    }
                    
    			}
    		}
    		world.func_147453_f(x, y, z, block);
    	}
    	super.breakBlock(world, x, y, z, block, metadata);
    }

    @Override
    public boolean renderAsNormalBlock(){
        return false;
    }

    @Override
    public int getRenderType(){
        return -1;
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }
    
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGodForge();
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY - 0.30D, (double)z + this.maxZ);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY - 0.30D, (double)z + this.maxZ);
	}
	
}
