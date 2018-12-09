package sample.WeatherRequest;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


/*
This Class is written to Send API Call through HTTP Request Using City ID
 */

public class WeatherRequest {

    //this method takes City id as int
    //then uses this CityID along with API Key to build url String
    //then uses this url string for HTTP Request
    //Receives JSON Response
    //Uses this JSON response String to call reponseToObject()  method of WeatherParse Class
    //Gets an OpenWeatherMap Objects and returns it
     public static OpenWeatherMap call_me(int id) throws Exception {
        String s1="http://api.openweathermap.org/data/2.5/weather?id=";
        String s2="&units=metric&type=accurate&APPID=c70491745cb029982d0fc23a4829bba4";
        String url=s1+String.valueOf(id)+s2;

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
        return WeatherParser.responseToObject(response.toString());
    }
}