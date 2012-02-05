package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

public class BooleanAsserter {
    final private Boolean actual;

    public BooleanAsserter(Boolean actual) {
        this.actual = actual;
    }
    
    public boolean shouldBeTrue(){
        if(!actual)
            throw new ExpectationMismatch("expected <true>, but was <false>");
        return true;
    }

    public boolean shouldBeFalse(){
        if(actual)
            throw new ExpectationMismatch("expected <false>, but was <true>");
        return true;
    }
}
