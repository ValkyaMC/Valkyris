package fr.valkya.valkyris.common.market.pricing;

public enum PricingCurrency {
	MONEY(0, "Money", "$", "moneyig"), 
	TOKENS(1, "Point_s_ Boutique", "PB_s_", "money");		
	
	private int id;
	private String name;
	private String abreviated;
	private String databaseColumn;
	
	PricingCurrency(int id, String name, String abreviated, String databaseColumn) {
		this.id = id;
		this.name = name;
		this.abreviated = abreviated;
		this.databaseColumn = databaseColumn;
	}
	
	public int getID() {
		return id;
	}
	
	public String getNameSingular() {
		return name.replace("_s_", "");
	}
	
	public String getNamePlural() {
		return name.replace("_s_", "s");
	}
	
	public String getAbreviatedSingular() {
		return abreviated.replace("_s_", "");
	}
	
	public String getAbreviatedPlural() {
		return abreviated.replace("_s_", "s");
	}	
	
	public String getDatabaseColumn() {
		return databaseColumn;
	}

	public static PricingCurrency fromID(int id) {
		for (PricingCurrency pc : values())
			if (pc.id == id)
				return pc;
		return null;
	}
}