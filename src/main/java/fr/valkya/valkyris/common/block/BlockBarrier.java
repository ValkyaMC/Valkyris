package fr.valkya.valkyris.common.block;

import java.util.Random;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;

public class BlockBarrier extends Block {
	
	IIcon blockCrea;

	protected BlockBarrier() {
		super(Material.rock);
		setBlockUnbreakable();
		setResistance(6000000F);
		setLightOpacity(0);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		this.blockIcon = icon.registerIcon(References.MODID + ":invisible_block");
		this.blockCrea = icon.registerIcon(References.MODID + ":invisible_blockcrea");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(FMLClientHandler.instance().getClientPlayerEntity().capabilities.isCreativeMode) return this.blockCrea;
		return this.blockIcon;
	}
	
	@Override
	public boolean isOpaqueCube() { return false; }
	
	@Override
	public boolean renderAsNormalBlock() { return false; }
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass() {
		return 1;
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World w, int x, int y, int z) {
		return ReflectionHelper.getPrivateValue(PlayerControllerMP.class, Minecraft.getMinecraft().playerController, "currentGameType", "field_78779_k") == GameType.CREATIVE ? super.getSelectedBoundingBoxFromPool(w, x, y, z) : AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.0D,0.0D, 0.0D);
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random) { return 0; }
	
	@Override
	public boolean canDropFromExplosion(Explosion e) { return false; }
	
	@Override
	public void onBlockDestroyedByExplosion(World w, int x, int y, int z, Explosion e) { w.setBlock(x, y, z, this); }
	
	@Override
	public void onBlockExploded(World w, int x, int y, int z, Explosion e) { w.setBlock(x, y, z, this); }
	
	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) { return entity != null && entity instanceof EntityPlayer ? true : false; }
}
