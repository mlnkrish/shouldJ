package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static name.mlnkrishnan.shouldJ.ShouldJ.it;
import static org.junit.Assert.fail;

public class CollectionAsserterTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldHave_Pass() {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        it(integers).shouldHave(1);
        it(integers).shouldHave(2).shouldHave(3);
    }

    @Test
    public void shouldHave_Fail() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        try {
            it(integers).shouldHave(4);
            fail();
        } catch (Throwable e) {
            assertEquals("did not find expected item <4> in the collection " + integers.toString(), e.getMessage());
        }
    }

    @Test
    public void shouldHave_Predicate_Pass() {
        List<DummyType> dummyTypes = Arrays.asList(new DummyType(1, 'a'), new DummyType(2, 'b'));

        it(dummyTypes)
                .shouldHave(new P<DummyType>() {
                    @Override
                    public boolean is(DummyType obj) {
                        return obj.aNum == 1;
                    }
                })
                .shouldHave(new P<DummyType>() {
                    @Override
                    public boolean is(DummyType obj) {
                        return obj.aChar == 'b';
                    }
                });
    }

    @Test
    public void shouldHave_Predicate_Fail() {
        List<DummyType> dummyTypes = Arrays.asList(new DummyType(1, 'a'), new DummyType(2, 'b'));
        try {
            it(dummyTypes)
                    .shouldHave(new P<DummyType>() {
                        @Override
                        public boolean is(DummyType obj) {
                            return obj.aNum == 3;
                        }
                    });
            fail();
        } catch (Throwable e) {
            assertEquals("did not find expected item in the collection " + dummyTypes.toString(), e.getMessage());
        }
    }

    @Test
    public void shouldNotHave_Pass() {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        it(integers).shouldNotHave(5);
        it(integers).shouldNotHave(6).shouldNotHave(7);
    }

    @Test
    public void shouldNotHave_Fail() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        try {
            it(integers).shouldNotHave(2);
            fail();
        } catch (Throwable e) {
            assertEquals("found unwanted item <2> in the collection " + integers.toString() + ", at position <1>", e.getMessage());
        }
    }

    @Test
    public void shouldNotHave_Predicate_Pass() {
        List<DummyType> dummyTypes = Arrays.asList(new DummyType(1, 'a'), new DummyType(2, 'b'));

        it(dummyTypes)
                .shouldNotHave(new P<DummyType>() {
                    @Override
                    public boolean is(DummyType obj) {
                        return obj.aNum == 3;
                    }
                })
                .shouldNotHave(new P<DummyType>() {
                    @Override
                    public boolean is(DummyType obj) {
                        return obj.aChar == 'K';
                    }
                });
    }

    @Test
    public void shouldNotHave_Predicate_Fail() {
        DummyType unwanted = new DummyType(1, 'a');
        List<DummyType> dummyTypes = Arrays.asList(unwanted, new DummyType(2, 'b'));
        try {
            it(dummyTypes)
                    .shouldNotHave(new P<DummyType>() {
                        @Override
                        public boolean is(DummyType obj) {
                            return obj.aNum == 3;
                        }
                    })
                    .shouldNotHave(new P<DummyType>() {
                        @Override
                        public boolean is(DummyType obj) {
                            return obj.aNum == 1;
                        }
                    });
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("found unwanted item <%s> in the collection " + dummyTypes.toString() + ", at position <%d>", unwanted, 0), e.getMessage());
        }
    }

    @Test
    public void shouldBeOfSize_Pass() {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        it(integers).shouldBeOfSize(3);
    }

    @Test
    public void shouldBeOfSize_Fail() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        try {
            it(integers).shouldBeOfSize(4);
            fail();
        } catch (Throwable e) {
            assertEquals("expected collection " + integers.toString() + " to be of size <4>, but was of size <3>", e.getMessage());
        }
    }

    @Test
    public void shouldBeEmpty_Pass() {
        List<Integer> integers = new ArrayList<Integer>();

        it(integers).shouldBeEmpty();
    }

    @Test
    public void shouldBeEmpty_Fail() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        try {
            it(integers).shouldBeEmpty();
            fail();
        } catch (Throwable e) {
            assertEquals("expected collection to be empty, but was of size <3>", e.getMessage());
        }
    }

    @Test
    public void shouldNotBeEmpty_Pass() {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        it(integers).shouldNotBeEmpty();
    }

    @Test
    public void shouldNotBeEmpty_Fail() {
        List<Integer> integers = new ArrayList<Integer>();
        try {
            it(integers).shouldNotBeEmpty();
            fail();
        } catch (Error e) {
            assertEquals("expected non empty collection, but was empty", e.getMessage());
        }
    }

    @Test
    public void shouldHave_Pos__Pass() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 5);

        it(integers)
                .shouldHave(3).at(2)
                .shouldNotHave(4)
                .shouldHave(1).at(0)
                .shouldHave(2).at(1)
                .shouldHave(5);

    }

    @Test
    public void shouldHave_Pos_Fail() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        try {
            it(integers).shouldHave(1).at(2);
            fail();
        } catch (Error e) {
            assertEquals("expected <1> at position <2> but was at <0>", e.getMessage());
        }
    }

    @Test
    public void shouldHaveAll_Pass() {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        it(integers).shouldHaveAll(2, 3);
    }

    @Test
    public void shouldHaveAll_Fail() {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        expectedException.expectMessage("did not find expected item(s) [5, 4] in the collection");
        expectedException.expect(ExpectationMismatch.class);
        it(integers).shouldHaveAll(2, 5, 4);
    }

    @Test
    public void shouldNotHaveAny_Pass() {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        it(integers).shouldNotHaveAny(4, 5);
    }

    @Test
    public void shouldNotHaveAny_Fail() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        expectedException.expectMessage("found unwanted item(s) [5, 4] in the collection");
        expectedException.expect(ExpectationMismatch.class);
        it(integers).shouldNotHaveAny(5, 4, 6);
    }


    class DummyType {
        public int aNum;
        public char aChar;

        public DummyType(int a, char b) {
            this.aNum = a;
            this.aChar = b;
        }

        @Override
        public String toString() {
            return "DummyType{" +
                    "aNum=" + aNum +
                    ", aChar=" + aChar +
                    '}';
        }
    }
}
