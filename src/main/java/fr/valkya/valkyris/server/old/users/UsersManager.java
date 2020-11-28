package fr.valkya.valkyris.server.old.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.Valkyris;
import fr.valkya.valkyris.server.ServerProxy;
import fr.valkya.valkyris.server.sql.SQL;
import fr.valkya.valkyris.server.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent.Action;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

@SideOnly(Side.SERVER)
public class UsersManager {
	
	private SQL sql;
	
	public Map<UUID, User> users;
	public Map<UUID, Long> usersAuthTimer;

	private ServerProxy proxy;

	public UsersManager(SQL sql) {
		this.sql = sql;
		this.users = new HashMap<UUID, User>();
		this.usersAuthTimer = new HashMap<UUID, Long>();
		this.proxy = ((ServerProxy) Valkyris.getValkyris().getProxy());
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onClientConnect(PlayerLoggedInEvent event) {
		EntityPlayerMP player = (EntityPlayerMP) event.player;
		if(hasSession(player)) {
			if(proxy.getServerConfig().auth) authentificatePlayer(player);
			else addIfExist(player);
		}else {
			player.playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.RED + "Ce pseudo n'existe pas ! Veuiller créer ce compte sur le site : " + References.SITE);
		}
	}

	@SubscribeEvent
	public void onTick(ServerTickEvent event) {
		long currentTime = MinecraftServer.getSystemTimeMillis();
		usersAuthTimer.entrySet().forEach(e -> {
			EntityPlayerMP player = PlayerUtils.getPlayerFromUUID(e.getKey());
			Long dc = e.getValue();
			if(currentTime > dc) {
				player.playerNetServerHandler.kickPlayerFromServer("§cVous n'avez pas rentrer de mot de passe à temps !");
				usersAuthTimer.remove(e.getKey());
			}
		});
	}
	
	@SubscribeEvent
	public void onDisconect(PlayerLoggedOutEvent event) {
		UUID uuid = event.player.getUniqueID();
		if(users.containsKey(uuid)) { updateUser((EntityPlayerMP) event.player); }
	}
	
	public void save() {
		
	}

	private void updateUser(EntityPlayerMP player) {
		User u = getUser(player);
		try {
			PreparedStatement state = sql.getConnection().prepareStatement("UPDATE `users` SET `money` = ? WHERE `id` = ?");
			state.setInt(1, u.getRealMoney());
			state.setInt(2, u.getId());
			state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void authentificatePlayer(EntityPlayerMP player) {
		usersAuthTimer.put(player.getUniqueID(), System.currentTimeMillis() + 60000);
//		Valkyris.getValorion().getProxy().getNetwork().sendTo(new PacketRequestPassword(), player);
	}	
	
	public void verifyAuth(EntityPlayerMP player, String password) {
		User u = getUser(player);
		if(!u.getPassword().equalsIgnoreCase(password)) {
			player.playerNetServerHandler.kickPlayerFromServer("§cMot de passe invalide !");
			for(EntityPlayer op : PlayerUtils.getAllOP()) {
				op.addChatMessage(new ChatComponentText(player.getCommandSenderName() + " n'a pas réussi à se connecter !").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED).setBold(true).setChatHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ChatComponentText(player.getUniqueID().toString()).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GRAY).setBold(true))))));
			}
		} else {
//			((ServerProxy) Valkyris.getValorion().getProxy()).getAuthenticationManager().getAuthenticator(player).start();
			usersAuthTimer.remove(player.getUniqueID());
			addUser(player, u);
		}
	}
	
	private void addIfExist(EntityPlayerMP player) {
		if(hasSession(player)) addUser(player, getUser(player));
	}
	
	private void addUser(EntityPlayerMP player, User u) {
		users.put(player.getUniqueID(), u);		
	}

	private boolean hasSession(EntityPlayerMP player) {
		try {
			PreparedStatement state = sql.getConnection().prepareStatement("SELECT * FROM `users` WHERE `pseudo` = ?");
			state.setString(1, player.getCommandSenderName());
			ResultSet rs = state.executeQuery();
			if(rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private User getUserFromUsername(String name) {
		try {
			PreparedStatement state = sql.getConnection().prepareStatement("SELECT * FROM `users` WHERE `pseudo` = ?");
			state.setString(1, name);
			ResultSet rs = state.executeQuery();
			if(rs.next()) return new User(rs.getInt("id"), rs.getString("pseudo"), rs.getString("uuid"), rs.getString("password"), rs.getString("email"), rs.getInt("money"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private User getUser(EntityPlayerMP player) {
		return getUserFromUsername(player.getCommandSenderName());
	}
}
