package fr.valkya.valkyris.client.render.blocks;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderItemGodForge implements IItemRenderer {
	
	ResourceLocation texture;
	ResourceLocation objModel;
	IModelCustom model;
	
	public RenderItemGodForge() {
		texture = new ResourceLocation(References.MODID, "textures/models/block/godforge.png");
		objModel = new ResourceLocation(References.MODID, "models/block/godforge.obj");
		model = AdvancedModelLoader.loadModel(objModel);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch (type) {
		case INVENTORY:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();
		
		GL11.glPopMatrix();
	}

}
