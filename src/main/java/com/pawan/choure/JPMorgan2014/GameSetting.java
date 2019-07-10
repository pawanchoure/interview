package com.pawan.choure.JPMorgan2014;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import com.sun.org.apache.regexp.internal.recompile;

public class GameSetting {
	
	public GameSetting(){
	}
/**
 * Reading the Game Setting from a Property Files so that we can change the Frame size and shots
 * per game in future
 * @return
 * @throws IOException
 */
	public HashMap<String, Integer> getPropValues() throws IOException {
		HashMap<String, Integer> propFile = new HashMap<>();
		Properties prop = new Properties();
		String propFileName = "GameSetting.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);
		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}
		if (prop.getProperty("framePerGame") != null
				&& prop.getProperty("shotPerFrame") != null) {
			propFile.put("framePerGame",Integer.valueOf(prop.getProperty("framePerGame")));
			propFile.put("shotPerFrame",Integer.valueOf(prop.getProperty("shotPerFrame")));
			return propFile;
		}
		return null;
	}
	
	/**
	 * Loading the Input from txt File
	 * @param fileName
	 * @return
	 */
	public String loadInput(String fileName){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		try (BufferedReader bufferRead = new BufferedReader(new FileReader(file))) {
			return bufferRead.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
