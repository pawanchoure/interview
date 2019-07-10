package com.pawan.choure.BNYMellon2019;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CustomDateFormatter {
    private static final HashMap<String, String> monthMap;

    static {
        monthMap = new HashMap<>();
        monthMap.put("Jan", "01");
        monthMap.put("Feb", "02");
        monthMap.put("Mar", "03");
        monthMap.put("Apr", "04");
        monthMap.put("May", "05");
        monthMap.put("Jun", "06");
        monthMap.put("Jul", "07");
        monthMap.put("Aug", "08");
        monthMap.put("Sep", "09");
        monthMap.put("Oct", "10");
        monthMap.put("Nov", "11");
        monthMap.put("Dec", "12");
    }

    public static void main(String[] args) {

        List<String> dates = new ArrayList<String>(Arrays.asList("20th Oct 2052", "6th Jun 1933"));
        try {
            reformatDate(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static List<String> reformatDate(List<String> dates) throws ParseException {
        List<String> formattedDate = new ArrayList<>();
        for (String date : dates) {
            String[] dataElements = date.split(" ");
            //Check the Size it should be 3
            if (dataElements.length == 3) {
                //Extract the Date part
                String day = dataElements[0].replaceAll("\\D+", "");
                if (day.length() == 1) {
                    day = "0" + day;
                }

                //Extract Month
                String month = monthMap.get(dataElements[1]);

                //Extract year
                String year = dataElements[2];
                String extractedDate = year + "-" + month + "-" + day;
                System.out.println(extractedDate);
                formattedDate.add(extractedDate);
            }
        }
        return formattedDate;
    }
}
