package android.apps.hbmsu.com.weatherapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    private final static String PREF_NAME = "pref_name";
    public final static String TEMPERATURE_UNIT_PREF_KEY = "temperature_unit";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void saveBooleanPref(Context context, String key, boolean bool) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(key, bool);
        editor.apply();
    }

    public static boolean getBooleanPref(Context context, String key) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getBoolean(key, false);
    }

}
