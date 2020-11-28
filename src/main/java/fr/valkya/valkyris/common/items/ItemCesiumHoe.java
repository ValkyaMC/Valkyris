package fr.valkya.valkyris.common.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ItemCesiumHoe extends ItemHoe {

	public ItemCesiumHoe(ToolMaterial p_i45343_1_) {
		super(p_i45343_1_);
	}

	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        boolean successfull = false;
        Block clicked = world.getBlock(x, y, z);
        if (!player.isSneaking() && player.canPlayerEdit(x, y, z, par7, stack) && (clicked == Blocks.dirt || clicked == Blocks.grass || clicked == Blocks.farmland) && par7 == 1) {
            int size = 1;
            for (int x1 = -size; x1 <= size; x1++) {
                for (int z1 = -size; z1 <= size; z1++) {
                    Block topBlock = world.getBlock(x + x1, y + 1, z + z1);
                    if (topBlock.isReplaceable(world, x + x1, y + 1, z + z1)) {
                        world.setBlockToAir(x + x1, y + 1, z + z1);
                    }
                    Block topBlock2 = world.getBlock(x + x1, y + 2, z + z1);
                    if (topBlock2.isReplaceable(world, x + x1, y + 2, z + z1)) {
                        world.setBlockToAir(x + x1, y + 2, z + z1);
                    }
                    Block block = world.getBlock(x + x1, y, z + z1);
                    if (block.isReplaceable(world, x + x1, y, z + z1) && !block.getMaterial().equals(Material.water)) {
                        world.setBlockToAir(x + x1, y, z + z1);
                    }

                    if (world.getBlock(x + x1, y, z + z1) == Blocks.air && world.getBlock(x + x1, y - 1, z + z1).isBlockSolid(world, x, y, z, 1)) {
                        if (player.inventory.hasItem(Item.getItemFromBlock(Blocks.dirt)) || player.capabilities.isCreativeMode) {
                            world.setBlock(x + x1, y, z + z1, Blocks.dirt);
                            player.inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.dirt));
                        }
                    }

                    if ((world.getBlock(x + x1, y + 1, z + z1) == Blocks.dirt || world.getBlock(x + x1, y + 1, z + z1) == Blocks.grass || world.getBlock(x + x1, y + 1, z + z1) == Blocks.farmland) && world.getBlock(x + x1, y + 2, z + z1) == Blocks.air) {
                        if (!world.isRemote)
                            world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Item.getItemFromBlock(Blocks.dirt))));
                        world.setBlock(x + x1, y + 1, z + z1, Blocks.air);
                    }

                    if (hoe(stack, player, world, x + x1, y, z + z1, par7)) successfull = true;
                }
            }
        } else successfull = hoe(stack, player, world, x, y, z, par7);
        Block block1 = Blocks.farmland;
        if (successfull)
            world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);
        return successfull;
    }
	
	private boolean hoe(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7) {
        if (!player.canPlayerEdit(x, y, z, par7, stack)) {
            return false;
        } else {
            UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
            if (MinecraftForge.EVENT_BUS.post(event)) {
                return false;
            }

            if (event.getResult() == Result.ALLOW) {
                stack.damageItem(1, player);
                return true;
            }

            Block block = world.getBlock(x, y, z);

            if (par7 != 0 && world.getBlock(x, y + 1, z).isAir(world, x, y + 1, z) && (block == Blocks.grass || block == Blocks.dirt)) {
                Block block1 = Blocks.farmland;

                if (world.isRemote) {
                    return true;
                } else {
                    world.setBlock(x, y, z, block1);
                    stack.damageItem(1, player);
                    return true;
                }
            } else {
                return false;
            }
        }
    }
	
	
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.GRAY + "Shift pour plus d'informations...");
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			p_77624_3_.clear();
			p_77624_3_.add(EnumChatFormatting.WHITE + "Cesium Hoe");
			p_77624_3_.add("Utilisable en 3x3");
		}
		super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}

}
