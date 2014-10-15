package jp.yokomark.fit.sample;

import android.app.Application;

import jp.yokomark.fit.Fit;

/**
 * @author KeithYokoma
 */
public class SampleApplication extends Application {
    public static final String TAG = SampleApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Fit.initialize(this, new SampleModule());
        Fit.getInstance().execute();
    }

    @Override
    public void onTerminate() {
        Fit.destroy();
        super.onTerminate();
    }
}
