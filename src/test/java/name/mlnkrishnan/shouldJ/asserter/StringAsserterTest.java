package name.mlnkrishnan.shouldJ.asserter;

import org.junit.Test;

import static name.mlnkrishnan.shouldJ.ShouldJ.it;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

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
            assertEquals("expected <abc> to contain <cb>",e.getMessage());
        }
    }
}
