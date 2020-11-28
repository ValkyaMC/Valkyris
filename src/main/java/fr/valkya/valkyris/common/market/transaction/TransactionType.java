package fr.valkya.valkyris.common.market.transaction;

public enum TransactionType {
	BUY(0), SELL(1);

	int id;

	TransactionType(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}
	
	public static TransactionType fromID(int id) {
		for (TransactionType tt : values())
			if (tt.id == id)
				return tt;
		return null;
	}
}
