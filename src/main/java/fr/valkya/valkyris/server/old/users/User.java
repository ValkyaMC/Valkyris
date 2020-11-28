package fr.valkya.valkyris.server.old.users;

public class User {
	
	private int id;
	private String name;
	private String uuid;
	private String password;
	private String email;
	private int realMoney;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getUuid() { return uuid; }
	public void setUuid(String uuid) { this.uuid = uuid; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public int getRealMoney() { return realMoney; }
	public void setRealMoney(int realMoney) { this.realMoney = realMoney; }

	public User(int id, String name, String uuid, String password, String email, int realMoney) {
		this.id = id;
		this.name = name;
		this.uuid = uuid;
		this.password = password;
		this.email = email;
		this.realMoney = realMoney;
	}
}
