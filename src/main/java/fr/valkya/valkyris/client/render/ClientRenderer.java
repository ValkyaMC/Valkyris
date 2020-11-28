package fr.valkya.valkyris.client.render;

import java.awt.Color;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.config.ClientConfig;
import fr.valkya.valkyris.client.key.Keybindings;
import fr.valkya.valkyris.client.manager.ClientSizeManager;
import fr.valkya.valkyris.client.render.notification.NotificationManager;
import fr.valkya.valkyris.client.utils.RenderHelper;
import fr.valkya.valkyris.client.utils.TimeHelper;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderLivingEvent;

@SideOnly(Side.CLIENT)
public class ClientRenderer {

	private final Minecraft mc = Minecraft.getMinecraft();
	
	String activestpm1, activestpm2, activestpm3, activestpm4, activestpm5;

	public static final String[] directions = { "Sud", "Ouest", "Nord", "Est" };

	@SubscribeEvent
	public void onEntityRender(RenderLivingEvent.Specials.Pre event) {
		if (!ClientSizeManager.INSTANCE.getSize(event.entity).isNormal()) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onRenderGameOverlayPre(RenderGameOverlayEvent.Pre e) {
		
			if (e.type == RenderGameOverlayEvent.ElementType.DEBUG) {
			e.setCanceled(true);

			Integer fps = Integer.valueOf(mc.debug.split(" fps", 2)[0]);

			drawShit(EnumChatFormatting.YELLOW + "Valkya " + EnumChatFormatting.GOLD + "V" + References.VERSION + " " + EnumChatFormatting.YELLOW + References.RELEASE_TYPE, 1, 1, 0xFFFFFF);

			double goodspot = 30;
			double val = fps / goodspot;
			val = Math.min(val, 1);
			val *= 255;

			Color c = new Color((int) (255 - val), (int) val, 0);
			if (val < 0.5)
				c = new Color(255, (int) (255 - val), 0);
			drawShit("FPS : " + fps, 1, 50, c.getRGB());
		
			double couche= mc.thePlayer.posY;
			if(couche >= 15 && couche <= 30) {
				drawShit(EnumChatFormatting.YELLOW + "Couche minerais moddé :" + EnumChatFormatting.RED + " Mauvaise position", 1, 150, 0xFFFFFF);
			} else if (couche <= 15 && couche >= 10) {
				drawShit(EnumChatFormatting.YELLOW + "Couche minerais moddé :" + EnumChatFormatting.GREEN + " Bonne position", 1, 150, 0xFFFFFF);
			} else if (couche >= 7 && couche <= 9) {
				drawShit(EnumChatFormatting.YELLOW + "Couche minerais moddé :" + EnumChatFormatting.DARK_GREEN + " Très bonne position", 1, 150, 0xFFFFFF);
			}			

//			drawShit("[Debug] (Market) Shop Listings: "
//					+ Valkyris.getValkyris().getProxy().getMarketManager().getProducts().size(), 1, 150, -1);
			drawShit(EnumChatFormatting.YELLOW + "Status des macros : PRESS F7", 1, 170, 0xFFFFFF);
			if(Keybindings.sco == true) {
				drawShit(EnumChatFormatting.YELLOW + "Chunk Updates : " + EnumChatFormatting.GOLD + WorldRenderer.chunksUpdated, 1, 60,
						0xFFFFFF);
				drawShit(EnumChatFormatting.YELLOW + "Chunk : " + EnumChatFormatting.GOLD + mc.thePlayer.chunkCoordX + ", "
						+ mc.thePlayer.chunkCoordY + ", " + EnumChatFormatting.GOLD + mc.thePlayer.chunkCoordZ + ".", 1, 70, 0xFFFFFF);
				drawShit(EnumChatFormatting.YELLOW + "Direction : "
						+ EnumChatFormatting.GOLD + directions[MathHelper.floor_double((mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3], 1,
						80, 0xFFFFFF);
				drawShit(EnumChatFormatting.YELLOW + "Coordonnees : ", 1, 100, 0xFFFFFF);
				drawShit(EnumChatFormatting.YELLOW + "X : " + EnumChatFormatting.GOLD + Integer.toString((int) Math.round(mc.thePlayer.posX)),
						11, 110, 0xFFFFFF);
				drawShit(EnumChatFormatting.YELLOW + "Y : " + EnumChatFormatting.GOLD + Integer.toString((int) Math.round(mc.thePlayer.posY)),
						11, 120, 0xFFFFFF);
				drawShit(EnumChatFormatting.YELLOW + "Z : " + EnumChatFormatting.GOLD + Integer.toString((int) Math.round(mc.thePlayer.posZ)),
						11, 130, 0xFFFFFF);
				}
			
			//macro status 
			if(ClientConfig.activem1)activestpm1=EnumChatFormatting.GREEN+ "Activée"; else activestpm1=EnumChatFormatting.RED + "Désactivée";
			if(ClientConfig.activem2)activestpm2=EnumChatFormatting.GREEN+ "Activée"; else activestpm2=EnumChatFormatting.RED + "Désactivée";
			if(ClientConfig.activem3)activestpm3=EnumChatFormatting.GREEN+ "Activée"; else activestpm3=EnumChatFormatting.RED + "Désactivée";
			if(ClientConfig.activem4)activestpm4=EnumChatFormatting.GREEN+ "Activée"; else activestpm4=EnumChatFormatting.RED + "Désactivée";
			if(ClientConfig.activem5)activestpm5=EnumChatFormatting.GREEN+ "Activée"; else activestpm5=EnumChatFormatting.RED + "Désactivée";
			
			if(Keyboard.isKeyDown(Keyboard.KEY_F7)) {				
				drawShit(EnumChatFormatting.YELLOW + "Macro 1 : " + activestpm1 + EnumChatFormatting.YELLOW + " (Commande : " + EnumChatFormatting.GOLD + ClientConfig.macro1 + EnumChatFormatting.YELLOW + ")", 1, 180, 0xFFFFFF);
				drawShit(EnumChatFormatting.YELLOW + "Macro 2 : " + activestpm2 + EnumChatFormatting.YELLOW + " (Commande : " + EnumChatFormatting.GOLD + ClientConfig.macro2 + EnumChatFormatting.YELLOW + ")", 1, 190, 0xFFFFFF);
				drawShit(EnumChatFormatting.YELLOW + "Macro 3 : " + activestpm3 + EnumChatFormatting.YELLOW + " (Commande : " + EnumChatFormatting.GOLD + ClientConfig.macro3 + EnumChatFormatting.YELLOW + ")", 1, 200, 0xFFFFFF);
				drawShit(EnumChatFormatting.YELLOW + "Macro 4 : " + activestpm4 + EnumChatFormatting.YELLOW + " (Commande : " + EnumChatFormatting.GOLD + ClientConfig.macro4 + EnumChatFormatting.YELLOW + ")", 1, 210, 0xFFFFFF);
				drawShit(EnumChatFormatting.YELLOW + "Macro 5 : " + activestpm5 + EnumChatFormatting.YELLOW + " (Commande : " + EnumChatFormatting.GOLD + ClientConfig.macro5 + EnumChatFormatting.YELLOW + ")", 1, 220, 0xFFFFFF);
				}
		} 
//		else if (e.type == ElementType.PLAYER_LIST) {
//			e.setCanceled(true);
//
//			NetHandlerPlayClient handler = mc.thePlayer.sendQueue;
//
//			List<GuiPlayerInfo> players = (List<GuiPlayerInfo>) handler.playerInfoList;
//
//			// GuiPlayerList
//
//			ScaledResolution sr = e.resolution;
//
//			int count = 0;
//			for (GuiPlayerInfo gpi : players) {
//				ScorePlayerTeam scoreplayerteam = this.mc.theWorld.getScoreboard().getPlayersTeam(gpi.name);
//				String s4 = ScorePlayerTeam.formatPlayerName(scoreplayerteam, gpi.name);
//
//				Gui.drawRect(sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(gpi.name) / 2 - 17, 30 + (count * 10) - 1, sr.getScaledWidth() / 2 + mc.fontRenderer.getStringWidth(gpi.name) / 2 + 17 + 3, 30 + (count * 10) + 15, Integer.MIN_VALUE);
//
//				mc.renderEngine.bindTexture(RenderHelper.loadHead(RenderHelper.getUUID(gpi.name)));
//				GL11.glPushMatrix();
//				{
//					GL11.glColor4d(1, 1, 1, 1);
//					Gui.func_152125_a(sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(gpi.name) / 2 - 17,
//							30 + (count * 10), 0, 0, 64, 64, 10, 0, 64, 64);
//				}
//				GL11.glPopMatrix();
//
//				mc.fontRenderer.drawStringWithShadow(s4,
//						sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(gpi.name) / 2, 30 + (count * 10), -1);
//				count++;
//			}
//		}
	}
	
	
	
	private void drawShit(String text, int x, int y, int color) {
		Gui.drawRect(x - 1, y - 1, x + mc.fontRenderer.getStringWidth(text), y + mc.fontRenderer.FONT_HEIGHT, Integer.MIN_VALUE);
		mc.fontRenderer.drawStringWithShadow(text, x, y, color);
	}

	@SubscribeEvent
	public void onRenderGameOverlayPost(RenderGameOverlayEvent.Post event) {
		if (event.type == ElementType.ALL) {
			ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	        int i = sr.getScaledWidth();
	        int j = sr.getScaledHeight();
	
			////////////////////////////// BROADCAST
			double speed = 4;
			if (msg != null) {
				if (!timerBc.hasReached(5000)) {
					msg = msg.replace("<lmfao>", "");

					int target = mc.fontRenderer.getStringWidth(msg) / 2 + 10;

					if (anim1 != null) {
						anim1.interpolate(target, 0, speed);
						if (anim1.getX() > mc.fontRenderer.getStringWidth(msg) / 2) {
							anim2 = new Animation(0, 0);
							anim1 = null;
						}
					}

					if (anim2 != null) {
						anim2.interpolate(target, 0, speed + 1);
					}

					double a1x = target;
					double a2x = 0;

					if (anim1 != null) {
						a1x = anim1.getX();
					}
					if (anim2 != null) {
						a2x = anim2.getX();
					}

					Gui.drawRect((int) (sr.getScaledWidth() / 2 - a1x), 20, (int) (sr.getScaledWidth() / 2 - a2x),
							20 + 15, Integer.MIN_VALUE);
					Gui.drawRect((int) (sr.getScaledWidth() / 2 + a1x), 20, (int) (sr.getScaledWidth() / 2 + a2x),
							20 + 15, Integer.MIN_VALUE);

					GL11.glPushMatrix();
					{
						this.scissor(sr.getScaledWidth() / 2 - a2x, 20, sr.getScaledWidth() / 2 + a2x, 20 + 15);
						GL11.glEnable(GL11.GL_SCISSOR_TEST);
						mc.fontRenderer.drawStringWithShadow(msg,
								sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(msg) / 2, 20 + 4, -1);
						GL11.glDisable(GL11.GL_SCISSOR_TEST);
					}
					GL11.glPopMatrix();

					msg = "<lmfao>" + msg;
				} else {
					if (msg.startsWith("<lmfao>")) {
						msg = msg.substring(7);
						anim1 = new Animation(0, 0);
						anim2 = null;
					}

					int target = mc.fontRenderer.getStringWidth(msg) / 2 + 10;

					if (anim1 != null) {
						anim1.interpolate(target, 0, speed);
						if (anim1.getX() > mc.fontRenderer.getStringWidth(msg) / 2) {
							anim2 = new Animation(0, 0);
							anim1 = null;
						}
					}

					if (anim2 != null) {
						if (anim2.interpolate(target, 0, speed + 1)) {
							msg = null;
						}
					}

					double a1x = target;
					double a2x = 0;

					if (anim1 != null) {
						a1x = anim1.getX();
					}

					if (anim2 != null) {
						a2x = anim2.getX();
					}

					Gui.drawRect((int) (sr.getScaledWidth() / 2 - target + a2x), 20,
							(int) (sr.getScaledWidth() / 2 - target + a1x), 20 + 15, Integer.MIN_VALUE);
					Gui.drawRect((int) (sr.getScaledWidth() / 2 + target - a2x), 20,
							(int) (sr.getScaledWidth() / 2 + target - a1x), 20 + 15, Integer.MIN_VALUE);

					GL11.glPushMatrix();
					{
						this.scissor(sr.getScaledWidth() / 2 - target + a1x, 20, sr.getScaledWidth() / 2 + target - a1x,
								20 + 15);
						GL11.glEnable(GL11.GL_SCISSOR_TEST);
						mc.fontRenderer.drawStringWithShadow(msg,
								sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(msg) / 2, 20 + 4, -1);
						GL11.glDisable(GL11.GL_SCISSOR_TEST);
					}
					GL11.glPopMatrix();
				}
			}
			
			///////////////////////////// TITLE
			if (titlesTimer > 0)
	        {
	            this.mc.mcProfiler.startSection("titleAndSubtitle");
	            float f3 = (float)titlesTimer - event.partialTicks;
	            int i2 = 255;

	            if (titlesTimer > titleFadeOut + titleDisplayTime)
	            {
	                float f4 = (float)(titleFadeIn + titleDisplayTime + titleFadeOut) - f3;
	                i2 = (int)(f4 * 255.0F / (float)titleFadeIn);
	            }

	            if (titlesTimer <= titleFadeOut)
	            {
	                i2 = (int)(f3 * 255.0F / (float)titleFadeOut);
	            }

	            i2 = MathHelper.clamp_int(i2, 0, 255);

	            if (i2 > 8)
	            {
	                GlStateManager.pushMatrix();
	                GlStateManager.translate((float)(i / 2), (float)(j / 2), 0.0F);
	                GlStateManager.enableBlend();
	                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
	                GlStateManager.pushMatrix();
	                GlStateManager.scale(4.0F, 4.0F, 4.0F);
	                int j2 = i2 << 24 & -16777216;
	                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(displayedTitle, (-Minecraft.getMinecraft().fontRenderer.getStringWidth(displayedTitle) / 2), -10, 16777215 | j2);
	                GlStateManager.popMatrix();
	                GlStateManager.pushMatrix();
	                GlStateManager.scale(2.0F, 2.0F, 2.0F);
	                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(displayedSubTitle, (-Minecraft.getMinecraft().fontRenderer.getStringWidth(displayedSubTitle) / 2), 5, 16777215 | j2);
	                GlStateManager.popMatrix();
	                GlStateManager.disableBlend();
	                GlStateManager.popMatrix();
	            }

	            this.mc.mcProfiler.endSection();
	        }
			
			///////////////////// NOTIFICATIONS
			NotificationManager.render();
		}
	}
	
	@SubscribeEvent
	public void onTICC(PlayerTickEvent e) {
		if(e.side == Side.CLIENT) {
			if(e.phase == Phase.START) {
				if (titlesTimer > 0)
		        {
		            --titlesTimer;

		            if (titlesTimer <= 0)
		            {
		                displayedTitle = "";
		                displayedSubTitle = "";
		            }
		        }
			}
		}
	}

    protected static int titlesTimer;
    protected static String displayedTitle = "";
    protected static String displayedSubTitle = "";
    protected static int titleFadeIn = 10;
    protected static int titleDisplayTime = 70;
    protected static int titleFadeOut = 20;
	
	public static void title(String titl, String txt) {
		displayedTitle = titl;
        titlesTimer = titleFadeIn + titleDisplayTime + titleFadeOut;   
        displayedSubTitle = txt;
        
        
	}
	
	private static TimeHelper timerBc = new TimeHelper();
	private static String msg;
	private static Animation anim1, anim2;

	public static void broadcast(String message) {
		msg = message;
		anim1 = new Animation(0, 0);
		anim2 = null;
		timerBc.reset();
	}
	
	private void scissor(double x, double y, double x2, double y2) {
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int factor = sr.getScaleFactor();
		GL11.glScissor((int) (x * factor), (int) ((sr.getScaledHeight() - y2) * factor), (int) ((x2 - x) * factor),
				(int) ((y2 - y) * factor));
	}

	public void drawStringWithShadow(FontRenderer fr, String msg, int x, int y, int color) {
		fr.drawStringWithShadow(msg, x, y, color);
	}

}
