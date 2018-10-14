package android.apps.hbmsu.com.weatherapp.base;

import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements BaseViewModel.ViewModelCallback {

    protected ProgressBar progressBar;

    protected void displayProgressBar(boolean display) {
        if (progressBar != null)
            progressBar.setVisibility(display ? View.VISIBLE : View.GONE);
    }
}
