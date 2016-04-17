package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;
import org.hamcrest.Matcher;

import java.lang.reflect.Array;

public class ObjectAsserter<T> {
    final protected T actual;

    public ObjectAsserter(T actual) {
        this.actual = actual;
    }

    public ObjectAsserter<T> shouldBe(T expected) {
        if (actual == null)
            throw new ActualValueIsNull();

        if (isArray(expected) && isArray(actual)) {
            if (!lengthsEqual(expected) || !elementsEqual(expected))
                throw new ExpectationMismatch(String.format("expected array <%s> to be <%s>", toArrayString(actual), toArrayString(expected)));
            return this;
        }

        if (!actual.equals(expected))
            throw new ExpectationMismatch(String.format("expected <%s>, but got <%s>", expected, actual));
        return this;
    }

    public ObjectAsserter<T> shouldNotBe(T notExpected) {
        if (actual.equals(notExpected))
            throw new ExpectationMismatch(String.format("expected not same"));
        return this;
    }

    public ObjectAsserter<T> shouldBeNull() {
        if (actual != null)
            throw new ExpectationMismatch(String.format("expected <null>, but was <%s>", actual));
        return this;
    }

    public ObjectAsserter<T> shouldNotBeNull() {
        if (actual == null)
            throw new ExpectationMismatch("expected not null");
        return this;
    }

    public ObjectAsserter<T> shouldBeOfType(Class<?> expectedType) {
        if (actual == null)
            throw new ActualValueIsNull();
        Class<? extends Object> actualType = actual.getClass();
        if (!actualType.equals(expectedType))
            throw new ExpectationMismatch(String.format("expected type <%s>, but was <%s>", expectedType, actualType));
        return this;
    }

    public ObjectAsserter<T> shouldMatch(Matcher<T> matcher) {
        boolean matches = matcher.matches(actual);
        if (!matches) {
            throw new ExpectationMismatch(matcher.toString() + ", but was <" + actual + ">");
        }
        return this;
    }

    private boolean isArray(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.getClass().isArray();
    }

    private boolean lengthsEqual(Object expected) {
        return Array.getLength(expected) == Array.getLength(actual);
    }

    private boolean elementsEqual(Object expected) {
        for (int i = 0; i < Array.getLength(expected); ++i) {
            if (!Array.get(expected, i).equals(Array.get(actual, i))) {
                return false;
            }
        }
        return true;
    }

    private String toArrayString(Object expected) {
        String begin = "[";
        String delimiter = ",";
        String end = "]";

        StringBuilder arrayString = new StringBuilder(begin);
        boolean separate = false;
        for (int i = 0; i < Array.getLength(expected); ++i) {
            if (separate) {
                arrayString.append(delimiter);
            }
            arrayString.append(Array.get(expected, i));
            separate = true;
        }

        arrayString.append(end);

        return arrayString.toString();
    }
}
