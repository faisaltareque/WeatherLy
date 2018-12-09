package sample.WeatherRequest;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



/*This Class has Only one method
  getMyCoordinate();
  This method calls getMyIp() method of IPRequest Class and receives user IP
  Then Uses this IP to Build String for HTTP request to get Coordinate
  Receives JSON Response Through API Call
  Then it parses Latitude and Longitude form the JSON response Using JSONObject Class
  Finally it returns An Object of Coordinate Class
 */

public class CoordinateRequest {

    public static Coordinate getMyCoordinate() throws Exception {


        //Building String For HTTP Request
        String mS1="https://ipapi.co/";
        String mS2=IPRequest.getMyIP();        //Receiving IP Address
        String mS3="/json/";
        String url3=mS1+mS2+mS3;



        URL obj = new URL(url3);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);                  //Receiving Response In JSON Format
        }
        in.close();
        JSONObject myResponse = new JSONObject(response.toString());

        System.out.println("Your Latitude is: "+myResponse.getString("latitude")+" Your Longitude is: "+myResponse.getString("longitude"));
        Coordinate coordinate=new Coordinate(-1.0,-1.0);
        try{
            coordinate.lat=Double.parseDouble(myResponse.getString("latitude"));        //Parsing Latitude from response
        }
        catch (Exception e){
            System.out.println("\n!!! Coordinate Not Found !!!");
        }
        try{
            coordinate.lon=Double.parseDouble(myResponse.getString("longitude"));       //Parsing Longitude from response
        }
        catch (Exception e){
            System.out.println("\n!!! Coordinate Not Found !!!");
        }
        return  coordinate;         //Returning Object of Coordinate Class Which Contains Longitude and Latitude
    }
}
