package com.cs.data;

public class ForwardTradeDataObject extends TradeDataObject {
	private String valueDate;
	
    public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	@Override
	public String toString() {
		return " [customer=" + getCustomer() + ", ccyPair="
						+ getCcyPair() + ", type=" + getType()
						+ ", direction=" + getDirection() + ", tradeDate="
						+ getTradeDate() + ", amount1=" + getAmount1()
						+ ", amount2=" + getAmount2() + ", rate=" + getRate()
						+ ", legalEntity=" + getLegalEntity() + ", trader="
						+ getTrader() +", valueDate=" + valueDate + "]";
	}

}
