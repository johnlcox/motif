package com.leacox.motif.benchmarks;

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.PrimitiveCases.caseLong;
import static com.leacox.motif.matchers.ArgumentMatchers.any;

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
        .when(caseLong(0)).get(x -> 1l)
        .when(caseLong(any())).get(x -> x * factMatching(x - 1))
        .getMatch();
  }
}
