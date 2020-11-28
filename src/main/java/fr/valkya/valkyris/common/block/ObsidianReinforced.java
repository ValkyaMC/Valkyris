package fr.valkya.valkyris.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.tile.TileEntityObsidianReinforced;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ObsidianReinforced extends Block {

	public ObsidianReinforced() {
		super(Material.rock);
		this.setBlockName("obsidian_reinforced");
		this.setBlockTextureName(References.MODID + ":obsidian/reinforced_obsidian");
		this.setResistance(2000.0F);
		this.setBlockUnbreakable();
		this.setCreativeTab(Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsObsidian);
	}
	
	public Block register() {
		GameRegistry.registerBlock(this, getUnlocalizedName().substring(5, getUnlocalizedName().length()));
		return this;
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if(!w.isRemote) {
			if(w.getTileEntity(x, y, z) instanceof TileEntityObsidianReinforced) {
				if(player.getHeldItem() == null || player.getHeldItem().getItem() == null) {
					if(((TileEntityObsidianReinforced) w.getTileEntity(x, y, z)).getOwner().equalsIgnoreCase(player.getUniqueID().toString())) {
						w.setBlock(x, y, z, Blocks.air);
						w.spawnEntityInWorld(new EntityItem(w, x, y, z, new ItemStack(VBlocks.obsidianReinforced)));
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack stack) {
		if(w.getTileEntity(x, y, z) instanceof TileEntityObsidianReinforced) ((TileEntityObsidianReinforced) w.getTileEntity(x, y, z)).setOwner(e.getUniqueID().toString());
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileEntityObsidianReinforced();
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
}
