package fr.valkya.valkyris.common.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.collect.Lists;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemSpongeStick extends ValoItem {

	public ItemSpongeStick(String name) {
		super(name, Valkyris.getValkyris().getProxy().getCreativesTabs().valoCreativeTabsUtils, 1);
		this.setMaxDamage(10);
		this.setTextureName(References.MODID + ":sponge_stick");
	}
	
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
			p_77624_3_.add("Durabilitée : " +  (p_77624_1_.getMaxDamage() - p_77624_1_.getItemDamage()) + " / " + p_77624_1_.getMaxDamage());
			super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);
		if (movingobjectposition != null) {
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int x = movingobjectposition.blockX;
                int y = movingobjectposition.blockY;
                int z = movingobjectposition.blockZ;

                if (!world.canMineBlock(player, x, y, z)) {
                    return stack;
                }
                if(!player.canPlayerEdit(x, y, z, movingobjectposition.sideHit, stack)) {
                	return stack;
                }
                
                Material material = world.getBlock(x, y, z).getMaterial();
                if(material == Material.water) {
                	world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(Blocks.water));
                	this.absorb(world, x, y, z);
                	stack.damageItem(1, player);
                }
            }
        }
		
		return stack;
	}
	
	private boolean absorb(World world, int x, int y, int z) {
		LinkedList<Tuple> linkedlist = Lists.newLinkedList();
		ArrayList<WorldCoord> arraylist = Lists.newArrayList();
		linkedlist.add(new Tuple(new WorldCoord(x, y, z), 0));
		int i = 0;
		WorldCoord blockpos1;

		while (!linkedlist.isEmpty()) {
			Tuple tuple = linkedlist.poll();
			blockpos1 = (WorldCoord) tuple.getFirst();
			int j = (Integer) tuple.getSecond();

			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				WorldCoord blockpos2 = blockpos1.add(dir);

				if (world.getBlock(blockpos2.x, blockpos2.y, blockpos2.z).getMaterial() == Material.water) {
					world.setBlockToAir(blockpos2.x, blockpos2.y, blockpos2.z);
					arraylist.add(blockpos2);
					i++;
					if (j < 6)
						linkedlist.add(new Tuple(blockpos2, j + 1));
				}
			}

			if (i > 64)
				break;
		}

		Iterator<WorldCoord> iterator = arraylist.iterator();

		while (iterator.hasNext()) {
			blockpos1 = iterator.next();
			world.notifyBlockOfNeighborChange(blockpos1.x, blockpos1.y, blockpos1.z, Blocks.air);
		}

		return i > 0;
	}

	private final class WorldCoord implements Comparable<WorldCoord> {

		public int x;
		public int y;
		public int z;

		public WorldCoord(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public WorldCoord add(ForgeDirection dir) {
			return new WorldCoord(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(x).append(y).append(z).hashCode();
		}

		@Override
		public int compareTo(WorldCoord wc) {
			int legthThis = x * x + y * y + z * z;
			int legthOther = wc.x * wc.x + wc.y * wc.y + wc.z * wc.z;

			return Integer.compare(legthThis, legthOther);
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof WorldCoord))
				return false;
			WorldCoord wc = (WorldCoord) obj;

			return x == wc.x && y == wc.y && z == wc.z;
		}

		@Override
		public String toString() {
			return "Coord: " + x + ", " + y + ", " + z;
		}
	}
	
}
