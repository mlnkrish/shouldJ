package name.mlnkrishnan.shouldJ.asserter;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static name.mlnkrishnan.shouldJ.ShouldJ.it;
import static org.junit.Assert.fail;

public class ThrowableAsserterTest {

    @Test
    public void shouldHaveMessage_Pass() {
        it(new Exception("message")).shouldHaveMessage("message");
    }

    @Test
    public void shouldHaveMessage_Fail() {
        try {
            it(new Exception("message")).shouldHaveMessage("message2");
            fail();
        } catch (Throwable t) {
            assertEquals(String.format("expected message to be <%s>, but was <%s>", "message2", "message"), t.getMessage());
        }
    }
}
