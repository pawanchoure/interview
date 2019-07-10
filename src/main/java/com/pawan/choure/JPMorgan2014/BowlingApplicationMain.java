package com.pawan.choure.JPMorgan2014;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class BowlingApplicationMain {
	public static HashMap<String, Integer> gameSetting;
	public static String input;
	/**
	 * Loading the Game Setting from Property File & Reading the Input File
	 */
	static {
		try {
			GameSetting gameSettingObject = new GameSetting();
			gameSetting = gameSettingObject.getPropValues();
			input = gameSettingObject.loadInput("input.txt");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bowling Application Main Class
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter your FirstName :");
		String firstName = sc.nextLine();
		System.out.println("Please Enter your LastName :");
		String lastName = sc.nextLine();
		User gameUser=new User(firstName, lastName);
		//User gameUser=new User("a", "b");
		gameUser.playGame(input);
		sc.close();

	}

}
