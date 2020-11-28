package fr.valkya.valkyris.common.market.exception;

@SuppressWarnings("serial")
public class MarketException extends RuntimeException {

	public MarketException() {}
	
	public MarketException(String message) {
		super(message);
	}
	
	public MarketException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
