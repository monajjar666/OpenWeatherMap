package at.fhtw.openweathermapfxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class OpenWeatherFXMLController {
    @FXML
    private Text showCity;
    @FXML
    private Text showTemperature;
    @FXML
    private Text showHumidity;
    @FXML
    private Text showWeather;
    @FXML
    private Text showWindSpeed;
    @FXML
    private ComboBox selectCities;
    @FXML
    private ImageView weatherIcon;

    @FXML
    private Text weatherAlert;

    @FXML
    protected void onClickWeatherAction(ActionEvent event){
        try{
            String apiKey = "867af03f01d0abe6ad281a43f856f541";
            String city = selectCities.getValue().toString();
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());
            JSONArray weather = data.getJSONArray("weather");
            JSONObject firstWeatherEntry = new JSONObject(weather.get(0).toString());

            //Get icon from the Weather Api
            String icon = firstWeatherEntry.getString("icon");
            String imgUrlString = "http://openweathermap.org/img/wn/"+icon+"@2x.png";
            weatherIcon.setImage(new Image(imgUrlString));

            //Get weather description from the Weather Api
            String weatherDescription = firstWeatherEntry.getString("description");
            double weatherID = firstWeatherEntry.getDouble("id");


            // Get the current temperature in Kelvin
            double temperature = data.getJSONObject("main").getDouble("temp");
            double humidity = data.getJSONObject("main").getDouble("humidity");
            double windSpeed = data.getJSONObject("wind").getDouble("speed");

            //format the celsius with many decimals to a number with only 2 decimal places
            DecimalFormat df = new DecimalFormat("0.00");

            showCity.setFont(Font.font("Arial Black",25));
            showCity.setText(city);

            showWeather.setFont(Font.font ("Arial Black"));
            showWeather.setText ("Current: " + weatherDescription + "\n");
            showTemperature.setText("Current temperature: " + df.format(temperature) + "°C\n");
            showHumidity.setText ("Current Humidity: " + humidity + "%\n");
            showWindSpeed.setText("Current Wind Speed: " + windSpeed + "km/h\n");

            weatherAlert.setFont(Font.font("Arial Black", 18));
            if(weatherID>=200 && weatherID<=299) { weatherAlert.setText("Thunderstorm Warning");};

        } catch (Exception e) {
            showCity.setText ("Error showing the weather.\n Try later!\n");
            showWeather.setText ("\n");
            showTemperature.setText("\n");
            showHumidity.setText ("\n");
            showWindSpeed.setText("\n");
        }
    }
}