package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.pattern.ListPattern.caseHeadNil;
import static com.leacox.motif.pattern.ListPattern.caseHeadTail;
import static com.leacox.motif.pattern.ListPattern.caseNil;
import static com.leacox.motif.pattern.OrElsePattern.orElse;
import static com.leacox.motif.pattern.OrElsePattern.otherwise;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class ListPatternSpec {
  {
    List<String> emptyList = new ArrayList<>();
    List<String> oneItemList = new ArrayList<>();
    oneItemList.add("one");
    List<String> twoItemList = new ArrayList<>();
    twoItemList.add("one");
    twoItemList.add("two");

    describe(
        "the list pattern", it -> {
          it.should(
              "match empty list", expect -> {
                String result = match(emptyList).on(
                    caseNil(() -> "Nil"),
                    orElse("orElse")
                );

                expect.that(result).is("Nil");
              });

          it.should(
              "match empty list and consume right side", expect -> {
                match(emptyList).on(
                    ListPattern.cazeNil(() -> System.out.println("nil")),
                    otherwise(() -> System.out.println("a")),
                    otherwise(t -> System.out.println(t))
                );
              });

          it.should(
              "match one item list", expect -> {
                String result = match(oneItemList).on(
                    caseNil(() -> "Nil"),
                    caseHeadNil((String s) -> s)
                );

                expect.that(result).is("one");
              });

          it.should(
              "match one item list and consume right side", expect -> {
                match(oneItemList).on(
                    ListPattern.cazeNil(() -> System.out.println("nil")),
                    ListPattern.cazeHeadNil((String s) -> System.out.println(s)),
                    otherwise(() -> System.out.println("Nope"))
                );
              });

          it.should(
              "match multi-item list", expect -> {
                String result = match(twoItemList).on(
                    caseNil(() -> "Nil"),
                    caseHeadNil((String s) -> s),
                    caseHeadTail((x, xs) -> "head: " + x + " tail: " + xs)
                );

                expect.that(result).is("head: one tail: [two]");
              });

          // TODO: actually test something
          it.should(
              "match multi-item list and consume right side", expect -> {
                match(twoItemList).on(
                    ListPattern.cazeNil(() -> System.out.println("nil")),
                    ListPattern.cazeHeadNil((String s) -> System.out.println(s)),
                    ListPattern.cazeHeadTail(
                        (String x, List<String> xs) -> System.out
                            .println("head: " + x + " tail: " + xs))
                );
              });
        }
    );
  }
}
