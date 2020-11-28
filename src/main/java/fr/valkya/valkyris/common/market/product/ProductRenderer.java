package fr.valkya.valkyris.common.market.product;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GL11;

import com.google.gson.annotations.Expose;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.common.utils.SerializableItemStack;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ProductRenderer {
	
	@SideOnly(Side.CLIENT)
	@Expose(deserialize = false, serialize = false)
	private transient static RenderItem renderItem;
	
	private RenderType type;
	
	private ResourceLocation icon;
	private transient int width;
	private transient int height;
	
	private SerializableItemStack stack;

	public ProductRenderer(ResourceLocation icon) {
		this.type = RenderType.ICON;
		this.icon = icon;
	}
	
	public ProductRenderer(Item item) { this(new ItemStack(item, 0)); }	
	public ProductRenderer(Item item, int itemMeta) { this(new ItemStack(item, itemMeta)); }
	public ProductRenderer(ItemStack stack) {
		this.type = RenderType.ITEM;
		this.stack = new SerializableItemStack(stack);
	}

	public ProductRenderer(Block block) {
		this(Item.getItemFromBlock(block));
	}
	
	@SideOnly(Side.CLIENT)
	public void render(int x, int y, int width, int height) {
		Minecraft mc = Minecraft.getMinecraft();
		
		boolean blendState = GL11.glIsEnabled(GL11.GL_BLEND);
		if(!blendState) GL11.glEnable(GL11.GL_BLEND);
		
		switch(type) {
			case ICON:
				if(this.width == 0 || this.height == 0) {
					try {
						Image image = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(icon).getInputStream());
						this.width = image.getWidth(null);
						this.height = image.getHeight(null);
					} catch(IOException e) {
						LogManager.getLogger("ProductRenderer").error("Couldn't read icon " + icon + "!");
						e.printStackTrace();
						
						// hope this works
						this.width = 16; 
						this.height = 16;
					}
				}
				mc.renderEngine.bindTexture(icon);
				Gui.func_152125_a(x, x, 0, 0, this.width, this.height, width, height, this.width, this.height);
				break;
			case ITEM:
				renderItem.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), stack.getItemStack(), x, y);
				break;
		}
		
		if(!blendState) GL11.glDisable(GL11.GL_BLEND);
	}
	
	public RenderType getType() {
		return type;
	}

	public ResourceLocation getIcon() {
		return icon;
	}

	public SerializableItemStack getStack() {
		return stack;
	}

	public enum RenderType { 
		ICON(0), ITEM(1);
		
		int id;
		RenderType(int id){
			this.id = id;
		}
		
		public int getID() {
			return id;
		}
		
		public static RenderType fromID(int id) {
			for (RenderType tt : values())
				if (tt.id == id)
					return tt;
			return null;
		}
	}
	
	private static void clinit() {
		clinit0();
	}
	
	@SideOnly(Side.CLIENT)
	private static void clinit0() {
		renderItem = new RenderItem();
	}
	
	static {
		try {
			if(FMLCommonHandler.instance().getSide().isClient()) {
				clinit();
			}
		} catch(ClassCastException ignored) {
			clinit();
		}
	}

}
