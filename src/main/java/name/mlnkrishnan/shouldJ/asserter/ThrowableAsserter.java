package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

public class ThrowableAsserter<T extends Throwable> extends ObjectAsserter<T>{
    private Throwable actual;

    public ThrowableAsserter(T actualThrowable) {
        super(actualThrowable);
        this.actual = actualThrowable;
    }
    
    public ThrowableAsserter<T> shouldHaveMessage(String expectedMessage){
        String actualMessage = actual.getMessage();
        if(!actualMessage.equals(expectedMessage)){
            throw new ExpectationMismatch(String.format("expected message to be <%s>, but was <%s>",expectedMessage,actualMessage));
        }
        return this;
    }
}
