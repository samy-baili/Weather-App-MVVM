package android.apps.hbmsu.com.weatherapp.service.model;

public class WeatherCityModel {

    public String cod;
    public Float message;
    public Integer cnt;
    public List[] list;
    public City city;

    public class City {

        public Integer id;
        public String name;
        public Coord coord;
        public String country;
    }

    public class Coord {

        public Float lon;
        public Float lat;

    }

    public class List {

        public Integer dt;
        public Main main;
        public Weather[] weather;
        public Clouds clouds;
        public Wind wind;
        public Sys sys;
        public String dtTxt;

    }

    public class Wind {

        public Float speed;
        public Float deg;

    }

    public class Main {

        public Float temp;
        public Float tempMin;
        public Float tempMax;
        public Float pressure;
        public Float seaLevel;
        public Float grndLevel;
        public Integer humidity;
        public Integer tempKf;

    }

    public class Clouds {

        public Integer all;

    }

    public class Sys {

        public String pod;

    }
}
