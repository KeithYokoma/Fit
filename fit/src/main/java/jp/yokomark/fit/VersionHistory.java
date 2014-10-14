package jp.yokomark.fit;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * @author KeithYokoma
 */
/* package */ class VersionHistory {
    public static final String TAG = VersionHistory.class.getSimpleName();
    private static final String PREF_NAME = "fit_version";
    private static final String KEY_VERSION_CODE = "version_code";
    private final Application mApplication;

    /* package */ VersionHistory(Application application) {
        mApplication = application;
    }

    public void storeCurrentVersion() {
        SharedPreferences preferences = Utils.getDefaultSharedPreferences(mApplication, PREF_NAME);
        preferences.edit()
                .putInt(KEY_VERSION_CODE, Utils.getCurrentVersionCode(mApplication))
                .apply();
    }

    public int readStoredVersion() {
        SharedPreferences preferences = Utils.getDefaultSharedPreferences(mApplication, PREF_NAME);
        return preferences.getInt(KEY_VERSION_CODE, 0);
    }

    public boolean isVersionChanged() {
        int stored = readStoredVersion();
        int current = Utils.getCurrentVersionCode(mApplication);
        Log.v(TAG, "stored ver: " + stored + ", current ver: " + current);
        return stored < current;
    }
}
