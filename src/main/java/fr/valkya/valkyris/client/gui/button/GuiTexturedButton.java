package fr.valkya.valkyris.client.gui.button;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiTexturedButton extends GuiButton {

	private ResourceLocation ICON;
    private ResourceLocation ICON_HOVER;
    private Integer U = 0;
    private Integer W = 0;

    public GuiTexturedButton(int buttonId, int x, int y, int u,int w, ResourceLocation icon, ResourceLocation icon_hover){
        super(buttonId, x, y, u, w, "");
        this.ICON = icon;
        this.ICON_HOVER = icon_hover;
        this.U = u;
        this.W = w;
    }
    public GuiTexturedButton(int buttonId, int x, int y, int u,int w, ResourceLocation icon){
        super(buttonId, x, y, u, w, "");
        this.ICON = icon;
        this.U = u;
        this.W = w;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY){
        if(this.visible){

            boolean mouseHover = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            if(ICON_HOVER == null) {
	            mc.getTextureManager().bindTexture(ICON);
	            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	            Gui.func_152125_a(this.xPosition, this.yPosition, 0, 0, 128, 128, U, W, 128, 128);
            }else {
            	if(mouseHover){
	                mc.getTextureManager().bindTexture(ICON_HOVER);
	            }else{
	                mc.getTextureManager().bindTexture(ICON);
	            }
	            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	            Gui.func_152125_a(this.xPosition, this.yPosition, 0, 0, 128, 128, U, W, 128, 128);
            }
        }

    }

}
