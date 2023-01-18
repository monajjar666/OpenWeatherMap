package at.fhtw;

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
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

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
            double windSpeed = data.getJSONObject("wind").getDouble("speed");

            //format the celsius with many decimals to a number with only 2 decimal places
            DecimalFormat df = new DecimalFormat("#.00");
            // Get the description

            System.out.println("Current weather in " + city + firstWeatherEntry);
            System.out.println("Current temperature in " + city + ": "+ df.format(temperature) + "°C");
            System.out.println("Current humidity in " + city + ": "+ humidity + "%");
            System.out.println("Current wind speed in " + city + ": "+ windSpeed + "km/h");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
