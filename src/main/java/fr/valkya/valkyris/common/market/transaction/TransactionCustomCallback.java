package fr.valkya.valkyris.common.market.transaction;

import fr.valkya.valkyris.common.market.exception.MarketException;

@FunctionalInterface
public interface TransactionCustomCallback {

	/**
	 * Execute a custom callback<br>
	 * <b>this MUST implement {@link TransactionData#getTransactionAmmount()}</b>
	 * 
	 * @param purchaseData
	 * 		the purchase's data

	 * @return wheather or not this purchase should go through
	 * 
	 * @throws MarketException
	 * 		if anything happens
	 */
	boolean execute(TransactionData purchaseData) throws MarketException;
	
}
