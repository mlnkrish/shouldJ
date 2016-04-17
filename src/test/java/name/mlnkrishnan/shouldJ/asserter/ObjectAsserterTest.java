package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static name.mlnkrishnan.shouldJ.ShouldJ.it;
import static org.hamcrest.Matchers.equalTo;

public class ObjectAsserterTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    @Test
    public void shouldMatchMatcher_Pass() {
        it("123").shouldMatch(Is.is(equalTo("123")));
    }

    @Test
    public void shouldMatchMatcher_Fail() {
        try {
            it("123").shouldMatch(Is.is(equalTo("456")));
            fail();
        } catch (Throwable e) {
            assertEquals("is \"456\", but was <123>", e.getMessage());
        }
    }


    @Test
    public void shouldBe_Pass_For_Primitive_Arrays() {
        int[] array = {10, 15};
        int[] expected = {10, 15};

        it(array).shouldBe(expected);
    }

    @Test
    public void shouldBe_Fail_For_Primitive_Arrays() {
        int[] array = {10, 15};
        int[] expected = {10, 15, 20};

        expectedException.expect(ExpectationMismatch.class);
        expectedException.expectMessage("expected array <[10,15]> to be <[10,15,20]>");
        it(array).shouldBe(expected);
    }


    class DummyType {
    }
}
