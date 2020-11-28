package fr.valkya.valkyris.client.render.chest;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.common.block.chest.VBlockChest;
import fr.valkya.valkyris.common.tile.chest.VTileEntityChest;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class VChestRenderer<T extends VTileEntityChest, K extends VBlockChest> extends TileEntitySpecialRenderer {

	private final ModelChest field_147510_h = new ModelChest();

	private final Class<K> chestClazz;
	private final ResourceLocation field_147504_g;

	public VChestRenderer(Class<K> chestClazz, ResourceLocation chestTexture) {
		this.chestClazz = chestClazz;
		this.field_147504_g = chestTexture;
	}

	public void renderTileEntityAt(T p_147502_1_, double p_147502_2_, double p_147502_4_, double p_147502_6_,
			float p_147502_8_) {
		int i;

		if (!p_147502_1_.hasWorldObj()) {
			i = 0;
		} else {
			Block block = p_147502_1_.getBlockType();
			i = p_147502_1_.getBlockMetadata();

			if (chestClazz.isInstance(block) && i == 0) {
				try {
					((K) block).func_149951_m(p_147502_1_.getWorldObj(), p_147502_1_.xCoord, p_147502_1_.yCoord,
							p_147502_1_.zCoord);
				} catch (ClassCastException e) {
					FMLLog.severe("Attempted to render a chest at %d, %d, %d that was not a chest", p_147502_1_.xCoord,
							p_147502_1_.yCoord, p_147502_1_.zCoord);
				}
				i = p_147502_1_.getBlockMetadata();
			}
		}

		ModelChest modelchest;

		modelchest = this.field_147510_h;
		this.bindTexture(field_147504_g);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) p_147502_2_, (float) p_147502_4_ + 1.0F, (float) p_147502_6_ + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short short1 = 0;

		if (i == 2)
			short1 = 180;
		if (i == 3)
			short1 = 0;
		if (i == 4)
			short1 = 90;
		if (i == 5)
			short1 = -90;

		GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float f1 = p_147502_1_.prevLidAngle + (p_147502_1_.lidAngle - p_147502_1_.prevLidAngle) * p_147502_8_;

		f1 = 1.0F - f1;
		f1 = 1.0F - f1 * f1 * f1;
		modelchest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
		modelchest.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_,
			float p_147500_8_) {
		this.renderTileEntityAt((T) p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
	}
}