package fr.valkya.valkyris.client.gui;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.common.container.ContainerGodForge;
import fr.valkya.valkyris.common.tile.TileEntityGodForge;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGodForge extends GuiContainer {
	
	private static final ResourceLocation bg = new ResourceLocation(References.MODID, "textures/gui/forge/god_forge.png");
	private static final ResourceLocation bar = new ResourceLocation(References.MODID, "textures/gui/forge/god_forge_bar.png");
	private TileEntityGodForge tileGodForge;
	
	public GuiGodForge(TileEntityGodForge tile, InventoryPlayer inventory) {
		super(new ContainerGodForge(tile, inventory));
		this.tileGodForge = tile;
		this.allowUserInput = false;
		this.ySize = 256;
		this.xSize = 256;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y) {
		this.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(bg);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		
		if(this.tileGodForge.getForgeCharge() > 0) {
			this.mc.getTextureManager().bindTexture(bar);
			
			double charge = this.tileGodForge.getForgeCharge();
			double width = charge / 150D * 138D;
			
			this.drawTexturedModalRect((int) (this.xSize * 0.38) + this.guiLeft, (int) (this.ySize * 0.524) + this.guiTop, 0, 0, (int) width, 8);
		}
		
		mc.fontRenderer.drawStringWithShadow("Charge: " + tileGodForge.getForgeCharge(), (int) (this.xSize * 0.38) + this.guiLeft + (138 / 2) - mc.fontRenderer.getStringWidth("Charge: " + tileGodForge.getForgeCharge()) / 2, (int) (this.ySize * 0.524) + this.guiTop, -1);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		this.drawString(fontRendererObj, "", 1, 1, 435216);
	}
}
