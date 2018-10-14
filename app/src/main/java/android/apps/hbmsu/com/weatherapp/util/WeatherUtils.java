package android.apps.hbmsu.com.weatherapp.util;

public class WeatherUtils {

    public static float fahrenheitToCelsius(float temperature) {
        return ((temperature - 32) * 5) / 9;
    }

    public static float celsiusToFahrenheit(float t1) {
        return (t1 * 9 / 5) + 32;
    }
}
