package fr.valkya.valkyris.server.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class PlayerUtils {
	
	public static EntityPlayerMP getPlayerByName(String name) {
		return MinecraftServer.getServer().getConfigurationManager().func_152612_a(name);
	}
	
	public static List<EntityPlayerMP> getAllPlayers() {
		return Arrays.asList(MinecraftServer.getServer().getAllUsernames()).stream().map(PlayerUtils::getPlayerByName).filter(Objects::nonNull).collect(Collectors.toList());
	}

	public static EntityPlayerMP getPlayerFromUUID(UUID key) {
		return getAllPlayers().stream().filter(p -> p.getUniqueID() == key).findFirst().orElse(null);
	}
	
	public static List<EntityPlayer> getAllOP(){
		return getAllPlayers().stream().filter(PlayerUtils::isOP).collect(Collectors.toList());
	}

	public static boolean isOP(EntityPlayer sender) {
		return MinecraftServer.getServer().getConfigurationManager().func_152596_g(sender.getGameProfile());
	}

}
