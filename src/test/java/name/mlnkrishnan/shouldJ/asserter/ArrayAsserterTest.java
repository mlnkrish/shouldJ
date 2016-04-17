package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

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
        expectedException.expectMessage("found unwanted item <obj1> in the array " + Arrays.toString(array) + ", at position <0>");
        it(array).shouldNotHave("obj1");
    }

    @Test
    public void shouldHaveAt_Pass() {
        String[] array = {"obj1", "obj2"};

        it(array).shouldHave("obj1").at(0);
    }

    @Test
    public void shouldHaveAt_Fail() {
        String[] array = {"obj1", "obj2"};

        expectedException.expect(ExpectationMismatch.class);
        expectedException.expectMessage("expected <obj1> at position <1> but was at <0>");
        it(array).shouldHave("obj1").at(1);
    }

    @Test
    public void shouldBe_Pass() {
        String[] array = {"obj1", "obj2"};
        String[] expected = {"obj1", "obj2"};

        it(array).shouldBe(expected);
    }

    @Test
    public void shouldBe_Fail() {
        String[] array = {"obj1", "obj2"};
        String[] expected = {"obj1", "obj2", "obj3"};

        expectedException.expect(ExpectationMismatch.class);
        expectedException.expectMessage("expected array <[obj1, obj2]> to be <[obj1, obj2, obj3]>");
        it(array).shouldBe(expected);
    }
}
