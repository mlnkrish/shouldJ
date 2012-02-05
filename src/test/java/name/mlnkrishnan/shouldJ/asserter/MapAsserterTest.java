package name.mlnkrishnan.shouldJ.asserter;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static name.mlnkrishnan.shouldJ.ShouldJ.it;

public class MapAsserterTest {

    @Test
    public void shouldHaveKey_Pass() {
        Map<String, Object> stringObjectMap = new HashMap<String, Object>() {{
            put("key1", new Object());
            put("key2", new Object());
        }};

        it(stringObjectMap).shouldHaveKey("key1").shouldHaveKey("key2");
    }

    @Test
    public void shouldHaveKey_Fail() {
        Map<String, Object> stringObjectMap = new HashMap<String, Object>() {{
            put("key1", new Object());
            put("key2", new Object());
        }};

        try {
            it(stringObjectMap).shouldHaveKey("key1").shouldHaveKey("key4");
            fail();
        } catch (Throwable e) {
            assertEquals("expected map to contain key <key4>, but was not present", e.getMessage());
        }
    }

    @Test
    public void shouldHaveKey_Value_Pass() {
        final Object value1 = new Object();
        final Object value2 = new Object();
        Map<String, Object> stringObjectMap = new HashMap<String, Object>() {{
            put("key1", value1);
            put("key2", value2);
        }};

        it(stringObjectMap)
                .shouldHaveKey("key1").withValue(value1)
                .shouldHaveKey("key2").withValue(value2);
    }

    @Test
    public void shouldHaveKey_Value_Fail() {
        final Object value1 = new Object();
        final Object value2 = new Object();
        final Object notInMap = new Object();
        Map<String, Object> stringObjectMap = new HashMap<String, Object>() {{
            put("key1", value1);
            put("key2", value2);
        }};

        try {
            it(stringObjectMap)
                    .shouldHaveKey("key1").withValue(value1)
                    .shouldHaveKey("key2").withValue(notInMap);
            fail();
        } catch (Throwable e) {
            assertEquals(String.format("expected key <%s> to have value <%s>, but the value was <%s>", "key2", notInMap, value2), e.getMessage());
        }
    }
}
