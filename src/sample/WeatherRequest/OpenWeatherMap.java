package sample.WeatherRequest;


/*
This Call is written to store all weather related information From API call
It has one coordinate of Coordinate Class
One weather of Weather Class
One String base(Place)
One main of Main Class
One wind of Wind Class
One clouds of Clouds Class
One sys of SYS Class
One int ID(City ID)
One String Name(City Name)
 */


public class OpenWeatherMap {
    public Coordinate coordinate;
    public Weather weather;
    public String base;
    public Main main;
    public Wind wind;
    public Clouds clouds;
    public SYS sys;
    public int dt;
    public int id;
    public String name;
    public String cod;

    public OpenWeatherMap(Coordinate coordinate, Weather weather, String base, Main main, Wind wind,
                          Clouds clouds, SYS sys, int dt, int id, String name, String cod) {
        this.coordinate = coordinate;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.sys = sys;
        this.dt = dt;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }
}
