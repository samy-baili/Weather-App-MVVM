package android.apps.hbmsu.com.weatherapp.ui.viewholder;

import android.apps.hbmsu.com.weatherapp.R;
import android.apps.hbmsu.com.weatherapp.util.Constant;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    private TextView cityView;
    private TextView descriptionView;
    private ImageView iconView;

    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);

        this.cityView = itemView.findViewById(R.id.city_view);
        this.descriptionView = itemView.findViewById(R.id.description_view);
        this.iconView = itemView.findViewById(R.id.icon_view);
    }

    public void config(String city, String description, String icon) {
        this.cityView.setText(city);
        this.descriptionView.setText(description);
        initIcon(itemView.getContext(), icon);
    }

    private void initIcon(Context context, String icon) {
        Glide.with(context)
                .load(Constant.IMAGE_URL + icon)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(iconView);
    }
}
