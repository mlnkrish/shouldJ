package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;
import org.hamcrest.Matcher;

public class BooleanAsserter {
    final private Boolean actual;

    public BooleanAsserter(Boolean actual) {
        this.actual = actual;
    }

    public boolean shouldBeTrue() {
        if (actual == null)
            throw new ActualValueIsNull();
        if (!actual)
            throw new ExpectationMismatch("expected <true>, but was <false>");
        return true;
    }

    public boolean shouldBeFalse() {
        if (actual == null)
            throw new ActualValueIsNull();
        if (actual)
            throw new ExpectationMismatch("expected <false>, but was <true>");
        return true;
    }

    public <T> boolean shouldMatch(Matcher<T> matcher) {
        boolean matches = matcher.matches(actual);

        if (!matches) {
            throw new ExpectationMismatch(matcher.toString() + ", but was <" + actual + ">");
        }
        return true;
    }
}
