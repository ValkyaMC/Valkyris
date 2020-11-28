package fr.valkya.valkyris.client.gui.overrides;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;

import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.gui.ConfigSelector;
import fr.valkya.valkyris.client.gui.GuiNormalizedScreen;
import fr.valkya.valkyris.client.gui.GuiOtherLinks;
import fr.valkya.valkyris.client.gui.button.CAnimatedButton;
import fr.valkya.valkyris.client.gui.button.CFadingButton;
import fr.valkya.valkyris.client.gui.button.IAnimatedButton;
import fr.valkya.valkyris.client.utils.RenderHelper;
import fr.valkya.valkyris.client.utils.TimeHelper;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.ResourceLocation;

public class GuiIngameMenuValorion extends GuiNormalizedScreen {
	
    private static final ResourceLocation minecraftTitleTextures = new ResourceLocation(References.MODID + ":textures/gui/logo.png");
    private TimeHelper timer = new TimeHelper();
    private Animation translateAnim;
    
    public void initGui() {
    	super.initGui();
        this.buttonList.clear();
        
        this.buttonList.add(new CAnimatedButton(0, this.width / 2 - 175, this.height / 2 - 100, 100, 20, "Site Web", false));
        this.buttonList.add(new CAnimatedButton(1, this.width / 2 + 175 - 100, this.height / 2 - 100, 100, 20, "Autres liens", true));
        this.buttonList.add(new CAnimatedButton(3, this.width / 2 - 175, this.height /2 - 50, 100, 20, "Règlement", false));
        this.buttonList.add(new CAnimatedButton(2, this.width / 2 + 175 - 100, this.height / 2 - 50, 100, 20, "Options Valkya", true));
        this.buttonList.add(new CAnimatedButton(4, this.width / 2 - 175, this.height / 2, 100, 20, "Déconnexion", false) {
        	private TimeHelper timer = new TimeHelper();
        	@Override
        	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        		String old = this.displayString;
        		if(!timer.hasReached(6000)) {
        			this.displayString = "Déconnexion (" + String.valueOf(5 - (int)timer.getOffset() / 1000) + "s)";
        		}
        		super.drawButton(mc, mouseX, mouseY);
        		this.displayString = old;
        	}
        	
        	@Override
        	public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
        		if(!timer.hasReached(6000)) return false;
        		return super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_);
        	}
        });        
        this.buttonList.add(new CAnimatedButton(5, this.width / 2 + 175 - 100, this.height / 2, 100, 20, "Options", true));        
        this.buttonList.add(new CFadingButton(6, this.width / 2 - 50, this.height / 2 + 75, 100, 20, "Retour"));  
        
        timer.reset();
        translateAnim = new Animation(0, 0);
    }
 
    protected void actionPerformed(GuiButton p_146284_1_) {
        switch (p_146284_1_.id) {
	        case 0:
				try {
					Desktop.getDesktop().browse(new URI("https://valkya.fr/"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
	        case 1:
	        	this.mc.displayGuiScreen(new GuiOtherLinks());
				break;
	        case 2:
	        	this.mc.displayGuiScreen(new ConfigSelector());
                break;
	        case 3:
				try {
					Desktop.getDesktop().browse(new URI("https://valkya.fr/p/reglement"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
        	case 4:
        		p_146284_1_.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
                this.mc.displayGuiScreen(new GuiMainMenu());
                break;
            case 5:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 6:
        		this.mc.displayGuiScreen(null);
        		break;
        }
    }
 
    public void updateScreen() {
        super.updateScreen();
    }
 
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
    	if(timer.hasReached(300)) {
    		timer.reset();
    		
    		int count = 0;
    		for(GuiButton b : (List<GuiButton>)this.buttonList) {
    			if(count >= 2) break;
    			
    			if(b instanceof IAnimatedButton) {
    				IAnimatedButton iab = (IAnimatedButton)b;
    				if(!iab.animationStarted()) {
    					iab.performAnimation();
    					count++;
    				}
    			}
    		}
    	}
    	
    	this.drawDefaultBackground();
    	ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
    	
    	translateAnim.interpolate(100, 0, 3);
    	double percentage = translateAnim.getX() / 100;
    	
    	Gui.drawRect(sr.getScaledWidth() / 2 - (int)(185 * percentage), (sr.getScaledHeight() / 2 - (int)(150 * percentage)) - 1, sr.getScaledWidth() / 2 + (int)(185 * percentage), (sr.getScaledHeight() / 2 - (int)(150 * percentage)), 0xFFFFB011);
    	Gui.drawRect(sr.getScaledWidth() / 2 - (int)(185 * percentage), sr.getScaledHeight() / 2 - (int)(150 * percentage), sr.getScaledWidth() / 2 + (int)(185 * percentage), sr.getScaledHeight() / 2 + (int)(105 * percentage), Integer.MIN_VALUE);
    	RenderHelper.scissor(sr.getScaledWidth() / 2 - (int)(185 * percentage), sr.getScaledHeight() / 2 - (int)(200 * percentage), sr.getScaledWidth() / 2 + (int)(185 * percentage), sr.getScaledHeight() / 2 + (int)(105 * percentage));
    	
    	GL11.glPushMatrix();
    	{
    		GL11.glEnable(GL11.GL_SCISSOR_TEST);
        	{    		
            	int k = this.width/2;
        		int l = this.height/2+50;
        		
        		GuiInventory.func_147046_a(k, l, 75, (float)(k) - p_73863_1_, (float)(l - 50) - sr.getScaledHeight() / 2, this.mc.thePlayer);
                
                GL11.glEnable(GL11.GL_BLEND);
                
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        		this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
        		int logoWidth = 100;
        		int logoHeight = 100;
        		Gui.func_152125_a(sr.getScaledWidth() / 2 - logoWidth / 2, sr.getScaledHeight() / 2 - (int)(150 * percentage) - logoHeight / 2, 0, 0, 512, 512, logoWidth, logoHeight, 512, 512);
                
                GL11.glDisable(GL11.GL_BLEND);
                
                super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        	}
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
    	}
    	GL11.glPopMatrix();
    }

}
