package fr.valkya.valkyris.client.gui.macros.list;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.config.ClientConfig;
import fr.valkya.valkyris.client.gui.GuiNormalizedScreen;
import fr.valkya.valkyris.client.gui.button.CFadingButton;
import fr.valkya.valkyris.client.gui.button.IAnimatedButton;
import fr.valkya.valkyris.client.gui.macros.MacrosSelector;
import fr.valkya.valkyris.client.utils.RenderHelper;
import fr.valkya.valkyris.client.utils.TimeHelper;
import fr.valkya.valkyris.client.utils.animate.Animation;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class Macro1 extends GuiNormalizedScreen {
	
	String m;
	protected GuiTextField text;
	private String command;
	
    private static final ResourceLocation minecraftTitleTextures = new ResourceLocation(References.MODID + ":textures/gui/logo.png");
    private TimeHelper timer = new TimeHelper();
    private Animation translateAnim;
    
    
    public void initGui() {
    	super.initGui();
        this.buttonList.clear();
        
        if(ClientConfig.activem1) {
        	m = EnumChatFormatting.GREEN + "Activée";
        } else {
        	m = EnumChatFormatting.RED + "Désactivée";
        }
        
        this.text = new GuiTextField(fontRendererObj, this.width / 2 - 100, this.height / 2 + (-25), 200, 30);
 		this.text.setMaxStringLength(20);
 		text.setText("");
 		this.text.setFocused(false);
 		
 		

        this.buttonList.add(new CFadingButton(7, this.width / 2 - 100, this.height / 2 + 75, 80, 20, "Retour")); 
        this.buttonList.add(new CFadingButton(6, this.width / 2 - 0, this.height / 2 + 75, 80, 20, "Quitter"));  
        
       
		
		this.buttonList.add(new CFadingButton(0, this.width / 2 - 100, this.height / 2 + 15, 200, 20, "Changer la commande"));
		
		if(ClientConfig.activem1) {
			this.buttonList.add(new CFadingButton(1, this.width / 2 - 100, this.height / 2 + 40, 200, 20, "Désactiver la macro"));
		} else {
			this.buttonList.add(new CFadingButton(2, this.width / 2 - 100, this.height / 2 + 40, 200, 20, "Activer la macro"));
		}
        
        timer.reset();
        translateAnim = new Animation(0, 0);
    }
    
    protected void keyTyped(char par1, int par2) {
        super.keyTyped(par1, par2);
        this.text.textboxKeyTyped(par1, par2);
    }
    
    public void updateScreen() {
        super.updateScreen();
        this.text.updateCursorCounter();
    }
			
    protected void mouseClicked(int x, int y, int btn) {
        super.mouseClicked(x, y, btn);
        this.text.mouseClicked(x, y, btn);
    }
 
    protected void actionPerformed(GuiButton p_146284_1_) {
        switch (p_146284_1_.id) {
	        case 0:
	        	if(this.text.getText() == "" || this.text.getText() == " ") return; else {
		        	sendMacro(this.text.getText());
		        	this.mc.displayGuiScreen(new Macro1());}
				break;
	        case 6:
        		this.mc.displayGuiScreen(null);
        		break;
            case 7:
        		this.mc.displayGuiScreen(new MacrosSelector());
        		break;
            case 1:
            	ClientConfig.activem1 = false;
            	ClientConfig.save();
            	this.mc.displayGuiScreen(new Macro1());
            	break;
            case 2:
            	ClientConfig.activem1 = true;
            	ClientConfig.save();
            	this.mc.displayGuiScreen(new Macro1());
        		break;
        }
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
        		
//        		GuiInventory.func_147046_a(k, l, 75, (float)(k) - p_73863_1_, (float)(l - 50) - sr.getScaledHeight() / 2, this.mc.thePlayer); //player
                
                GL11.glEnable(GL11.GL_BLEND);
                
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        		this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
        		int logoWidth = 100;
        		int logoHeight = 100;
        		Gui.func_152125_a(sr.getScaledWidth() / 2 - logoWidth / 2, sr.getScaledHeight() / 2 - (int)(150 * percentage) - logoHeight / 2, 0, 0, 512, 512, logoWidth, logoHeight, 512, 512);
                
                GL11.glDisable(GL11.GL_BLEND);
                this.text.drawTextBox();
                super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
                
                this.drawString(fontRendererObj, EnumChatFormatting.GOLD + "Configuration de : " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Macro 1", this.width / 2 - fontRendererObj.getStringWidth("Confuguration de : " + "Macro 1") / 2, this.height / 2 - 100, -1);
				this.drawString(fontRendererObj, EnumChatFormatting.GOLD + "Status : " + EnumChatFormatting.BOLD + m, this.width / 2 - fontRendererObj.getStringWidth("Status : " + m) / 2, this.height / 2 - 57, -1);
				this.drawString(fontRendererObj, EnumChatFormatting.GOLD + "Commande actuelle : " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + ClientConfig.macro1, this.width / 2 - fontRendererObj.getStringWidth("Commande actuelle : " + ClientConfig.macro1) / 2, this.height / 2 - 70, -1);
				
				
        	}
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
    	}
    	GL11.glPopMatrix();
    }
	
	private void sendMacro(String pass) {
		ClientConfig.macro1 = pass;
		ClientConfig.save();
	}

	
}