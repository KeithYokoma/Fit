package jp.yokomark.fit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This entity has the information about the execution result.
 * @author KeithYokoma
 */
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

    public boolean isSuccess() {
        return mSuccess;
    }

    public boolean hasError() {
        return mThrowable != null;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public int getFrom() {
        return mFrom;
    }
}
