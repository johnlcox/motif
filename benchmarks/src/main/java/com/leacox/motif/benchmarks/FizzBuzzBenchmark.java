/*
 * Copyright (C) 2015 John Leacox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.leacox.motif.benchmarks;

import static com.leacox.motif.MatchesAny.any;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.Tuple2Cases.tuple2;

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
                .when(tuple2(0, 0)).get(() -> "FizzBuzz")
                .when(tuple2(0, any())).get(y -> "Fizz")
                .when(tuple2(any(), 0)).get(x -> "Buzz")
                .orElse(String.valueOf(n))
                .getMatch()
        )
    );
  }
}
