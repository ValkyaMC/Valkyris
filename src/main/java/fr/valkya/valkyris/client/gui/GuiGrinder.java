package fr.valkya.valkyris.client.gui;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.common.container.ContainerGrinder;
import fr.valkya.valkyris.common.tile.TileEntityGrinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGrinder extends GuiContainer {
	
    private static final ResourceLocation texture = new ResourceLocation(References.MODID + ":" + "textures/gui/gui_compressor.png");
    private TileEntityGrinder tile;
    
    public GuiGrinder(TileEntityGrinder tile, InventoryPlayer inventory)
    {
        super(new ContainerGrinder(tile, inventory));
        this.tile = tile;
        this.allowUserInput = false;
        
        this.ySize = 166;
        this.xSize = 176;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
    {    	
    	int k = (this.width - this.xSize) / 2;
    	int l = (this.height - this.ySize) / 2;
    	
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	
    	this.mc.getTextureManager().bindTexture(texture);        
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        
        if(tile.isBurning()) {
        	
        }
        
//        if(tile.canSmelt()) {
//        	
//        }
        
//        this.drawTexturedModalRect(0, 0, 176, 14, 80, 25);
    }

    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        this.fontRendererObj.drawStringWithShadow("Compresseur", this.xSize / 2 - fontRendererObj.getStringWidth("Compresseur") / 2, 7, -1);
    }
}