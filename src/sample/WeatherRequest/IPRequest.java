package sample.WeatherRequest;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*This Class has One Method.
getMyIP() sends A HTTP request for User IP address
then it receives a JSON response through API Call
After Parsing the IP address it returns the IP address as String
 */

public class IPRequest {
        public static String getMyIP() throws Exception {
        String url = "http://httpbin.org/ip";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);                  //Receiving Response in JSON Format
        }
        in.close();
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("Your IP IS: "+myResponse.getString("origin"));      //Pasting IP Address from JSON Response
        return myResponse.getString("origin");      //Returning the IP address of User as String
    }
}