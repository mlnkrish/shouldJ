package name.mlnkrishnan.shouldJ.asserter;

import name.mlnkrishnan.shouldJ.failure.ActualValueIsNull;
import name.mlnkrishnan.shouldJ.failure.ExpectationMismatch;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class NumberAsserter<T extends Number> extends ObjectAsserter<T> {
    final private BigDecimal actualNumber;

    public NumberAsserter(T actualValue) {
        super(actualValue);
        this.actualNumber = getBigDecimalValue(actualValue);

    }

    private BigDecimal getBigDecimalValue(T actualValue) {
        if(actualValue == null)
            return null;
        if (actualValue instanceof Byte)
            return BigDecimal.valueOf((Byte) actualValue);
        if (actualValue instanceof Short)
            return BigDecimal.valueOf((Short) actualValue);
        if (actualValue instanceof Integer)
            return BigDecimal.valueOf((Integer) actualValue);
        if (actualValue instanceof Long)
            return BigDecimal.valueOf((Long) actualValue);
        if (actualValue instanceof Float)
            return BigDecimal.valueOf((Float) actualValue);
        if (actualValue instanceof Double)
            return BigDecimal.valueOf((Double) actualValue);
        if (actualValue instanceof AtomicInteger)
            return new BigDecimal(((AtomicInteger) actualValue).get());
        if (actualValue instanceof AtomicLong)
            return new BigDecimal(((AtomicLong) actualValue).get());
        if (actualValue instanceof BigInteger)
            return new BigDecimal((BigInteger) actualValue);
        if (actualValue instanceof BigDecimal)
            return (BigDecimal) actualValue;
        throw new IllegalArgumentException(String.format("cant find a conversion to big-decimal for type <%s>",actualValue.getClass()));
    }

    public NumberAsserter shouldBeGreaterThan(T expectedNumber) {
        if(actual == null)
            throw new ActualValueIsNull();
        BigDecimal expectedValue = getBigDecimalValue(expectedNumber);
        if (!(actualNumber.compareTo(expectedValue) == 1))
            throw new ExpectationMismatch(String.format("expected <%s> to be greater than <%s>", actual, expectedNumber));
        return this;
    }

    public NumberAsserter shouldBeLesserThan(T expectedNumber) {
        if(actual == null)
            throw new ActualValueIsNull();
        BigDecimal expectedValue = getBigDecimalValue(expectedNumber);
        if (!(actualNumber.compareTo(expectedValue) == -1))
            throw new ExpectationMismatch(String.format("expected <%s> to be lesser than <%s>", actual, expectedNumber));
        return this;
    }

    public NumberAsserter shouldBeGreaterThanOrEqualTo(T expectedNumber) {
        if(actual == null)
            throw new ActualValueIsNull();
        BigDecimal expectedValue = getBigDecimalValue(expectedNumber);
        if (actualNumber.compareTo(expectedValue) == -1)
            throw new ExpectationMismatch(String.format("expected <%s> to be greater than or equal to <%s>", actual, expectedNumber));
        return this;
    }

    public NumberAsserter shouldBeLesserThanOrEqualTo(T expectedNumber) {
        if(actual == null)
            throw new ActualValueIsNull();
        BigDecimal expectedValue = getBigDecimalValue(expectedNumber);
        if (actualNumber.compareTo(expectedValue) == 1)
            throw new ExpectationMismatch(String.format("expected <%s> to be lesser than or equal to <%s>", actual, expectedNumber));
        return this;
    }
}
