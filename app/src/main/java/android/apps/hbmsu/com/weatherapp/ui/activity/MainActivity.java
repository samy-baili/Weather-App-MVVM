package android.apps.hbmsu.com.weatherapp.ui.activity;

import android.apps.hbmsu.com.weatherapp.BuildConfig;
import android.apps.hbmsu.com.weatherapp.R;
import android.apps.hbmsu.com.weatherapp.base.BaseActivity;
import android.apps.hbmsu.com.weatherapp.service.model.WeatherCitiesModel;
import android.apps.hbmsu.com.weatherapp.service.service.UpdateWeatherBroadcastService;
import android.apps.hbmsu.com.weatherapp.ui.adapter.WeatherListAdapter;
import android.apps.hbmsu.com.weatherapp.util.Constant;
import android.apps.hbmsu.com.weatherapp.util.NotificationUtils;
import android.apps.hbmsu.com.weatherapp.util.PrefUtils;
import android.apps.hbmsu.com.weatherapp.viewmodel.WeatherCitiesViewModel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity {

    private WeatherCitiesViewModel viewModel;

    private RecyclerView recyclerView;

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity_layout);

        init();

        launchQuery();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.more_icon)
            showPopMenu(findViewById(R.id.more_icon));
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        viewModel.unsubscribe();

        if (receiver != null)
            unregisterReceiver(receiver);

        stopService(new Intent(this, UpdateWeatherBroadcastService.class));

        super.onDestroy();
    }

    private void init() {
        this.progressBar = findViewById(R.id.progress_bar);

        this.recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.viewModel = new WeatherCitiesViewModel(this);

        registerBroadcast();

        startService(new Intent(this, UpdateWeatherBroadcastService.class));
    }

    private void setAdapter(WeatherCitiesModel cities) {
        recyclerView.setAdapter(new WeatherListAdapter(this, cities));
    }

    private void launchQuery() {
        displayProgressBar(true);
        viewModel.getWeatherByCityIDs(BuildConfig.CITIES_ID_LIST);
        viewModel.getWeatherAlerts();
    }

    private void showPopMenu(View anchorView) {
        final boolean isFahreinheit = PrefUtils.getBooleanPref(this, PrefUtils.TEMPERATURE_UNIT_PREF_KEY);

        PopupMenu popup = new PopupMenu(MainActivity.this, anchorView);
        popup.getMenuInflater().inflate(R.menu.main_activity_pop_up_menu, popup.getMenu());
        popup.getMenu().findItem(R.id.unit_temp).setTitle(String.format(getString(R.string.display_temp), getString(isFahreinheit ? R.string.celcius_label : R.string.fahrenheit_label)));
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                PrefUtils.saveBooleanPref(MainActivity.this, PrefUtils.TEMPERATURE_UNIT_PREF_KEY, !isFahreinheit);
                return true;
            }
        });
        popup.show();
    }

    @Override
    public void onSuccess(Object cities) {
        displayProgressBar(false);
        if (cities instanceof WeatherCitiesModel)
            setAdapter((WeatherCitiesModel) cities);
        else
            NotificationUtils.createLocalNotification(this, getString(R.string.notification_tilte), getString(R.string.notification_content));
    }

    @Override
    public void onError() {
        displayProgressBar(false);
        Toast.makeText(this, R.string.error_query, Toast.LENGTH_LONG).show();
    }

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.UPDATE_WEATHER_BROADCAST);

        this.receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                launchQuery();
            }
        };

        registerReceiver(receiver, filter);
    }
}
