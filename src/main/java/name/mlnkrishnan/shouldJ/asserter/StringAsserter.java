package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

public class StringAsserter extends ObjectAsserter<String> {
    public StringAsserter(String actual) {
        super(actual);
    }

    public StringAsserter shouldContain(String expectedSubstring) {
        if (actual == null)
            throw new ActualValueIsNull();
        if (!actual.contains(expectedSubstring)) {
            throw new ExpectationMismatch(String.format("expected <%s> to contain <%s>", actual, expectedSubstring));
        }
        return this;
    }


    public StringAsserter shouldStartWith(String expectedPrefix) {
        if (actual == null)
            throw new ActualValueIsNull();
        if (!actual.startsWith(expectedPrefix)) {
            throw new ExpectationMismatch(String.format("expected <%s> to start with <%s>", actual, expectedPrefix));
        }
        return this;
    }

    public StringAsserter shouldEndWith(String expectedSuffix) {
        if (actual == null)
            throw new ActualValueIsNull();
        if (!actual.endsWith(expectedSuffix)) {
            throw new ExpectationMismatch(String.format("expected <%s> to end with <%s>", actual, expectedSuffix));
        }
        return this;
    }
}
