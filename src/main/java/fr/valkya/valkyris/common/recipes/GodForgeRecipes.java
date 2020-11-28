package fr.valkya.valkyris.common.recipes;

import java.util.HashMap;
import java.util.Map;

import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GodForgeRecipes {
	
	private static final GodForgeRecipes craft = new GodForgeRecipes();
	private Map<ItemStack[], ItemStack> craftingList = new HashMap<ItemStack[], ItemStack>();
	
	public GodForgeRecipes() {
//		this.addRecipe(VItems.hand, VItems.corne, VItems.gobelin, VItems.carnivore, VItems.fish, VItems.ghost, VItems.titanhead, VItems.skull, VItems.valoIngot, VItems.valoArmors.valoHelmet);
//		this.addRecipe(VItems.hand, VItems.corne, VItems.gobelin, VItems.carnivore, VItems.fish, VItems.ghost, VItems.titanhead, VItems.wing, VItems.valoIngot, VItems.valoArmors.valoChestplate);
//		this.addRecipe(VItems.hand, VItems.corne, VItems.gobelin, VItems.carnivore, VItems.fish, VItems.ghost, VItems.titanhead, VItems.spiderleg, VItems.valoIngot, VItems.valoArmors.valoLeggins);
//		this.addRecipe(VItems.hand, VItems.corne, VItems.gobelin, VItems.carnivore, VItems.fish, VItems.ghost, VItems.titanhead, VItems.mermaidtail, VItems.valoIngot, VItems.valoArmors.valoBoots);
//		this.addRecipe(VItems.hand, VItems.corne, VItems.gobelin, VItems.carnivore, VItems.fish, VItems.ghost, VItems.titanhead, VItems.barbare, VItems.valoIngot, VItems.valoSword);
//		this.addRecipe(VItems.hand, VItems.corne, VItems.gobelin, VItems.carnivore, VItems.fish, VItems.ghost, VItems.titanhead, VItems.hammer, VItems.valoIngot, VItems.valoPickaxe);
//		this.addRecipe(VItems.hand, VItems.corne, VItems.gobelin, VItems.carnivore, VItems.fish, VItems.ghost, VItems.titanhead, VItems.valkyaxe, VItems.valoIngot, VItems.valoAxe);
//		this.addRecipe(VItems.hand, VItems.corne, VItems.gobelin, VItems.carnivore, VItems.fish, VItems.ghost, VItems.titanhead, VItems.motherboard, VItems.valoIngot, VItems.valoShovel);
//		
		this.addRecipe(new ItemStack(VItems.valo_fragment, 4, 0), VBlocks.chargedzirconiumBlock, VBlocks.chargedzirconiumBlock, VBlocks.chargedzirconiumBlock, VBlocks.chargedzirconiumBlock, VBlocks.chargedcesiumBlock, VBlocks.chargedcesiumBlock, VBlocks.chargedcesiumBlock, VBlocks.chargedcesiumBlock, VBlocks.randomOre);
	}
	
	private void addRecipe(ItemStack result, Object... ingredients) {
		if(ingredients.length != 9) {
			throw new UnsupportedOperationException("Crafting ingredients must total to 9");
		}
		
		ItemStack[] arr = new ItemStack[ingredients.length];
		for(int i = 0; i < ingredients.length; i++) {
			Object owo = ingredients[i];
			
			ItemStack stacc = null;
			if(owo instanceof Item) {
				stacc = new ItemStack((Item)owo, 1);
			}else if(owo instanceof Block) {
				stacc = new ItemStack(Item.getItemFromBlock((Block)owo), 1);
			}else if(owo instanceof ItemStack) {
				stacc = (ItemStack)owo;
			}else {
				throw new UnsupportedOperationException("Cannot create recipe with ingredient type: " + owo.getClass().getName() + " (" + owo + ")");
			}
			
			arr[i] = stacc;
		}
		
		addRecipe(arr, result);
	}
	
	private void addRecipe(ItemStack[] items, ItemStack result) {
		this.craftingList.put(items, result);
	}

	public ItemStack getCraftingResult(ItemStack[] stack) {
		for(ItemStack[] craftings : craftingList.keySet()) {
			if(isSameKey(stack, craftings)) {
				return craftingList.get(craftings);
			}
		}
		return null;
	}

	private boolean isSameKey(ItemStack[] stacklist, ItemStack[] stacklist2) {
		boolean isSame = false;
		for (int i = 0; i <= 8; i++) {
			if(stacklist[i].getItem() == stacklist2[i].getItem()) {
				isSame = true;
			}else {
				return false;
			}
		}
		return isSame;
	}
	
	public static GodForgeRecipes crafting() {
		return craft;
	}
	
	public Map<ItemStack[], ItemStack> getCraftingList(){
		return craftingList;
	}
}
