Fluent unit testing for java.

Add the following to your pom.xml
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
        <version>1.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>

See unit tests in this project for usage.
