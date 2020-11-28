package fr.valkya.valkyris.common.items;

import net.minecraft.block.BlockGrass;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EasterEggColorants extends BlockGrass {

	
	public boolean onBlockActivated(World world, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
         if(player.getHeldItem() != null && player.getHeldItem().getItem() == Items.apple)
         { 
             EntityItem entityItem = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Items.arrow));
             world.spawnEntityInWorld(entityItem);
         }
         else
         {
             player.addChatMessage(new ChatComponentText("L'item utilisé n'est pas le bon !"));
         }
		return blockConstructorCalled;
    }
	
}
