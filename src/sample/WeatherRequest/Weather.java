package sample.WeatherRequest;


/*
This Class written to store weather information from API Call
int id(Weather type ID)
String main(Weather Type)
String description(Weather description)
String icon(Particular icon id for particular type of weather)
 */

public class Weather{
    public int id;
    public String main;
    public String description;
    public String icon;

    public Weather(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }
}