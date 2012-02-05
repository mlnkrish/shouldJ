package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

public class ObjectAsserter<T> {
    final protected T actual;

    public ObjectAsserter(T actual) {
        this.actual = actual;
    }

    public ObjectAsserter<T> shouldBe(T expected) {
        if(!actual.equals(expected))
           throw new ExpectationMismatch(String.format("expected <%s>, but got <%s>", expected, actual));
        return this;
    }

    public ObjectAsserter<T> shouldNotBe(T notExpected) {
        if(actual.equals(notExpected))
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
    
    public ObjectAsserter<T> shouldBeOfType(Class<?> expectedType){
        Class<? extends Object> actualType = actual.getClass();
        if(!actualType.equals(expectedType))
            throw new ExpectationMismatch(String.format("expected type <%s>, but was <%s>", expectedType, actualType));
        return this;
    }
}
