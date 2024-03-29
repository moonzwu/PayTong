package com.ace.production.text;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class SMSContent implements Serializable{
	private String cardNumberTrail;
	private String bankName;
	private int billingMonth;
	private Date payTime;
	private Map<String, BigDecimal> payMoneyMap = new HashMap<String, BigDecimal>();
	private float theLeastPayMoney;

    public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayMoney(String currency, BigDecimal payMoney) {
		this.payMoneyMap.put(currency, payMoney);
	}
	public BigDecimal getPayMoney(String currency) {
		return this.payMoneyMap.get(currency);
	}
    public Set<String> getCurrencies() {
        return this.payMoneyMap.keySet();
    }
	public void setTheLeastPayMoney(float theLeastPayMoney) {
		this.theLeastPayMoney = theLeastPayMoney;
	}
	public float getTheLeastPayMoney() {
		return theLeastPayMoney;
	}
	public void setBillingMonth(int billingMonth) {
		this.billingMonth = billingMonth;
	}
	public int getBillingMonth() {
		return billingMonth;
	}
	public void setCardNumberTrail(String cardNumberTrail) {
		this.cardNumberTrail = cardNumberTrail;
	}
	public String getCardNumberTrail() {
		return cardNumberTrail;
	}
}

