bukkit-statsd-client
====================

[![Build Status](https://ci.minotopia.me/buildStatus/icon?job=public~bukkit-statsd-client)](https://ci.minotopia.me/job/public~bukkit-statsd-client)

A statsd client library implemented in Java. Allows for Bukkit plugins to easily communicate with statsd. 
Fork of [tim-group/java-statsd-client](https://github.com/tim-group/java-statsd-client).

Downloads
---------
The client jar is distributed via a custom Maven repository.
```xml
<repositories>
  <repository>
    <id>xxyy-repo</id>
    <url>https://repo.l1t.li/public-all/</url>
  </repository>
</repositories>
<dependencies>
  <dependency>
    <groupId>me.minotopia</groupId>
    <artifactId>bukkit-statsd-client</artifactId>
    <version>1.0.1</version>
  </dependency>
</dependencies>
```

Usage
-----
```java
import com.timgroup.statsd.StatsDClient;
import com.timgroup.statsd.NonBlockingStatsDClient;

public class Foo {
  private static final StatsDClient statsd = new NonBlockingStatsDClient("my.prefix", "statsd-host", 8125);

  public static final void main(String[] args) {
    statsd.incrementCounter("bar");
    statsd.recordGaugeValue("baz", 100);
    statsd.recordExecutionTime("bag", 25);
    statsd.recordSetEvent("qux", "one");
  }
}
```

