package fr.valkya.valkyris.common.market.product;

public enum ProductCategory {
	GRADES(0, "Grades"),	 
	SPAWNERS(1, "Spawners"), 
	KITS(2, "Kits"),
	MINERAIS(3, "Minerais"),
	FARM(4, "Farm"),
	MOBS(5, "Mobs"),
	FOOD(6, "Food"),
	AUTRES(7, "Autres");
	
	private int id;
	private String name;
	
	ProductCategory(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public static ProductCategory fromID(int id) {
		for(ProductCategory pc : values()) if(pc.id == id) return pc;
		return null;
	}
	
}