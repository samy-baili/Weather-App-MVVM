package android.apps.hbmsu.com.weatherapp.util;

import android.apps.hbmsu.com.weatherapp.ui.activity.DetailActivity;
import android.content.Context;
import android.content.Intent;

public class IntentHelper {

    public static Intent getDetailActivityIntent(Context context, String cityId, String cityName) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constant.CITY_ID_INTENT_KEY, cityId);
        intent.putExtra(Constant.CITY_NAME_INTENT_KEY, cityName);
        return intent;
    }
}
