package com.pawan.choure.Sapient2014;

import java.util.Date;

public class Swipe {
	
	private String stationName;
	private Date swipeDate;
	private Integer swipeIn;
	private Integer swipeOut;
	
	public Swipe(String stationName,Date swipeDate,Integer swipeIn,Integer swipeOut){
		this.stationName=stationName;
		this.swipeDate=swipeDate;
		this.swipeIn=swipeIn;
		this.swipeOut=swipeOut;
	}
	

	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Date getSwipeDate() {
		return swipeDate;
	}
	public void setSwipeDate(Date swipeDate) {
		this.swipeDate = swipeDate;
	}
	public Integer getSwipeIn() {
		return swipeIn;
	}
	public void setSwipeIn(Integer swipeIn) {
		this.swipeIn = swipeIn;
	}
	public Integer getSwipeOut() {
		return swipeOut;
	}
	public void setSwipeOut(Integer swipeOut) {
		this.swipeOut = swipeOut;
	}
	
	
	

}
