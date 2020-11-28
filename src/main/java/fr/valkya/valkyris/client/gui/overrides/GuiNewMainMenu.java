package fr.valkya.valkyris.client.gui.overrides;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.gui.GuiNormalizedScreen;
import fr.valkya.valkyris.client.gui.button.CAnimatedButton;
import fr.valkya.valkyris.client.gui.button.CFadingButton;
import fr.valkya.valkyris.client.gui.button.CFatButton;
import fr.valkya.valkyris.client.gui.button.IAnimatedButton;
import fr.valkya.valkyris.client.utils.RenderHelper;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

@SuppressWarnings("all")
public class GuiNewMainMenu extends GuiNormalizedScreen {

	private static ResourceLocation rl = new ResourceLocation(References.MODID + ":textures/gui/logo.png");
	private static ResourceLocation bg = new ResourceLocation(References.MODID + ":textures/gui/bg_mainmenu.png");

	private static Animation logoEntrance, logoMoveup, logoSpin, logoPLOOF;
	private static boolean initOnce, initFinished;

	private static boolean changelogOpen;

	private IAnimatedButton skip;

	public GuiNewMainMenu() {
		FMLClientHandler.instance().setupServerList();
	}
	
	@Override
	public void initGui() {
		super.initGui();
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

		cachedChangelog = null;

		if (!initFinished) {
			this.buttonList.add(skip = new CFadingButton(420, this.width - 85, this.height - 30, 75, 20, "Skip Intro"));
		}

		int heightt = (int) (sr.getScaledHeight() / 2 - 20);
		int widtth = (int) ((sr.getScaledHeight() / 2) + (heightt / 3));

		int bruh = Math.min(widtth, 200);
		this.buttonList.add(new CFatButton(1, sr.getScaledWidth() / 2 + widtth - bruh - 2,
				sr.getScaledHeight() / 2 + heightt - (bruh / 5) - 2, bruh, bruh / 5, "Se connecter", true));

		this.buttonList.add(new CAnimatedButton(2, sr.getScaledWidth() / 2 + widtth - (bruh * 2 / 3) - 2,
				sr.getScaledHeight() / 2 + heightt - (bruh / 5) - 2 - 2 - 20, bruh * 2 / 3, 20, "Options", true));

		this.buttonList.add(new CAnimatedButton(4, sr.getScaledWidth() / 2 + widtth - (bruh / 2) - 2,
				sr.getScaledHeight() / 2 - heightt + 2, bruh / 2, 20, "Boutique", false));
		this.buttonList.add(new CAnimatedButton(5, sr.getScaledWidth() / 2 + widtth - (bruh / 2) - 2,
				sr.getScaledHeight() / 2 - heightt + 2 + 22, bruh / 2, 20, "Discord", false));

		this.buttonList.add(new CAnimatedButton(6, sr.getScaledWidth() / 2 - widtth + 2,
				sr.getScaledHeight() / 2 + heightt - 2 - 20, 130, 20, "Quitter le jeu", true));

		if (initFinished) {
			for (Object o : this.buttonList) {
				if (o instanceof IAnimatedButton) {
					((IAnimatedButton) o).skipAnimation();
				}
			}
		}

		if (!initOnce) {
			logoEntrance = new Animation(0, 0);
			logoMoveup = new Animation(0, 0);
			logoSpin = new Animation(0, 0);
			logoPLOOF = new Animation(0, 0);
			initOnce = true;
		}

		if (skip != null) {
			skip.performAnimation();
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// System.out.println("ayaYAYAAAAAAAAAAAAAAA");
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

		this.drawDefaultBackground();
		Gui.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0xFF111111);

		double logoWidth = sr.getScaledWidth() / 6;
		double logoHeight = logoWidth;

		double percentage1 = logoEntrance.getX() / 500;
		double percentage2 = logoSpin.getX() / 500;
		double percentage3 = logoMoveup.getX() / 500;
		double percentage3_2 = Math.max(0, logoMoveup.getX() - 175) / 325;
		double percentage4 = logoPLOOF.getX() / 500;

		int sw = sr.getScaledWidth() / 2;
		int sh = sr.getScaledHeight() / 2;
		int dist = (int) Math.sqrt(sw * sw + sh * sh) + 10;

		mc.renderEngine.bindTexture(bg);
		GL11.glColor4d(1, 1, 1, 1);
		Gui.func_152125_a(0, 0, 0, 0, 2048, 1152, sr.getScaledWidth(), sr.getScaledHeight(), 2048, 1152);

		if (percentage2 == 0) {
			// Gui.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0xFF111111);
		}
		for (int i = 0; dist - i * 2 > dist * percentage2; i++) {
			drawCircle(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, dist - i * 2, 720, 0xFF111111, 50);
		}
		// mc.fontRenderer.drawStringWithShadow("in: " + in, 2, 2, -1);
		// mc.fontRenderer.drawStringWithShadow("frame updates: " +
		// mc.debug.split(Pattern.quote(","))[0], 2, 12, -1);

		// Gui.drawRect(sr.getScaledWidth() / 2 - (int)(120 * percentage3),
		// sr.getScaledHeight() / 2 - (int)((sr.getScaledHeight() / 3) * percentage3),
		// sr.getScaledWidth() / 2 + (int)(120 * percentage3), sr.getScaledHeight() / 2
		// + (int)((sr.getScaledHeight() / 3) * percentage3), Integer.MIN_VALUE);

		int heightt = (int) ((sr.getScaledHeight() / 2 - 20) * percentage3);
		int widtth = (int) (((sr.getScaledHeight() / 2) + (heightt / 3)) * percentage3);

		Gui.drawRect(sr.getScaledWidth() / 2 - widtth, sr.getScaledHeight() / 2 - heightt,
				sr.getScaledWidth() / 2 + widtth, sr.getScaledHeight() / 2 + heightt, Integer.MIN_VALUE);

		GL11.glPushMatrix();
		{
			boolean enabled = GL11.glIsEnabled(GL11.GL_BLEND);
			if(!enabled)
				GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4d(1, 1, 1, 1);
			GL11.glTranslated(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, 0);

			GL11.glTranslated(0, (1 - percentage1) * ((sr.getScaledHeight() / 2) + (logoHeight / 2)), 0);

			GL11.glRotated(180, 0, 0, 1);
			GL11.glRotated(180 * percentage1, 0, 0, 1);

			GL11.glRotated(-360 * percentage2, 0, 0, 1);
			GL11.glScaled(1 + .6 * (percentage2 - percentage3_2), 1 + .6 * (percentage2 - percentage3_2), 1);

			heightt = (int) ((sr.getScaledHeight() / 2 - 20) * percentage3_2);
			widtth = (int) (((sr.getScaledHeight() / 2) + (heightt / 3)) * percentage3_2);

			GL11.glTranslated(-widtth, -heightt, 0);
			GL11.glTranslated(this.width / 64 * percentage3_2, 0, 0); // a bit of spacing
			GL11.glTranslated((logoWidth / 2) * percentage3_2, (logoHeight / 2) * percentage3_2, 0);

			mc.renderEngine.bindTexture(rl);
			// Gui.drawRect((int)-logoWidth/2, (int)-logoHeight/2, (int)logoWidth,
			// (int)logoHeight, Integer.MAX_VALUE);
			Gui.func_152125_a((int) (-logoWidth / 2), (int) (-logoHeight / 2), 0, 0, 512, 512, (int) logoWidth,
					(int) logoHeight, 512, 512);

			if (percentage4 != 1 && percentage4 != 0) {
				GL11.glColor4d(1, 1, 1, 1 - percentage4);
				// GL11.glTranslated(widtth, heightt, 0);
				// GL11.glTranslated(-((logoWidth / 2 + 20) * percentage3_2), -((logoHeight / 2)
				// * percentage3_2), 0);
				GL11.glScaled(1 + .5 * percentage4, 1 + .5 * percentage4, 1);
				Gui.func_152125_a((int) (-logoWidth / 2), (int) (-logoHeight / 2), 0, 0, 512, 512, (int) logoWidth,
						(int) logoHeight, 512, 512);
			}
			if(!enabled) 
				GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			boolean enabled = GL11.glIsEnabled(GL11.GL_BLEND);
			if(!enabled)
				GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4d(1, 1, 1, 1 * percentage4);
			mc.renderEngine.bindTexture(RenderHelper.loadHead());
			Gui.func_152125_a(sr.getScaledWidth() / 2 - widtth + 2, sr.getScaledHeight() / 2 + heightt - 30 + 2 - 24, 0,
					0, 64, 64, 26, 26, 64, 64); // k
			if(!enabled) {
				GL11.glDisable(GL11.GL_BLEND);
			}
		}
		GL11.glPopMatrix();

		long diff = System.currentTimeMillis() - Valkyris.getValkyris().getProxy().startTimestamp;
		long diffSeconds = (diff / 1000) % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (1000 * 60 * 60);

		String played = "Joue depuis: " + EnumChatFormatting.GRAY;
		// Temps en jeu

		if (diffHours > 0) {
			if (diffHours > 12)
				played += EnumChatFormatting.RED;
			played += diffHours + "h";
		}
		if (diffMinutes > 0) {
			played += diffMinutes + "m";
		}
		if (diffSeconds > 0) {
			played += diffSeconds + "s";
		}

		boolean enabled = GL11.glIsEnabled(GL11.GL_BLEND);
		if(!enabled)
			GL11.glEnable(GL11.GL_BLEND);
		
		int textColor = 16777215 + (((int) (0xFF * Math.max(0.1, percentage4))) << 24);
		mc.fontRenderer.drawStringWithShadow(mc.getSession().getUsername(), sr.getScaledWidth() / 2 - widtth + 30 + 1,
				sr.getScaledHeight() / 2 + heightt - 30 + 3 + 1 - 24, textColor);
		mc.fontRenderer.drawStringWithShadow(played, sr.getScaledWidth() / 2 - widtth + 30 + 1,
				sr.getScaledHeight() / 2 + heightt - 30 + 3 + 16 - 24, textColor);

		int bruh = Math.min(widtth, 200);
		int daWidth = (sr.getScaledWidth() / 2 + widtth - 2) - ((int) (sr.getScaledWidth() / 2 - this.width / 8));

		Color c = new Color(18, 18, 18, (int) (190 * percentage4));

		Gui.drawRect(
				changelogOpen ? ((int) (sr.getScaledWidth() / 2 - this.width / 8))
						: ((sr.getScaledWidth() / 2 + widtth - 5
								- (mc.fontRenderer.getStringWidth("Mises à jour") + 24))),
				sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2, sr.getScaledWidth() / 2 + widtth - 2,
				sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + 12, c.getRGB());
		mc.fontRenderer.drawStringWithShadow("Mises à jour", changelogOpen
				? ((int) (sr.getScaledWidth() / 2 - this.width / 8) + daWidth / 2
						- mc.fontRenderer.getStringWidth("Mises à jour") / 2)
				: (sr.getScaledWidth() / 2 + widtth - 5 - (mc.fontRenderer.getStringWidth("Mises à jour") + 20)),
				sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + 6 - 9 / 2, textColor);

		if (hover(mouseX, mouseY, sr.getScaledWidth() / 2 + widtth - 14,
				sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + 1, sr.getScaledWidth() / 2 + widtth - 4,
				sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + 11))
			Gui.drawRect(sr.getScaledWidth() / 2 + widtth - 14,
					sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + 1, sr.getScaledWidth() / 2 + widtth - 4,
					sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + 11, Integer.MIN_VALUE);
		mc.fontRenderer.drawStringWithShadow(changelogOpen ? "▼" : "◄", sr.getScaledWidth() / 2 + widtth - 10,
				sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + 6 - 9 / 2, textColor);

		if (changelogOpen)
			Gui.drawRect((int) (sr.getScaledWidth() / 2 - this.width / 8),
					sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + 12, sr.getScaledWidth() / 2 + widtth - 2,
					sr.getScaledHeight() / 2 + heightt - 2 - bruh / 5 - 20 - 2 - 2, c.getRGB());

		if(!enabled)
			GL11.glDisable(GL11.GL_BLEND);

		RenderHelper.scissor((int) (sr.getScaledWidth() / 2 - this.width / 8),
				sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2, sr.getScaledWidth() / 2 + widtth - 18,
				sr.getScaledHeight() / 2 + heightt - 2 - bruh / 5 - 20 - 2 - 2);

		boolean canGoThrough = false;
		if (logoEntrance.getX() == 0) {
			if (mc.debug.contains(" fps") && mc.debug.split(" fps")[0] != "") {
				int fps = Integer.parseInt(mc.debug.split(" fps")[0]);
				if (fps > 15) {
					canGoThrough = true;
				}
			}
		} else {
			canGoThrough = true;
		}

		if (canGoThrough) {
			if (logoEntrance.interpolate(500, 0, 2)) {
				if (logoSpin.interpolate(500, 0, 2.15)) {
					if (logoMoveup.interpolate(500, 0, 2)) {
						logoPLOOF.interpolate(500, 0, 2);

						if (changelogOpen) {
							GL11.glPushMatrix();
							{
								GL11.glEnable(GL11.GL_SCISSOR_TEST);
								GL11.glEnable(GL11.GL_BLEND);
								int yPos = 0;
								for (String line : getChangelog(daWidth)) {
									mc.fontRenderer.drawStringWithShadow(line.replace("\n", ""),
											(sr.getScaledWidth() / 2 - this.width / 8) + 2,
											sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + yPos + 2 + 12,
											textColor);
									yPos += 10;

									if (sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + yPos + 2 + 12
											+ 20 > sr.getScaledHeight() / 2 + heightt - 2 - bruh / 5 - 20 - 2 - 2) {
										mc.fontRenderer.drawStringWithShadow("...",
												(sr.getScaledWidth() / 2 - this.width / 8) + 3, sr.getScaledHeight() / 2
														- heightt + 20 + 20 + 2 + 2 + 2 + yPos + 2 + 12,
												textColor);
										break;
									}
								}
								GL11.glDisable(GL11.GL_BLEND);
								GL11.glDisable(GL11.GL_SCISSOR_TEST);
							}
							GL11.glPopMatrix();
						}

						for (Object o : this.buttonList) {
							if (o instanceof IAnimatedButton) {
								IAnimatedButton iab = (IAnimatedButton) o;
								if (!iab.animationStarted()) {
									iab.performAnimation();
								}
							}
						}

						initFinished = true;
						if (skip != null) {
							this.buttonList.remove(skip);
							skip = null;
						}
					}
				}
			}
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0) {
			ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			int heightt = (int) (sr.getScaledHeight() / 2 - 20);
			int widtth = (int) ((sr.getScaledHeight() / 2) + (heightt / 3));

			if (hover(mouseX, mouseY, sr.getScaledWidth() / 2 + widtth - 14,
					sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + 1, sr.getScaledWidth() / 2 + widtth - 4,
					sr.getScaledHeight() / 2 - heightt + 20 + 20 + 2 + 2 + 2 + 11)) {
				if (changelogOpen) {
					mc.getSoundHandler().playSound(
							PositionedSoundRecord.func_147674_a(new ResourceLocation("tile.piston.in"), 1.0F));
				} else {
					mc.getSoundHandler().playSound(
							PositionedSoundRecord.func_147674_a(new ResourceLocation("tile.piston.out"), 1.0F));
				}
				changelogOpen ^= true;
			}
		}

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	private boolean hover(int mouseX, int mouseY, int x, int y, int x2, int y2) {
		return mouseX >= x && mouseY >= y && mouseX <= x2 && mouseY <= y2;
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
			case 1:
				// if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) &&
				// Keyboard.isKeyDown(Keyboard.KEY_W)) {
				// mc.displayGuiScreen(new GuiConnectingValkya(this, mc, new
				// ServerData("Valkya", "proxy.valkya.fr", false)));
				mc.displayGuiScreen(new GuiConnectingValkya(this, mc, new ServerData("Valkya", References.IP, false)));
				// } else {
				// mc.displayGuiScreen(new GuiSelectWorld(this));
				// }
				break;
			case 2:
				mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
				break;
			case 4:
				try {
					Desktop.getDesktop().browse(new URL("https://valkya.fr/shop").toURI());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 5:
				try {
					Desktop.getDesktop().browse(new URL("https://discord.valkya.fr").toURI());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 6:
				mc.shutdown();
				break;
			case 420:
				initOnce = true;
				initFinished = true;
				logoEntrance = new Animation(500, 0);
				logoMoveup = new Animation(500, 0);
				logoSpin = new Animation(500, 0);
				logoPLOOF = new Animation(500, 0);
				GuiNewMainMenu oof = new GuiNewMainMenu();
				mc.displayGuiScreen(oof);
				break;
		}
	}

	private static List<String> cachedChangelog;

	private List<String> getChangelog(int daWidth) {
		if (cachedChangelog != null) {
			return cachedChangelog;
		}

		List<String> rawChangelog = new ArrayList<>();
		try {
			rawChangelog.addAll(Arrays.asList(
					IOUtils.toString(new File(Minecraft.getMinecraft().mcDataDir, "changelog.json").toURI().toURL())
							.split(Pattern.quote("\n"))));
		} catch (IOException e) {
			e.printStackTrace();
			rawChangelog = Arrays.asList(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "Error! "
					+ EnumChatFormatting.GRAY + "Couldn't fetch changelog. ",
					e.getClass().getSimpleName() + ": " + e.getMessage());
		}

		rawChangelog = rawChangelog.stream()
				.map(fff -> fff
						.replace("[+]",
								EnumChatFormatting.GRAY + "[" + EnumChatFormatting.GREEN + "+" + EnumChatFormatting.GRAY
										+ "]" + EnumChatFormatting.WHITE)
						.replace("[-]",
								EnumChatFormatting.GRAY + "[" + EnumChatFormatting.DARK_RED + "-"
										+ EnumChatFormatting.GRAY + "]" + EnumChatFormatting.WHITE)
						.replace("[!]",
								EnumChatFormatting.GRAY + "[" + EnumChatFormatting.RED + "!" + EnumChatFormatting.GRAY
										+ "]" + EnumChatFormatting.WHITE)
						.replace("[*]",
								EnumChatFormatting.GRAY + "[" + EnumChatFormatting.YELLOW + "*"
										+ EnumChatFormatting.GRAY + "]" + EnumChatFormatting.WHITE))
				.collect(Collectors.toList());

		cachedChangelog = new ArrayList<>();

		for (String line : rawChangelog) {
			int currentSize = mc.fontRenderer.getStringWidth(line);
			if (currentSize > daWidth) {
				String[] words = line.split(Pattern.quote(" "));
				StringBuilder sb = new StringBuilder();
				for (int wordCount = 0; wordCount < words.length; wordCount++) {
					String word = words[wordCount];
					String oldLine = sb.toString();
					sb.append(word);
					sb.append(" ");
					String currentLine = sb.toString();
					if (mc.fontRenderer.getStringWidth(currentLine) <= daWidth) {
						continue;
					}
					// else
					cachedChangelog.add(oldLine.trim());

					sb = new StringBuilder();
					sb.append(word);
					sb.append(" ");
				}
				cachedChangelog.add(sb.toString().trim());
			} else {
				cachedChangelog.add(line);
			}
		}

		return cachedChangelog;
	}

	public void drawCircle(float cx, float cy, float r, int num_segments, int c, int lw) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		r *= 2;
		cx *= 2;
		cy *= 2;
		float f = (float) (c >> 24 & 0xff) / 255F;
		float f1 = (float) (c >> 16 & 0xff) / 255F;
		float f2 = (float) (c >> 8 & 0xff) / 255F;
		float f3 = (float) (c & 0xff) / 255F;
		float theta = (float) (2 * Math.PI / (num_segments));
		float p = (float) Math.cos(theta);// calculate the sine and cosine
		float s = (float) Math.sin(theta);
		float t;
		GL11.glLineWidth(lw);
		GL11.glColor4f(f1, f2, f3, f);
		float x = r;
		float y = 0;// start at angle = 0
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int ii = 0; ii < num_segments; ii++) {
			GL11.glVertex2f(x + cx, y + cy);// final vertex vertex

			// rotate the stuff
			t = x;
			x = p * x - s * y;
			y = s * t + p * y;
		}
		GL11.glEnd();
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glScalef(2F, 2F, 2F);
	}

	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
	}

}
