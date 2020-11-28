package fr.valkya.valkyris.common.market.transaction;

import java.util.UUID;

import fr.valkya.valkyris.common.market.pricing.PricingCurrency;
import fr.valkya.valkyris.common.market.product.Product;
import net.minecraft.entity.player.EntityPlayerMP;

public class TransactionData {

	private EntityPlayerMP player;
	private Product product;
	private TransactionType type;
	private PricingCurrency currency;
	private int transactionAmmount;

	private String transactionID;

	public TransactionData(EntityPlayerMP player, Product product, TransactionType type, PricingCurrency currency, int ammount) {
		this.player = player;
		this.product = product;
		this.type = type;
		this.currency = currency;
		this.transactionAmmount = ammount;
		this.transactionID = UUID.randomUUID().toString();
	}

	public EntityPlayerMP getPlayer() {
		return player;
	}

	public Product getProduct() {
		return product;
	}

	public TransactionType getType() {
		return type;
	}

	public PricingCurrency getCurrency() {
		return currency;
	}

	public int getTransactionAmmount() {
		return transactionAmmount;
	}

	public String getTransactionID() {
		return transactionID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + transactionAmmount;
		result = prime * result + ((transactionID == null) ? 0 : transactionID.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (!(obj instanceof TransactionData)) {
			return false;
		}
		TransactionData other = (TransactionData) obj;
		if (currency != other.currency) {
			return false;
		}
		if (player == null) {
			if (other.player != null) {
				return false;
			}
		} else if (!player.equals(other.player)) {
			return false;
		}
		if (product == null) {
			if (other.product != null) {
				return false;
			}
		} else if (!product.equals(other.product)) {
			return false;
		}
		if (transactionAmmount != other.transactionAmmount) {
			return false;
		}
		if (transactionID == null) {
			if (other.transactionID != null) {
				return false;
			}
		} else if (!transactionID.equals(other.transactionID)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PurchaseData[");
		sb.append("player=");
		sb.append(player.getCommandSenderName());
		sb.append(",");
		sb.append("product=");
		sb.append(product.toString());
		sb.append(",");
		sb.append("type=");
		sb.append(type.name());
		sb.append(",");
		sb.append("currency=");
		sb.append(currency.name());
		sb.append(",");
		sb.append("transactionAmmount=");
		sb.append(transactionAmmount);
		sb.append("]");
		return sb.toString();
	}
}
