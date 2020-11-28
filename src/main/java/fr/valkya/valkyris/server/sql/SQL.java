package fr.valkya.valkyris.server.sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.valkya.valkyris.server.config.ServerConfig;
import fr.valkya.valkyris.server.sql.Table.EnumSQLEngine;

public class SQL {
	
	private Connection conn;
	public Connection getConnection() { return conn; }
	private ServerConfig config;
	
	public SQL(ServerConfig cfg) {
		this.config = cfg;
		try {
			Class<?> c = Class.forName("com.mysql.jdbc.Driver");
			Driver driver = (Driver) c.newInstance();
			DriverManager.registerDriver(driver);
			this.connect();
			System.out.println("Connecté à la database : " + this.config.dataBaseName);
		}catch (Exception e) {
			System.out.println("Connection Impossible : " + e.getMessage());
			e.printStackTrace();
			System.err.println("BDD Injoignable !");
			FMLCommonHandler.instance().exitJava(0, false);
		}
	}
	
	public Connection connect() {
		if(!this.isConnected()) {
			String finURI = this.config.protocole + "//" + this.config.ip + ":" + this.config.port + "/" + this.config.dataBaseName;
			System.out.println(finURI);
			try {
				conn = DriverManager.getConnection(finURI, config.user, config.password);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("The database is unreachable !");
				FMLCommonHandler.instance().exitJava(0, false);
			}
		}
		return conn;
	}
	
	public Table createTable(String name, EnumSQLEngine engine, Column... col) throws SQLException {
		verify();
		Table table = null;
		table = new Table(conn, name, engine, col);
		table.createTable();
		table.createColumns();
		return table;
	}
	
	public Table createTable(String name, EnumSQLEngine engine, String comment, Column... col) throws SQLException {
		verify();
		Table table = null;
		table = new Table(conn, name, comment, engine, col);
		table.createTable();
		table.createColumns();
		return table;
	}
	
	public boolean isConnected() {
		return this.conn != null;
	}

	public boolean tableExist(String string) throws SQLException {
		verify();
		ResultSet rs = conn.getMetaData().getTables(null, null, string, null);
		if(rs.next()) return true;
		return false;
	}
	
	private void verify() {
		try {
			if(!conn.isClosed()) {
				this.connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
