# ShouldJ

Fluent unit testing for java.

## Using with Maven

Add the following to your pom.xml

```xml
<repositories>
    <repository>
        <id>mln-releases</id>
        <url>https://github.com/mlnkrish/mvn-repo/raw/master/releases</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>name.mlnkrishnan.shouldJ</groupId>
        <artifactId>shouldJ</artifactId>
        <version>1.6</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Basic Usage


```java
it(2).shouldBe(5);
it(2).shouldNotBe(5);

Object obj = null;
it(obj).shouldBeNull();

DummyType obj = new DummyType();
it(obj).shouldBeOfType(DummyType.class);

it(new Boolean(true)).shouldBeTrue();
it(false).shouldBeFalse();
```

## Collections

```java
List<Integer> integers = Arrays.asList(1, 2, 3, 5);
it(integers)
        .shouldHave(3).at(2)
        .shouldNotHave(4)
        .shouldHave(1).at(0)
        .shouldHave(2).at(1)
        .shouldHave(5);

List<DummyType> dummyTypes = Arrays.asList(new DummyType(1, 'a'), new DummyType(2, 'b'));

it(dummyTypes)
        .shouldNotHave(new P<DummyType>() {
            @Override
            public boolean is(DummyType obj) {
                return obj.aNum == 3;
            }
        })
        .shouldNotHave(new P<DummyType>() {
            @Override
            public boolean is(DummyType obj) {
                return obj.aChar == 'K';
            }
        });
```

## Maps
```java
final Object value1 = new Object();
final Object value2 = new Object();
Map<String, Object> stringObjectMap = new HashMap<String, Object>() {{
    put("key1", value1);
    put("key2", value2);
}};

it(stringObjectMap)
        .shouldHaveKey("key1").withValue(value1)
        .shouldHaveKey("key2").withValue(value2);
```

## Exceptions
```java
it(new E() {
    @Override
    public void perform() {
        throw new NumberFormatException("i cant count");
    }
}).shouldThrow(NumberFormatException.class)
        .withMessage("i cant count");

```

See [unit tests](https://github.com/mlnkrish/shouldJ/tree/master/src/test/java/name/mlnkrishnan/shouldJ/asserter) in this project for full api.

