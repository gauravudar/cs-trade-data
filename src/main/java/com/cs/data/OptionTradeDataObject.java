package com.cs.data;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

import com.cs.dto.OptionExerciseStyle;
import com.cs.dto.OptionStrategy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OptionTradeDataObject extends TradeDataObject{
	private OptionExerciseStyle style;
	private OptionStrategy strategy;
	private String deliveryDate;
	private String expiryDate;
	private String excerciseStartDate;
	private String payCcy;
	private BigDecimal premium;
	private String premiumCcy;
	private String premiumType;
	private String premiumDate;
	
	
	public OptionExerciseStyle getStyle() {
		return style;
	}
	public void setStyle(OptionExerciseStyle style) {
		this.style = style;
	}
	public OptionStrategy getStrategy() {
		return strategy;
	}
	public void setStrategy(OptionStrategy strategy) {
		this.strategy = strategy;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getExcerciseStartDate() {
		return excerciseStartDate;
	}
	public void setExcerciseStartDate(String excerciseStartDate) {
		this.excerciseStartDate = excerciseStartDate;
	}
	public String getPayCcy() {
		return payCcy;
	}
	public void setPayCcy(String payCcy) {
		this.payCcy = payCcy;
	}
	public BigDecimal getPremium() {
		return premium;
	}
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	public String getPremiumCcy() {
		return premiumCcy;
	}
	public void setPremiumCcy(String premiumCcy) {
		this.premiumCcy = premiumCcy;
	}
	public String getPremiumType() {
		return premiumType;
	}
	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}
	public String getPremiumDate() {
		return premiumDate;
	}
	public void setPremiumDate(String premiumDate) {
		this.premiumDate = premiumDate;
	}
	
	
	@Override
	public String toString() {
		return " [customer=" + getCustomer() + ", ccyPair="
				+ getCcyPair() + ", type=" + getType()
				+ ", direction=" + getDirection() + ", tradeDate="
				+ getTradeDate() + ", amount1=" + getAmount1()
				+ ", amount2=" + getAmount2() + ", rate=" + getRate()
				+ ", legalEntity=" + getLegalEntity() + ", trader="
				+ super.getTrader() + ", style=" + style + ", strategy="
				+ strategy + ", deliveryDate=" + deliveryDate + ", expiryDate="
				+ expiryDate + ", excerciseStartDate=" + excerciseStartDate
				+ ", payCcy=" + payCcy + ", premium=" + premium
				+ ", premiumCcy=" + premiumCcy + ", premiumType=" + premiumType
				+ ", premiumDate=" + premiumDate +"]";
	}
	
}
