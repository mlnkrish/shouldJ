package name.mlnkrishnan.shouldJ.asserter;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static name.mlnkrishnan.shouldJ.ShouldJ.it;

public class StringAsserterTest {

    @Test
    public void shouldContain_Pass() {
        it("abc").shouldContain("bc");
    }

    @Test
    public void shouldContain_Fail() {
        try {
            it("abc").shouldContain("cb");
            fail();
        } catch (Throwable e) {
            assertEquals("expected <abc> to contain <cb>", e.getMessage());
        }
    }

    @Test
    public void shouldStartWith_Pass() {
        it("abc-xyz").shouldStartWith("abc-");
    }

    @Test
    public void shouldStartWith_Fail() {
        try {
            it("abc-xyz").shouldStartWith("ax-");
            fail();
        } catch (Throwable e) {
            assertEquals("expected <abc-xyz> to start with <ax->", e.getMessage());
        }
    }

    @Test
    public void shouldEndWith_Pass() {
        it("abc-xyz").shouldEndWith("-xyz");
    }

    @Test
    public void shouldEndWith_Fail() {
        try {
            it("abc-xyz").shouldEndWith("ax-");
            fail();
        } catch (Throwable e) {
            assertEquals("expected <abc-xyz> to end with <ax->", e.getMessage());
        }
    }
}
