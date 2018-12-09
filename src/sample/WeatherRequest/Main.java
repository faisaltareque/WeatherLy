package sample.WeatherRequest;


/*
This Class Written to store Main Information of weather From API Call
Has five double variable
temp(Temperature)
temp-min(Minimum Temperature)
temp-max(Maximum Temperature)
humidity(Humidity Level)
pressure(Atmospheric Pressure)
 */


public class Main {
    public double temp;
    public double temp_min;
    public double pressure;
    public double humidity;
    public double temp_max;
    //double sea_level;
    //double grnd_level;

    public Main(double temp, double temp_min,double humidity, double pressure,  double temp_max) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }
}
