package jp.yokomark.fit.sample;

import android.app.Application;
import android.util.Log;

import jp.yokomark.fit.Fit;
import jp.yokomark.fit.Result;

/**
 * @author KeithYokoma
 */
public class SampleApplication extends Application {
    public static final String TAG = SampleApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Fit.initialize(this, new SampleModule());
        Result result = Fit.getInstance().execute();
        Log.v(TAG, "upgraded from " + result.getFrom());
    }

    @Override
    public void onTerminate() {
        Fit.destroy();
        super.onTerminate();
    }
}
