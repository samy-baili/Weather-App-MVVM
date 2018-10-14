package android.apps.hbmsu.com.weatherapp.service.service;

import android.apps.hbmsu.com.weatherapp.base.BaseBroadcastService;
import android.apps.hbmsu.com.weatherapp.util.Constant;

public class UpdateWeatherBroadcastService extends BaseBroadcastService {

    @Override
    public String actionBroadCast() {
        return Constant.UPDATE_WEATHER_BROADCAST;
    }

    @Override
    public int handlerDuration() {
        return Constant.REFRESH_BROADCAST_TIMER_SECONDE;
    }

}
