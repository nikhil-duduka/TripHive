package com.example.TripHive.repository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;

public class JsonParserExample {
    public static void main(String[] args) throws Exception {
        JSONTokener tokener = new JSONTokener(new FileReader("flight_data.json"));
        JSONObject jsonData = new JSONObject(tokener);
        JSONArray dataArray = jsonData.getJSONArray("data");

        // Display the base fares of the first five flights
        for (int i = 0; i < 5 && i < dataArray.length(); i++) {
            JSONObject flight = dataArray.getJSONObject(i);
            JSONObject priceDropdown = flight.getJSONObject("price_dropdown");
            double baseFare = priceDropdown.getDouble("base_fare");
            System.out.println("Flight " + (i + 1) + " - Base Fare: " + baseFare);
        }
    }
}
