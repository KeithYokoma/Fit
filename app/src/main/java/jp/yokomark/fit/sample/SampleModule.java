package jp.yokomark.fit.sample;

import android.util.Log;

import jp.yokomark.fit.UpgradeOnly;
import jp.yokomark.fit.VersionCode;
import jp.yokomark.fit.VersionModule;

/**
 * @author KeithYokoma
 */
public class SampleModule implements VersionModule {
    public static final String TAG = SampleModule.class.getSimpleName();

    @VersionCode({1, 2})
    public void onUpdate() {
        Log.v(TAG, "1 or 2");
    }

    @VersionCode({2})
    @UpgradeOnly
    public void onUpgradeOnly() {
        Log.v(TAG, "only executed on upgrade");
    }
}
