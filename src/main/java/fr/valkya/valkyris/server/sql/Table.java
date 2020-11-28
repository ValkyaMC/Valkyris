package fr.valkya.valkyris.server.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Table {
	
	private String name;
	private EnumSQLEngine engine;
	private Column[] column;
	private String comment;
	private Connection conn;
	
	public Table(Connection conn, String name, EnumSQLEngine engine, Column... columns) {
		this.conn = conn;
		this.name = name;
		this.comment = "";
		this.engine = engine;
		this.column = columns;
	}
	
	public Table(Connection conn, String name, String comment, EnumSQLEngine engine, Column... columns) {
		this.conn = conn;
		this.name = name;
		this.comment  = comment;
		this.engine = engine;
		this.column = columns;
	}
	
	public void createTable() throws SQLException {
		Column fcol = column[0];
		String s = null;
		if(!fcol.isAutoIncrement()) {
			if(fcol.getType() != null) s = "CREATE TABLE IF NOT EXISTS " + this.name + "(" + fcol.getName() + " " + fcol.getType().toString() + "(" + fcol.getLength() + ") NOT NULL,  " + fcol.getIndex() + " (" + fcol.getName() + ")) ENGINE=" + this.engine.toString() + " COMMENT='" + this.comment + "';";
			else s = "CREATE TABLE IF NOT EXISTS " + this.name + "(" + fcol.getName() + " " + fcol.getType().toString() + "(" + fcol.getLength() + ") NOT NULL) ENGINE=" + this.engine.toString() + " COMMENT='" + this.comment + "';"; 
		}else {
			if(fcol.getType() != null) s = "CREATE TABLE IF NOT EXISTS " + this.name + "(" + fcol.getName() + " " + fcol.getType().toString() + "(" + fcol.getLength() + ") NOT NULL AUTO_INCREMENT,  " + fcol.getIndex() + " (" + fcol.getName() + ")) ENGINE=" + this.engine.toString() + " COMMENT='" + this.comment + "';";
			else s = "CREATE TABLE IF NOT EXISTS " + this.name + "(" + fcol.getName() + " " + fcol.getType().toString() + "(" + fcol.getLength() + ") NOT NULL AUTO_INCREMENT) ENGINE=" + this.engine.toString() + " COMMENT='" + this.comment + "';"; 
		}
		PreparedStatement state = conn.prepareStatement(s);
		state.executeUpdate();
		System.out.println("Table " + this.name + " crée !");
	}
	
	public void createColumns() throws SQLException {
		for(Column col : this.column) {
			if(col != this.column[0]) col.createColumn(this.name, conn);
		}
		System.out.println("Colonne(s) ajoutée(s) dans " + this.name + " !");
	}
	
	public void addColumn(Column col) throws SQLException {
		col.createColumn(this.name, conn);
	}
	
	public enum EnumSQLEngine {
		
		CSV("CSV"),
		MEMORY("MEMORY"),
		MYISAM("MyISAM"),
		SEQUENCE("SEQUENCE"),
		ARIA("Aria"),
		INNODB("InnoDB");
		
		private String s;
		
		EnumSQLEngine(String s){
			this.s = s;
		}

		public String toString() {
			return s;
		}
		
	}

}
