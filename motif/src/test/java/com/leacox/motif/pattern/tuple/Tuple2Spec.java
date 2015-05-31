package com.leacox.motif.pattern.tuple;

import static com.insightfullogic.lambdabehave.Suite.describe;

import com.leacox.motif.tuple.Tuple2;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class Tuple2Spec {
  {
    describe(
        "Tuple2", it -> it.should(
            "put tuple values inside () for toString", expect -> {
              Tuple2<String, Integer> tuple2 = Tuple2.of("AString", 83);

              expect.that(tuple2.toString()).is(("(AString,83)"));
            })
    );
  }
}
