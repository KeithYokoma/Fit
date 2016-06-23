package jp.yokomark.fit;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles version upgrade procedure when the version code is incremented.
 * @author KeithYokoma
 */
public final class Fit {
    public static final String TAG = Fit.class.getSimpleName();
    private static volatile Fit sInstance;
    private final Application mApplication;
    private final VersionHistory mHistory;
    private final Helper mHelper;
    private final List<VersionModule> mVersionModules;

    protected Fit(Application application) {
        mApplication = application;
        mHistory = new VersionHistory(application);
        mHelper = new Helper(application);
        mVersionModules = new ArrayList<>();
    }

    public void addModules(VersionModule... modules) {
        List<VersionModule> list = Arrays.asList(modules);
        mHelper.validate(list);
        mVersionModules.addAll(list);
    }

    /**
     * Synchronously executes the version up procedure.
     */
    public Result execute() {
        if (!mHistory.isVersionChanged()) {
            return new Result(false, 0);
        }
        int code = mHistory.readStoredVersion();
        try {
            Result result = execute(code);
            mHistory.storeCurrentVersion();
            return result;
        } catch (Exception e) {
            return new Result(false, code, e);
        }
    }

    public Result execute(int versionCode) {
        mHelper.execute(versionCode, mVersionModules);
        return new Result(true, versionCode);
    }

    /* package */ Application getApplication() {
        return mApplication;
    }

    /* package */ List<VersionModule> getVersionModules() {
        return new ArrayList<>(mVersionModules);
    }

    public static void initialize(Application application, VersionModule... modules) {
        if (sInstance == null) {
            synchronized (Fit.class) {
                if (sInstance == null) {
                    sInstance = new Fit(application);
                    sInstance.addModules(modules);
                }
            }
        } else {
            Log.v(TAG, "Fit is already initialized.");
        }
    }

    public static synchronized Fit getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(
                    "Fit is not initialized yet. Call Fit.initialize() first in your application class.");
        }
        return sInstance;
    }

    public static void destroy() {
        if (sInstance != null) {
            synchronized (Fit.class) {
                if (sInstance != null) {
                    sInstance = null;
                }
            }
        } else {
            Log.v(TAG, "Fit is already destroyed or not initialized yet.");
        }
    }
}
