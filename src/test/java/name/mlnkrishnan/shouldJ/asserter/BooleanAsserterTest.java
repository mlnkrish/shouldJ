package name.mlnkrishnan.shouldJ.asserter;

import org.hamcrest.core.Is;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static name.mlnkrishnan.shouldJ.ShouldJ.it;

public class BooleanAsserterTest {

    @Test
    public void shouldBeTrue_Pass() {
        it(new Boolean(true)).shouldBeTrue();
    }

    @Test
    public void shouldBeTrue_Fail() {
        try {
            it(false).shouldBeTrue();
            fail();
        } catch (Throwable e) {
            assertEquals("expected <true>, but was <false>",e.getMessage());
        }
    }

    @Test
    public void shouldBeTrue_PassWithMatcher() {
        it(true).shouldMatch(Is.is(true));
    }

    @Test
    public void shouldBeTrue_FailWithMatcher() {
        try {
            it(false).shouldMatch(Is.is(true));
            fail();
        } catch (Throwable e) {
            assertEquals("is <true>, but was <false>",e.getMessage());
        }
    }

    @Test
    public void shouldBeFalse_FailWithMatcher() {
        try {
            it(true).shouldMatch(Is.is(false));
            fail();
        } catch (Throwable e) {
            assertEquals("is <false>, but was <true>",e.getMessage());
        }
    }

    @Test
    public void shouldBeFalse_Pass() {
        it(false).shouldBeFalse();
    }

    @Test
    public void shouldBeFalse_Fail() {
        try {
            it(new Boolean(true)).shouldBeFalse();
            fail();
        } catch (Throwable e) {
            assertEquals("expected <false>, but was <true>",e.getMessage());
        }
    }
}
