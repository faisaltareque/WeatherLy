package sample.WeatherRequest;



/*This Class is written to Store SYS information of weather data from API call

 */


public class SYS {
    public String type;
    public int id;
    public String message;
    public String country;
    public int sunrise;
    public  int sunset;

    public SYS(String type, int id, String message, String country, int sunrise, int sunset) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
    public SYS(int id, String message, String country, int sunrise, int sunset) {
        this.type = "Null Type";
        this.id = id;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
}
