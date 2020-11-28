package fr.valkya.valkyris.client.gui.serverselector;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import fr.valkya.valkyris.client.gui.GuiNormalizedScreen;
import fr.valkya.valkyris.client.gui.serverselector.data.ServerCategory;
import fr.valkya.valkyris.client.gui.serverselector.data.ServerData;
import fr.valkya.valkyris.client.gui.serverselector.data.ServerFlag;
import fr.valkya.valkyris.client.utils.RenderHelper;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;

public class GuiServerSelector extends GuiNormalizedScreen {

	private Animation anim1, scrollAnim;
	private int scrollTarget;

	private List<ServerData> servers;

	private ServerCategory cat;

	public GuiServerSelector(ServerCategory defaultCategory) {
		servers = new ArrayList<>();

		servers.add(new ServerData("Faction #1", "4an7q336t097csm", ServerCategory.FACTION,
				Lists.newArrayList(ServerFlag.FACTIONS, ServerFlag.PVP)));
		
		servers.add(new ServerData("Ressources", "h94k6360g3rvka3", ServerCategory.RESOURCE,
				Lists.newArrayList(ServerFlag.MINE)));
		servers.add(new ServerData("Ressources (Nether)", "f5EbDHk84", ServerCategory.RESOURCE,
				Lists.newArrayList(ServerFlag.MINE, ServerFlag.NETHER)));
		servers.add(new ServerData("Ressources (End)", "u5U6K7ivX", ServerCategory.RESOURCE,
				Lists.newArrayList(ServerFlag.MINE, ServerFlag.END)));
		servers.add(new ServerData("Events", "fezfjzepfke", ServerCategory.OTHER,
			Lists.newArrayList(ServerFlag.PVP, ServerFlag.SKY)));

//		servers.add(new ServerData("Quêtes #1", "4bt63hkb678h69p", ServerCategory.OTHER, Lists.newArrayList(ServerFlag.SKY)));

		anim1 = new Animation(0, 0);
		scrollAnim = new Animation(0, 0);

		cat = defaultCategory;
	}

	private DaBigButton owo;

	@Override
	public void initGui() {
		super.initGui();

		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int height = (int) (sr.getScaledHeight() / 1.5D);
		int width = (int) (height * ((double) (10D / 12D)));

		owo = new DaBigButton(cat, 42069, 4, 4, width - 4 - 5, 50 - 4 - 4, "");

		int count = 0;
		for (int i = 0; i < servers.size(); i++) {
			ServerData data = servers.get(i);
			if (cat != ServerCategory.ALL && data.category != cat)
				continue;

			this.buttonList
					.add(new ServerButton(data, count, 4, (count + 1) * 54, width - 4 - 5, 50, data.displayName));
			count++;
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

		if (Mouse.hasWheel()) {
			int wheel = Mouse.getDWheel();
			while (wheel != 0) {
				if (wheel < 0) {
					this.scrollTarget += 20;
					wheel += 40;
				} else if (wheel > 0) {
					this.scrollTarget -= 20;
					if (this.scrollTarget < 0) {
						this.scrollTarget = 0;
					}
					wheel -= 40;
				}
			}
		}

		int height = (int) (sr.getScaledHeight() / 1.5D);
		int width = (int) (height * ((double) (10D / 12D)));

		int scrollMax = ((GuiButton) this.buttonList.get(this.buttonList.size() - 1)).yPosition;

		scrollMax -= height;
		scrollMax += 54;

		if (scrollMax < 0) {
			scrollMax = 0;
		}

		// System.out.println(scrollMax);
		// System.out.println(scrollTarget);

		if (scrollTarget > scrollMax) {
			scrollTarget = scrollMax;
		}

		scrollAnim.interpolate(scrollTarget * 10, 0, 3);
		int scroll = (int) (scrollAnim.getX() / 10D);

		anim1.interpolate(100, 0, 2.35);
		double scale = anim1.getX() / 100D;

		GL11.glPushMatrix();
		GL11.glTranslated(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, 0);
		GL11.glScaled(scale, scale, 1);

		Gui.drawRect(-width / 2, -height / 2, width / 2, height / 2, Integer.MIN_VALUE);

		GL11.glTranslated(-width / 2, -height / 2, 0);

		width -= 1;

		// fat button
		// Gui.drawRect(4, 4, width - 4, 50 - 4, );

		owo.drawButton(mc, mouseX - (sr.getScaledWidth() / 2 - width / 2),
				mouseY - (sr.getScaledHeight() / 2 - height / 2));

		GL11.glTranslated(0, -scroll, 0); // scroll

		RenderHelper.scissor(sr.getScaledWidth() / 2 - width / 2, sr.getScaledHeight() / 2 - height / 2 + 46,
				sr.getScaledWidth() / 2 + width / 2 - 4, (float) (sr.getScaledHeight() / 2 + ((height / 2) * scale)));
		GL11.glEnable(GL11.GL_SCISSOR_TEST);

		// DRAW BUTTONS
		GL11.glTranslated(0, -4, 0);
		super.drawScreen(mouseX - (sr.getScaledWidth() / 2 - width / 2),
				mouseY - (sr.getScaledHeight() / 2 - height / 2) + 4 + scroll, partialTicks);
		GL11.glTranslated(0, 4, 0);

		GL11.glDisable(GL11.GL_SCISSOR_TEST);

		GL11.glScaled(1 / scale, 1 / scale, 1);
		GL11.glPopMatrix();
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int scroll = (int) (scrollAnim.getX() / 10D);

		int height = (int) (sr.getScaledHeight() / 1.5D);
		int width = (int) (height * ((double) (10D / 12D)));

		super.mouseClicked(mouseX - (sr.getScaledWidth() / 2 - width / 2),
				mouseY - (sr.getScaledHeight() / 2 - height / 2) + 4 + scroll, mouseButton);

		if (owo.mousePressed(mc, mouseX - (sr.getScaledWidth() / 2 - width / 2),
				mouseY - (sr.getScaledHeight() / 2 - height / 2))) {
			owo.func_146113_a(this.mc.getSoundHandler());
			this.actionPerformed(owo);
		}
	}

}
