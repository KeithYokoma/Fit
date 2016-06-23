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
        for (VersionModule module : modules) {
            if (!Utils.hasAnnotatedMethods(module.getClass(), VersionCode.class)) {
                throw new IllegalArgumentException("No annotated method found.");
            }
        }
    }

    public void execute(int baseVersion, List<VersionModule> modules) {
        for (VersionModule module : modules) {
            List<Method> methods = Utils.getAnnotatedMethods(module.getClass(), VersionCode.class);
            execute(baseVersion, module, methods);
        }
    }

    /* package */ void execute(int baseVersion, VersionModule module, List<Method> methods) {
        for (Method method : methods) {
            VersionCode ann = method.getAnnotation(VersionCode.class);
            int[] annotatedVersion = ann.value();
            boolean reject = Utils.isNewInstall(baseVersion) && method.getAnnotation(UpgradeOnly.class) != null;
            if (reject) {
                continue;
            }
            if (Utils.containsValueInRange(
                    annotatedVersion, baseVersion, Utils.getCurrentVersionCode(mApplication))) {
                Utils.dispatch(module, method);
            }
        }
    }
}
