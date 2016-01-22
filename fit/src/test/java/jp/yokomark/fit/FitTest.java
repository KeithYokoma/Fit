package jp.yokomark.fit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

/**
 * @author KeishinYokomaku
 */
@Config(constants = BuildConfig.class, sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class FitTest {
    @Test
    public void initialization() throws Exception {
        Fit.initialize(RuntimeEnvironment.application, new MockModule());
        assertNotNull(Fit.getInstance());
    }

    static class MockModule implements VersionModule {
        @VersionCode(1)
        public void foo() {
        }
    }
}
