package at.fhtw;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        try {

            String apiKey = "867af03f01d0abe6ad281a43f856f541";

            String city = "Vienna";
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            System.out.println();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());
            JSONArray weather = data.getJSONArray("weather");
            JSONObject firstWeatherEntry = new JSONObject(weather.get(0).toString());

            // Get the current temperature in Kelvin
            double temperature = data.getJSONObject("main").getDouble("temp");
            double humidity = data.getJSONObject("main").getDouble("humidity");
            // Convert from Kelvin to Celsius
            double celsius = temperature - 273;
            //format the celsius with many decimals to a number with only 2 decimal places
            DecimalFormat df = new DecimalFormat("#.00");
            // Get the description



            System.out.println("Current temperature in Vienna: " + df.format(celsius) + "°C");
            System.out.println("Current weather in Vienna: " + firstWeatherEntry);
            System.out.println("Humidity: " + humidity);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
