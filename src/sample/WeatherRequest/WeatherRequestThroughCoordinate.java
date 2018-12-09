package sample.WeatherRequest;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static sample.Main.coordinate;

public class WeatherRequestThroughCoordinate {

    //this method is to get weather info from Coordinate
    //then uses this Coordinate along with API Key to build url String
    //then uses this url string for HTTP Request
    //Receives JSON Response
    //Uses this JSON response String to call reponseToObject()  method of WeatherParse Class
    //Gets an OpenWeatherMap Objects and returns it



    public static OpenWeatherMap call_me() throws Exception {
        String s1="http://api.openweathermap.org/data/2.5/weather?lat=";
        String s2="&lon=";
        String s3="&units=metric&type=accurate&APPID=c70491745cb029982d0fc23a4829bba4";

        String url=s1+coordinate.lat+s2+coordinate.lon+s3;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        OpenWeatherMap weatherobject=WeatherParser.responseToObject(response.toString());
        return weatherobject;
    }
}
