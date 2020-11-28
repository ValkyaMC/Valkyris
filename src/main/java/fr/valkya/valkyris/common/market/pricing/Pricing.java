package fr.valkya.valkyris.common.market.pricing;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public class Pricing {
	
	private Map<PricingCurrency, Number> prices;
	
	public Pricing(Map<PricingCurrency, Number> prices) {
		this.prices = prices;
	}
	
	public Number getPricing(PricingCurrency currency) {
		return currency == null ? null : prices.get(currency);
	}
	
	public boolean isEmpty() {
		return prices.isEmpty();
	}
	
	public Map<PricingCurrency, Number> getDiscountedPrices(int percentage){
		Map<PricingCurrency, Number> newPrices = new HashMap<>();
		prices.keySet().forEach(pt -> {
			Number oldPrice = prices.get(pt);
			newPrices.put(pt, oldPrice.doubleValue() * (percentage / 100));
		});
		return newPrices;
	}
	
	public Map<PricingCurrency, Number> getPrices() {
		return prices;
	}
	
	public static Pricing of(Object... objects) {
		if(objects.length == 0)
			return Pricing.empty();
		if(objects.length % 2 != 0)
			throw new IllegalStateException("Cannot create ProductPricing with an odd object number!");
		
		Map<PricingCurrency, Number> prices = new HashMap<>();
		
		PricingCurrency lastPricing = null;
		for(int i = 0; i < objects.length; i++) {
			Object o = objects[i];
			if(i % 2 == 0) {
				if(!(o instanceof PricingCurrency)) 
					throw new UnsupportedOperationException("Cannot create ProductPricing with " + o.getClass().getName() + " !");
				lastPricing = (PricingCurrency)o;
			}else {
				if(!(o instanceof Number))
					throw new UnsupportedOperationException("Cannot create ProductPricing with non-numerical value " + o.getClass().getName() + " !");
				prices.put(lastPricing, (Number)o);
			}
		}
		
		return new Pricing(prices);
	}
	
	public static Pricing empty() {
		return new Pricing(Maps.newHashMap());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prices == null) ? 0 : prices.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pricing)) {
			return false;
		}
		Pricing other = (Pricing) obj;
		if (prices == null) {
			if (other.prices != null) {
				return false;
			}
		} else if (!prices.equals(other.prices)) {
			return false;
		}
		return true;
	}
}
