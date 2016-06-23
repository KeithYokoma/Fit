package jp.yokomark.fit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * An object representing an upgrade procedure execution result.
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public APIs
public class Result implements Parcelable {
    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
    private final boolean mSuccess;
    private final int mFrom;
    private final Throwable mThrowable;

    public Result(boolean success, int from) {
        this(success, from, null);
    }

    public Result(boolean success, int from, Throwable throwable) {
        mSuccess = success;
        mFrom = from;
        mThrowable = throwable;
    }

    protected Result(Parcel in) {
        mSuccess = in.readInt() == 1;
        mThrowable = (Throwable) in.readSerializable();
        mFrom = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mSuccess ? 1 : 0);
        dest.writeSerializable(mThrowable);
        dest.writeInt(mFrom);
    }

    /**
     * @return true if all executions went well, false otherwise.
     */
    public boolean isSuccess() {
        return mSuccess;
    }

    /**
     * @return true if an error occurred in the upgrade procedure.
     */
    public boolean hasError() {
        return mThrowable != null;
    }

    /**
     * @return a throwable object that is thrown in the upgrade procedure.
     */
    public Throwable getThrowable() {
        return mThrowable;
    }

    /**
     * @return the previous version code.
     */
    public int getFrom() {
        return mFrom;
    }
}
