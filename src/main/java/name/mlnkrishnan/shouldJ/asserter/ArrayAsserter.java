package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

import java.util.Arrays;
import java.util.List;

public class ArrayAsserter<T> extends ObjectAsserter<Object> {
    private T[] actual;

    public ArrayAsserter(T[] array) {
        super(array);
        this.actual = array;
    }

    public ArrayAsserter<T> shouldBeOfLength(int length) {
        assertNotNull();

        if (actual.length != length)
            throw new ExpectationMismatch(String.format("expected array <%s> to be of length <%d>", Arrays.toString(actual), length));

        return this;
    }

    public ArrayAsserter<T> shouldHave(T obj) {
        assertNotNull();

        List<T> actualAsList = Arrays.asList(actual);
        new CollectionAsserter<T>(actualAsList, "array").shouldHave(obj);
        return this;
    }

    public ArrayAsserter<T> shouldNotHave(T obj) {
        assertNotNull();

        List<T> actualAsList = Arrays.asList(actual);
        new CollectionAsserter<T>(actualAsList, "array").shouldNotHave(obj);
        return this;
    }

    private void assertNotNull() {
        if (actual == null)
            throw new ActualValueIsNull();
    }
}
