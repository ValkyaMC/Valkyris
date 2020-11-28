package fr.valkya.valkyris.client.render;

import org.lwjgl.opengl.GL11;

import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;
import cpw.mods.fml.common.Optional;
import fr.valkya.valkyris.client.manager.ClientSizeManager;
import fr.valkya.valkyris.common.entity.EntitySize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.OpenGlHelper;

@Optional.InterfaceList({
	@cpw.mods.fml.common.Optional.Interface(iface="api.player.render.RenderPlayerAPI", modid="RenderPlayerAPI", striprefs=true), 
	@cpw.mods.fml.common.Optional.Interface(iface="api.player.render.RenderPlayerBase", modid="RenderPlayerAPI", striprefs=true)
})
public class PlayerRenderer extends RenderPlayerBase {

	public PlayerRenderer(RenderPlayerAPI arg0) {
		super(arg0);
	}
	
	@Override
	public void renderPlayerScale(AbstractClientPlayer player, float arg1) {
		EntitySize size = ClientSizeManager.INSTANCE.getSize(player);
		if (size.isSmall()) {
			
			GL11.glDisable(2896);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
			GL11.glTranslatef(0.0F, -1.458F, 0.0F);
			GL11.glScalef(0.1F, 0.1F, 0.1F);
			if (player.isSneaking()) GL11.glTranslatef(0.0F, -0.7F, 0.0F);
			if (player != Minecraft.getMinecraft().thePlayer) GL11.glTranslatef(0.0F, 14.5F, 0.0F);
			GL11.glEnable(2896);
			
		} else if(size.isGiant()) {
			
			GL11.glDisable(2896);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
			GL11.glTranslatef(0.0F, 3.0F, 0.0F);
			GL11.glScalef(3.0F, 3.0F, 3.0F);
			if (player != Minecraft.getMinecraft().thePlayer) GL11.glTranslatef(0.0F, -1F, 0.0F);
			GL11.glEnable(2896);
			
		} else {
			super.renderPlayerScale(player, arg1);
		}
	}

}
