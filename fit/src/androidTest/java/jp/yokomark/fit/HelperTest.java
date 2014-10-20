package jp.yokomark.fit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KeishinYokomaku
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class HelperTest {
    private Helper mHelper;

    @Before
    public void setUp() throws Exception {
        mHelper = new Helper(Robolectric.application);
    }

    @Test
    public void validation() throws Exception {
        List<VersionModule> modules = new ArrayList<VersionModule>();
        modules.add(new MockModule());
        mHelper.validate(modules);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validationFailure_null() throws Exception {
        mHelper.validate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validationFailure_empty() throws Exception {
        mHelper.validate(new ArrayList<VersionModule>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validationFailure_noAnnotation() throws Exception {
        List<VersionModule> modules = new ArrayList<VersionModule>();
        modules.add(new NoAnnotationModule());
        mHelper.validate(modules);
    }

    static class MockModule implements VersionModule {
        @VersionCode(1)
        public void foo() {
        }
    }

    static class NoAnnotationModule implements VersionModule {
        public void foo() {
        }
    }
}
