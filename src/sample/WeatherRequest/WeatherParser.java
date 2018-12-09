package sample.WeatherRequest;

import org.json.JSONObject;

/*
This Class written to parse weather data from weather JSON Response

 */

public class WeatherParser {

    //This method will be Useful to print weather information into console in further development

    public static void weatherPrinter(String response)throws Exception{
        OpenWeatherMap weatherObject=responseToObject(response);
        System.out.println("\nCoordinate: ");
        System.out.println("Longitude: "+weatherObject.coordinate.lon);
        System.out.println("latitude: "+weatherObject.coordinate.lat);

        System.out.println("\nWeather: ");
        System.out.println("Icon: "+weatherObject.weather.icon);
        System.out.println("Description: "+weatherObject.weather.description);
        System.out.println("Main: "+weatherObject.weather.main);
        System.out.println("ID: "+weatherObject.weather.id);

        System.out.println("\nMain: ");
        System.out.println("Temperature: "+weatherObject.main.temp);
        System.out.println("Humidity: "+weatherObject.main.humidity);
        System.out.println("Pressure: "+weatherObject.main.pressure);
        System.out.println("Minimum Temperature: "+weatherObject.main.temp_min);
        System.out.println("Maximum Temperature: "+weatherObject.main.temp_max);

        System.out.println("\nBase: "+weatherObject.base);

        System.out.println("\nWind: ");
        System.out.println("Deg: "+weatherObject.wind.deg);
        System.out.println("Speed: "+weatherObject.wind.speed);

        System.out.println("\nClouds: ");
        System.out.println("All: "+weatherObject.clouds.all);

        System.out.println("\nDT: "+weatherObject.dt);

        System.out.println("\nSYS: ");
        System.out.println("Country: "+weatherObject.sys.country);
        System.out.println("Sunrise: "+weatherObject.sys.sunrise);
        System.out.println("Sunset: "+weatherObject.sys.sunset);
        System.out.println("ID: "+weatherObject.sys.id);
        System.out.println("Type: "+weatherObject.sys.type);
        System.out.println("Message: "+weatherObject.sys.message);

        System.out.println("\nID: "+weatherObject.id);

        System.out.println("\nName: "+weatherObject.name);
        System.out.println("\nCOD: "+weatherObject.cod);
    }

    //This method is going to Take a single String in JSON Format from API call
    //Then parse Latitude and Longitude information from that JSON response
    //Create a Coordinate Class object with that information and return it

    public static Coordinate coordinateResponseToObject(String sCoordinate)throws Exception{
        JSONObject  jCoordinate= new JSONObject(sCoordinate);
        Double longitude=00000.00;
        Double latitude=00000.00;
        try{
            longitude=Double.parseDouble(jCoordinate.getString("lon"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"lon\" not found in response!!!");
        }
        try{
            latitude=Double.parseDouble(jCoordinate.getString("lat"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"lat\" not found in response!!!");
        }
        return new Coordinate(longitude,latitude);
    }

    //This method is going to Take a single String in JSON Format from API call
    //Then parse Weather information from that JSON response
    //Create a Weather Class object with that information and return it


    public static Weather weatherResponseToObject(String sWeather)throws Exception{
        JSONObject jWeather=new JSONObject(sWeather);
        int id=-1;
        String main=null;
        String description=null;
        String icon=null;
        try{
            id=Integer.parseInt(jWeather.getString("id"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"id\" not found in response!!!");
        }
        try{
            main=jWeather.getString("main");
        }
        catch (Exception e){
            System.out.println("\n!!!\"main\" not found in response!!!");
        }
        try{
            description=jWeather.getString("description");
        }
        catch (Exception e){
            System.out.println("\n!!!\"description\" not found in response!!!");
        }
        try{
            icon=jWeather.getString("icon");
        }
        catch (Exception e){
            System.out.println("\n!!!\"icon\" not found in response!!!");
        }
        return new Weather(id,main,description,icon);
    }

    //This method is going to Take a single String in JSON Format from API call
    //Then parse Main information from that JSON response
    //Create a Main Class object with that information and return it

    public static Main mainResponseToObject(String sMain)throws Exception{
        JSONObject  jMain= new JSONObject(sMain);
        double temp=-273.00;
        double temp_min=-273.00;
        double temp_max=-273.00;
        double humidity=-273.00;
        double pressure=-273.00;
        try{
            temp=Double.parseDouble(jMain.getString("temp"));
        }
        catch(Exception e){
            System.out.println("\n!!!\"temp\" not found in response!!!");
        }
        try{
            temp_min=Double.parseDouble(jMain.getString("temp_min"));
        }
        catch(Exception e){
            System.out.println("\n!!!\"temp_min\" not found in response!!!");
        }
        try{
            temp_max=Double.parseDouble(jMain.getString("temp_max"));
        }
        catch(Exception e){
            System.out.println("\n!!!\"temp_max\" not found in response!!!");
        }
        try{
            humidity=Double.parseDouble(jMain.getString("humidity"));
        }
        catch(Exception e){
            System.out.println("\n!!!\"humidity\" not found in response!!!");
        }
        try{
            pressure=Double.parseDouble(jMain.getString("pressure"));
        }
        catch(Exception e){
            System.out.println("\n!!!\"pressure\" not found in response!!!");
        }

        return new Main(temp,temp_min,humidity,pressure,temp_max);
    }


    //This method is going to Take a single String in JSON Format from API call
    //Then parse Wind information from that JSON response
    //Create a Wind Class object with that information and return it


    public  static Wind windResponseToObject(String sWind)throws Exception{
        JSONObject jWind=new JSONObject(sWind);
        double speed=-1;
        double deg=-1;
        try{
            speed=Double.parseDouble(jWind.getString("speed"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"speed\" not found in response!!!");
        }
        try{
            deg=Double.parseDouble(jWind.getString("deg"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"deg\" not found in response!!!");
        }
        return new Wind(speed,deg);
    }


    //This method is going to Take a single String in JSON Format from API call
    //Then parse Cloud information from that JSON response
    //Create a Cloud Class object with that information and return it



    public  static Clouds cloudsResponseToObject(String sClouds)throws Exception{
        JSONObject jClouds=new JSONObject(sClouds);
        double all=-1;
        try{
            all=Double.parseDouble(jClouds.getString("all"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"all\" not found in response!!!");
        }
        return new Clouds(all);
    }



    //This method is going to Take a single String in JSON Format from API call
    //Then parse SYS information from that JSON response
    //Create a SYS Class object with that information and return it

    public static SYS sysResponseToObject(String sSYS)throws Exception{
        JSONObject jSYS=new JSONObject(sSYS);
        String type=null;
        int id=-1;
        String message=null;
        String country=null;
        int sunrise=-1;
        int sunset=-1;

        try{
            type=jSYS.getString("type");
        }
        catch (Exception e){
            System.out.println("\n!!!\"type\" not found in response!!!");
        }
        try{
            id=Integer.parseInt(jSYS.getString("id"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"id\" not found in response!!!");
        }
        try{
            message=jSYS.getString("message");
        }
        catch (Exception e){
            System.out.println("\n!!!\"message\" not found in response!!!");
        }
        try{
            country=jSYS.getString("country");
        }
        catch (Exception e){
            System.out.println("\n!!!\"country\" not found in response!!!");
        }
        try{
            sunrise=Integer.parseInt(jSYS.getString("sunrise"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"sunrise\" not found in response!!!");
        }
        try{
            sunset=Integer.parseInt(jSYS.getString("sunset"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"sunset\" not found in response!!!");
        }
        return new SYS(type,id,message,country,sunrise,sunset);
    }

    /*
    This method takes String JSON weather response
    Parse the kew weather values
    Then uses this value to Create other Weather objects
    Then returns an Object of OpneWeatherMap Class
     */


    public static OpenWeatherMap responseToObject(String response)throws Exception{
        JSONObject myResponse = new JSONObject(response);
        String sCoordinate=myResponse.getString("coord");
        Coordinate coordinate=coordinateResponseToObject(sCoordinate);


        StringBuilder sbWeather=new StringBuilder(myResponse.getString("weather"));
        sbWeather.deleteCharAt(0);
        sbWeather.deleteCharAt(sbWeather.length()-1);
        String sWeather=sbWeather.toString();
        Weather weather=weatherResponseToObject(sWeather);

        String sMain= myResponse.getString("main");
        Main main=mainResponseToObject(sMain);


        String sBase=null;
        try {
            sBase = myResponse.getString("base");
        }
        catch (Exception e){
            System.out.println("\n!!!\"base\" not found in response!!!");
        }

        String sWind=myResponse.getString("wind");
        Wind wind=windResponseToObject(sWind);

        String sClouds=myResponse.getString("clouds");
        Clouds clouds=cloudsResponseToObject(sClouds);

        int dt=-1;
        try{
            dt=Integer.parseInt(myResponse.getString("dt"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"dt\" not found in response!!!");
        }

        String sSYS=myResponse.getString("sys");
        SYS sys=sysResponseToObject(sSYS);

        int id=-1;
        try{
            id=Integer.parseInt(myResponse.getString("id"));
        }
        catch (Exception e){
            System.out.println("\n!!!\"id\" not found in response!!!");
        }

        String sName=null;
        try {
            sName = myResponse.getString("name");
        }
        catch (Exception e){
            System.out.println("\n!!!\"name\" not found in response!!!");
        }
        String sCOD=null;
        try {
            sCOD = myResponse.getString("cod");
        }
        catch (Exception e){
            System.out.println("\n!!!\"cod\" not found in response!!!");
        }
        return new OpenWeatherMap(coordinate,weather,sBase,main,wind,clouds,sys,dt,id,sName,sCOD);
    }
}
