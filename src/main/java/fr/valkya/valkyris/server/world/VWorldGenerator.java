package fr.valkya.valkyris.server.world;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.common.block.VBlocks;
import fr.valkya.valkyris.server.ServerProxy;
import fr.valkya.valkyris.server.config.ServerConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public enum VWorldGenerator implements IWorldGenerator {
	INSTANCE;
	
	private final ServerConfig cfg;
	private final List<OreInfo> ores;
	
	private VWorldGenerator() {
		cfg = ((ServerProxy)Valkyris.getValkyris().getProxy()).getServerConfig();
				
		ores = Arrays.asList(
				new OreInfo(VBlocks.cesiumOre).setMinY(1).setMaxY(16).setMaxVein(8).setVeinPerChunk(6).setSpawnChancePercentage(cfg.cesiumPercentage).build(),
				new OreInfo(VBlocks.zirconiumOre).setMinY(1).setMaxY(16).setMaxVein(6).setVeinPerChunk(4).setSpawnChancePercentage(cfg.zircoPercentage).build(),
				new OreInfo(VBlocks.randomOre).setMinY(1).setMaxY(16).setMaxVein(3).setVeinPerChunk(4).setSpawnChancePercentage(cfg.randomPercentage).build()
		);
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId) {
            case 0:
                if(cfg.activeOre) {
                	this.ores.forEach(oi -> oi.generate(world, random, chunkX, chunkZ));
                }
                break;
        }
	}
   	
	private class OreInfo {
		
		private final Block oreBlock;
		private int minY, maxY, maxVein, veinPerChunk, spawnChancePercentage;
		
		private WorldGenMinable generator;
		
		public OreInfo(Block oreBlock) {
			this.oreBlock = oreBlock;
		}
		
		public OreInfo setMinY(int minY) {
			this.minY = minY;
			return this;
		}
		
		public OreInfo setMaxY(int maxY) {
			this.maxY = maxY;
			return this;
		}
		
		public OreInfo setMaxVein(int maxVein) {
			this.maxVein = maxVein;
			return this;
		}
		
		public OreInfo setVeinPerChunk(int veinPerChunk) {
			this.veinPerChunk = veinPerChunk;
			return this;
		}
		
		public OreInfo setSpawnChancePercentage(int spawnChancePercentage) {
			this.spawnChancePercentage = spawnChancePercentage;
			return this;
		}
		
		public OreInfo build() {
			generator = new WorldGenMinable(this.oreBlock, this.maxVein, Blocks.stone);
			return this;
		}
		
		public void generate(World worldIn, Random rng, int chunkX, int chunkZ) {
			int rn = rng.nextInt(100);
			
			if(rn > (100 - spawnChancePercentage)) {
				int chunkSize = 16;
				
				int posX = chunkX * chunkSize;
				int posZ = chunkZ * chunkSize;
				
	            for(int i = 0; i < veinPerChunk; i++) {
	            	int x = posX + rng.nextInt(chunkSize);
		            int y = minY + rng.nextInt(maxY - minY);
		            int z = posZ + rng.nextInt(chunkSize);
		            
					generator.generate(worldIn, rng, x, y, z);
	            }
			}
		}
	}

}
