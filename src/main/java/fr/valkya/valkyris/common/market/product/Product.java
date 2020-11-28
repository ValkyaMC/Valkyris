package fr.valkya.valkyris.common.market.product;

import java.util.Map;

import fr.valkya.valkyris.common.market.pricing.Pricing;
import fr.valkya.valkyris.common.market.transaction.TransactionAction;
import fr.valkya.valkyris.common.market.transaction.TransactionType;

public class Product {

	private String internalName, displayName;
	private String description;
	private ProductCategory category;

	private ProductRenderer renderer;

	private Map<TransactionType, Pricing> pricings;
	private boolean discounted, available, allowsMultiplePurchases;

	private Map<TransactionType, TransactionAction> callbacks;

	public Product(String internalName, String displayName, String description, ProductCategory category, ProductRenderer renderer, Map<TransactionType, Pricing> pricings, boolean discounted, boolean available, boolean allowsMultiplePurchases, Map<TransactionType, TransactionAction> callbacks) {
		this.internalName = internalName;
		this.displayName = displayName;
		this.description = description;
		this.category = category;
		this.renderer = renderer;
		this.pricings = pricings;
		this.discounted = discounted;
		this.available = available;
		this.allowsMultiplePurchases = allowsMultiplePurchases;
		this.callbacks = callbacks;
	}

	public String getInternalName() {
		return internalName;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public ProductCategory getCategory() {
		return category;
	}

	public ProductRenderer getRenderer() {
		return renderer;
	}
	
	public Map<TransactionType, Pricing> getPricings() {
		return pricings;
	}

	public Pricing getPricing(TransactionType type) {
		return type == null ? null : this.pricings.get(type);
	}

	public boolean allows(TransactionType type) {
		Pricing pricing = getPricing(type);
		return pricing != null && !pricing.isEmpty();
	}

	public boolean isDiscounted() {
		return discounted;
	}

	public void setDiscounted(boolean discounted) {
		this.discounted = discounted;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public boolean allowsMultiplePurchases() {
		return allowsMultiplePurchases;
	}

	public Map<TransactionType, TransactionAction> getCallbacks() {
		return callbacks;
	}

}
