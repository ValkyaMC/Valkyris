package fr.valkya.valkyris.client.gui.overrides;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.client.authentication.VNetHandlerLoginClient;
import fr.valkya.valkyris.client.utils.RenderHelper;
import fr.valkya.valkyris.client.utils.animate.Animation;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiConnectingValkya extends GuiScreen {

	private Animation animT;
	
    private static final AtomicInteger field_146372_a = new AtomicInteger(0);
    private static final Logger logger = LogManager.getLogger();
    private NetworkManager netManager;
    private boolean cancelConnection;
    private final GuiScreen parentScreen;

    public GuiConnectingValkya(GuiScreen p_i1181_1_, Minecraft p_i1181_2_, ServerData p_i1181_3_)
    {
        this.mc = p_i1181_2_;
        this.parentScreen = p_i1181_1_;
        ServerAddress serveraddress = ServerAddress.func_78860_a(p_i1181_3_.serverIP);
        p_i1181_2_.loadWorld((WorldClient)null);
        p_i1181_2_.setServerData(p_i1181_3_);
        this.func_146367_a(serveraddress.getIP(), serveraddress.getPort());
    }

    public GuiConnectingValkya(GuiScreen p_i1182_1_, Minecraft p_i1182_2_, String p_i1182_3_, int p_i1182_4_)
    {
        this.mc = p_i1182_2_;
        this.parentScreen = p_i1182_1_;
        p_i1182_2_.loadWorld((WorldClient)null);
        this.func_146367_a(p_i1182_3_, p_i1182_4_); // on peut try avec astolfomc ?? euh ue
    }

    private void func_146367_a(final String p_146367_1_, final int p_146367_2_)
    {    	
        logger.info("Connecting to " + p_146367_1_ + ", " + p_146367_2_);
        (new Thread("Server Connector #" + field_146372_a.incrementAndGet())
        {
            public void run()
            {
                InetAddress inetaddress = null;

                try
                {
                    if (GuiConnectingValkya.this.cancelConnection)
                    {
                        return;
                    }

                    inetaddress = InetAddress.getByName(p_146367_1_);
                    GuiConnectingValkya.this.netManager = NetworkManager.provideLanClient(inetaddress, p_146367_2_);
                    GuiConnectingValkya.this.netManager.setNetHandler(new VNetHandlerLoginClient(GuiConnectingValkya.this.netManager, GuiConnectingValkya.this.mc, GuiConnectingValkya.this.parentScreen));
                    GuiConnectingValkya.this.netManager.scheduleOutboundPacket(new C00Handshake(5, p_146367_1_, p_146367_2_, EnumConnectionState.LOGIN), new GenericFutureListener[0]);
                    GuiConnectingValkya.this.netManager.scheduleOutboundPacket(new C00PacketLoginStart(GuiConnectingValkya.this.mc.getSession().func_148256_e()), new GenericFutureListener[0]);
                }
                catch (UnknownHostException unknownhostexception)
                {
                    if (GuiConnectingValkya.this.cancelConnection)
                    {
                        return;
                    }

                    GuiConnectingValkya.logger.error("Couldn\'t connect to server", unknownhostexception);
                    GuiConnectingValkya.this.mc.displayGuiScreen(new GuiDisconnectedValkya(GuiConnectingValkya.this.parentScreen, "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[] {"Unknown host"})));
                }
                catch (Exception exception)
                {
                    if (GuiConnectingValkya.this.cancelConnection)
                    {
                        return;
                    }

                    GuiConnectingValkya.logger.error("Couldn\'t connect to server", exception);
                    String s = exception.toString();

                    if (inetaddress != null)
                    {
                        String s1 = inetaddress.toString() + ":" + p_146367_2_;
                        s = s.replaceAll(s1, "");
                    }

                    GuiConnectingValkya.this.mc.displayGuiScreen(new GuiDisconnectedValkya(GuiConnectingValkya.this.parentScreen, "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[] {s})));
                }
            }
        }).start();
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        if (this.netManager != null)
        {
            if (this.netManager.isChannelOpen())
            {
                this.netManager.processReceivedPackets();
            }
            else if (this.netManager.getExitMessage() != null)
            {
                this.netManager.getNetHandler().onDisconnect(this.netManager.getExitMessage());
            }
        }
    }
    
    @Override
    public void initGui() {
    	animT = new Animation(0, 0);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char p_73869_1_, int p_73869_2_) {
    	if(p_73869_2_ == Keyboard.KEY_ESCAPE) {
    		this.cancelConnection = true;

            if (this.netManager != null)
            {
                this.netManager.closeChannel(new ChatComponentText("Aborted"));
            }

            this.mc.displayGuiScreen(this.parentScreen);
    	}
    }

    private boolean back;
    
    private static final ResourceLocation bg = new ResourceLocation(References.MODID, "textures/gui/bg_connect.png");
    private static final ResourceLocation logo = new ResourceLocation(References.MODID, "textures/gui/logo.png");
    
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {    	
    	ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        
        mc.renderEngine.bindTexture(bg);
        Gui.func_152125_a(0, 0, 0, 0, 1920, 1057, sr.getScaledWidth(), sr.getScaledHeight(), 1920, 1057);
        
        int w = mc.fontRenderer.getStringWidth(I18n.format("connect.connecting", new Object[0]));
        int w2 = mc.fontRenderer.getStringWidth(I18n.format("connect.authorizing", new Object[0]));
        if(w2 > w)
        	w = w2;

        Gui.drawRect(0, this.height - 30, this.width, this.height, Integer.MIN_VALUE);        
        // head -> x = 2, y = 2, width = 26, height = 26
        GL11.glPushMatrix();
        {       
        	GL11.glColor4d(1, 1, 1, 1);
        	mc.renderEngine.bindTexture(RenderHelper.loadHead());
        	Gui.func_152125_a(2, this.height - 30 + 2, 0, 0, 64, 64, 26, 26, 64, 64); // k 
        }
        GL11.glPopMatrix();

        mc.fontRenderer.drawStringWithShadow(mc.getSession().getUsername(), 32, this.height - 30 + 3, -1);
        
        long diff = System.currentTimeMillis() - Valkyris.getValkyris().getProxy().startTimestamp;
        long diffSeconds = (diff / 1000) % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (1000 * 60 * 60);
        
        String played = "En jeu depuis: " + EnumChatFormatting.GRAY;
        
        if(diffHours > 0) {
        	if(diffHours > 12)
        		played += EnumChatFormatting.RED; 
        	played += diffHours + "h ";
        }
        if(diffMinutes > 0) {
        	played += diffMinutes + "m ";
        }
        if(diffSeconds > 0) {
        	played += diffSeconds + "s";
        }
        
        mc.fontRenderer.drawStringWithShadow(played, 32, this.height - 30 + 3 + 16, -1);
        
        int barX = 32 + mc.fontRenderer.getStringWidth("En jeu depuis: " + "999h 69m 69s"); // déja 99h wtf mdrr on sait jamais
        Gui.drawRect(barX, this.height - 30 + 2, this.width - (2 + w + 20), this.height - 2, Integer.MIN_VALUE);
        
        int barWidth = this.width - (2 + w + 20) - barX;
        int barBumperWidth = 35;
        double percentage = animT.getX() / 100D;
        int barBumperX = (int) (barX + ((barWidth - barBumperWidth) * percentage));
        
        double percentageLogo2 = back ? ((100 - animT.getX()) / 100) : (animT.getX() / 100);
        
        if(animT.interpolate(back ? 0 : 100, 0, 2)) {
        	back = !back;
        }
        
        Gui.drawRect(barBumperX, this.height - 30 + 2, barBumperX + barBumperWidth, this.height - 2, 0xFFFFB011);
        
        GL11.glPushMatrix();
        {
        	boolean blend = GL11.glIsEnabled(GL11.GL_BLEND);
        	if(!blend)
        		GL11.glEnable(GL11.GL_BLEND);
        	mc.renderEngine.bindTexture(logo);

        	GL11.glColor4d(1, 1, 1, 1);
        	GL11.glTranslated(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 - 50, 0); 
        	Gui.func_152125_a(-100, -100, 0, 0, 512, 512, 200, 200, 512, 512); 
        	
        	if(percentageLogo2 != 0) {
        		GL11.glColor4d(1, 1, 1, 1 - percentageLogo2);
        		GL11.glScaled(1 + .15 * percentageLogo2, 1 + .15 * percentageLogo2, 1);
        		Gui.func_152125_a(-100, -100, 0, 0, 512, 512, 200, 200, 512, 512);            	
            }
        	
        	if(!blend)
        		GL11.glDisable(GL11.GL_BLEND); 
        }
        GL11.glPopMatrix();
        
        if (this.netManager == null) {  
            this.drawCenteredString(this.fontRendererObj, I18n.format("connect.connecting", new Object[0]), this.width - ((2 + w + 20) / 2), this.height - 30 + 11, 16777215);
        } else {
            this.drawCenteredString(this.fontRendererObj, I18n.format("connect.authorizing", new Object[0]), this.width - ((2 + w + 20) / 2), this.height - 30 + 11, 16777215);
        }

        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_); // bouton
    }
}