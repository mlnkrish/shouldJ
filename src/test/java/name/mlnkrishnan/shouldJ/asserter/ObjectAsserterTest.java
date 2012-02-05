package name.mlnkrishnan.shouldJ.asserter;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static name.mlnkrishnan.shouldJ.ShouldJ.it;

public class ObjectAsserterTest {

    @Test
    public void shouldBe_Pass() {
        it(2).shouldBe(2);
    }

    @Test
    public void shouldBe_Fail() {
        try {
            it(2).shouldBe(5);
            fail();
        } catch (Throwable e) {
            assertEquals("expected <5>, but got <2>", e.getMessage());
        }
    }

    @Test
    public void shouldNotBe_Pass() {
        it(2).shouldNotBe(5);
    }

    @Test
    public void shouldNotBe_Fail() {
        try {
            it(2).shouldNotBe(2);
            fail();
        } catch (Throwable e) {
            assertEquals("expected not same", e.getMessage());
        }
    }

    @Test
    public void shouldBeNull_Pass() {
        Object obj = null;
        it(obj).shouldBeNull();
    }

    @Test
    public void shouldBeNull_Fail() {
        Object obj = new Object();
        try {
            it(obj).shouldBeNull();
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("expected <null>, but was <%s>", obj), e.getMessage());
        }
    }

    @Test
    public void shouldNotBeNull_Pass() {
        it(new Object()).shouldNotBeNull();
    }

    @Test
    public void shouldNotBeNull_Fail() {
        try {
            Object obj = null;
            it(obj).shouldNotBeNull();
            fail();
        } catch (Throwable e) {
            assertEquals("expected not null", e.getMessage());
        }
    }

    @Test
    public void shouldBeOfType_Pass() {
        DummyType obj = new DummyType();
        it(obj).shouldBeOfType(DummyType.class);
    }

    @Test
    public void shouldBeOfType_Fail() {
        try {
            it("").shouldBeOfType(DummyType.class);
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("expected type <%s>, but was <%s>", DummyType.class, String.class), e.getMessage());
        }
    }

    class DummyType {
    }
}
