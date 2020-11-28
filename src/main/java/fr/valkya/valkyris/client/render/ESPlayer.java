package fr.valkya.valkyris.client.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.valkya.valkyris.api.APIProvider;
import fr.valkya.valkyris.common.manager.ESPManager;
import fr.valkya.valkyris.common.tile.TileEntityGodForge;
import fr.valkya.valkyris.common.tile.TileEntityGrinder;
import fr.valkya.valkyris.common.tile.chest.VTileEntityCesiumChest;
import fr.valkya.valkyris.common.tile.chest.VTileEntityValkyriteChest;
import fr.valkya.valkyris.common.tile.chest.VTileEntityZircoChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class ESPlayer {
	
	private boolean lastState = true;
	
	@SubscribeEvent
	public void onWorldRender(RenderWorldLastEvent ev) {
		boolean state = ESPManager.timer.hasReached(ESPManager.COOLDOWN);
		if(lastState != state) {
			if(state) {
				APIProvider.provideAPI().getRenderer().sendInfoNotification("§cNotification Personnelle", "Le cooldown du casque est terminé.", 4);
			}
		}
		lastState = state;
		
		if (ESPManager.espEnabled) {
			if(ESPManager.timer.hasReached(25_000)) {
				ESPManager.espEnabled = false;
				APIProvider.provideAPI().getRenderer().sendInfoNotification("§cNotification Personnelle", "Le casque s'est désactivé.", 3);
			}
			
			if (Minecraft.getMinecraft().theWorld != null) {
				for (int i = 0; i < Minecraft.getMinecraft().theWorld.loadedTileEntityList.size(); ++i) {
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof TileEntityChest) {
						TileEntityChest chest = (TileEntityChest) Minecraft.getMinecraft().theWorld.loadedTileEntityList
								.get(i);
						if (chest.func_145980_j() == 0) {
							this.drawChestESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
									(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
									(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
									ev.partialTicks);
						} else {
							this.drawTrappedChestESP(
									(double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
									(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
									(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
									ev.partialTicks);
						}
					}

					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof TileEntityEnderChest) {
						TileEntityEnderChest chest = (TileEntityEnderChest) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawEnderChestESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}
					
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof VTileEntityCesiumChest) {
						VTileEntityCesiumChest chest = (VTileEntityCesiumChest) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawCesiumChestESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}
					
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof VTileEntityZircoChest) {
						VTileEntityZircoChest chest = (VTileEntityZircoChest) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawZircoChestESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}
					
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof VTileEntityValkyriteChest) {
						VTileEntityValkyriteChest chest = (VTileEntityValkyriteChest) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawValoChestESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}
					
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof TileEntityDispenser) {
						TileEntityDispenser chest = (TileEntityDispenser) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawMachinesESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}
					
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof TileEntityDropper) {
						TileEntityDropper chest = (TileEntityDropper) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawMachinesESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}
										
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof TileEntityGodForge) {
						TileEntityGodForge chest = (TileEntityGodForge) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawMachinesESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}
					
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof TileEntityGrinder) {
						TileEntityGrinder chest = (TileEntityGrinder) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawMachinesESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}
					
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof TileEntityHopper) {
						TileEntityHopper chest = (TileEntityHopper) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawMachinesESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}
					
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof TileEntityFurnace) {
						TileEntityFurnace chest = (TileEntityFurnace) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawMachinesESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}
					
					
					if (Minecraft.getMinecraft().theWorld.loadedTileEntityList.get(i) instanceof TileEntityMobSpawner) {
						TileEntityMobSpawner chest = (TileEntityMobSpawner) Minecraft
								.getMinecraft().theWorld.loadedTileEntityList.get(i);
						this.drawSpwanersESP((double) chest.xCoord - Minecraft.getMinecraft().thePlayer.lastTickPosX,
								(double) chest.yCoord - Minecraft.getMinecraft().thePlayer.lastTickPosY,
								(double) chest.zCoord - Minecraft.getMinecraft().thePlayer.lastTickPosZ,
								ev.partialTicks);
					}	
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onRender2D(RenderGameOverlayEvent e) {
		if(e.type == ElementType.ALL) {
			if(ESPManager.espEnabled) {
				Gui.drawRect(0, 0, e.resolution.getScaledWidth(), e.resolution.getScaledHeight(), 0x2200FF00);
			}
		}
	}

	private void drawChestESP(double x, double y, double z, float renderPartialTicks) {
		Entity entity = Minecraft.getMinecraft().thePlayer;
		final double d = entity.lastTickPosX + (x)
                - RenderManager.renderPosX;
        final double d1 = entity.lastTickPosY + (y)
                - RenderManager.renderPosY;
        final double d2 = entity.lastTickPosZ + (z)
                - RenderManager.renderPosZ;
		
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(0.5F);
		GL11.glDisable(3553);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glColor4f(181.0F, 45.0F, 0.0F, 0.2F);
		drawBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
		drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glDepthMask(true);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
	}

	private void drawTrappedChestESP(double x, double y, double z, float renderPartialTicks) {
		Entity entity = Minecraft.getMinecraft().thePlayer;
		final double d = entity.lastTickPosX + (x)
                - RenderManager.renderPosX;
        final double d1 = entity.lastTickPosY + (y)
                - RenderManager.renderPosY;
        final double d2 = entity.lastTickPosZ + (z)
                - RenderManager.renderPosZ;
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(0.5F);
		GL11.glDisable(3553);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glColor4f(255.0F, 0.0F, 0.0F, 0.2F);
		drawBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
		drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glDepthMask(true);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
	}

	private void drawEnderChestESP(double x, double y, double z, float renderPartialTicks) {
		Entity entity = Minecraft.getMinecraft().thePlayer;
		final double d = entity.lastTickPosX + (x)
                - RenderManager.renderPosX;
        final double d1 = entity.lastTickPosY + (y)
                - RenderManager.renderPosY;
        final double d2 = entity.lastTickPosZ + (z)
                - RenderManager.renderPosZ;
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(0.5F);
		GL11.glDisable(3553);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
		drawBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
		drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glDepthMask(true);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
	}
	
	private void drawCesiumChestESP(double x, double y, double z, float renderPartialTicks) {
		Entity entity = Minecraft.getMinecraft().thePlayer;
		final double d = entity.lastTickPosX + (x)
                - RenderManager.renderPosX;
        final double d1 = entity.lastTickPosY + (y)
                - RenderManager.renderPosY;
        final double d2 = entity.lastTickPosZ + (z)
                - RenderManager.renderPosZ;
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(0.5F);
		GL11.glDisable(3553);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glColor4f(0.0F, 255.0F, 0.0F, 0.2F);
		drawBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
		drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glDepthMask(true);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
	}
	
	private void drawZircoChestESP(double x, double y, double z, float renderPartialTicks) {
		Entity entity = Minecraft.getMinecraft().thePlayer;
		final double d = entity.lastTickPosX + (x)
                - RenderManager.renderPosX;
        final double d1 = entity.lastTickPosY + (y)
                - RenderManager.renderPosY;
        final double d2 = entity.lastTickPosZ + (z)
                - RenderManager.renderPosZ;
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(0.5F);
		GL11.glDisable(3553);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glColor4f(232.0F, 110.0F, 73.0F, 0.2F);
		drawBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
		drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glDepthMask(true);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
	}
	
	private void drawValoChestESP(double x, double y, double z, float renderPartialTicks) {
		Entity entity = Minecraft.getMinecraft().thePlayer;
		final double d = entity.lastTickPosX + (x)
                - RenderManager.renderPosX;
        final double d1 = entity.lastTickPosY + (y)
                - RenderManager.renderPosY;
        final double d2 = entity.lastTickPosZ + (z)
                - RenderManager.renderPosZ;
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(0.5F);
		GL11.glDisable(3553);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glColor4f(106.0F, 0.0F, 128.0F, 0.2F);
		drawBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
		drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glDepthMask(true);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
	}
	
	private void drawMachinesESP(double x, double y, double z, float renderPartialTicks) {
		Entity entity = Minecraft.getMinecraft().thePlayer;
		final double d = entity.lastTickPosX + (x)
                - RenderManager.renderPosX;
        final double d1 = entity.lastTickPosY + (y)
                - RenderManager.renderPosY;
        final double d2 = entity.lastTickPosZ + (z)
                - RenderManager.renderPosZ;
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(0.5F);
		GL11.glDisable(3553);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glColor4f(31.0F, 29.0F, 24.0F, 0.2F);
		drawBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
		drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glDepthMask(true);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
	}
	
	private void drawSpwanersESP(double x, double y, double z, float renderPartialTicks) {
		Entity entity = Minecraft.getMinecraft().thePlayer;
		final double d = entity.lastTickPosX + (x)
                - RenderManager.renderPosX;
        final double d1 = entity.lastTickPosY + (y)
                - RenderManager.renderPosY;
        final double d2 = entity.lastTickPosZ + (z)
                - RenderManager.renderPosZ;
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(0.5F);
		GL11.glDisable(3553);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glEnable(2848);
		GL11.glColor4f(144.0F, 246.0F, 253.0F, 0.2F);
		drawBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
		drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
		GL11.glDepthMask(true);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
	}

	public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing(3);
		tessellator.addVertex(aa.minX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.minZ);
		tessellator.draw();
		tessellator.startDrawing(3);
		tessellator.addVertex(aa.minX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.minZ);
		tessellator.draw();
		tessellator.startDrawing(1);
		tessellator.addVertex(aa.minX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.maxZ);
		tessellator.draw();
	}

	public static void drawBoundingBox(AxisAlignedBB aa) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertex(aa.minX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.maxZ);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.addVertex(aa.maxX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.maxZ);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.addVertex(aa.minX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.minZ);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.addVertex(aa.minX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.minZ);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.addVertex(aa.minX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.minZ);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.addVertex(aa.minX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.maxZ);
		tessellator.addVertex(aa.minX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.minX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.minZ);
		tessellator.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		tessellator.addVertex(aa.maxX, aa.minY, aa.maxZ);
		tessellator.draw();
	}
}
