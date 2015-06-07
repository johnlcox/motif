Motif
=====

A pattern matching library for Java 8. Motif provides scala-like pattern matching in Java 8.

## Examples

### FizzBuzz

```java
IntStream.range(0, 101).forEach(
    n -> System.out.println(
        match(Tuple2.of(n % 3, n % 5))
            .when(caseTuple2(eq(0), eq(0))).get((x, y) -> "FizzBuzz")
            .when(caseTuple2(eq(0), any())).get((x, y) -> "Fizz")
            .when(caseTuple2(any(), eq(0))).get((x, y) -> "Buzz")
            .orElse(String.valueOf(n))
            .getMatch()
    )
);
```

### Factorial

```java
public long factorial(long i) {
  return match(i)
      .when(caseLong(0)).get(x -> 1l)
      .when(caseLong(any())).get(x -> x * factorial(x - 1))
      .getMatch();
}
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
