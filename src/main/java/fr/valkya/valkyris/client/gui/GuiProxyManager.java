package fr.valkya.valkyris.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.gui.button.GuiTexturedButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiProxyManager extends GuiScreen {

	public void initGui() {
		
		Minecraft mc = Minecraft.getMinecraft();
		
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
		this.buttonList.add(new GuiTexturedButton(301, 25, height / 3, width / 3, height / 3, new ResourceLocation(References.MODID, "textures/gui/button_mi1.png"), new ResourceLocation(References.MODID, "textures/gui/button_mi1_over.png")));
		this.buttonList.add(new GuiTexturedButton(302, 250, height / 3, width / 3, height / 3, new ResourceLocation(References.MODID, "textures/gui/button_mi2.png"), new ResourceLocation(References.MODID, "textures/gui/button_mi2_over.png")));
		this.buttonList.add(new GuiTexturedButton(303, 450, height / 3, width / 3, height / 3, new ResourceLocation(References.MODID, "textures/gui/button_mi3.png"), new ResourceLocation(References.MODID, "textures/gui/button_mi3_over.png")));
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
//		mc.getTextureManager().bindTexture(new ResourceLocation(References.MODID, "textures/gui/background.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//		Gui.func_152125_a(0, 0, 0, 0, 1, 1, width, height, 1, 1);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	protected void actionPerformed(GuiButton button) {
		
		if(button.id == 301) {
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/1v69knb6m19zb44");
			mc.thePlayer.closeScreen();
		}else if(button.id == 302) {
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/3w4gl90666ojt2x");
			mc.thePlayer.closeScreen();
		}else if(button.id == 303) {
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/3276ew8r950xclg");
			mc.thePlayer.closeScreen();
		}
	}
	
	public static void drawLogo(double p_1521250, double p_1521251, float p_1521252, float p_1521253, int p_1521254, int p_1521255, double p_1521256, double p_1521257, float p_1521258, float p_1521259)
    {
        float f4 = 1.0F / p_1521258;
        float f5 = 1.0F / p_1521259;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)p_1521250, (double)(p_1521251 + p_1521257), 0.0D, (double)(p_1521252 * f4), (double)((p_1521253 + (float)p_1521255) * f5));
        tessellator.addVertexWithUV((double)(p_1521250 + p_1521256), (double)(p_1521251 + p_1521257), 0.0D, (double)((p_1521252 + (float)p_1521254) * f4), (double)((p_1521253 + (float)p_1521255) * f5));
        tessellator.addVertexWithUV((double)(p_1521250 + p_1521256), (double)p_1521251, 0.0D, (double)((p_1521252 + (float)p_1521254) * f4), (double)(p_1521253 * f5));
        tessellator.addVertexWithUV((double)p_1521250, (double)p_1521251, 0.0D, (double)(p_1521252 * f4), (double)(p_1521253 * f5));
        tessellator.draw();
    }
}
