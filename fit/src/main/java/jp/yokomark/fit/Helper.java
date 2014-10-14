package jp.yokomark.fit;

import android.app.Application;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author KeithYokoma
 */
/* package */ final class Helper {
    public static final String TAG = Helper.class.getSimpleName();
    private final Application mApplication;

    /* package */ Helper(Application application) {
        mApplication = application;
    }

    public void validate(List<VersionModule> modules) {
        if (modules == null || modules.isEmpty()) {
            throw new IllegalArgumentException("No modules found.");
        }
    }

    public void execute(int baseVersion, List<VersionModule> modules) {
        for (VersionModule module : modules) {
            List<Method> methods = Utils.getAnnotatedMethods(module.getClass(), OnVersion.class);
            execute(baseVersion, module, methods);
        }
    }

    /* package */ void execute(int baseVersion, VersionModule module, List<Method> methods) {
        for (Method method : methods) {
            OnVersion ann = method.getAnnotation(OnVersion.class);
            int annotatedVersion = ann.value();
            if (baseVersion < annotatedVersion) {
                Utils.dispatch(module, method);
            }
        }
    }
}
