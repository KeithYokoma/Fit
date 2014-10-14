package jp.yokomark.fit.sample;

import jp.yokomark.fit.OnVersion;
import jp.yokomark.fit.VersionModule;

/**
 * @author KeishinYokomaku
 */
public class SampleModule implements VersionModule {
    public static final String TAG = SampleModule.class.getSimpleName();

    @OnVersion(1)
    public void onUpdate() {

    }
}
