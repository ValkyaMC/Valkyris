package fr.valkya.valkyris.common.entity;

public enum EntitySize {

	SMALL(0.1F), NORMAL(1.0F), GIANT(3.0F);

	private float size;

	private EntitySize(float size) {
		this.size = size;
	}

	public float getSize() {
		return size;
	}
	
	public boolean isSmall() {
		return this == SMALL;
	}
	
	public boolean isNormal() {
		return this == NORMAL;
	}
	
	public boolean isGiant() {
		return this == GIANT;
	}

	public static EntitySize getSizeOrdinal(int i) {
		for (EntitySize size : EntitySize.values()) {
			if (size.ordinal() == i) {
				return size;
			}
		}
		return null;
	}

}
