package name.mlnkrishnan.shouldJ;

import name.mlnkrishnan.shouldJ.asserter.*;

import java.util.Collection;
import java.util.Map;

public class ShouldJ {

    public static <T> ObjectAsserter<T> it(T anObj) {
        return new ObjectAsserter<T>(anObj);
    }

    public static <T> CollectionAsserter<T> it(Collection<T> aCollection) {
        return new CollectionAsserter<T>(aCollection);
    }

    public static  BooleanAsserter it(Boolean aBoolean) {
        return new BooleanAsserter(aBoolean);
    }

    public static <T extends Number> NumberAsserter<T> it(T aNumber) {
        return new NumberAsserter<T>(aNumber);
    }

    public static <K,V> MapAsserter<K,V> it(Map<K,V> aMap) {
        return new MapAsserter<K, V>(aMap);
    }

    public static <T extends Throwable> ThrowableAsserter<T> it(T aThrowable) {
        return new ThrowableAsserter<T>(aThrowable);
    }
}
