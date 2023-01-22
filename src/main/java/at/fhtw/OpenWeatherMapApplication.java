package at.fhtw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.MenuButton;

import java.io.IOException;

public class OpenWeatherMapApplication extends Application{
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(OpenWeatherMapApplication.class.getResource("open_weather_map.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 320, 240);
        stage.setTitle("OpenWeather");
        stage.setScene(scene);
        stage.show();
    }
}
