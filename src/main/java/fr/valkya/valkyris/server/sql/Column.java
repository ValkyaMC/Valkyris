package fr.valkya.valkyris.server.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Column {
	
	private String name;
	private EnumSQLType type;
	private int length;
	private int default_values = 0;
	private EnumSQLIndex index;
	private boolean auto_increment = false;
	
	public Column(String name, EnumSQLType type, int length) {
		this(name, type, length, 0, null, false);
	}
	
	public Column(String name, EnumSQLType type, int length, boolean auto_increment) {
		this(name, type, length, 0, null, auto_increment);
	}
	
	public Column(String name, EnumSQLType type, int length, int defaultvalues, boolean auto_increment) {
		this(name, type, length, defaultvalues, null, auto_increment);
	}
	
	public Column(String name, EnumSQLType type, int length, EnumSQLIndex index, boolean auto_increment) {
		this(name, type, length, 0, index, auto_increment);
	}
	
	public Column(String name, EnumSQLType type, int length, int defaultvalues, EnumSQLIndex index, boolean auto_increment) {
		this.name = name;
		this.type = type;
		this.length = length;
		this.default_values = defaultvalues;
		this.index = index;
		this.auto_increment = auto_increment;
	}
	
	public String getName() {return this.name;}
	public EnumSQLType getType() {return this.type;}
	public int getLength() {return this.length;}
	public int getDefaultValues() {return this.default_values;}
	public EnumSQLIndex getIndex() {return this.index;}
	public boolean isAutoIncrement() {return this.auto_increment;}
	
	public void createColumn(String tableName, Connection conn) throws SQLException {
		PreparedStatement state = null;
		if(index == null && default_values == 0) {
			state = conn.prepareStatement("ALTER TABLE `" + tableName + "` ADD COLUMN IF NOT EXISTS `" + this.name + "` " + this.type.toString() + "(" + this.length + ") NULL");
		}else if(index == null && default_values != 0) {
			state = conn.prepareStatement("ALTER TABLE `" + tableName + "` ADD COLUMN IF NOT EXISTS `" + this.name + "` " + this.type.toString() + "(" + this.length + ") NOT NULL DEFAULT `" + default_values + "`");
		}else if(index != null && default_values == 0) {
			state = conn.prepareStatement("ALTER TABLE `" + tableName + "` ADD COLUMN IF NOT EXISTS `" + this.name + "` " + this.type.toString() + "(" + this.length + ") NULL, ADD " + index.toString() + " (`" + this.name + "`)");
		}else if(index != null && default_values != 0) {
			state = conn.prepareStatement("ALTER TABLE `" + tableName + "` ADD COLUMN IF NOT EXISTS `" + this.name + "` " + this.type.toString() + "(" + this.length + ") NOT NULL DEFAULT `" + default_values + "`, ADD " + index.toString() + " (`" + this.name + "`)");
		}
		state.executeUpdate();
	}
	
	public enum EnumSQLType {
		
		INT("int"),
		VARCHAR("varchar"),
		TEXT("text"), 
		DATETIME("datetime");
		
		private String name;
		
		private EnumSQLType(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
	}
	
	public enum EnumSQLIndex {
		
		PRIMARY("PRIMARY KEY"),
		UNIQUE("UNIQUE"),
		INDEX("INDEX"),
		FULLTEXT("FULLTEXT"),
		SAPTIAL("SPATIAL");
		
		private String name;
		
		EnumSQLIndex(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;
		}

	}
}
