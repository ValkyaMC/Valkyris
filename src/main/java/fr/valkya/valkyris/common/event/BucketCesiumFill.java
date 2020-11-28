package fr.valkya.valkyris.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class BucketCesiumFill {
	
	@SubscribeEvent
    public void onBucketFill(FillBucketEvent event)
    {
        Block id = event.world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
        int metadata = event.world.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ);
        if(id == VBlocks.cesiumliquidb && metadata == 0)
        {
            event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
            if (!event.world.isRemote){
            	event.entityPlayer.inventory.consumeInventoryItem(Items.bucket);
                EntityItem itemsc = new EntityItem(event.world, event.entityPlayer.posX, event.entityPlayer.posY, event.entityPlayer.posZ, new ItemStack(VItems.cesiumBucket, 1));
                event.world.spawnEntityInWorld(itemsc);
            }
        }
    }
	
	

}
