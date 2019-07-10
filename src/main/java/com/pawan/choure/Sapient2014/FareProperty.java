package com.pawan.choure.Sapient2014;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class FareProperty {
	
	public HashMap<String, String> getPropValues() throws IOException {
	HashMap<String, String> propFile = new HashMap<>();
    Properties prop = new Properties();
    String propFileName = "SmartCard.properties";

    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
    prop.load(inputStream);
    if (inputStream == null) {
        throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
    }
    // get the property value and print it out
    propFile.put("weekendFare", prop.getProperty("weekendFare"));
    propFile.put("weekDayFare", prop.getProperty("weekDayFare"));
    return propFile;
	}

}
