package at.fhtw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {

        String requestUrl = "http://api.openweathermap.org/data/2.5/weather?&APPID=f46c41d3ff9363cb6319493cbbf3e57b&q=Vienna";
        // URL: a pointer to a "resource" on the World Wide Web
        URL url = new URL(requestUrl);
        // HttpURLConnection: to make a single request but the underlying network connection to the HTTP server
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET"); // GET returns data from the server.
        conn.connect();

        int responseCode = conn.getResponseCode(); //Gets the status code from an HTTP response message

        if (responseCode == HttpURLConnection.HTTP_OK){
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
        } else {
            System.out.println("Something is wrong with connection!");
        }
    }
}