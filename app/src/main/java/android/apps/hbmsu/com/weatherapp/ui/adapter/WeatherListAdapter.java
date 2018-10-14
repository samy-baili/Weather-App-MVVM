package android.apps.hbmsu.com.weatherapp.ui.adapter;

import android.apps.hbmsu.com.weatherapp.R;
import android.apps.hbmsu.com.weatherapp.service.model.WeatherCitiesModel;
import android.apps.hbmsu.com.weatherapp.service.model.WeatherModel;
import android.apps.hbmsu.com.weatherapp.ui.viewholder.WeatherViewHolder;
import android.apps.hbmsu.com.weatherapp.util.IntentHelper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private Context context;
    private WeatherCitiesModel cities;
    private LayoutInflater inflater;

    public WeatherListAdapter(Context context, WeatherCitiesModel cities) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.cities = cities;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherViewHolder(inflater.inflate(R.layout.weather_list_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        final WeatherModel item = getItem(position);

        if (item.weather != null && item.weather.length > 0)
            holder.config(item.name, item.weather[0].description, item.weather[0].icon + ".png");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(IntentHelper.getDetailActivityIntent(context, String.valueOf(item.id), item.name));
            }
        });
    }

    private WeatherModel getItem(int position) {
        return cities.list[position];
    }

    @Override
    public int getItemCount() {
        return cities.cnt;
    }
}
