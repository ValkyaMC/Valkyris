package fr.valkya.valkyris.client.gui.overrides;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import cpw.mods.fml.client.FMLClientHandler;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.gui.button.GuiTextButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.ISaveFormat;

@Deprecated
public class GuiMainMenuValorion extends GuiScreen implements GuiYesNoCallback{
	
	private Minecraft mc = Minecraft.getMinecraft(); 
	private FontRenderer font = mc.fontRenderer; 
	private static final Logger logger = LogManager.getLogger();
    private String field_104024_v;
    private static final ResourceLocation minecraftTitleTextures = new ResourceLocation(References.MODID, "textures/gui/logo-full.png");
    private static ResourceLocation background;
    private ServerData loadbalancer = new ServerData("loadbalancer", "185.44.81.72:25565", false);
    
    private final OldServerPinger serverPinger = new OldServerPinger();
    private static final ThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(5, (new ThreadFactoryBuilder()).setNameFormat("Server Pinger #%d").setDaemon(true).build());
    
    private String beta;
    
    public GuiMainMenuValorion() {
    	background = new ResourceLocation(References.MODID, "textures/gui/background_main.png");
    	this.beta = "185.44.81.72:50000";
    } 

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char p_73869_1_, int p_73869_2_) {}

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
	public void initGui() {
    	FMLClientHandler.instance().setupServerList();
    	
        int fontheight = font.FONT_HEIGHT / 2;

        this.buttonList.add(new GuiTextButton(0, this.width / 2 - (font.getStringWidth("owo?") / 2) - 10, this.height / 2 - fontheight - 5, font.getStringWidth("owo?") + 10, font.FONT_HEIGHT + 5, -1, 16777120, "owo?"));
//        this.buttonList.add(new GuiTextButton(96, this.width / 2 - (font.getStringWidth("Serveur Bêta") / 2) - 10, this.height - 15 - fontheight - 5, font.getStringWidth("Serveur Bêta") + 10, font.FONT_HEIGHT + 5, -1, 16777120, "Serveur Bêta"));
//        this.buttonList.add(new GuiTextButton(1, this.width / 9 - 10, this.height / 2 - fontheight - 5, font.getStringWidth("Site") + 10, font.FONT_HEIGHT + 5, -1, 16777120, "Site"));
//        this.buttonList.add(new GuiTextButton(2, this.width * 4 / 5 - 10, this.height / 2 - fontheight - 5, font.getStringWidth("Discord") + 10, font.FONT_HEIGHT + 5, -1, 16777120, "Discord"));
//        this.buttonList.add(new GuiTextButton(3, this.width / 4 - 10, this.height / 10 * 7 - fontheight - 5, font.getStringWidth("Options") + 10, font.FONT_HEIGHT + 5, -1, 16777120, "Options"));
//        this.buttonList.add(new GuiTextButton(4, this.width / 4 * 3 - font.getStringWidth("Quitter") - 10, this.height / 10 * 7 - fontheight - 5, font.getStringWidth("Quitter") + 10, font.FONT_HEIGHT + 5, -1, 16777120, "Quitter"));
//        
//        this.buttonList.add(new GuiTextButton(420, 2, 2, 75, 20, -1, 0xFFCCCC, "Sol-owo"));
//    	this.buttonList.add(new GuiTextButton(69, 2, 24, 75, 20, -1, 0xFFCCCC, "Muwultijoueur"));
    }
  

    protected void actionPerformed(GuiButton p_146284_1_)
    {
    	switch (p_146284_1_.id) {
	    	case 0:
	    		mc.displayGuiScreen(new GuiConnectingValkya(this, mc, new ServerData("Valkya", "localhost", false)));
	            break;
	    	case 1:
	    		try {
					Desktop.getDesktop().browse(new URI("https://valorion-mc.fr/shop"));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
	    		break;
	    	case 2:
	    		try {
					Desktop.getDesktop().browse(new URI("https://discord.gg/E69mKUd"));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
	    		break;
	    	case 3:
	    		mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
	    		break;
	    	case 4:
	    		mc.shutdown();
	    		break;
	    	case 420:
	    		this.mc.displayGuiScreen(new GuiSelectWorld(this));
	    		break;
	    	case 69:
	    		this.mc.displayGuiScreen(new GuiMultiplayer(this));
	    		break;
	    	case 96:
	            FMLClientHandler.instance().connectToServer(this, new ServerData("Valorion Beta", beta, false));
	            break;
        }
    }

    public void confirmClicked(boolean p_73878_1_, int p_73878_2_)
    {
        if (p_73878_1_ && p_73878_2_ == 12)
        {
            ISaveFormat isaveformat = this.mc.getSaveLoader();
            isaveformat.flushCache();
            isaveformat.deleteWorldDirectory("Demo_World");
            this.mc.displayGuiScreen(this);
        }
        else if (p_73878_2_ == 13)
        {
            if (p_73878_1_)
            {
                try
                {
                	Desktop.getDesktop().browse(new URI(field_104024_v));
                }
                catch (Throwable throwable)
                {
                    logger.error("Couldn\'t open link", throwable);
                }
            }

            this.mc.displayGuiScreen(this);
        }
    }

    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
    	Gui.drawRect(0, 0, this.width, this.height, 0xFF000000);
    	super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    	
    	if("true".equals("true")) {
    		return;
    	}
    	
        mc.getTextureManager().bindTexture(background);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.func_152125_a(0, 0, 0, 0, 1, 1, this.width, this.height, 1, 1);
       
        GL11.glPushMatrix();
        double scale = 2;
        GL11.glScaled(scale, scale, 0);
        for(int i = 0; i < 20; i++) {
        	for(int j = 0; j < 10; j++) {
            	this.fontRendererObj.drawStringWithShadow("GUI Temporaire, niquez vos meres.", (int) (2 + (j * (this.fontRendererObj.getStringWidth("GUI Temporaire, niquez vos meres") + 5))), (int) (2 + (i * 10 * scale)), 42069);
            }
        }
        GL11.glPopMatrix();
        
        this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
        
        this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.func_152125_a(this.width / 2 - 75, this.height / 4 - 75, 0,0, 1, 1, 150, 150, 1, 1);
                
        String s1 = "Copyright Mojang AB. Do not distribute!";
        this.fontRendererObj.drawStringWithShadow(s1, this.width - this.fontRendererObj.getStringWidth(s1) - 2, this.height - 10, -1);
        
        
        if(!this.loadbalancer.field_78841_f)
        {
            this.loadbalancer.field_78841_f = true;
            this.loadbalancer.pingToServer = -2L;
            this.loadbalancer.serverMOTD = "";
            this.loadbalancer.populationInfo = "";
            EXECUTOR.submit(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        GuiMainMenuValorion.this.serverPinger.func_147224_a(GuiMainMenuValorion.this.loadbalancer);
                    }
                    catch(UnknownHostException unknowHostException)
                    {
                    	GuiMainMenuValorion.this.loadbalancer.pingToServer = -1L;
                        GuiMainMenuValorion.this.loadbalancer.serverMOTD = EnumChatFormatting.DARK_RED + "Impossible de résoudre le nom d'hôte";
                    }
                    catch(Exception exception)
                    {
                    	GuiMainMenuValorion.this.loadbalancer.pingToServer = -1L;
                        GuiMainMenuValorion.this.loadbalancer.serverMOTD = EnumChatFormatting.DARK_RED + "Impossible de se connecter au serveur";
                    }
                }
            });
        }
        
        if(this.loadbalancer.pingToServer > 0) {   		
        	String[] data = this.loadbalancer.populationInfo.split(Pattern.quote("/"));
        	this.fontRendererObj.drawStringWithShadow(data[0] + " / " + data[1], 2, this.height - mc.fontRenderer.FONT_HEIGHT - 2, 16249521);
        } else {
        	this.fontRendererObj.drawStringWithShadow("Serveur hors ligne", 1, this.height - mc.fontRenderer.FONT_HEIGHT - 2, 16249521);
        }
        
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);    
    }    
	
}
