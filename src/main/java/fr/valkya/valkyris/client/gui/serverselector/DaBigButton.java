package fr.valkya.valkyris.client.gui.serverselector;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.client.gui.serverselector.data.ServerCategory;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;

public class DaBigButton extends GuiButton {

	public ServerCategory category = ServerCategory.FACTION;
	private Animation animation;
	
	public DaBigButton(ServerCategory defaultCategory, int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
		super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
		this.animation = new Animation(0, 0);
		this.category = defaultCategory;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(this.visible) {
			field_146123_n = mouseX >= xPosition && mouseX <= xPosition + width && mouseY >= yPosition && mouseY <= yPosition + height;
			
			boolean left = field_146123_n && mouseX <= xPosition + width / 6;
			boolean right = field_146123_n && mouseX >= xPosition + width - (width / 6);
			
			Gui.drawRect(xPosition, yPosition, (int) (xPosition + width), yPosition + height, 0xFFFFB715);
			
			animation.interpolate(left ? (width / 6) : right ? -(width / 6) : 0, 0, 2);
			
			GL11.glPushMatrix();
			
			double scale = 2;
			GL11.glTranslated(((xPosition + 25) / 2), (yPosition + height / 2), 0);
			GL11.glScaled(scale, scale, 0);
			mc.fontRenderer.drawStringWithShadow("<", 0, - mc.fontRenderer.FONT_HEIGHT / 2, -1);
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			GL11.glTranslated(((xPosition + width) - 25), (yPosition + height / 2), 0);
			GL11.glScaled(scale, scale, 0);
			mc.fontRenderer.drawStringWithShadow(">", 0, -mc.fontRenderer.FONT_HEIGHT / 2, -1);
			GL11.glPopMatrix();
			
			drawGradiant(xPosition, yPosition, (int) (xPosition + (width / 12) + (Math.max(0, animation.getX()))), yPosition + height, 0xCC000000, 0x00000000);
			drawGradiant((int) (xPosition + width - (width / 12 - (Math.min(0, animation.getX())))), yPosition, xPosition + width, yPosition + height, 0x00000000, 0xCC000000);
			
			GL11.glPushMatrix();
			
			int posX = xPosition + width / 2;
			
			double val = (height / 2);
			int posY = (int) (yPosition + (val));
			
			scale = 2;
			GL11.glTranslated(posX, posY, 0);
			GL11.glScaled(scale, scale, 0);
			GL11.glColor4d(1, 1, 1, 1);
			
			mc.fontRenderer.drawStringWithShadow(category.getName(), -mc.fontRenderer.getStringWidth(category.getName()) / 2, -mc.fontRenderer.FONT_HEIGHT / 2, -1);
			
			GL11.glPopMatrix();
		}
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		boolean field_146123_n = mouseX >= xPosition && mouseX <= xPosition + width && mouseY >= yPosition && mouseY <= yPosition + height;
		
		boolean left = field_146123_n && mouseX <= xPosition + width / 6;
		boolean right = field_146123_n && mouseX >= xPosition + width - (width / 6);
		
		ServerCategory cat = this.category;
		if(left) {
			switch(this.category) {
				case FACTION:
					this.category = ServerCategory.ALL;
					break;
				case ALL:
//					this.category = ServerCategory.OTHER;
//					break;
//				case OTHER:
					this.category = ServerCategory.RESOURCE;
					break;
				case RESOURCE:
					this.category = ServerCategory.FACTION;
					break;
			}
		}else if(right) {
			switch(this.category) {
				case FACTION:
					this.category = ServerCategory.RESOURCE;
					break;
				case RESOURCE:
//					this.category = ServerCategory.OTHER;
//					break;
//				case OTHER:
					this.category = ServerCategory.ALL;
					break;
				case ALL:
					this.category = ServerCategory.FACTION;
					break;
			}
		}
		if(cat != this.category) {
			mc.displayGuiScreen(new GuiServerSelector(this.category));
		}
		
		return left || right;
	}
	
	private void drawGradiant(int x, int y, int x2, int y2, int color1, int color2) {
		float f = (float)(color1 >> 24 & 255) / 255.0F;
        float f1 = (float)(color1 >> 16 & 255) / 255.0F;
        float f2 = (float)(color1 >> 8 & 255) / 255.0F;
        float f3 = (float)(color1 & 255) / 255.0F;
        float f4 = (float)(color2 >> 24 & 255) / 255.0F;
        float f5 = (float)(color2 >> 16 & 255) / 255.0F;
        float f6 = (float)(color2 >> 8 & 255) / 255.0F;
        float f7 = (float)(color2 & 255) / 255.0F;
        
		GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(f1, f2, f3, f);
        tessellator.addVertex((double)x, (double)y, (double)zLevel);
        tessellator.addVertex((double)x, (double)y2, (double)zLevel);
        tessellator.setColorRGBA_F(f5, f6, f7, f4);
        tessellator.addVertex((double)x2, (double)y2, (double)this.zLevel);
        tessellator.addVertex((double)x2, (double)y, (double)this.zLevel);
        tessellator.draw();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
}
