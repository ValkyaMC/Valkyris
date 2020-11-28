package fr.valkya.valkyris.client.gui;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class GuiMenu extends GuiScreen {
	
	private static final Random rand = new Random();
	private double time;
	protected List<GuiContainer> containerList;
	
	protected int FONT_HEIGHT = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
	
	public static final ResourceLocation[] background = new ResourceLocation[] {
			new ResourceLocation(References.MODID, "textures/gui/title/background1.jpg"),
			new ResourceLocation(References.MODID, "textures/gui/title/background2.jpg"),
			new ResourceLocation(References.MODID, "textures/gui/title/background3.jpg"),
			new ResourceLocation(References.MODID, "textures/gui/title/background4.jpg"),
			new ResourceLocation(References.MODID, "textures/gui/title/background5.jpg") };

	public static ResourceLocation currentBackground, fadingInBackground;
	
	@Override
	public void initGui() {
		this.time = System.currentTimeMillis();
		super.initGui();
	}
	
	protected void renderBackground() {
		
		GL11.glPushMatrix();
		if (currentBackground == null) {
			currentBackground = background[rand.nextInt(background.length)];
		}
		
		double current = (double) System.currentTimeMillis();
		double diff = Math.abs(time - current);
		double fin = diff / 100000;

		double scale = 1.2 - fin;

		double imWidth = Math.max(1, scale) * mc.displayWidth;
		double imHeight = Math.max(1, scale) * mc.displayHeight;

		double diffWidth = (imWidth - mc.displayWidth) / 2;
		double diffHeight = (imHeight - mc.displayHeight) / 2;

		int k = this.width;
		int l = this.height;

		GL11.glScaled(Math.max(1, scale), Math.max(1, scale), 1);
		this.mc.getTextureManager().bindTexture(currentBackground);
		GL11.glViewport((int) -diffWidth, (int) -diffHeight, (int) (this.mc.displayWidth + diffWidth), (int) (this.mc.displayHeight + diffHeight));

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
		tessellator.addVertexWithUV(0, 0, this.zLevel, 0, 0);
		tessellator.addVertexWithUV(0, l, this.zLevel, 0, 1);
		tessellator.addVertexWithUV(k, l, this.zLevel, 1, 1);
		tessellator.addVertexWithUV(k, 0, this.zLevel, 1, 0);
		tessellator.draw();

		if (scale <= 1.01) {
			if (fadingInBackground == null) {
				do {
					fadingInBackground = background[rand.nextInt(background.length)];
				} while (fadingInBackground == currentBackground);
			}
			double alpha = Math.min(1, (-((scale - 1) * 100)) + 1);

			scale = 1.2;
			imWidth = Math.max(1, scale) * mc.displayWidth;
			imHeight = Math.max(1, scale) * mc.displayHeight;

			diffWidth = (imWidth - mc.displayWidth) / 2;
			diffHeight = (imHeight - mc.displayHeight) / 2;

			GL11.glScaled(Math.max(1, scale), Math.max(1, scale), 1);
			this.mc.getTextureManager().bindTexture(fadingInBackground);

			GL11.glViewport((int) -diffWidth, (int) -diffHeight, (int) (this.mc.displayWidth + diffWidth),
					(int) (this.mc.displayHeight + diffHeight));

			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			tessellator.startDrawingQuads();
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, (float) alpha);
			tessellator.addVertexWithUV(0, 0, this.zLevel, 0, 0);
			tessellator.addVertexWithUV(0, l, this.zLevel, 0, 1);
			tessellator.addVertexWithUV(k, l, this.zLevel, 1, 1);
			tessellator.addVertexWithUV(k, 0, this.zLevel, 1, 0);
			tessellator.draw();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();

			if (alpha == 1) {
				time = System.currentTimeMillis();
				currentBackground = fadingInBackground;
				fadingInBackground = null;
			}
		}
		GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
		GL11.glPopMatrix();
	}

}
