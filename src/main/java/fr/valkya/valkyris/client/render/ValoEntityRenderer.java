package fr.valkya.valkyris.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.client.manager.ClientSizeManager;
import fr.valkya.valkyris.common.entity.EntitySize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;

@SideOnly(Side.CLIENT)
public class ValoEntityRenderer extends EntityRenderer {

	private Minecraft mc;

	public ValoEntityRenderer(Minecraft mc) {
		super(mc, mc.getResourceManager());
		this.mc = mc;
	}
	
	@Override
	public void updateCameraAndRender(float tick) {
		if ((this.mc.thePlayer == null) || (this.mc.thePlayer.isPlayerSleeping())) {
			super.updateCameraAndRender(tick);
			return;
		}
		
		EntitySize size = ClientSizeManager.INSTANCE.getSize(this.mc.thePlayer);
	    if(size == EntitySize.GIANT) {
	    	float ySize = -(1.62F - 1.62F * size.getSize());
		    
		    this.mc.thePlayer.yOffset -= ySize;
		    this.mc.thePlayer.eyeHeight = -0.7F;
		    
		    super.updateCameraAndRender(tick);
		    
		    this.mc.thePlayer.yOffset = 1.62F;
		    this.mc.thePlayer.eyeHeight = this.mc.thePlayer.getDefaultEyeHeight();
		    
	    } else {
	    	float ySize = -(1.62F - 1.62F * size.getSize());
		    
		    this.mc.thePlayer.yOffset -= ySize;
		    this.mc.thePlayer.eyeHeight = -0.7F;
		    
		    super.updateCameraAndRender(tick);
		    
		    this.mc.thePlayer.yOffset = 1.62F;
		    this.mc.thePlayer.eyeHeight = this.mc.thePlayer.getDefaultEyeHeight();
	    }
	}
	
	@Override
	public void getMouseOver(float tick) {
		if ((this.mc.thePlayer == null) || (this.mc.thePlayer.isPlayerSleeping())) {
			super.getMouseOver(tick);
			return;
		}
		
		EntitySize size = ClientSizeManager.INSTANCE.getSize(mc.thePlayer);
	    if(size == EntitySize.GIANT) {
	    	float offsetY = -3.99F;
		    
		    this.mc.thePlayer.posY -= offsetY;
		    this.mc.thePlayer.prevPosY -= offsetY;
		    this.mc.thePlayer.lastTickPosY -= offsetY;
		    this.mc.thePlayer.eyeHeight = -0.7F;
		    
		    super.getMouseOver(tick);
		    this.mc.thePlayer.posY += offsetY;
		    
		    this.mc.thePlayer.prevPosY += offsetY;
		    this.mc.thePlayer.lastTickPosY += offsetY;
	    } else if(size == EntitySize.SMALL){
			float offsetY = 0.65000004F;
		    
		    this.mc.thePlayer.posY -= offsetY;
		    this.mc.thePlayer.prevPosY -= offsetY;
		    this.mc.thePlayer.lastTickPosY -= offsetY;
		    this.mc.thePlayer.eyeHeight = -0.7F;
		    
		    super.getMouseOver(tick);
		    this.mc.thePlayer.posY += offsetY;
		    
		    this.mc.thePlayer.prevPosY += offsetY;
		    this.mc.thePlayer.lastTickPosY += offsetY;
	    }
	}

}
