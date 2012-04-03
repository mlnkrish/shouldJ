package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

import java.lang.annotation.Annotation;

public class TypeAsserter extends ObjectAsserter<Class>{
    public TypeAsserter(Class actual) {
        super(actual);
    }
    
    public TypeAsserter shouldHaveAnnotation(Class<? extends Annotation> expectedAnnotation){
        if(actual == null)
            throw new ActualValueIsNull();
       if(!actual.isAnnotationPresent(expectedAnnotation)){
           throw new ExpectationMismatch(String.format("expected annotation <%s> not applied to <%s>", expectedAnnotation, actual));
       } 
       return this;
    }
}
