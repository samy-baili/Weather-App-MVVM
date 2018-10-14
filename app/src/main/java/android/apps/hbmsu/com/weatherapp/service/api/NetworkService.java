package android.apps.hbmsu.com.weatherapp.service.api;

import android.apps.hbmsu.com.weatherapp.service.model.WeatherCitiesModel;
import android.apps.hbmsu.com.weatherapp.service.model.WeatherCityModel;
import android.apps.hbmsu.com.weatherapp.util.Constant;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkService {

    @GET(Constant.GROUP_QUERY)
    Single<WeatherCitiesModel> getWeatherByCityIDs(@Query(Constant.ID_KEY) String id, @Query(Constant.API_ID_KEY) String appid, @Query(Constant.UNITS_KEY) String units);

    @GET(Constant.FORECAST_QUERY)
    Single<WeatherCityModel> getWeatherByCityID(@Query(Constant.ID_KEY) String id, @Query(Constant.CNT_KEY) int cnt, @Query(Constant.API_ID_KEY) String appid, @Query(Constant.UNITS_KEY) String units);

    @GET(Constant.TRIGGERS_ID_QUERY)
    Single<JsonObject> getWeatherAlertsById(@Path(value = Constant.TRIGGER_ID_KEY, encoded = true) String triggerId, @Query(Constant.API_ID_KEY) String appid);

    @POST(Constant.TRIGGERS_QUERY)
    Single<JsonObject> getWeatherAlerts(@Query(Constant.API_ID_KEY) String appid, @Body JsonObject json);
}
