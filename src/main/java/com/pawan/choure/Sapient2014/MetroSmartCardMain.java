package com.pawan.choure.Sapient2014;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class MetroSmartCardMain {
	private static ArrayList<User> userList=new ArrayList<>();
	private static Calendar cal = Calendar.getInstance();
	private static final int SATURDAY = 6;
	private static final int SUNDAY = 7;
	private static HashMap<String, String> fareMap = new HashMap<>();
	private static HashMap<String, Station> stationMap = new HashMap<>();

	public static void main(String[] args) {

		try {
			fareMap = new FareProperty().getPropValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
		User user1 = new User("pawanchoure", "Pawan", "Choure", 1, 100.0);
		userList.add(user1);
		stationMap.put("A1", new Station("A1", 1));
		stationMap.put("A2", new Station("A2", 2));
		stationMap.put("A3", new Station("A3", 3));
		stationMap.put("A4", new Station("A4", 4));
		stationMap.put("A5", new Station("A5", 5));
		stationMap.put("A6", new Station("A6", 6));
		stationMap.put("A7", new Station("A7", 7));
		stationMap.put("A8", new Station("A8", 8));
		stationMap.put("A9", new Station("A9", 9));
		stationMap.put("A10", new Station("A10", 10));

		if (validateSwipeIn(user1)) {
			TravelTransaction t = new TravelTransaction();
			t.setCardNumber(user1.getCardNumber());
			t.setStartStation("A1");
			t.setEndStation("A9");
			t.setIsCurrent(true);
			ArrayList<TravelTransaction> tt = new ArrayList<>();
			tt.add(t);
			user1.getTravelJournal().setTransaction(tt);
			validateSwipeOut(user1);
			swipeReportPerStation();
			userCardReport();

		} else {
			System.out.println("InSufficient Balance, Please Recharge your Balance");
		}

	}

	/**
	 * Validate SwipeIn :This Basically validates whether the user has sufficient balance
	 * @param endUser
	 * @return
	 */
	private static Boolean validateSwipeIn(User endUser) {
		if (isWeekend() && endUser.getCard().getBalance() > Double.parseDouble(fareMap.get("weekendFare")))
			return true;
		else if (!isWeekend() && endUser.getCard().getBalance() > Double.parseDouble(fareMap .get("weekDayFare")))
			return true;
		return false;
	}

	/**
	 * validateSwipeOut :This Function validate whether user has sufficient balance to pay the travel cost
	 * @param endUser
	 * @return
	 */
	private static Boolean validateSwipeOut(User endUser) {
		String startStation = null;
		String endStation = null;
		TravelTransaction tcurr = null;

		ArrayList<TravelTransaction> tr = endUser.getTravelJournal().getTransaction();
		for (int j = 0; j < tr.size(); j++) {
			TravelTransaction t = tr.get(j);
			if (t.getIsCurrent()) {
				tcurr = t;
				startStation = tcurr.getStartStation();
				endStation = tcurr.getEndStation();
			}
		}

		Station startSt = stationMap.get(startStation);
		Station endSt = stationMap.get(endStation);
		Double fare = calculateFare(startSt.getStationNumber(),endSt.getStationNumber(), isWeekend());

		Double currBal = endUser.getCard().getBalance();
		if (currBal > fare) {
			endUser.getCard().setBalance(currBal - fare);
			for (int j = 0; j < tr.size(); j++) {
				TravelTransaction t = tr.get(j);
				if (t.getIsCurrent()) {
					t.setIsCurrent(false);
				}
			}
			Swipe sed = new Swipe(startSt.getSwipe().getStationName(), startSt.getSwipe().getSwipeDate(),startSt.getSwipe().getSwipeIn() + 1, startSt.getSwipe()
							.getSwipeOut());
			startSt.setSwipe(sed);
			Swipe sed2 = new Swipe(endSt.getSwipe().getStationName(), endSt.getSwipe().getSwipeDate(),
					endSt.getSwipe().getSwipeIn() + 1, endSt.getSwipe().getSwipeOut());
			endSt.setSwipe(sed2);
			
			tcurr.setFare(fare);
			tcurr.setBalance(endUser.getCard().getBalance());
			return true;
		}

		return false;
	}

	/**
	 * calculateFare : This Function calculates the fare
	 * @param stationNumber
	 * @param stationNumber2
	 * @param weekend
	 * @return
	 */
	private static Double calculateFare(Integer stationNumber,
			Integer stationNumber2, boolean weekend) {
		Integer stval;
		stval = stationNumber - stationNumber2;
		if (isWeekend()) {
			if (Integer.signum(stval) == -1) {
				stval = stval * -1;
			}

			return stval * Double.parseDouble(fareMap.get("weekendFare"));
		} else {
			if (Integer.signum(stval) == -1) {
				stval = stval * -1;
			}
			return stval * Double.parseDouble(fareMap.get("weekDayFare"));
		}
	}

	/**
	 * swipeReportPerStation :This Function generates the Report of SwipeIn/SwipeOut per station
	 */
	private static void swipeReportPerStation() {
		Iterator<String> a=stationMap.keySet().iterator();
		while(a.hasNext())
		{
			Station station=stationMap.get(a.next());
			System.out.println("Station Name :"+ station.getStationName() +" Station Number :"+ station.getStationNumber()
					+" SwipeIn :"+ station.getSwipe().getSwipeIn() +" SwipeOut :"+ station.getSwipe().getSwipeOut());
			
		}
			
	}

	/**
	 * userCardReport: This Function prints the user Transaction
	 */
	private static void userCardReport() {
		for(int i=0;i <userList.size(); i++)
		{
			User u=userList.get(i);
			
			ArrayList<TravelTransaction> tr = u.getTravelJournal().getTransaction();
			for (int j = 0; j < tr.size(); j++) {
				TravelTransaction t = tr.get(j);
				System.out.println(" Card number : "+ u.getCardNumber() +" Source Station :"+ t.getStartStation()
						+" Destionation Station :"+ t.getEndStation() +" Fare :"+ t.getFare() + 
						" Balance :"+ t.getBalance());
			}
		}
	}

	/**
	 * isWeekend :This Function verify whether today date is Weekday/weekend
	 * @return
	 */
	private static boolean isWeekend() {
		if (SUNDAY == cal.DAY_OF_WEEK || SATURDAY == cal.DAY_OF_WEEK)
			return true;
		return false;
	}
}
