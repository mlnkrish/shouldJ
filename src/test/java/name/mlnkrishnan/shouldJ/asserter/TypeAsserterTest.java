package name.mlnkrishnan.shouldJ.asserter;

import org.junit.Test;

import static name.mlnkrishnan.shouldJ.ShouldJ.it;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TypeAsserterTest {
    @Test
    public void shouldHaveAnnotation_Pass() {
        it(Dummy.class).shouldHaveAnnotation(Deprecated.class);
    }

    @Test
    public void shouldHaveAnnotation_Fail() {
        try {
            it(Dummy.class).shouldHaveAnnotation(SuppressWarnings.class);
            fail();
        } catch (Throwable e) {
            assertEquals("expected annotation <interface java.lang.SuppressWarnings> not applied to <class name.mlnkrishnan.shouldJ.asserter.TypeAsserterTest$Dummy>", e.getMessage());
        }
    }
    
    @Deprecated
    class Dummy{
        
    }
}
