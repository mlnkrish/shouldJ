package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

public class ExpressionAsserter {
    final private E givenExpression;

    public ExpressionAsserter(E givenExpression) {
        this.givenExpression = givenExpression;
    }

    public <T extends Throwable> ThrownExceptionAsserter<T> shouldThrow(Class<T> expectedThrowable) {
        if(givenExpression == null)
            throw new ActualValueIsNull();
        try {
            givenExpression.perform();
        } catch (Throwable e) {
            Class<? extends Throwable> actualThrowableClass = e.getClass();
            if (actualThrowableClass.equals(expectedThrowable)) {
                return new ThrownExceptionAsserter<>(e);
            }
            throw new ExpectationMismatch(String.format("expected throwable of type <%s>, but got <%s>", expectedThrowable, actualThrowableClass));
        }
        throw new ExpectationMismatch(String.format("expected <%s> to be thrown, but got none", expectedThrowable));
    }

    class ThrownExceptionAsserter<T extends Throwable> {
        private final ThrowableAsserter<Throwable> throwableThrowableAsserter;

        public ThrownExceptionAsserter(Throwable e) {
            throwableThrowableAsserter = new ThrowableAsserter<>(e);
        }

        public ThrownExceptionAsserter<T> withMessage(String expectedMessage) {
            throwableThrowableAsserter.shouldHaveMessage(expectedMessage);
            return this;
        }
    }
}
