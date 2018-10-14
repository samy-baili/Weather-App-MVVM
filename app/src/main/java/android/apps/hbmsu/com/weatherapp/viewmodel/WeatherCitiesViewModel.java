package android.apps.hbmsu.com.weatherapp.viewmodel;

import android.apps.hbmsu.com.weatherapp.base.BaseViewModel;
import android.apps.hbmsu.com.weatherapp.service.api.NetworkManager;
import android.apps.hbmsu.com.weatherapp.service.model.WeatherCitiesModel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherCitiesViewModel extends BaseViewModel {

    public WeatherCitiesViewModel(ViewModelCallback callback) {
        super(callback);
    }

    public void getWeatherByCityIDs(String cityIds) {
        disposable.add(NetworkManager
                        .getWeatherByCityIDs(cityIds)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<WeatherCitiesModel>() {
                            @Override
                            public void onSuccess(WeatherCitiesModel cities) {
                                data = cities;
                                callback.onSuccess(cities);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                callback.onError();
                            }
                        }));
    }

    public void getWeatherAlerts() {
        disposable.add(NetworkManager
                .getWeatherAlerts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<JsonObject>() {
                    @Override
                    public void onSuccess(JsonObject trigger) {
                        JsonElement element = trigger.get("_id");
                        if (element != null)
                            getWeatherAlertsById(element.getAsString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }));
    }

    private void getWeatherAlertsById(String triggerId) {
        disposable.add(NetworkManager
                .getWeatherAlertsById(triggerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<JsonObject>() {
                    @Override
                    public void onSuccess(JsonObject trigger) {
                        callback.onSuccess(trigger);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }));
    }

    public WeatherCitiesModel getData() {
        return (data != null) ? (WeatherCitiesModel) data : null;
    }

}
