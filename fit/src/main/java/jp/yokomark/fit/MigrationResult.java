package jp.yokomark.fit;

/**
 * @author KeishinYokomaku
 */
public class MigrationResult {
    public static final String TAG = MigrationResult.class.getSimpleName();
    private final boolean mSuccess;

    public MigrationResult(boolean success) {
        mSuccess = success;
    }

    public boolean isSuccess() {
        return mSuccess;
    }
}
