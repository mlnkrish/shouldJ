package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static name.mlnkrishnan.shouldJ.ShouldJ.it;

public class ArrayAsserterTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldNotBeNull() {
        String[] array = {"obj1", "obj2"};

        it(array).shouldNotBeNull();
    }

    @Test
    public void shouldBeNull() {
        String[] array = null;

        it(array).shouldBeNull();
    }

    @Test
    public void shouldBeOfLength_Pass() {
        String[] array = {"obj1", "obj2"};

        it(array).shouldBeOfLength(2);
    }

    @Test
    public void shouldBeOfLength_Fail() {
        String[] array = {"obj1"};

        expectedException.expect(ExpectationMismatch.class);
        expectedException.expectMessage("expected array <[obj1]> to be of length <2>");
        it(array).shouldBeOfLength(2);
    }

    @Test
    public void shouldHave_Pass() {
        String[] array = {"obj1", "obj2"};

        it(array).shouldHave("obj1");
    }

    @Test
    public void shouldHave_Fail() {
        String[] array = {"obj1", "obj2"};

        expectedException.expect(ExpectationMismatch.class);
        expectedException.expectMessage("did not find expected item <invalid> in the array");
        it(array).shouldHave("invalid");
    }

    @Test
    public void shouldNotHave_Pass() {
        String[] array = {"obj1", "obj2"};

        it(array).shouldNotHave("invalid");
    }

    @Test
    public void shouldNotHave_Fail() {
        String[] array = {"obj1", "obj2"};

        expectedException.expect(ExpectationMismatch.class);
        expectedException.expectMessage("found unwanted item <obj1> in the array, at position <0>");
        it(array).shouldNotHave("obj1");
    }
}
