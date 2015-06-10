package com.leacox.motif.benchmarks;

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.TupleCases.caseTuple2;
import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.tuple.Tuple2;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author John Leacox
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class FizzBuzzBenchmark {
  @Benchmark
  public void fizzbuzzConditional() {







    IntStream.range(0, 101).forEach(
        n -> {
          if (n % (3 * 5) == 0) {
            System.out.println("FizzBuzz");
          } else if (n % 3 == 0) {
            System.out.println("Fizz");
          } else if (n % 5 == 0) {
            System.out.println("Buzz");
          } else {
            System.out.println(n);
          }
        }
    );








  }

  @Benchmark
  public void fizzBuzzPatternMatching() {
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
  }
}
