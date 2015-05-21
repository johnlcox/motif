package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.pattern.ListPattern.caseHeadNil;
import static com.leacox.motif.pattern.ListPattern.caseHeadTail;
import static com.leacox.motif.pattern.ListPattern.caseNil;

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
                String result = match(emptyList)
                    .when(caseNil(() -> "Nil"))
                    .get();

                expect.that(result).is("Nil");
              });

          it.should(
              "match empty list and consume right side", expect -> {
                match(emptyList)
                    .when(caseNil(() -> System.out.println("nil")));
              });

          it.should(
              "match one item list", expect -> {
                String result = match(oneItemList)
                    .when(caseNil(() -> "Nil"))
                    .when(caseHeadNil((String s) -> s))
                    .get();

                expect.that(result).is("one");
              });

          it.should(
              "match one item list and consume right side", expect -> {
                match(oneItemList)
                    .when(caseNil(() -> System.out.println("nil")))
                    .when(caseHeadNil((String s) -> System.out.println(s)));
              });

          it.should(
              "match multi-item list", expect -> {
                String result = match(twoItemList)
                    .when(caseNil(() -> "Nil"))
                    .when(caseHeadNil((String s) -> s))
                    .when(caseHeadTail((x, xs) -> "head: " + x + " tail: " + xs))
                    .get();

                expect.that(result).is("head: one tail: [two]");
              });

          it.should(
              "match multi-item list and consume right side", expect -> {
                match(twoItemList)
                    .when(caseNil(() -> System.out.println("nil")))
                    .when(caseHeadNil((String s) -> System.out.println(s)))
                    .when(
                        caseHeadTail(
                            (String x, List<String> xs) -> System.out
                                .println("head: " + x + " tail: " + xs)));
              });
        }

    );
  }
}
