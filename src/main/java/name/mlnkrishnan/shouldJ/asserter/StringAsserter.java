package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

public class StringAsserter extends ObjectAsserter<String>{
    public StringAsserter(String actual) {
        super(actual);
    }
    
    public StringAsserter shouldContain(String expectedSubstring){
        if(actual == null)
            throw new ActualValueIsNull();
        if(!actual.contains(expectedSubstring)){
            throw new ExpectationMismatch(String.format("expected <%s> to contain <%s>",actual,expectedSubstring));
        }
        return this;
    }
    
}
