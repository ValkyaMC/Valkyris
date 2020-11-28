package fr.valkya.valkyris.client.render.valkyrite;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderValorionAxe implements IItemRenderer {

	public IModelCustom model;
	ResourceLocation texture = new ResourceLocation(References.MODID, "textures/models/tools/valopickaxe.png");
	ResourceLocation obj = new ResourceLocation(References.MODID, "models/tools/valoaxe.obj");
	
	public RenderValorionAxe() {
		model = AdvancedModelLoader.loadModel(obj);
		
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
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScalef(0.9F, 0.9F, 0.9F);
	
		GL11.glRotatef(90, 0, 0, 1);
		GL11.glTranslatef(0F, -1F, 0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();
		
		GL11.glPopMatrix();
	}

}
