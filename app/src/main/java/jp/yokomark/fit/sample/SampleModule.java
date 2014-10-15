package jp.yokomark.fit.sample;

import android.util.Log;

import jp.yokomark.fit.OnVersion;
import jp.yokomark.fit.VersionModule;

/**
 * @author KeithYokoma
 */
public class SampleModule implements VersionModule {
    public static final String TAG = SampleModule.class.getSimpleName();

    @OnVersion({1, 2})
    public void onUpdate() {
        Log.v(TAG, "1 or 2");
    }
}
