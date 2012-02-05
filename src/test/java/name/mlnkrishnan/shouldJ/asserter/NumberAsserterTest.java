package name.mlnkrishnan.shouldJ.asserter;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static name.mlnkrishnan.shouldJ.ShouldJ.it;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NumberAsserterTest {

    @Test
    public void shouldBeGreaterThan_Pass() {
        it(1).shouldBeGreaterThan(0);
        it(1.1).shouldBeGreaterThan(1.0);
        it(1.1f).shouldBeGreaterThan(1.0f);
        it(new BigDecimal("10.455634575788")).shouldBeGreaterThan(new BigDecimal("10.455634575786"));
        it(new BigInteger("10455634575788")).shouldBeGreaterThan(new BigInteger("10455634575786"));
    }

    @Test
    public void shouldBeGreaterThan_Fail() {
        try {
            it(1).shouldBeGreaterThan(1);
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("expected <1> to be greater than <1>"), e.getMessage());
        }
    }

    @Test
    public void shouldBeGreaterThanOrEqualTo_Pass() {
        it(1).shouldBeGreaterThanOrEqualTo(1);
        it(1.1).shouldBeGreaterThanOrEqualTo(1.0);
    }

    @Test
    public void shouldBeGreaterThanOrEqualTo_Fail() {
        try {
            it(1.456).shouldBeGreaterThanOrEqualTo(1.457);
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("expected <1.456> to be greater than or equal to <1.457>"), e.getMessage());
        }
    }

    @Test
    public void shouldBeLesserThan_Pass() {
        it(1).shouldBeLesserThan(2);
    }

    @Test
    public void shouldBeLesserThan_Fail() {
        try {
            it(1).shouldBeLesserThan(0);
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("expected <1> to be lesser than <0>"), e.getMessage());
        }
    }

    @Test
    public void shouldBeLesserThanOrEqualTo_Pass() {
        it(new BigDecimal("123.45733688")).shouldBeLesserThanOrEqualTo(new BigDecimal("123.45733688"));
        it(new BigDecimal("123.45733688")).shouldBeLesserThanOrEqualTo(new BigDecimal("123.45733689"));
    }

    @Test
    public void shouldBeLesserThanOrEqualTo_Fail() {
        try {
            it(1.4563f).shouldBeLesserThanOrEqualTo(1.3667f);
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("expected <1.4563> to be lesser than or equal to <1.3667>"), e.getMessage());
        }
    }
}
