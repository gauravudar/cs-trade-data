package com.cs.data;

import java.io.Serializable;
import java.math.BigDecimal;

import org.joda.time.LocalDate;

import com.cs.dto.TradeDirection;
import com.cs.dto.TradeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonUnwrapped;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = OptionTradeDataObject.class, name = "OptionTradeDataObject"),
    @JsonSubTypes.Type(value = ForwardTradeDataObject.class, name = "ForwardTradeDataObject") }
)
public class TradeDataObject implements Serializable {
	private String customer;
	private String ccyPair;
	private TradeType type;
	private TradeDirection direction;
	private String tradeDate;
	private BigDecimal amount1;
	private BigDecimal amount2;
	private BigDecimal rate;
	private String legalEntity;
	private String trader;
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCcyPair() {
		return ccyPair;
	}
	public void setCcyPair(String ccyPair) {
		this.ccyPair = ccyPair;
	}
	public TradeType getType() {
		return type;
	}
	public void setType(TradeType type) {
		this.type = type;
	}
	public TradeDirection getDirection() {
		return direction;
	}
	public void setDirection(TradeDirection direction) {
		this.direction = direction;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public BigDecimal getAmount1() {
		return amount1;
	}
	public void setAmount1(BigDecimal amount1) {
		this.amount1 = amount1;
	}
	public BigDecimal getAmount2() {
		return amount2;
	}
	public void setAmount2(BigDecimal amount2) {
		this.amount2 = amount2;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	public String getLegalEntity() {
		return legalEntity;
	}
	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}
	public String getTrader() {
		return trader;
	}
	public void setTrader(String trader) {
		this.trader = trader;
	}
	
	@Override
	public String toString() {
		return "TradeDataObject [customer=" + customer + ", ccyPair=" + ccyPair
				+ ", type=" + type + ", direction=" + direction
				+ ", tradeDate=" + tradeDate + ", amount1=" + amount1
				+ ", amount2=" + amount2 + ", rate=" + rate + ", legalEntity="
				+ legalEntity + ", trader=" + trader + "]";
	}

}
