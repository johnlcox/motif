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

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.PrimitiveCases.caseLong;
import static com.leacox.motif.MatchesAny.any;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

/**
 * @author John Leacox
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class FactorialBenchmark {
  @Param({"0", "1", "10"})
  public int n;

  @Benchmark
  public long factorialConditional() {
    return factConditionl(n);
  }

  private long factConditionl(long i) {
    if (i == 0) {
      return 1;
    } else {
      return i * factConditionl(i - 1);
    }
  }

  @Benchmark
  public long factorialPatternMatching() {
    return factMatching(n);
  }

  private long factMatching(long i) {
    return match(i)
        .when(caseLong(0)).get(() -> 1l)
        .when(caseLong(any())).get(x -> x * factMatching(x - 1))
        .getMatch();
  }
}
