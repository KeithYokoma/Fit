package jp.yokomark.fit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KeishinYokomaku
 */
/* package */ final class Utils {
    public static final String TAG = Utils.class.getSimpleName();

    private Utils() {
        throw new AssertionError();
    }

    public static int getCurrentVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "cannot obtain package info for the package name: " + context.getPackageName(), e);
            return 0;
        }
    }

    public static SharedPreferences getDefaultSharedPreferences(Context context, String prefName) {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    public static List<Method> getAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> ann) {
        List<Method> methods = new ArrayList<Method>();
        Method[] declared = clazz.getDeclaredMethods();
        for (Method method : declared) {
            if (method.isAnnotationPresent(ann)) {
                methods.add(method);
            }
        }
        return methods;
    }

    public static void dispatch(VersionModule module, Method method) {
        try {
            method.invoke(module);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "cannot access to the method: " + method.getName(), e);
        } catch (InvocationTargetException e) {
            Log.e(TAG, "an error occurred while invoking the method: " + method.getName(), e);
        }
    }

    public static boolean containsValueInRange(int[] values, int min, int max) {
        for (int value : values) {
            if (min < value && value <= max) {
                return true;
            }
        }
        return false;
    }
}
