module at.fhtw.openweathermapfxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens at.fhtw.openweathermapfxml to javafx.fxml;
    exports at.fhtw.openweathermapfxml;
}