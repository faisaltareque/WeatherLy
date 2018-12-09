package sample.CityID;



/*
This Class is written to Listing City Name and Particular City ID
Two Variable
String Name(City Name)
int id(City ID)
 */

public class City {
    public String name;
    public int id;

    public City(String name, int id) {
        this.name = name.toUpperCase();
        this.id = id;
    }
}
