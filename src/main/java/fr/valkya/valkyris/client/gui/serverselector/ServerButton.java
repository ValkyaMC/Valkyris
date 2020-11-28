package fr.valkya.valkyris.client.gui.serverselector;

import java.util.concurrent.atomic.AtomicInteger;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.client.gui.serverselector.data.ServerData;
import fr.valkya.valkyris.client.gui.serverselector.data.ServerFlag;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;

public class ServerButton extends GuiButton {
	
	private ServerData data;
	private Animation animation;
	
	public ServerButton(ServerData data, int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
		super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
		this.data = data;
		this.animation = new Animation(0, 0);
	}

	public ServerButton(ServerData data, int p_i1020_1_, int p_i1020_2_, int p_i1020_3_, String p_i1020_4_) {
		super(p_i1020_1_, p_i1020_2_, p_i1020_3_, p_i1020_4_);
		this.data = data;
		this.animation = new Animation(0, 0);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(this.visible) {
			field_146123_n = mouseX >= xPosition && mouseX <= xPosition + width && mouseY >= yPosition && mouseY <= yPosition + height;
			
			Gui.drawRect(xPosition, yPosition, (int) (xPosition + width - animation.getX()), yPosition + height, 0xCC151515);
			
			if(animation.getX() != 0) {
				Gui.drawRect((int) (xPosition + width - animation.getX()), yPosition, xPosition + width, yPosition + height, 0xBBFFB715);
			}
			
			GL11.glPushMatrix();
			
			double percentage = (double)animation.getX() / (double)width;
			
			int posX = xPosition + width / 2;
			posX = (int) Math.min(posX, ((xPosition + width - animation.getX()) + 2 + (mc.fontRenderer.getStringWidth(displayString) / 2)));
			
			double val = (height / 2);
			val *= (1D - percentage);
			int posY = (int) (yPosition + (val));
			posY = (int) Math.max(posY, yPosition + 6);
			
			double scale = 2 - (1D * percentage);
			GL11.glTranslated(posX, posY, 0);
			GL11.glScaled(scale, scale, 0);
			GL11.glColor4d(1, 1, 1, 1);
			GL11.glTranslated(-mc.fontRenderer.getStringWidth(displayString) / 2, -mc.fontRenderer.FONT_HEIGHT / 2, 0);
			
			mc.fontRenderer.drawStringWithShadow(displayString, 0, 0, -1);
			
			GL11.glEnable(GL11.GL_BLEND);
			
			AtomicInteger xPos = new AtomicInteger();
			for(int i = 0; i < data.flags.size(); i++) {
				ServerFlag f = data.flags.get(i);
				GL11.glColor4d(1, 1, 1, percentage);
				mc.getTextureManager().bindTexture(f.getIcon());
				Gui.func_152125_a(xPos.getAndAdd(14), 10, 0, 0, 64, 64, 12, 12, 64, 64);
			}
			
			GL11.glDisable(GL11.GL_BLEND);
			
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			GL11.glTranslated(xPosition, yPosition, 0);
			GL11.glTranslated(width - 5, height - height / 3, 0);
			GL11.glScaled(1.25, 1.25, 0);
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4d(1, 1, 1, percentage);
			mc.fontRenderer.drawStringWithShadow("Cliquez pour rejoindre", (int) (-mc.fontRenderer.getStringWidth("Cliquez pour rejoindre") * percentage), 0, 16777215 + (((int)Math.max((percentage * 255), 20)) << 24));
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
			
//			Gui.drawRect((int) Math.max((xPosition + width - 3 - (percentage * (width / 1.75))), (xPosition + width - animation.getX())), yPosition + height / 3, Math.maxxPosition + width - 3, yPosition + height - 3, 0xBC12FA12);
			
			animation.interpolate(field_146123_n ? width : 0, 0, 2.5);
		}
	}
	
	@Override
	public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
		if(super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_)) {
			p_146116_1_.thePlayer.sendChatMessage("/" + data.internalName);
			return true;
		}
		return false;
	}

}
