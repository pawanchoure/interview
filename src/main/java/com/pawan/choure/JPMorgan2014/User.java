package com.pawan.choure.JPMorgan2014;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class User {
	private String firstName;
	private String lastName;
	private Game game;

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;

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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Method to Parse the input file and create an Object and then 
	 * Calculate the score
	 * @param input
	 */
	public void playGame(String input) {
		Game gameObj = parseInput(input);
		Integer result=calculateScore(gameObj);
		System.out.println("Bowling Game Result :"+result);
	}

	/**
	 * This Method will take the Game Object which we have created after parsing the
	 * input file and calculate the score
	 * @param gameObj
	 * @return
	 */
	private int calculateScore(Game gameObj) {
		int frameCounter = 1;
		int finalValue = 0;
		Boolean isSpare = false, isTopCleanStrike = false,isTopPlusCleanStrike = false,isStrike=false;
		for (Frames frame : gameObj.getFrames()) {
			if (!isFinalFrame(frameCounter)) {
				int shotValue = 0;
				int shotCounter=1;
				
				for (Shot shot : frame.getShots()) {
						shotValue += shot.getValue();
						
						if(isSpare){
							finalValue+=shotValue;
							isSpare=false;
						}
						if(isStrike){
								finalValue+=shotValue;
						}
						if(isTopPlusCleanStrike){
							finalValue+=shotValue;
							isTopPlusCleanStrike=false;
						}
						if(isTopCleanStrike){
							finalValue+=shotValue;
							isTopCleanStrike=false;
						}
						if(shotCounter==1 && shotValue==BowlingApplicationMain.gameSetting.get("framePerGame")){
							if(isTopCleanStrike)
								isTopPlusCleanStrike=true;
							if(isStrike)
								isTopCleanStrike=true;
						
							isStrike=true;
							break;
						}
						if(shotCounter==2 && shotValue==BowlingApplicationMain.gameSetting.get("framePerGame")){
							isSpare=true;
							break;
						}
						shotCounter++;
				}
				finalValue+=shotValue;
			}
			else{
				int shotValue = 0;
				for (Shot shot : frame.getShots()) {
						shotValue += shot.getValue();
						
						if(isSpare){
							finalValue+=shotValue;
							isSpare=false;
						}
						if(isStrike){
							finalValue+=shotValue;
							isStrike=false;
							//shotValue=0;
						}
						if(isTopCleanStrike){
							finalValue+=shotValue;
							isTopCleanStrike=false;
							isTopPlusCleanStrike=true;
						}
						if(isTopPlusCleanStrike){
							finalValue+=shotValue;
							isTopPlusCleanStrike=false;
						}

				}
				finalValue+=shotValue;
			}
			frameCounter++;
		}
		return finalValue;

	}

	/**
	 * This method will parse the input and create a Game Object 
	 * @param input
	 * @return
	 */
	public Game parseInput(String input) {
		int frameCounter = 1;
		game = new Game();
		ArrayList<Frames> frameList = new ArrayList<>();
		Pattern ptn = Pattern.compile("\\],\\[");
		String[] frames = ptn.split(input);

		if (frames != null) {
			for (String frame : frames) {
				if (!isValidFrame(frameCounter)) {
					System.out.println("Invalid Bowling Frame");
					break;
				}
				frame = StringUtils.removeStart(frame, "[");
				frame = StringUtils.removeEnd(frame, "]");
				String[] shots = StringUtils.split(frame, ',');
				ArrayList<Shot> shotList = new ArrayList<>();
				int shotCounter = 1;
				for (String shotName : shots) {
					if (!isFinalFrame(frameCounter) && !isValidShot(shotCounter)) {
						System.out.println("Invalid Bowling Tries");
						break;
					}
					Shot shotObj = new Shot();
					shotObj.setValue(Integer.valueOf(shotName));
					shotList.add(shotObj);
					shotCounter++;
				}
				Frames frameObj = new Frames();
				frameObj.setShots(shotList);
				frameList.add(frameObj);
				frameCounter++;
			}
			game.setFrames(frameList);
		}
		return game;
	}

	public Boolean isFinalFrame(int counter) {
		if (counter == BowlingApplicationMain.gameSetting.get("framePerGame"))
			return true;
		return false;
	}

	public Boolean isValidFrame(int counter) {
		if (counter <= BowlingApplicationMain.gameSetting.get("framePerGame"))
			return true;
		return false;
	}

	public Boolean isValidShot(int counter) {
		if (counter <= BowlingApplicationMain.gameSetting.get("shotPerFrame"))
			return true;
		return false;
	}
}
