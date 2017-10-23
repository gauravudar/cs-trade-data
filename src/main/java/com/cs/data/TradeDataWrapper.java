package com.cs.data;

import com.cs.dto.TradeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TradeDataWrapper {
	
	@JsonUnwrapped
	private TradeDataObject tradeDataObject;
	
	

	public TradeDataObject getTradeDataObject() {
		return tradeDataObject;
	}

	public void setTradeDataObject(TradeDataObject tradeDataObject) {
		this.tradeDataObject = tradeDataObject;
	}

	@JsonUnwrapped
	private OptionTradeDataObject optionTradeDataObject;
	
	@JsonUnwrapped
	private ForwardTradeDataObject forwardTradeDataObject;

	public OptionTradeDataObject getOptionTradeDataObject() {
		return optionTradeDataObject;
	}

	public void setOptionTradeDataObject(
			OptionTradeDataObject optionTradeDataObject) {
		this.optionTradeDataObject = optionTradeDataObject;
	}

	public ForwardTradeDataObject getForwardTradeDataObject() {
		return forwardTradeDataObject;
	}

	public void setForwardTradeDataObject(
			ForwardTradeDataObject forwardTradeDataObject) {
		this.forwardTradeDataObject = forwardTradeDataObject;
	}

	@Override
	public String toString() {
		if(tradeDataObject.getType()==TradeType.VanillaOption){
		return "[OptionTradeDataObject="
				+ optionTradeDataObject + "]";
		} 
		else {
			return "[ForwardTradeDataObject="
					+ forwardTradeDataObject + "]";
		}
	}

	

}
