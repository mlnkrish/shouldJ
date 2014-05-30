package name.mlnkrishnan.shouldJ;

import name.mlnkrishnan.shouldJ.asserter.*;

import java.util.Collection;
import java.util.Map;

public class ShouldJ {

    public static <T> ObjectAsserter<T> it(T actualObj) {
        return new ObjectAsserter<>(actualObj);
    }

    public static <T> ArrayAsserter<T> it(T[] actualObj) {
        return new ArrayAsserter<>(actualObj);
    }

    public static <T> CollectionAsserter<T> it(Collection<T> actualCollection) {
        return new CollectionAsserter<>(actualCollection);
    }

    public static BooleanAsserter it(Boolean actualBoolean) {
        return new BooleanAsserter(actualBoolean);
    }

    public static <T extends Number> NumberAsserter<T> it(T actualNumber) {
        return new NumberAsserter<>(actualNumber);
    }

    public static <T extends String> StringAsserter it(T actualString) {
        return new StringAsserter(actualString);
    }

    public static <K, V> MapAsserter<K, V> it(Map<K, V> actualMap) {
        return new MapAsserter<>(actualMap);
    }

    public static <T extends Throwable> ThrowableAsserter<T> it(T actualThrowable) {
        return new ThrowableAsserter<>(actualThrowable);
    }

    public static <T extends Class> TypeAsserter it(T actualType) {
        return new TypeAsserter(actualType);
    }

    public static ExpressionAsserter it(E anExpression) {
        return new ExpressionAsserter(anExpression);
    }
}
