package com.pawan.choure.Sapient2014;

public class SmartCard {
	private Integer cardNumber;
	private Double balance;
	
	
	public SmartCard(Integer cardNumber,Double balance)
	{
		this.balance=balance;
		this.cardNumber=cardNumber;
	}
	public Integer getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	
	
}
