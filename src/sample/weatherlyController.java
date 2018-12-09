package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.CityID.CityRecords;
import sample.WeatherRequest.OpenWeatherMap;
import sample.WeatherRequest.WeatherRequest;
import sample.WeatherRequest.WeatherRequestThroughCoordinate;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class weatherlyController implements Initializable {


    //For Time Control
    public Pane PaneTime;

    public Label LabelHour;
    public Label LabelMinute;
    public Label LabelSecond;
    public Label LabelAMPM;
    public Label LabelWeekDay;
    public Label LabelDate;
    public Label LabelMonth;
    public Label LabelYear;
    public Label LabelColon1;
    public Label LabelColon2;

    public ImageView ImageViewClock;
    public ImageView ImageViewCalendar;

    public JFXButton TimeEanbleDisable;


    public int mySecond=Main.second;
    public int myMinute=Main.minute;
    public int myHour=Main.hour;
    public String myWeekday=Main.weekday;
    public String myMonth=Main.Month;
    public int myYear=Main.year;
    public int myDate=Main.dateint;

    boolean timeanimate=true;
    boolean colonanimate=true;

    boolean timePaneToUp=false;
    boolean timePaneToDown=false;



    //Weather Background Transition
    public boolean toWinter=false;
    public boolean toSummer=false;
    public boolean toRainy=false;
    public boolean night=false;


    //Refresh Control
    public boolean forRefresh=false;
    public int refreshID;




    //Temperature Background Control
    public ImageView ImageLayerCelsiusBackground;
    public ImageView ImageLayerFahrenheitBackground;

    //Main Background Control
    public ImageView ImageLayerWinter;
    public ImageView ImageLayerSummer;
    public ImageView ImageLayerRain;
    public ImageView ImageLayerNight;

    public ImageView ImageLayerCloud2;
    public ImageView ImageLayerCloud3;

    //Sun control
    public ImageView ImageLayerSun;
    boolean sun=true;

    //Main Icon Control
    public ImageView ImageLayerMainIcon;
    boolean icon=true;


    //Weather Icon Control
    public ImageView Icon01d;
    public ImageView Icon01n;
    public ImageView Icon02d;
    public ImageView Icon02n;
    public ImageView Icon03dn;
    public ImageView Icon04dn;
    public ImageView Icon09dn;
    public ImageView Icon10d;
    public ImageView Icon10n;
    public ImageView Icon11d;
    public ImageView Icon11n;




    //Used Labels
    public Label LabelCityName;
    public Label LabelTemperature;
    public Label LabelMinTemperature;
    public Label LabelMaxTemperature;
    public Label LabelErrorComment;

    public Label LabelHumidity;
    public Label LabelWindSpeed;
    public Label LabelPressure;
    public Label LabelHumidityStatic;
    public Label LabelWindSpeedStatic;
    public Label LabelPressureStatic;

    public Label LabelWeatherDescription;
    public Label LabelCloudLevel;


    public ProgressBar ProgressBarCloudLevel;



    //Used Buttons
    public JFXButton ButtonSearchLocation;
    public JFXButton ButtonFahrenheit;
    public JFXButton ButtonCelsius;
    public JFXButton ButtonRefresh;
    public JFXButton ButtonGPS;



    //Used TextField
    public JFXTextField TextFieldSearchLocation;

    //Temperature Unit Convert
    //This Method converts the unit of temperature to Fahrenheit if the temperature already in Celsius format
    @FXML
    void fahrenheitButtonAction(ActionEvent event){
        ImageLayerCelsiusBackground.setOpacity(0.0);
        ImageLayerFahrenheitBackground.setOpacity(1.0);
        String temp1=LabelTemperature.getText();
        String temp2=temp1.substring(0,temp1.indexOf('\''));
        double celsius=Double.parseDouble(temp2);
        if(temp1.charAt(temp1.length()-1)=='C'){
            double fah=celsius*(1.8)+32;
            String temp3=String.valueOf(fah);
            if(temp3.length()>3){
                temp3=temp3.substring(0,4);
            }
            LabelTemperature.setText(temp3+"\' F");
            temp3=String.valueOf(fah-3);
            if(temp3.length()>3){
                temp3=temp3.substring(0,4);
            }
            LabelMinTemperature.setText(temp3+"\' F");
            temp3=String.valueOf(fah+2.4);
            if(temp3.length()>3){
                temp3=temp3.substring(0,4);
            }
            LabelMaxTemperature.setText(temp3+"\' F");
        }
    }

    //Temperature Unit Convert
    //This Method converts the unit of temperature to Celsius if the temperature already in Fahrenheit format
    @FXML
    void celsiusButtonAction(ActionEvent event){
        ImageLayerCelsiusBackground.setOpacity(1.0);
        ImageLayerFahrenheitBackground.setOpacity(0.0);
        String temp1=LabelTemperature.getText();
        String temp2=temp1.substring(0,temp1.indexOf('\''));
        double fahrenheit=Double.parseDouble(temp2);

        if(temp1.charAt(temp1.length()-1)=='F'){
            double cel=(fahrenheit-32)/1.8;
            String temp3=String.valueOf(cel);
            if(temp3.length()>3){
                temp3=temp3.substring(0,4);
            }
            LabelTemperature.setText(temp3+"\' C");
            temp3=String.valueOf(cel-3);
            if(temp3.length()>3){
                temp3=temp3.substring(0,4);
            }
            LabelMinTemperature.setText(temp3+"\' C");
            temp3=String.valueOf(cel+2.4);
            if(temp3.length()>3){
                temp3=temp3.substring(0,4);
            }
            LabelMaxTemperature.setText(temp3+"\' C");
        }
    }


    //Clock Enable Disable
    @FXML
    void timeEnabler(ActionEvent event){
//        if(PaneTime.getOpacity()==0){
//            PaneTime.setOpacity(0.75);
//            return;
//        }
//        if(PaneTime.getOpacity()>0.5){
//            PaneTime.setOpacity(0.0);
//        }
        if(PaneTime.getLayoutY()==0) {
            timePaneToUp=true;
            timePaneToDown=false;
        }
        if(PaneTime.getLayoutY()==-65){
            timePaneToDown=true;
            timePaneToUp=false;
        }
    }

    //This method will be executed when the user want to know the weather of Particular City

    @FXML
    void searchLocationButtonAction(ActionEvent event){

        try {
            String temp=TextFieldSearchLocation.getText().toUpperCase();
            int id=CityRecords.getCityId(temp);
            if(id==-1){
                LabelErrorComment.setOpacity(1.0);
                LabelErrorComment.setText("!!Not Found!!");
            }else{
                OpenWeatherMap weatherobject = WeatherRequest.call_me(id);
                forRefresh=true;
                ButtonRefresh.setButtonType(JFXButton.ButtonType.RAISED);
                refreshID=weatherobject.id;
//                weatherobject.main.temp=14;
//                weatherobject.weather.icon="11d";
//                weatherobject.weather.icon="11n";

                ImageLayerCelsiusBackground.setOpacity(1.0);
                ImageLayerFahrenheitBackground.setOpacity(0.0);


                if(weatherobject.weather.icon.equals("50d")){
                    weatherobject.weather.icon="02d";
                }
                if(weatherobject.weather.icon.equals("50n")){
                    weatherobject.weather.icon="02n";
                }

                //BackGround Change

                if(weatherobject.weather.icon.contains("01")||weatherobject.weather.icon.contains("02")||weatherobject.weather.icon.contains("03")||weatherobject.weather.icon.contains("04")){
                    toWinter=false;
                    toSummer=true;
                    toRainy=false;
                }
                if(weatherobject.weather.icon.contains("09")||weatherobject.weather.icon.contains("10")||weatherobject.weather.icon.contains("11")){
                    toWinter=false;
                    toSummer=false;
                    toRainy=true;
                }
                if(weatherobject.main.temp<15){
                    toWinter=true;
                    toSummer=false;
                    toRainy=false;
                }
                if(weatherobject.weather.icon.charAt(weatherobject.weather.icon.length()-1)=='n'){
                    night=true;
                    ImageLayerNight.setVisible(true);
                    ImageLayerNight.setOpacity(0.0);
                    ImageLayerSun.setVisible(false);
                }
                else{
                    night=false;
                    ImageLayerNight.setVisible(false);
                    ImageLayerNight.setOpacity(0.0);
                    ImageLayerSun.setVisible(true);
                }


                //Weather Icon Change
                if(weatherobject.weather.icon.equals("01d")){
                    Icon01d.setOpacity(1.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("01n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(1.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("02d")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(1.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("02n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(1.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("03d")||weatherobject.weather.icon.equals("03n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(1.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("04d")||weatherobject.weather.icon.equals("04n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(1.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("09d")||weatherobject.weather.icon.equals("09n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(1.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("10d")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(1.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("10n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(1.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("11d")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(1.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("11n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(1.0);
                }


                //Text Value Change
                LabelCityName.setText(weatherobject.name);
                LabelTemperature.setText(String.valueOf(weatherobject.main.temp)+"\' C");
                LabelMinTemperature.setText(String.valueOf(weatherobject.main.temp-3)+"\' C");
                LabelMaxTemperature.setText(String.valueOf(weatherobject.main.temp+2.4)+"\' C");
                LabelHumidity.setText(String.valueOf(weatherobject.main.humidity)+" %");
                LabelWindSpeed.setText(String.valueOf(weatherobject.wind.speed)+" m/sec");
                LabelWeatherDescription.setText(weatherobject.weather.description.toUpperCase());
                LabelPressure.setText(String.valueOf(weatherobject.main.pressure));
                LabelCloudLevel.setText("Cloud Level : "+String.valueOf(weatherobject.clouds.all));
                ProgressBarCloudLevel.setProgress(weatherobject.clouds.all/100);

                //
                LabelErrorComment.setText("");
                LabelErrorComment.setOpacity(0.0);

            }
        }
        catch (Exception e) {
            LabelErrorComment.setOpacity(1.0);
            LabelErrorComment.setText("!!!ERROR!!!");
        }
    }

    //This method will b executed if the user want to know the weather of their location

    @FXML
    void gpsButtonAction(ActionEvent event) {
        try{
            OpenWeatherMap weatherobject =WeatherRequestThroughCoordinate.call_me();
            forRefresh=true;
            ButtonRefresh.setButtonType(JFXButton.ButtonType.RAISED);
            refreshID=weatherobject.id;
            //weatherobject.main.temp=14;
//            weatherobject.weather.icon="11d";
//            weatherobject.weather.icon="11n";
            ImageLayerCelsiusBackground.setOpacity(1.0);
            ImageLayerFahrenheitBackground.setOpacity(0.0);

            if(weatherobject.weather.icon.equals("50d")){
                weatherobject.weather.icon="02d";
            }
            if(weatherobject.weather.icon.equals("50n")){
                weatherobject.weather.icon="02n";
            }

            //BackGround Change

            if(weatherobject.weather.icon.contains("01")||weatherobject.weather.icon.contains("02")||weatherobject.weather.icon.contains("03")||weatherobject.weather.icon.contains("04")){
                toWinter=false;
                toSummer=true;
                toRainy=false;
            }
            if(weatherobject.weather.icon.contains("09")||weatherobject.weather.icon.contains("10")||weatherobject.weather.icon.contains("11")){
                toWinter=false;
                toSummer=false;
                toRainy=true;
            }
            if(weatherobject.main.temp<15){
                toWinter=true;
                toSummer=false;
                toRainy=false;
            }
            if(weatherobject.weather.icon.charAt(weatherobject.weather.icon.length()-1)=='n'){
                night=true;
                ImageLayerNight.setVisible(true);
                ImageLayerNight.setOpacity(0.0);
                ImageLayerSun.setVisible(false);
            }
            else{
                night=false;
                ImageLayerNight.setVisible(false);
                ImageLayerNight.setOpacity(0.0);
                ImageLayerSun.setVisible(true);
            }


            //Weather Icon Change
            if(weatherobject.weather.icon.equals("01d")){
                Icon01d.setOpacity(1.0);
                Icon01n.setOpacity(0.0);
                Icon02d.setOpacity(0.0);
                Icon02n.setOpacity(0.0);
                Icon03dn.setOpacity(0.0);
                Icon04dn.setOpacity(0.0);
                Icon09dn.setOpacity(0.0);
                Icon10d.setOpacity(0.0);
                Icon10n.setOpacity(0.0);
                Icon11d.setOpacity(0.0);
                Icon11n.setOpacity(0.0);
            }
            if(weatherobject.weather.icon.equals("01n")){
                Icon01d.setOpacity(0.0);
                Icon01n.setOpacity(1.0);
                Icon02d.setOpacity(0.0);
                Icon02n.setOpacity(0.0);
                Icon03dn.setOpacity(0.0);
                Icon04dn.setOpacity(0.0);
                Icon09dn.setOpacity(0.0);
                Icon10d.setOpacity(0.0);
                Icon10n.setOpacity(0.0);
                Icon11d.setOpacity(0.0);
                Icon11n.setOpacity(0.0);
            }
            if(weatherobject.weather.icon.equals("02d")){
                Icon01d.setOpacity(0.0);
                Icon01n.setOpacity(0.0);
                Icon02d.setOpacity(1.0);
                Icon02n.setOpacity(0.0);
                Icon03dn.setOpacity(0.0);
                Icon04dn.setOpacity(0.0);
                Icon09dn.setOpacity(0.0);
                Icon10d.setOpacity(0.0);
                Icon10n.setOpacity(0.0);
                Icon11d.setOpacity(0.0);
                Icon11n.setOpacity(0.0);
            }
            if(weatherobject.weather.icon.equals("02n")){
                Icon01d.setOpacity(0.0);
                Icon01n.setOpacity(0.0);
                Icon02d.setOpacity(0.0);
                Icon02n.setOpacity(1.0);
                Icon03dn.setOpacity(0.0);
                Icon04dn.setOpacity(0.0);
                Icon09dn.setOpacity(0.0);
                Icon10d.setOpacity(0.0);
                Icon10n.setOpacity(0.0);
                Icon11d.setOpacity(0.0);
                Icon11n.setOpacity(0.0);
            }
            if(weatherobject.weather.icon.equals("03d")||weatherobject.weather.icon.equals("03n")){
                Icon01d.setOpacity(0.0);
                Icon01n.setOpacity(0.0);
                Icon02d.setOpacity(0.0);
                Icon02n.setOpacity(0.0);
                Icon03dn.setOpacity(1.0);
                Icon04dn.setOpacity(0.0);
                Icon09dn.setOpacity(0.0);
                Icon10d.setOpacity(0.0);
                Icon10n.setOpacity(0.0);
                Icon11d.setOpacity(0.0);
                Icon11n.setOpacity(0.0);
            }
            if(weatherobject.weather.icon.equals("04d")||weatherobject.weather.icon.equals("04n")){
                Icon01d.setOpacity(0.0);
                Icon01n.setOpacity(0.0);
                Icon02d.setOpacity(0.0);
                Icon02n.setOpacity(0.0);
                Icon03dn.setOpacity(0.0);
                Icon04dn.setOpacity(1.0);
                Icon09dn.setOpacity(0.0);
                Icon10d.setOpacity(0.0);
                Icon10n.setOpacity(0.0);
                Icon11d.setOpacity(0.0);
                Icon11n.setOpacity(0.0);
            }
            if(weatherobject.weather.icon.equals("09d")||weatherobject.weather.icon.equals("09n")){
                Icon01d.setOpacity(0.0);
                Icon01n.setOpacity(0.0);
                Icon02d.setOpacity(0.0);
                Icon02n.setOpacity(0.0);
                Icon03dn.setOpacity(0.0);
                Icon04dn.setOpacity(0.0);
                Icon09dn.setOpacity(1.0);
                Icon10d.setOpacity(0.0);
                Icon10n.setOpacity(0.0);
                Icon11d.setOpacity(0.0);
                Icon11n.setOpacity(0.0);
            }
            if(weatherobject.weather.icon.equals("10d")){
                Icon01d.setOpacity(0.0);
                Icon01n.setOpacity(0.0);
                Icon02d.setOpacity(0.0);
                Icon02n.setOpacity(0.0);
                Icon03dn.setOpacity(0.0);
                Icon04dn.setOpacity(0.0);
                Icon09dn.setOpacity(0.0);
                Icon10d.setOpacity(1.0);
                Icon10n.setOpacity(0.0);
                Icon11d.setOpacity(0.0);
                Icon11n.setOpacity(0.0);
            }
            if(weatherobject.weather.icon.equals("10n")){
                Icon01d.setOpacity(0.0);
                Icon01n.setOpacity(0.0);
                Icon02d.setOpacity(0.0);
                Icon02n.setOpacity(0.0);
                Icon03dn.setOpacity(0.0);
                Icon04dn.setOpacity(0.0);
                Icon09dn.setOpacity(0.0);
                Icon10d.setOpacity(0.0);
                Icon10n.setOpacity(1.0);
                Icon11d.setOpacity(0.0);
                Icon11n.setOpacity(0.0);
            }
            if(weatherobject.weather.icon.equals("11d")){
                Icon01d.setOpacity(0.0);
                Icon01n.setOpacity(0.0);
                Icon02d.setOpacity(0.0);
                Icon02n.setOpacity(0.0);
                Icon03dn.setOpacity(0.0);
                Icon04dn.setOpacity(0.0);
                Icon09dn.setOpacity(0.0);
                Icon10d.setOpacity(0.0);
                Icon10n.setOpacity(0.0);
                Icon11d.setOpacity(1.0);
                Icon11n.setOpacity(0.0);
            }
            if(weatherobject.weather.icon.equals("11n")){
                Icon01d.setOpacity(0.0);
                Icon01n.setOpacity(0.0);
                Icon02d.setOpacity(0.0);
                Icon02n.setOpacity(0.0);
                Icon03dn.setOpacity(0.0);
                Icon04dn.setOpacity(0.0);
                Icon09dn.setOpacity(0.0);
                Icon10d.setOpacity(0.0);
                Icon10n.setOpacity(0.0);
                Icon11d.setOpacity(0.0);
                Icon11n.setOpacity(1.0);
            }


            //Text Value Change
            LabelCityName.setText(weatherobject.name);
            LabelTemperature.setText(String.valueOf(weatherobject.main.temp)+"\' C");
            LabelMinTemperature.setText(String.valueOf(weatherobject.main.temp-3)+"\' C");
            LabelMaxTemperature.setText(String.valueOf(weatherobject.main.temp+2.4)+"\' C");
            LabelHumidity.setText(String.valueOf(weatherobject.main.humidity)+" %");
            LabelWindSpeed.setText(String.valueOf(weatherobject.wind.speed)+" m/sec");
            LabelWeatherDescription.setText(weatherobject.weather.description.toUpperCase());
            LabelPressure.setText(String.valueOf(weatherobject.main.pressure));
            LabelCloudLevel.setText("Cloud Level : "+String.valueOf(weatherobject.clouds.all));
            ProgressBarCloudLevel.setProgress(weatherobject.clouds.all/100);

            //
            LabelErrorComment.setText("");
            LabelErrorComment.setOpacity(0.0);
        }
        catch (Exception e){
            LabelErrorComment.setOpacity(1.0);
            LabelErrorComment.setText("!!!ERROR!!!");
        }

    }

    //This method will be executed when the user want to refresh the weather


    public void refreshButtonAction(ActionEvent event) {
        //Main.graph.show();

        if (forRefresh) {
            try {           
                OpenWeatherMap weatherobject = WeatherRequest.call_me(refreshID);
                forRefresh=true;
                refreshID=weatherobject.id;
//                weatherobject.main.temp=14;
//                weatherobject.weather.icon="11d";
//                weatherobject.weather.icon="11n";
                ImageLayerCelsiusBackground.setOpacity(1.0);
                ImageLayerFahrenheitBackground.setOpacity(0.0);

                if(weatherobject.weather.icon.equals("50d")){
                    weatherobject.weather.icon="02d";
                }
                if(weatherobject.weather.icon.equals("50n")){
                    weatherobject.weather.icon="02n";
                }

                //BackGround Change

                if(weatherobject.weather.icon.contains("01")||weatherobject.weather.icon.contains("02")||weatherobject.weather.icon.contains("03")||weatherobject.weather.icon.contains("04")){
                    toWinter=false;
                    toSummer=true;
                    toRainy=false;
                }
                if(weatherobject.weather.icon.contains("09")||weatherobject.weather.icon.contains("10")||weatherobject.weather.icon.contains("11")){
                    toWinter=false;
                    toSummer=false;
                    toRainy=true;
                }
                if(weatherobject.main.temp<15){
                    toWinter=true;
                    toSummer=false;
                    toRainy=false;
                }
                if(weatherobject.weather.icon.charAt(weatherobject.weather.icon.length()-1)=='n'){
                    night=true;
                    ImageLayerNight.setVisible(true);
                    ImageLayerNight.setOpacity(0.0);
                    ImageLayerSun.setVisible(false);
                }
                else{
                    night=false;
                    ImageLayerNight.setVisible(false);
                    ImageLayerNight.setOpacity(0.0);
                    ImageLayerSun.setVisible(true);
                }


                //Weather Icon Change
                if(weatherobject.weather.icon.equals("01d")){
                    Icon01d.setOpacity(1.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("01n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(1.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("02d")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(1.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("02n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(1.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("03d")||weatherobject.weather.icon.equals("03n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(1.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("04d")||weatherobject.weather.icon.equals("04n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(1.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("09d")||weatherobject.weather.icon.equals("09n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(1.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("10d")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(1.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("10n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(1.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("11d")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(1.0);
                    Icon11n.setOpacity(0.0);
                }
                if(weatherobject.weather.icon.equals("11n")){
                    Icon01d.setOpacity(0.0);
                    Icon01n.setOpacity(0.0);
                    Icon02d.setOpacity(0.0);
                    Icon02n.setOpacity(0.0);
                    Icon03dn.setOpacity(0.0);
                    Icon04dn.setOpacity(0.0);
                    Icon09dn.setOpacity(0.0);
                    Icon10d.setOpacity(0.0);
                    Icon10n.setOpacity(0.0);
                    Icon11d.setOpacity(0.0);
                    Icon11n.setOpacity(1.0);
                }


                //Text Value Change
                LabelCityName.setText(weatherobject.name);
                LabelTemperature.setText(String.valueOf(weatherobject.main.temp)+"\' C");
                LabelMinTemperature.setText(String.valueOf(weatherobject.main.temp-3)+"\' C");
                LabelMaxTemperature.setText(String.valueOf(weatherobject.main.temp+2.4)+"\' C");
                LabelHumidity.setText(String.valueOf(weatherobject.main.humidity)+" %");
                LabelWindSpeed.setText(String.valueOf(weatherobject.wind.speed)+" m/sec");
                LabelWeatherDescription.setText(weatherobject.weather.description.toUpperCase());
                LabelPressure.setText(String.valueOf(weatherobject.main.pressure));
                LabelCloudLevel.setText("Cloud Level : "+String.valueOf(weatherobject.clouds.all));
                ProgressBarCloudLevel.setProgress(weatherobject.clouds.all/100);

                //
                LabelErrorComment.setText("");
                LabelErrorComment.setOpacity(0.0);

            } 
            catch (Exception e) {
                LabelErrorComment.setOpacity(1.0);
                LabelErrorComment.setText("!!!ERROR!!!");
            }
        }
    }

    //this method will call itself repeatedly
    //it will move the clouds
    //smoothing transition of background during weather change
    //change opacity of Icon and sun to show Illuminating Effect
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline animate = new Timeline(new KeyFrame(Duration.seconds(.04), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(toSummer){
                    if(ImageLayerSummer.getOpacity()<1)ImageLayerSummer.setOpacity(ImageLayerSummer.getOpacity()+.01);
                    if(ImageLayerWinter.getOpacity()>0)ImageLayerWinter.setOpacity(ImageLayerWinter.getOpacity()-.01);
                    if(ImageLayerRain.getOpacity()>0)ImageLayerRain.setOpacity(ImageLayerRain.getOpacity()-.01);
                }
                if(toRainy){
                    if(ImageLayerSummer.getOpacity()>0)ImageLayerSummer.setOpacity(ImageLayerSummer.getOpacity()-.01);
                    if(ImageLayerWinter.getOpacity()>0)ImageLayerWinter.setOpacity(ImageLayerWinter.getOpacity()-.01);
                    if(ImageLayerRain.getOpacity()<1)ImageLayerRain.setOpacity(ImageLayerRain.getOpacity()+.01);
                }
                if(toWinter){
                    if(ImageLayerSummer.getOpacity()>0)ImageLayerSummer.setOpacity(ImageLayerSummer.getOpacity()-.01);
                    if(ImageLayerWinter.getOpacity()<1)ImageLayerWinter.setOpacity(ImageLayerWinter.getOpacity()+.01);
                    if(ImageLayerRain.getOpacity()<0)ImageLayerRain.setOpacity(ImageLayerRain.getOpacity()-.01);
                }

                //Night Control
                if(night) {
                    if (ImageLayerNight.getOpacity() < .5)
                        ImageLayerNight.setOpacity(ImageLayerNight.getOpacity() + .01);
                }


                //Cloud
                if(ImageLayerCloud2.getLayoutX()==-460)ImageLayerCloud2.setLayoutX(970);
                if(ImageLayerCloud3.getLayoutX()==-460)ImageLayerCloud3.setLayoutX(970);

                ImageLayerCloud2.setLayoutX(ImageLayerCloud2.getLayoutX()-.5);
                ImageLayerCloud3.setLayoutX(ImageLayerCloud3.getLayoutX()-.5);


                //Weather Icon Control
                if(icon){
                    ImageLayerMainIcon.setOpacity(ImageLayerMainIcon.getOpacity()-.01);
                }
                if(ImageLayerMainIcon.getOpacity()<.2){
                    icon=false;
                }
                if(!icon){
                    ImageLayerMainIcon.setOpacity(ImageLayerMainIcon.getOpacity()+.01);
                }
                if(ImageLayerMainIcon.getOpacity()>1.0){
                    icon=true;
                }
                //Sun Control
                if(sun){
                    ImageLayerSun.setOpacity(ImageLayerSun.getOpacity()-.005);
                }
                if(ImageLayerSun.getOpacity()<.4){
                    sun=false;
                }
                if(!sun){
                    ImageLayerSun.setOpacity(ImageLayerSun.getOpacity()+.005);
                }
                if(ImageLayerSun.getOpacity()>1.0){
                    sun=true;
                }
                //Time control
                if(timeanimate){
                    ImageViewClock.setOpacity(ImageViewClock.getOpacity()-.01);
                    ImageViewCalendar.setOpacity(ImageViewCalendar.getOpacity()-.01);
                }
                if(ImageViewClock.getOpacity()<.2){
                    timeanimate=false;
                }
                if(!timeanimate){
                    ImageViewClock.setOpacity(ImageViewClock.getOpacity()+.01);
                    ImageViewCalendar.setOpacity(ImageViewCalendar.getOpacity()+.01);
                }
                if(ImageViewClock.getOpacity()>1.0){
                    timeanimate=true;
                }
            }
        }));
        animate.setCycleCount(Timeline.INDEFINITE);
        animate.play();


        Timeline animate2 = new Timeline(new KeyFrame(Duration.seconds(.5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!colonanimate){
                    LabelColon1.setOpacity(1.0);
                    LabelColon2.setOpacity(1.0);
                    colonanimate=true;
                    return;
                }
                if(colonanimate){
                    LabelColon1.setOpacity(0.1);
                    LabelColon2.setOpacity(0.1);
                    colonanimate=false;
                    return;
                }
            }
        }));
        animate2.setCycleCount(Timeline.INDEFINITE);
        animate2.play();


        Timeline animate3 = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mySecond++;
                if(mySecond==60){
                    mySecond=0;
                    myMinute++;
                }
                LabelSecond.setText(String.valueOf(mySecond));
                if(myMinute==60){
                    myMinute=0;
                    myHour++;
                }
                LabelMinute.setText(String.valueOf(myMinute));
                if(myHour==24){
                    myHour=0;
                }
                if(myHour==12||myHour==0) {
                    LabelHour.setText(String.valueOf(12));
                }else{
                    LabelHour.setText(String.valueOf(myHour % 12));
                }
                if(myHour>=12)LabelAMPM.setText("PM");
                else LabelAMPM.setText("AM");
                LabelWeekDay.setText(myWeekday);
                LabelDate.setText(String.valueOf(myDate));
                LabelMonth.setText(myMonth);
                LabelYear.setText(String.valueOf(myYear));
            }
        }));
        animate3.setCycleCount(Timeline.INDEFINITE);
        animate3.play();

        Timeline animate4 = new Timeline(new KeyFrame(Duration.seconds(.02), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(timePaneToDown){
                    PaneTime.setLayoutY(PaneTime.getLayoutY()+1);
                    if(PaneTime.getLayoutY()==0){
                        timePaneToDown=false;
                        TimeEanbleDisable.setButtonType(JFXButton.ButtonType.FLAT);
                    }
                }
                if(timePaneToUp){
                    PaneTime.setLayoutY(PaneTime.getLayoutY()-1);
                    if(PaneTime.getLayoutY()==-65){
                        timePaneToUp=false;
                        TimeEanbleDisable.setButtonType(JFXButton.ButtonType.RAISED);
                    }
                }
            }
        }));
        animate4.setCycleCount(Timeline.INDEFINITE);
        animate4.play();
    }


}
