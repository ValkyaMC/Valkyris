package fr.valkya.valkyris.client.render.zirconium;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderZirconiumHoe implements IItemRenderer {
	
	public IModelCustom model;
	ResourceLocation texture = new ResourceLocation(References.MODID, "textures/models/tools/zirconium/zirconium_hoe.png");
	ResourceLocation obj = new ResourceLocation(References.MODID, "models/tools/zirconium/zirconium_hoe.obj");
	
	public RenderZirconiumHoe() {
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
		GL11.glScalef(0.2F, 0.2F, 0.2F);		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		if(type == ItemRenderType.FIRST_PERSON_MAP) {
			
			GL11.glTranslatef(6F, 1F, -5F);			
			GL11.glRotatef(90F, 0, 1, 1);
			
		}else {
			
			GL11.glTranslatef(4.3F, -1.5F, -0.7F);
			GL11.glRotatef(180, 0, 1, 0); //Lame � l'envers
		}
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();
		
		GL11.glPopMatrix();
	}

}

