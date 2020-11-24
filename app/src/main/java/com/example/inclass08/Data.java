package com.example.inclass08;
/*
    FileName: HW04
    Assignment: 08
    Names: Pramukh Nagendra
            Nikhil Surya Peteti (Group 1C)
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Data {
    static public final HashMap<String, ArrayList<String>> cities = new HashMap<String, ArrayList<String>>(){{
        put("US", new ArrayList<String>(Arrays.asList("Charlotte", "Chicago", "New York", "Miami", "San Francisco", "Baltimore", "Houston")));
        put("UK", new ArrayList<String>(Arrays.asList("London", "Bristol", "Cambridge", "Liverpool")));
        put("AE", new ArrayList<String>(Arrays.asList("Abu Dhabi", "Dubai", "Sharjah")));
        put("JP", new ArrayList<String>(Arrays.asList("Tokyo", "Kyoto", "Hashimoto", "Osaka")));
    }};
}
