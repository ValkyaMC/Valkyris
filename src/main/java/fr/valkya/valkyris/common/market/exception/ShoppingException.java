package fr.valkya.valkyris.common.market.exception;

import java.util.Arrays;
import java.util.List;

import fr.valkya.valkyris.common.market.pricing.PricingCurrency;
import fr.valkya.valkyris.common.market.transaction.TransactionType;

@SuppressWarnings("serial")
public class ShoppingException extends MarketException {

	private ShoppingError error;
	private List<String> details;
	
	public ShoppingException(ShoppingError errorType, String... moreInfo) {
		super();
		this.error = errorType;

		this.details = Arrays.asList(moreInfo);
		if(!this.details.isEmpty()) {
			this.details.add(0, "Plus de détails:");
		}
	}
	
	public ShoppingError getError() {
		return error;
	}

	public List<String> getDetails() {
		return details;
	}

	public enum ShoppingError {
		NO_MULTIPLE_PURCHASES("Vous avez déjà acheté ce produit!"),
		PRODUCT_UNAVAILABLE("Le produit que vous avez demandé est actuellement indisponible."),
		TRANSACTION_NOT_SUPPORTED("Ce produit ne peux pas être %s."),
		CURRENCY_NOT_SUPPORTED("Vous ne pouvez pas %s ce produit %s des %s."),
		NO_ITEMS("Vous n'avez pas suffisament d'items dans votre inventaire pour en vendre."),
		NOT_ENOUGH_ITEMS("Vous n'avez pas suffisament d'items dans votre inventaire pour en vendre autant."),
		NOT_ENOUGH_INVENTORY_SPACE("Vous n'avez pas assez de place dans votre inventaire pour reçevoir ce produit."),
		NOT_ENOUGH_MONEY("Votre solde actuel est insuffisant pour acheter ce produit."),
		INTERNAL_ERROR("Une erreur interne est survenue.");
		
		private String msg;
		
		private ShoppingError(String msg) {
			this.msg = msg;
		}
		
		public String getMessage(Object... format) {
			if(this == TRANSACTION_NOT_SUPPORTED) {
				TransactionType type = (TransactionType)format[0];
				return String.format(msg, type == TransactionType.BUY ? "acheté" : "vendu");
			}
			if(this == CURRENCY_NOT_SUPPORTED) {
				TransactionType type = (TransactionType)format[0];
				PricingCurrency currency = (PricingCurrency)format[1];
				return String.format(msg, type == TransactionType.BUY ? "acheter" : "vendre", type == TransactionType.BUY ? "avec" : "pour", currency.getAbreviatedPlural());
			}
			return msg;
		}
	}
	
}
