package fr.valkya.valkyris.api.impl;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import fr.valkya.valkyris.api.ISharedRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class SharedRegistry implements ISharedRegistry {

	private final BiMap<String, Item> nameToItemMap;
	private final BiMap<String, Block> nameToBlockMap;
	
	public SharedRegistry() {
		this.nameToItemMap = HashBiMap.create();
		this.nameToBlockMap = HashBiMap.create();
	}
	
	@Override
	public void registerItem(Item item, String registryName) {
		this.nameToItemMap.put(registryName, item);
	}

	@Override
	public void registerBlock(Block block, String registryName) {
		this.nameToBlockMap.put(registryName, block);
	}

	@Override
	public Item getItem(String registryName) {
		return registryName == null ? null : nameToItemMap.get(registryName);
	}

	@Override
	public Block getBlock(String registryName) {
		return registryName == null ? null : nameToBlockMap.get(registryName);
	}

	@Override
	public String getRegistryName(Item item) {
		return item == null ? null : nameToItemMap.inverse().get(item);
	}

	@Override
	public String getRegistryName(Block block) {
		return block == null ? null : nameToBlockMap.inverse().get(block);
	}

}
