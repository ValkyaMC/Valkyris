package fr.valkya.valkyris.client.gui.overrides;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.gui.button.CFadingButton;
import fr.valkya.valkyris.client.gui.button.IAnimatedButton;
import fr.valkya.valkyris.client.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class GuiDisconnectedValkya extends GuiScreen
{
    private String field_146306_a;
    private IChatComponent field_146304_f;
    private List<String> field_146305_g;
    private final GuiScreen field_146307_h;

    public GuiDisconnectedValkya(GuiScreen p_i45020_1_, String p_i45020_2_, IChatComponent p_i45020_3_)
    {
        this.field_146307_h = p_i45020_1_;
        this.field_146306_a = I18n.format(p_i45020_2_, new Object[0]);
        this.field_146304_f = p_i45020_3_;
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char p_73869_1_, int p_73869_2_) {}

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
	public void initGui()
    {
        IAnimatedButton b = null;
        this.buttonList.add(b = new CFadingButton(2, this.width / 2 - 100, this.height / 4 + 120 + 12, "Réessayer") {
        	private TimeHelper timer = new TimeHelper();
        	
        	{
        		timer.reset();
        	}
        	
        	@Override
        	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        		String txt = this.displayString;
        		
        		if(timer.hasReached(3000)) {
        			GuiDisconnectedValkya.this.actionPerformed(this);
        			timer.reset();
        		}
        		

        		long var = 3000L - timer.getOffset();
        		var *= 10L;
        		var = Math.round(var);
        		var /= 10000L;
        		var++;
        		
        		this.displayString = "Réessayer (Auto: " + (var) + "s)";
        		super.drawButton(mc, mouseX, mouseY);
        		this.displayString = txt;
        	}
        });
        b.performAnimation();
        this.buttonList.add(b = new CFadingButton(0, this.width / 2 - 100, this.height / 4 + 120 + 36, "Retour"));
        b.performAnimation();
        
        this.field_146305_g = this.fontRendererObj.listFormattedStringToWidth(this.field_146304_f.getFormattedText(), this.width - 50);
        
        if(this.field_146305_g == null) {
        	this.field_146305_g = Lists.newArrayList();
        }
    }

    protected void actionPerformed(GuiButton p_146284_1_)
    {
        if (p_146284_1_.id == 0)
        {
            this.mc.displayGuiScreen(this.field_146307_h);
        }
        if (p_146284_1_.id == 2) {
        	mc.displayGuiScreen(new GuiConnectingValkya(this.field_146307_h, mc, new ServerData("Valkya", References.IP, false)));
        }
    }

    private static final ResourceLocation bg = new ResourceLocation(References.MODID, "textures/gui/bg_connect.png");
    
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {    	
    	ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        
        mc.renderEngine.bindTexture(bg);
        Gui.func_152125_a(0, 0, 0, 0, 1920, 1057, sr.getScaledWidth(), sr.getScaledHeight(), 1920, 1057);
        
        int w = mc.fontRenderer.getStringWidth(field_146306_a);
        
        if(this.field_146305_g == null) {
        	field_146305_g = new ArrayList<>();
        }
        
        try {
        	for(String s : this.field_146305_g) {
            	if(mc.fontRenderer.getStringWidth(s) > w) {
            		w = mc.fontRenderer.getStringWidth(s);
            	}
            }
        } catch(Exception e) {}
        
        int v = ((w / 2) + 20);
        Gui.drawRect(this.width / 2 - v, this.height / 2 - 60, this.width / 2 + v, this.height / 2 + (Math.max(0, this.field_146305_g.size() - 1) * this.fontRendererObj.FONT_HEIGHT), Integer.MIN_VALUE);
        
        this.drawCenteredString(this.fontRendererObj, this.field_146306_a, this.width / 2, this.height / 2 - 50, 11184810);
        int k = this.height / 2 - 30;
        
        if (this.field_146305_g != null)
        {
            for (Iterator<String> iterator = this.field_146305_g.iterator(); iterator.hasNext(); k += this.fontRendererObj.FONT_HEIGHT)
            {
                String s = iterator.next();
                this.drawCenteredString(this.fontRendererObj, s, this.width / 2, k, 16777215);
            }
        }

        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}