package android.apps.hbmsu.com.weatherapp.ui.activity;

import android.apps.hbmsu.com.weatherapp.R;
import android.apps.hbmsu.com.weatherapp.base.BaseActivity;
import android.apps.hbmsu.com.weatherapp.service.model.WeatherCityModel;
import android.apps.hbmsu.com.weatherapp.util.Constant;
import android.apps.hbmsu.com.weatherapp.util.PrefUtils;
import android.apps.hbmsu.com.weatherapp.util.WeatherUtils;
import android.apps.hbmsu.com.weatherapp.viewmodel.WeatherCityViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

public class DetailActivity extends BaseActivity {

    private WeatherCityViewModel cityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_activity_layout);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        Intent intent = getIntent();

        String cityName = intent.getStringExtra(Constant.CITY_NAME_INTENT_KEY);
        String cityId = intent.getStringExtra(Constant.CITY_ID_INTENT_KEY);

        this.progressBar = findViewById(R.id.progress_bar);

        this.cityViewModel = new WeatherCityViewModel(this);

        initToolbar(cityName);
        launchQuery(cityId);
    }


    private void initViews(WeatherCityModel object) {
        initChart(object);
    }

    private void initToolbar(String cityName) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(cityName);
        }
    }

    private void launchQuery(String cityID) {
        cityViewModel.getWeatherByCityID(cityID);
    }

    private void initChart(WeatherCityModel model) {
        boolean isFahreinheit = PrefUtils.getBooleanPref(this, PrefUtils.TEMPERATURE_UNIT_PREF_KEY);

        LineChart lineChart = findViewById(R.id.chart);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.disableScroll();

        Description description = new Description();
        description.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime()));
        lineChart.setDescription(description);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(createXValueFormatter());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setValueFormatter(createYValueFormatter(getString(!isFahreinheit ? R.string.degree_celsius_symbol : R.string.degree_fahrenheit_symbol)));

        lineChart.setData(new LineData(new LineDataSet(convertChartEntries(model.list, isFahreinheit), getString(R.string.temp_chart_label))));
        lineChart.invalidate();
    }

    private List<Entry> convertChartEntries(WeatherCityModel.List[] list, boolean isFahreinheit) {
        List<Entry> entries = new ArrayList<>();

        for (WeatherCityModel.List data : list)
            entries.add(new Entry(data.dt, checkTemp(data.main.temp, isFahreinheit)));

        return entries;
    }

    private float checkTemp(float temp, boolean isFahreinheit) {
        if (isFahreinheit)
            return WeatherUtils.celsiusToFahrenheit(temp);
        return temp;
    }

    private IAxisValueFormatter createXValueFormatter() {
        return new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date((long) (value * 1000)));
            }
        };
    }

    private IAxisValueFormatter createYValueFormatter(final String degreSymbol) {
        return new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value + " " + degreSymbol;
            }
        };
    }

    @Override
    protected void onDestroy() {
        cityViewModel.unsubscribe();

        super.onDestroy();
    }

    @Override
    public void onSuccess(Object object) {
        displayProgressBar(false);
        initViews((WeatherCityModel) object);
    }

    @Override
    public void onError() {
        displayProgressBar(false);
        finish();
        Toast.makeText(this, R.string.error_query, Toast.LENGTH_LONG).show();
    }
}
