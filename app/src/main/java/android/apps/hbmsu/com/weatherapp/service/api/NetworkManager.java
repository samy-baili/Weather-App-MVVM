package android.apps.hbmsu.com.weatherapp.service.api;

import android.apps.hbmsu.com.weatherapp.service.model.WeatherCitiesModel;
import android.apps.hbmsu.com.weatherapp.service.model.WeatherCityModel;
import android.apps.hbmsu.com.weatherapp.util.Constant;
import android.apps.hbmsu.com.weatherapp.util.JsonUtils;

import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private static NetworkService networkService = configureService();

    private static NetworkService configureService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NetworkService.class);
    }

    public static Single<WeatherCitiesModel> getWeatherByCityIDs(String cityIds) {
        return NetworkManager.networkService.getWeatherByCityIDs(cityIds, Constant.OPEN_WEATHER_MAP_API_KEY, Constant.METRIC_KEY);
    }

    public static Single<WeatherCityModel> getWeatherByCityID(String cityId) {
        return NetworkManager.networkService.getWeatherByCityID(cityId, Constant.CITY_TEMP_COUNT, Constant.OPEN_WEATHER_MAP_API_KEY, Constant.METRIC_KEY);
    }

    public static Single<JsonObject> getWeatherAlertsById(String triggerId) {
        return NetworkManager.networkService.getWeatherAlertsById(triggerId, Constant.OPEN_WEATHER_MAP_API_KEY);
    }

    public static Single<JsonObject> getWeatherAlerts() {
        return NetworkManager.networkService.getWeatherAlerts(Constant.OPEN_WEATHER_MAP_API_KEY, JsonUtils.stringToJson(Constant.DUMMY_ALERT_BODY));
    }
}
