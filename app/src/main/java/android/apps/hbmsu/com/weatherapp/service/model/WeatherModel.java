package android.apps.hbmsu.com.weatherapp.service.model;

public class WeatherModel {

    public Coord coord;
    public Sys sys;
    public Weather[] weather;
    public Main main;
    public Integer visibility;
    public Wind wind;
    public Clouds clouds;
    public Integer dt;
    public Integer id;
    public String name;


    private class Wind {
        public Double speed;
        public Float deg;
    }

    private class Sys {
        public Integer type;
        public Integer id;
        public Double message;
        public String country;
        public Integer sunrise;
        public Integer sunset;
    }

    private class Main {
        public Double temp;
        public Float pressure;
        public Integer humidity;
        public Float tempMin;
        public Float tempMax;
    }

    private class Coord {
        public Double lon;
        public Double lat;
    }

    private class Clouds {
        public Integer all;
    }
}
