package com.example.TripHive.repository;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.JSONObject;

public class FlightScraper {



    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter origin airport code: ");
            String fromLoc = reader.readLine();

            System.out.print("Enter destination airport code: ");
            String toLoc = reader.readLine();

            System.out.print("Enter boarding date (DD/MM/YYYY): ");
            String boardingDate = reader.readLine();

            // Format the boarding date to match the API's date format
            String boardingDateFormatted = boardingDate.replace("/", "%2F");

            // Construct the API URL with the dynamic inputs
            String apiUrl = "https://api.tequila.kiwi.com/v2/search?fly_from=" + fromLoc + "&fly_to=" + toLoc + "&date_from=" + boardingDateFormatted + "&date_to=" + boardingDateFormatted + "&max_stopovers=1&vehicle_type=aircraft&limit=20&curr=INR&sort=price";

            // Set the API key in the headers
            String apiKey = "1wU6jIQnCeBsJ12o94xH5xr0IpQ2YU_k";

            // Send a GET request to the API endpoint
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("apikey", apiKey);

            // Check if the request was successful (status code 200)
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String responseLine;
                StringBuilder responseContent = new StringBuilder();
                while ((responseLine = responseReader.readLine()) != null) {
                    responseContent.append(responseLine);
                }
                responseReader.close();

                // Extract the JSON data from the response
                JSONObject data = new JSONObject(responseContent.toString());

                // Save the data to a JSON file
                try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("flight_data.json"))) {
                    fileWriter.write(data.toString(4));
                }

                System.out.println("Flight data saved to 'flight_data.json' file.");
            } else {
                System.out.println("Error occurred while fetching flight data. Response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
