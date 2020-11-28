package fr.valkya.valkyris.common.recipes;

import java.util.HashMap;
import java.util.Map;

import fr.valkya.valkyris.api.APIProvider;
import fr.valkya.valkyris.common.items.VItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GrinderRecipes {
	
    private static final GrinderRecipes smeltingBase = new GrinderRecipes();
    private Map<ItemStack[], ItemStack> smeltingList = new HashMap<>();

    public GrinderRecipes() {
        this.addRecipe(VItems.cesium_gem, VItems.cesium_gem, Items.stick, new ItemStack(VItems.cesium_activator));
        this.addRecipe(VItems.zirconium_gem, VItems.zirconium_gem, Items.stick, new ItemStack(VItems.zirconium_activator));
        this.addRecipe(APIProvider.provideAPI().getRegistry().getItem("prismarine_shard"), APIProvider.provideAPI().getRegistry().getItem("prismarine_shard"), APIProvider.provideAPI().getRegistry().getItem("prismarine_crystals"), new ItemStack(VItems.aqua_ingot));
        this.addRecipe(VItems.cesium_gem, VItems.cesium_gem, VItems.esp_helmet, new ItemStack(VItems.esp_helmet));
    }

    public void addRecipe(ItemStack left, ItemStack right, ItemStack base, ItemStack result) {
        ItemStack[] stackList = new ItemStack[] {left, right, base};
        this.smeltingList.put(stackList, result);
    }

    public void addRecipe(Item item1, Item item2, Item item3, ItemStack stack) {
        this.addRecipe(new ItemStack(item1), new ItemStack(item2), new ItemStack(item3), stack);
    }

    public ItemStack getSmeltingResult(ItemStack[] stack) {
    	for(ItemStack[] is : smeltingList.keySet()) {
    		if(isSameKey(is, stack)) {
    			return smeltingList.get(is);
    		}
    	}
    	return null;
    }

    private boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2) {
        boolean isSame = false;
        for(int i = 0; i <= 2; i++) {
            if(stackList[i].getItem() == stackList2[i].getItem()) isSame = true;
            else return false;
        }
        return isSame;
    }

    public Map<ItemStack[], ItemStack> getSmeltingList() {
        return this.smeltingList;
    }

    public static GrinderRecipes smelting() {
        return smeltingBase;
    }

}
