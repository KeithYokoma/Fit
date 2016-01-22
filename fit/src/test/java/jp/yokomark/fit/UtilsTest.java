package jp.yokomark.fit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author KeishinYokomaku
 */
@Config(constants = BuildConfig.class, sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class UtilsTest {
    @Test
    public void containsValueInRange() throws Exception {
        assertTrue(Utils.containsValueInRange(new int[]{1}, 0, 1));
        assertTrue(Utils.containsValueInRange(new int[]{0, 1}, 0, 1));
        assertTrue(Utils.containsValueInRange(new int[]{0, 1, 2}, 0, 1));
        assertTrue(Utils.containsValueInRange(new int[]{1, 2, 3}, 0, 1));
        assertTrue(Utils.containsValueInRange(new int[]{1, 2, 3}, 1, 2));
        assertTrue(Utils.containsValueInRange(new int[]{1, 2, 3}, 1, 3));
        assertTrue(Utils.containsValueInRange(new int[]{1, 2, 3}, 1, 9));
        assertFalse(Utils.containsValueInRange(new int[]{0}, 0, 1));
        assertFalse(Utils.containsValueInRange(new int[]{0, 1}, 2, 9));
    }

    @Test
    public void getAnnotatedMethods() throws Exception {
        assertEquals(1, Utils.getAnnotatedMethods(MockVersionModule.class, VersionCode.class).size());
        assertEquals("foo", Utils.getAnnotatedMethods(MockVersionModule.class, VersionCode.class).get(0).getName());
    }

    @Test
    public void isNewInstall() throws Exception {
        assertTrue(Utils.isNewInstall(VersionHistory.NEW_INSTALL_CODE));
        assertFalse(Utils.isNewInstall(1));
    }

    static class MockVersionModule implements VersionModule {
        @VersionCode(1)
        public void foo() {
        }
    }
}
