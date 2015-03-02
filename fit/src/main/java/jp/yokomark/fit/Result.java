package jp.yokomark.fit;

/**
 * @author KeithYokoma
 */
public class Result {
    private final boolean mSuccess;
    private final Throwable mThrowable;

    public Result(boolean success) {
        this(success, null);
    }

    public Result(boolean success, Throwable throwable) {
        mSuccess = success;
        mThrowable = throwable;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public boolean hasError() {
        return mThrowable != null;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }
}
