package at.fhtw;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Main {
    public static void main(String[] args) {
        try {

            @FXML
            private MenuButton CityMenu;

            String apiKey = "867af03f01d0abe6ad281a43f856f541";

            String city = "Vienna";
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // Get the current temperature in Kelvin
            double temperature = data.getJSONObject("main").getDouble("temp");
            // Convert from Kelvin to Celsius
            double celsius = temperature - 273;
            //format the celsius with many decimals to a number with only 2 decimal places
            DecimalFormat df = new DecimalFormat("#.00");

            System.out.println("Current temperature in Vienna: " + df.format(celsius) + "°C");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
