package com.pawan.choure.Sapient2014;

import java.util.ArrayList;

public class UserReport {
	private Integer cardNumber;
	private ArrayList<TravelTransaction> transaction;

	public Integer getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	public ArrayList<TravelTransaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(ArrayList<TravelTransaction> transaction) {
		this.transaction = transaction;
	}

}
