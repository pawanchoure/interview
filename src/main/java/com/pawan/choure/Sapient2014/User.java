package com.pawan.choure.Sapient2014;


public class User {

	private String firstName;
	private String lastName;
	private Integer cardNumber;
	private String userName;
	
	private SmartCard card;
	private UserReport travelJournal;
	
	public User(String userName,String firstName,String lastName,Integer cardNumber,Double balance){
		this.userName=userName;
		this.firstName=firstName;
		this.lastName=lastName;
		this.cardNumber=cardNumber;
		SmartCard c=new SmartCard(cardNumber,balance);
		setCard(c);
		UserReport ur=new UserReport();
		ur.setCardNumber(cardNumber);
		setTravelJournal(ur);
				
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public SmartCard getCard() {
		return card;
	}
	public void setCard(SmartCard card) {
		this.card = card;
	}

	public Integer getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserReport getTravelJournal() {
		return travelJournal;
	}

	public void setTravelJournal(UserReport travelJournal) {
		this.travelJournal = travelJournal;
	}
	
	
	
	
	
	
}
