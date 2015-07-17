Motif
=====

A pattern matching library for Java 8. Motif provides scala-like pattern matching in Java 8.

## Examples

### FizzBuzz

```java
IntStream.range(0, 101).forEach(
    n -> System.out.println(
        match(Tuple2.of(n % 3, n % 5))
            .when(tuple2(eq(0), eq(0))).get(() -> "FizzBuzz")
            .when(tuple2(eq(0), any())).get(y -> "Fizz")
            .when(tuple2(any(), eq(0))).get(x -> "Buzz")
            .orElse(String.valueOf(n))
            .getMatch()
    )
);
```

### Optional

```java
Optional<Person> personOpt = getPerson();
match(personOpt)
    .when(some(any())).then(person -> doStuff(person))
    .when(none()).then(() -> System.out.println("Person not found"))
    .doMatch();
```

### Factorial

```java
public long factorial(long i) {
  return match(i)
      .when(eq(0)).get(() -> 1l)
      .when(any()).get(x -> x * factorial(x - 1))
      .getMatch();
}
```

### Nested Matching

```java
Optional<Tuple2<String, String>> opt = Optional.of(Tuple2.of("first", "second"));
match(opt)
    .when(some(tuple2(eq("third"), any()))).then(b -> doStuff(b))
    .when(some(tuple2(any(), eq("second")))).then(a -> doStuff(a))
    .when(none()).then(() -> System.out.println("Tuple not found"))
    .doMatch();
```

### List Cons Matching

```java
List<String> list = Arrays.asList("a", "b", "c", "d");
match(list)
    .when(nil()).then(() -> System.out.println("Empty List"))
    .when(headNil(eq("b"))).then(() -> System.out.println("Singleton List of 'b'"))
    .when(headNil(any())).then(head -> System.out.println("Singleton List of " + head))
    .when(headTail(any(), any())).then(
        (head, tail) -> System.out.println("head: " + head + " Remaining: " + tail))
    .doMatch();
```

## Download

Download the latest JAR via Maven:

```xml
<dependency>
  <groupId>com.leacox.motif</groupId>
  <artifactId>motif</artifactId>
  <version>0.1</version>
</dependency>
```

## License

    Copyright 2015 John Leacox

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
