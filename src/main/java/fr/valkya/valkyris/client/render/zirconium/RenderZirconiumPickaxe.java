package fr.valkya.valkyris.client.render.zirconium;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderZirconiumPickaxe implements IItemRenderer {
	
	public IModelCustom model;
	ResourceLocation texture = new ResourceLocation(References.MODID, "textures/models/tools/zirconium/zirconium_pickaxe.png");
	ResourceLocation obj = new ResourceLocation(References.MODID, "models/tools/zirconium/zirconium_pickaxe.obj");
	
	public RenderZirconiumPickaxe() {
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
			GL11.glRotatef(90F, 0, 0, 1);
			
		}else {
			
			GL11.glTranslatef(6F, 0.8F, 0.3F);
			GL11.glRotatef(90, 0, 0, 1); 
			
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();
		
		GL11.glPopMatrix();
	}

}
}
