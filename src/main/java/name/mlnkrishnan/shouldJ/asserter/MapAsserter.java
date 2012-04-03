package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

import java.util.Map;

public class MapAsserter<K, V> extends ObjectAsserter<Map<K, V>> {
    final protected Map<K, V> actual;

    public MapAsserter(Map<K, V> actual) {
        super(actual);
        this.actual = actual;
    }

    public MapValueAsserter<K, V> shouldHaveKey(K expectedKey) {
        if(actual == null)
            throw new ActualValueIsNull();
        if (!actual.containsKey(expectedKey))
            throw new ExpectationMismatch(String.format("expected map to contain key <%s>, but was not present", expectedKey));

        return new MapValueAsserter<K, V>(expectedKey, actual);
    }

    public class MapValueAsserter<K, V> extends MapAsserter<K,V>{
        final private K key;

        public MapValueAsserter(K key, Map<K, V> actual) {
            super(actual);
            this.key = key;
        }

        public MapAsserter<K,V> withValue(V expectedValue){
            V actualValue = actual.get(key);
            if(!actualValue.equals(expectedValue))
                throw new ExpectationMismatch(String.format("expected key <%s> to have value <%s>, but the value was <%s>", key, expectedValue, actualValue));
            return new MapAsserter<K, V>(actual);
        }
    }
}
