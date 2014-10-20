package jp.yokomark.fit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

/**
 * @author KeishinYokomaku
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class FitTest {
    @Test
    public void initialization() throws Exception {
        Fit.initialize(Robolectric.application, new MockModule());
        assertNotNull(Fit.getInstance());
    }

    static class MockModule implements VersionModule {
        @VersionCode(1)
        public void foo() {
        }
    }
}
