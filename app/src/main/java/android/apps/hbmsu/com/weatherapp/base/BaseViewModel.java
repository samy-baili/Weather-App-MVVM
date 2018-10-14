package android.apps.hbmsu.com.weatherapp.base;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposable;
    protected ViewModelCallback callback;
    protected Object data;

    public BaseViewModel(ViewModelCallback callback) {
        this.disposable = new CompositeDisposable();
        this.callback = callback;
    }

    public void unsubscribe() {
        disposable.clear();
    }

    public interface ViewModelCallback {
        void onSuccess(Object object);
        void onError();
    }
}
