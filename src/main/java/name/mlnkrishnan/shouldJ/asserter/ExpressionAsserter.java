package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

public class ExpressionAsserter<R> {
    final private E<R> givenExpression;

    public ExpressionAsserter(E<R> givenExpression) {
        this.givenExpression = givenExpression;
    }

    public boolean shouldReturn(R expectedReturnValue) {
        R actualReturnValue = givenExpression.perform();
        if (expectedReturnValue == null || actualReturnValue == null) {
            if (!(expectedReturnValue == actualReturnValue))
                throw new ExpectationMismatch(String.format("expected return value to be <%s>, but was <%s>", expectedReturnValue, actualReturnValue));
            return true;
        }
        if (!(expectedReturnValue.equals(actualReturnValue)))
            throw new ExpectationMismatch(String.format("expected return value to be <%s>, but was <%s>", expectedReturnValue, actualReturnValue));
        return true;
    }

    public <T extends Throwable> ThrownExceptionAsserter<T> shouldThrow(Class<T> expectedThrowable) {
        try {
            givenExpression.perform();
        } catch (Throwable e) {
            Class<? extends Throwable> actualThrowableClass = e.getClass();
            if (actualThrowableClass.equals(expectedThrowable)) {
                return new ThrownExceptionAsserter<T>(e);
            }
            throw new ExpectationMismatch(String.format("expected throwable of type <%s>, but got <%s>", expectedThrowable, actualThrowableClass));
        }
        throw new ExpectationMismatch(String.format("expected <%s> to be thrown, but got none", expectedThrowable));
    }

    class ThrownExceptionAsserter<T extends Throwable>{
        private final ThrowableAsserter<Throwable> throwableThrowableAsserter;

        public ThrownExceptionAsserter(Throwable e) {
            throwableThrowableAsserter = new ThrowableAsserter<Throwable>(e);
        }
        
        public ThrownExceptionAsserter<T> withMessage(String expectedMessage){
            throwableThrowableAsserter.shouldHaveMessage(expectedMessage);
            return this;
        }
    }
}
