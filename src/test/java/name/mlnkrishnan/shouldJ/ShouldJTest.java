package name.mlnkrishnan.shouldJ;

import name.mlnkrishnan.shouldJ.asserter.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static name.mlnkrishnan.shouldJ.ShouldJ.it;
import static org.junit.Assert.assertEquals;

public class ShouldJTest {

    @Test
    public void shouldReturnObjectAsserter() {
        assertEquals(ObjectAsserter.class, it(new Object()).getClass());

        DummyType dummyType = null;
        assertEquals(ObjectAsserter.class, it(dummyType).getClass());
    }

    @Test
    public void shouldReturnCollectionAsserter() {
        assertEquals(CollectionAsserter.class, it(new ArrayList<Object>()).getClass());
    }

    @Test
    public void shouldReturnBooleanAsserter() {
        assertEquals(BooleanAsserter.class, it(true).getClass());

        Boolean bool = false;
        assertEquals(BooleanAsserter.class, it(bool).getClass());
    }

    @Test
    public void shouldReturnMapAsserter() {
        assertEquals(MapAsserter.class, it(new TreeMap<String,Object>()).getClass());
    }

    @Test
    public void shouldReturnStringAsserter() {
        assertEquals(StringAsserter.class, it("foo").getClass());
    }

    @Test
    public void shouldReturnTypeAsserter() {
        assertEquals(TypeAsserter.class, it(Integer.class).getClass());
    }

    @Test
    public void shouldReturnThrowableAsserter() {
        assertEquals(ThrowableAsserter.class, it(new Error()).getClass());
    }

    @Test
    public void shouldReturnExpressionAsserter() {
        assertEquals(ExpressionAsserter.class, it(new E() {
            @Override
            public void perform() {
                //something
            }
        }).getClass());
    }

    @Test
    public void shouldReturnNumberAsserter() {
        byte b = 1;
        short s = 1;
        int i =1;
        long l = 2L;
        float f = 5f;
        double d = 5d;
        AtomicInteger atomicInteger = new AtomicInteger(i);
        AtomicLong atomicLong = new AtomicLong(l);
        BigInteger bigInteger= new BigInteger("1");
        BigDecimal bigDecimal = new BigDecimal(l);

        assertEquals(NumberAsserter.class, it(b).getClass());
        assertEquals(NumberAsserter.class, it(s).getClass());
        assertEquals(NumberAsserter.class, it(i).getClass());
        assertEquals(NumberAsserter.class, it(l).getClass());
        assertEquals(NumberAsserter.class, it(f).getClass());
        assertEquals(NumberAsserter.class, it(d).getClass());
        assertEquals(NumberAsserter.class, it(atomicInteger).getClass());
        assertEquals(NumberAsserter.class, it(atomicLong).getClass());
        assertEquals(NumberAsserter.class, it(bigInteger).getClass());
        assertEquals(NumberAsserter.class, it(bigDecimal).getClass());
    }

    class DummyType {
    }
}


