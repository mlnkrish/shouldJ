package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

import java.util.Collection;
import java.util.Iterator;

public class CollectionAsserter<T> extends ObjectAsserter<Collection<T>> {
    final protected Collection<T> actual;

    public CollectionAsserter(Collection<T> collection) {
        super(collection);
        this.actual = collection;
    }

    public PositionalAsserter<T> shouldHave(T expected) {
        if(actual == null)
            throw new ActualValueIsNull();
        Iterator<T> iterator = actual.iterator();
        long pos = 0;
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (obj.equals(expected))
                return new PositionalAsserter<T>(pos, obj, actual);
            pos++;
        }
        throw new ExpectationMismatch(String.format("did not find expected item <%s> in the collection", expected));
    }

    public PositionalAsserter<T> shouldHave(P<T> predicate) {
        if(actual == null)
            throw new ActualValueIsNull();
        Iterator<T> iterator = actual.iterator();
        long pos = 0;
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (predicate.is(obj))
                return new PositionalAsserter<T>(pos, obj, actual);
            pos++;
        }
        throw new ExpectationMismatch(String.format("did not find expected item in the collection"));
    }

    public CollectionAsserter<T> shouldNotHave(T notExpected) {
        if(actual == null)
            throw new ActualValueIsNull();
        Iterator<T> iterator = actual.iterator();
        long pos = 0;
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (obj.equals(notExpected))
                throw new ExpectationMismatch(String.format("found unwanted item <%s> in the collection, at position <%d>", notExpected, pos));
            pos++;
        }
        return this;
    }

    public CollectionAsserter<T> shouldNotHave(P<T> predicate) {
        if(actual == null)
            throw new ActualValueIsNull();
        Iterator<T> iterator = actual.iterator();
        long pos = 0;
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (predicate.is(obj))
                throw new ExpectationMismatch(String.format("found unwanted item <%s> in the collection, at position <%d>", obj, pos));
            pos++;
        }
        return this;
    }

    public CollectionAsserter<T> shouldBeOfSize(long expectedSize){
        if(actual == null)
            throw new ActualValueIsNull();
        int actualSize = actual.size();
        if(expectedSize != actualSize)
            throw new ExpectationMismatch(String.format("expected collection to be of size <%d>, but was of size <%d>", expectedSize, actualSize));
        return this;
    }

    public CollectionAsserter<T> shouldBeEmpty(){
        if(actual == null)
            throw new ActualValueIsNull();
        int actualSize = actual.size();
        if(actualSize != 0)
            throw new ExpectationMismatch(String.format("expected collection to be empty, but was of size <%d>", actualSize));
        return this;
    }

    public CollectionAsserter<T> shouldNotBeEmpty(){
        if(actual == null)
            throw new ActualValueIsNull();
        int actualSize = actual.size();
        if(actualSize == 0)
            throw new ExpectationMismatch(String.format("expected non empty collection, but was empty"));
        return this;
    }
    

    public class PositionalAsserter<T> extends CollectionAsserter<T> {
        final private long actualPosition;
        final private T item;

        public PositionalAsserter(long actualPosition, T item, Collection<T> actual) {
            super(actual);
            this.actualPosition = actualPosition;
            this.item = item;
        }

        public CollectionAsserter<T> at(long expectedPosition) {
            if (actualPosition != expectedPosition) {
                throw new ExpectationMismatch(String.format("expected <%s> at position <%d> but was at <%d>", item, expectedPosition, actualPosition));
            }
            return new CollectionAsserter<T>(actual);
        }
    }
}
