package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.CityID.*;
import sample.WeatherRequest.Coordinate;
import sample.WeatherRequest.CoordinateRequest;

import java.time.LocalDateTime;
import java.util.LinkedList;


public class Main extends Application {
    public static Coordinate coordinate;
    public static LinkedList<City> cityList=new LinkedList<>();

    //Time Variables
    public static int hour;
    public static int minute;
    public static int second;
    public static String weekday;
    public static int month;
    public static int dateint;
    public static int year;
    public static String Month;
    public static LocalDateTime date;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("weatherly.fxml"));
        primaryStage.setTitle("WeatherLy");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.getIcons().add(new Image("/sample/Image/AppLogo/MainAppIcon.png"));
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.show();
    }

    public static void main(String[] args) {
        date=LocalDateTime.now();
        hour=date.getHour();
        minute=date.getMinute();
        second=date.getSecond();
        weekday=date.getDayOfWeek().toString();
        dateint=date.getDayOfMonth();
        year=date.getYear();
        month=date.getMonthValue();
        switch (month){
            case 1:{
                Month="January";
                break;
            }
            case 2:{
                Month="February";
                break;
            }
            case 3:{
                Month="March";
                break;
            }
            case 4:{
                Month="April";
                break;
            }case 5:{
                Month="May";
                break;
            }case 6:{
                Month="June";
                break;
            }case 7:{
                Month="July";
                break;
            }
            case 8:{
                Month="August";
                break;
            }
            case 9:{
                Month="September";
                break;
            }
            case 10:{
                Month="October";
                break;
            }
            case 11:{
                Month="November";
                break;
            }
            case 12:{
                Month="December";
                break;
            }
        }

        CityRecords.loadCityList();
        //CityRecords2.loadCityList();
        //CityRecords3.loadCityList();
        try{
            coordinate=CoordinateRequest.getMyCoordinate();         //Loading User Location Coordinate
        }
        catch (Exception e){
            System.out.println("Connection Error!!!");
            System.out.println(e);
        }
        launch(args);
    }
}
