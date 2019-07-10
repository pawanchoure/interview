package com.pawan.choure.Sapient2014;

import java.util.Date;

public class Station {
	
	private String stationName;
	private Integer stationNumber;
	private Swipe swipe;
	
	public Station (String stationName,Integer stationNumber){
		this.stationName=stationName;
		this.stationNumber=stationNumber;
		Swipe s=new Swipe(stationName,new Date(),0,0);
		setSwipe(s);
	}

	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Integer getStationNumber() {
		return stationNumber;
	}
	public void setStationNumber(Integer stationNumber) {
		this.stationNumber = stationNumber;
	}
	public Swipe getSwipe() {
		return swipe;
	}
	public void setSwipe(Swipe swipe) {
		this.swipe = swipe;
	}
	
	
	
	

}
