package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionAsserter<T> extends ObjectAsserter<Collection<T>> {
    final protected Collection<T> actual;
    private final String type;


    public CollectionAsserter(Collection<T> collection) {
        super(collection);

        this.actual = collection;
        this.type = "collection";
    }

    public CollectionAsserter(Collection<T> collection, String type) {
        super(collection);
        this.actual = collection;
        this.type = type;
    }

    public PositionalAsserter<T> shouldHave(T expected) {
        if (actual == null)
            throw new ActualValueIsNull();
        Iterator<T> iterator = actual.iterator();
        long pos = 0;
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (obj.equals(expected))
                return new PositionalAsserter<T>(pos, obj, actual);
            pos++;
        }
        throw new ExpectationMismatch(String.format("did not find expected item <%s> in the %s", expected, type));
    }

    public PositionalAsserter<T> shouldHave(P<T> predicate) {
        if (actual == null)
            throw new ActualValueIsNull();
        Iterator<T> iterator = actual.iterator();
        long pos = 0;
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (predicate.is(obj))
                return new PositionalAsserter<T>(pos, obj, actual);
            pos++;
        }
        throw new ExpectationMismatch(String.format("did not find expected item in the %s", type));
    }

    public CollectionAsserter<T> shouldNotHave(T notExpected) {
        if (actual == null)
            throw new ActualValueIsNull();
        Iterator<T> iterator = actual.iterator();
        long pos = 0;
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (obj.equals(notExpected))
                throw new ExpectationMismatch(String.format("found unwanted item <%s> in the %s, at position <%d>", notExpected, type, pos));
            pos++;
        }
        return this;
    }

    public CollectionAsserter<T> shouldNotHave(P<T> predicate) {
        if (actual == null)
            throw new ActualValueIsNull();
        Iterator<T> iterator = actual.iterator();
        long pos = 0;
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (predicate.is(obj))
                throw new ExpectationMismatch(String.format("found unwanted item <%s> in the %s, at position <%d>", obj, type, pos));
            pos++;
        }
        return this;
    }

    public CollectionAsserter<T> shouldBeOfSize(long expectedSize) {
        if (actual == null)
            throw new ActualValueIsNull();
        int actualSize = actual.size();
        if (expectedSize != actualSize)
            throw new ExpectationMismatch(String.format("expected %s to be of size <%d>, but was of size <%d>", type, expectedSize, actualSize));
        return this;
    }

    public CollectionAsserter<T> shouldBeEmpty() {
        if (actual == null)
            throw new ActualValueIsNull();
        int actualSize = actual.size();
        if (actualSize != 0)
            throw new ExpectationMismatch(String.format("expected %s to be empty, but was of size <%d>", type, actualSize));
        return this;
    }

    public CollectionAsserter<T> shouldNotBeEmpty() {
        if (actual == null)
            throw new ActualValueIsNull();
        int actualSize = actual.size();
        if (actualSize == 0)
            throw new ExpectationMismatch(String.format("expected non empty %s, but was empty", type));
        return this;
    }

    public CollectionAsserter<T> shouldHaveAll(T... all) {
        if (actual == null)
            throw new ActualValueIsNull();

        Collection<T> missing = new ArrayList<T>();

        for (T t : all) {
            try {
                shouldHave(t);
            } catch (ExpectationMismatch e) {
                missing.add(t);
            }
        }

        if(missing.size() > 0)
            throw new ExpectationMismatch(String.format("did not find expected item(s) %s in the collection", missing));
        return this;
    }

    public CollectionAsserter<T> shouldNotHaveAny(T... all) {
        if (actual == null)
            throw new ActualValueIsNull();

        Collection<T> found = new ArrayList<T>();

        for (T t : all) {
            try {
                shouldNotHave(t);
            } catch (ExpectationMismatch e) {
                found.add(t);
            }
        }

        if(found.size() > 0)
            throw new ExpectationMismatch(String.format("found unwanted item(s) %s in the collection", found));
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
