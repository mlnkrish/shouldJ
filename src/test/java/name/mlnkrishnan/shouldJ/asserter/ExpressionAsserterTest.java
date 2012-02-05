package name.mlnkrishnan.shouldJ.asserter;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static name.mlnkrishnan.shouldJ.ShouldJ.it;

public class ExpressionAsserterTest {

    @Test
    public void shouldReturn_Pass() {
        it(new E<Integer>() {
            @Override
            public Integer perform() {
                return 5;
            }
        }).shouldReturn(5);

        it(new E<Void>() {
            @Override
            public Void perform() {
                int k = 1;
                return null;
            }
        }).shouldReturn(null);
    }

    @Test
    public void shouldReturn_Fail() {
        try {
            it(new E<Integer>() {
                @Override
                public Integer perform() {
                    return 5;
                }
            }).shouldReturn(4);
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("expected return value to be <%s>, but was <%s>", 4, 5), e.getMessage());
        }
    }

    @Test
    public void shouldReturn_Fail_OnNull_1() {
        try {
            it(new E<Integer>() {
                @Override
                public Integer perform() {
                    return null;
                }
            }).shouldReturn(4);
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("expected return value to be <%s>, but was <%s>", 4, null), e.getMessage());
        }
    }

    @Test
    public void shouldReturn_Fail_OnNull_2() {
        try {
            it(new E<Integer>() {
                @Override
                public Integer perform() {
                    return 5;
                }
            }).shouldReturn(null);
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("expected return value to be <%s>, but was <%s>", null, 5), e.getMessage());
        }
    }

    @Test
    public void shouldThrow_Pass() {
        it(new E<Void>() {
            @Override
            public Void perform() {
                throw new NumberFormatException();
            }
        }).shouldThrow(NumberFormatException.class);
    }

    @Test
    public void shouldThrow_Message_Pass() {
        it(new E<Void>() {
            @Override
            public Void perform() {
                throw new NumberFormatException("i cant count");
            }
        }).shouldThrow(NumberFormatException.class)
                .withMessage("i cant count");
    }

    @Test
    public void shouldThrow_Message_Fail() {
        try {
            it(new E<Void>() {
                @Override
                public Void perform() {
                    throw new NumberFormatException("i cant count");
                }
            }).shouldThrow(NumberFormatException.class)
                    .withMessage("i cant count well");
            fail();
        } catch (Throwable t) {
            assertEquals(String.format("expected message to be <%s>, but was <%s>","i cant count well","i cant count"), t.getMessage());
        }
    }

    @Test
    public void shouldThrow_Fail_WrongException() {
        try {
            it(new E<Void>() {
                @Override
                public Void perform() {
                    throw new NumberFormatException();
                }
            }).shouldThrow(AbstractMethodError.class);
            fail();
        } catch (Throwable t) {
            assertEquals(String.format("expected throwable of type <%s>, but got <%s>", AbstractMethodError.class, NumberFormatException.class), t.getMessage());
        }
    }

    @Test
    public void shouldThrow_Fail_NoException() {
        try {
            it(new E<Void>() {
                @Override
                public Void perform() {
                    return null;
                }
            }).shouldThrow(AbstractMethodError.class);
            fail();
        } catch (Throwable t) {
            assertEquals(String.format("expected <%s> to be thrown, but got none", AbstractMethodError.class), t.getMessage());
        }
    }
}
