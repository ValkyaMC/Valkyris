package fr.valkya.valkyris.client.render;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderTileGodForge extends TileEntitySpecialRenderer implements IItemRenderer{
	
	ResourceLocation texture;
	ResourceLocation objModel;
	IModelCustom model;
	
	public RenderTileGodForge() {
		texture = new ResourceLocation(References.MODID, "textures/models/block/godforge.png");
		objModel = new ResourceLocation(References.MODID, "models/block/godforge.obj");
		model = AdvancedModelLoader.loadModel(objModel);
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float timeSinceLastTick) {
		bindTexture(texture);
		float scale = 0.08F;
		
		GL11.glPushMatrix();
		{
			GL11.glTranslated(x, y - 0.15F, z + 1);
			GL11.glScalef(scale, scale, scale);
			GL11.glRotatef(0F, 0F, 1F, 0.5F);
			model.renderAll();
		}
		GL11.glPopMatrix();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON:
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(0.07F, 0.07F, 0.07F);
			
			GL11.glTranslatef(0, 0, 6);
			GL11.glTranslatef(0, 2, 0);

			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			model.renderAll();
		}
		GL11.glPopMatrix();
	}

}
