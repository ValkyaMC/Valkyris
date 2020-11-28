package fr.valkya.valkyris.server.old.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.valkya.valkyris.server.sql.Column;
import fr.valkya.valkyris.server.sql.SQL;
import fr.valkya.valkyris.server.sql.Column.EnumSQLIndex;
import fr.valkya.valkyris.server.sql.Column.EnumSQLType;
import fr.valkya.valkyris.server.sql.Table.EnumSQLEngine;
import fr.valkya.valkyris.server.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class MachineHelper {
	
	private static Connection conn;
	public static int acceptedNbAcc = 1;
	private HashMap<EntityPlayerMP, String> players;

	public MachineHelper(SQL sql) {
		conn = sql.getConnection();
		players = new HashMap<EntityPlayerMP, String>();
		
		Column[] col = new Column[] {
			new Column("id", EnumSQLType.INT, 11, EnumSQLIndex.PRIMARY, true),
			new Column("machineId", EnumSQLType.TEXT, 255, false),
			new Column("mcuid", EnumSQLType.TEXT, 255, false),
			new Column("username", EnumSQLType.TEXT, 255, false)
		};
		
		try {
			sql.createTable("accounts", EnumSQLEngine.MYISAM, col);
		} catch (SQLException e) { e.printStackTrace(); } 
	}

	public boolean hasOtherLinkAccount(String machineID, String uid) {
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("SELECT * FROM `accounts` WHERE machineId = ?");
			ps.setString(1, machineID);
			
			ResultSet rs = ps.executeQuery();
			int nb = 0;
	        while(rs.next()) {
				String uid1 = rs.getString("mcuid");
				if(uid1.equalsIgnoreCase(uid)) return false;
				nb++;
	        }
	        
	        if(nb < acceptedNbAcc ) {
	        	return false;
	        }
	        System.out.println(machineID + " se connecte avec un double compte !");
        	return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public void createAccount(String machineID, String uid, String name) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `accounts` (`id`, `machineId`, `mcuid`, `username`) VALUES (NULL, ?, ?, ?)");
		ps.setString(1, machineID);
		ps.setString(2, uid);
		ps.setString(3, name);
		ps.execute();
		System.out.println("Création de compte pour l'uid : " + uid);
	}

	public boolean isRegisteredAccount(String machineID, String uid) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("SELECT * FROM `accounts` WHERE machineId = ? AND mcuid = ?");
			ps.setString(1, machineID);
			ps.setString(2, uid);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println(machineID + " avait déja une session !");
				return true;
			}
				
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public String getMachineFromName(String name) {
		EntityPlayer player = PlayerUtils.getPlayerByName(name);
		if(player == null) return null;
		
		return players.get(player);
	}

	public void addPlayer(EntityPlayerMP playerEntity, String machineId) {
		players.put(playerEntity, machineId);
	}
	
	public void removePlayer(EntityPlayerMP player) {
		players.remove(player);
	}

	public Map<String,String> getAllAccountFromMachine(String machineId) throws SQLException {
		PreparedStatement st = conn.prepareStatement("SELECT `username`, `mcuid`  FROM `accounts` WHERE `machineId` = ?");
		st.setString(1, machineId);
		
		ResultSet rs = st.executeQuery();
		
		Map<String, String> acc = new HashMap<String, String>();
		
		while(rs.next())
			acc.put(rs.getString("username"), rs.getString("mcuid"));
		
		return acc;
	}

	public boolean isValidIdType(String machineId) {
		return false;
	}

}
