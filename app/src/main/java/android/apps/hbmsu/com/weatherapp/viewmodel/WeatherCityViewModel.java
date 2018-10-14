package android.apps.hbmsu.com.weatherapp.viewmodel;

import android.apps.hbmsu.com.weatherapp.base.BaseViewModel;
import android.apps.hbmsu.com.weatherapp.service.api.NetworkManager;
import android.apps.hbmsu.com.weatherapp.service.model.WeatherCityModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherCityViewModel extends BaseViewModel {

    public WeatherCityViewModel(ViewModelCallback callback) {
        super(callback);
    }

    public void getWeatherByCityID(String cityId) {
        disposable.add(NetworkManager
                .getWeatherByCityID(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<WeatherCityModel>() {
                    @Override
                    public void onSuccess(WeatherCityModel cities) {
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

    public WeatherCityModel getData() {
        return (data != null) ? (WeatherCityModel) data : null;
    }
}
