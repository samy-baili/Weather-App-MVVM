package android.apps.hbmsu.com.weatherapp.util;

public class Constant {

    /************* API CONSTANT *****************/

    public final static String OPEN_WEATHER_MAP_API_KEY = "ae41758ffe51e750af36264269424826";
    public static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String IMAGE_URL = "http://openweathermap.org/img/w/";
    public final static String GROUP_QUERY = "/data/2.5/group";
    public final static String FORECAST_QUERY = "/data/2.5/forecast";
    public final static String TRIGGERS_ID_QUERY = "/data/3.0/triggers/{trigger_id}";
    public final static String TRIGGERS_QUERY = "/data/3.0/triggers";


    /************* API QUERY CONSTANT *****************/

    public final static int CITY_TEMP_COUNT = 5;
    public final static String ID_KEY = "id";
    public final static String TRIGGER_ID_KEY = "trigger_id";
    public final static String API_ID_KEY = "APPID";
    public final static String UNITS_KEY = "units";
    public final static String CNT_KEY = "cnt";
    public final static String METRIC_KEY = "metric";


    /*************** INTENT CONSTANT *****************/

    public final static String CITY_NAME_INTENT_KEY = "city_name";
    public final static String CITY_ID_INTENT_KEY = "city_id";


    /***************** BROADCAST CONSTANT *****************/

    public final static String UPDATE_WEATHER_BROADCAST = "update_weather_broadcast";
    public final static int REFRESH_BROADCAST_TIMER_SECONDE = 60;


    //todo to remove
    public final static String DUMMY_ALERT_BODY = "{ \"time_period\":{ \"start\":{ \"expression\":\"after\", \"amount\":132000000 }, \"end\":{ \"expression\":\"after\", \"amount\":432000000 } }, \"conditions\":[ { \"name\":\"temp\", \"expression\":\"$gt\", \"amount\":299 } ], \"area\":[ { \"type\":\"Point\", \"coordinates\":[ 53, 37 ] } ] }";


}
