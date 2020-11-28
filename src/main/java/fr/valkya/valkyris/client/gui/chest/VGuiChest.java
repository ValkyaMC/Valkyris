package fr.valkya.valkyris.client.gui.chest;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.common.tile.chest.VTileEntityChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class VGuiChest extends GuiContainer {

	private final ResourceLocation texture;

	public VGuiChest(VTileEntityChest tile, InventoryPlayer inventory) {
		super(tile.newContainer(inventory, tile.getTextureSizes()[0], tile.getTextureSizes()[1]));
		this.allowUserInput = false;
		this.ySize = tile.getTextureSizes()[1];
		this.xSize = tile.getTextureSizes()[0];
		
		this.texture = tile.getGuiTexture();
	}

	public void initGui() {
		super.initGui();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}