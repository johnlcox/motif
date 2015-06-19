package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.ListCases.headNil;
import static com.leacox.motif.cases.ListCases.headTail;
import static com.leacox.motif.cases.ListCases.nil;
import static com.leacox.motif.decomposition.MatchesAny.__;
import static com.leacox.motif.decomposition.MatchesAny.any;

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
                    .when(nil()).get(() -> "Nil")
                    .orElse("orElse")
                    .getMatch();

                expect.that(result).is("Nil");
              });

          it.should(
              "match empty list and consume right side", expect -> {
                Consuming consuming = new Consuming();

                match(emptyList)
                    .when(nil()).then(() -> consuming.consume("nil"))
                    .orElse(x -> consuming.consume("orElse"))
                    .doMatch();

                expect.that(consuming.getConsumed()).is("nil");
              });

          it.should(
              "match one item list", expect -> {
                String result = match(oneItemList)
                    .when(nil()).get(() -> "Nil")
                    .when(headNil(__)).get(s -> s)
                    .getMatch();

                expect.that(result).is("one");
              });

          it.should(
              "match one item list and consume right side", expect -> {
                Consuming consuming = new Consuming();

                match(oneItemList)
                    .when(nil()).then(() -> consuming.consume("nil"))
                    .when(headNil(any())).then(consuming::consume)
                    .orElse(() -> consuming.consume("Nope"))
                    .doMatch();

                expect.that(consuming.getConsumed()).is("one");
              });

          it.should(
              "match multi-item list", expect -> {
                String result = match(twoItemList)
                    .when(nil()).get(() -> "Nil")
                    .when(headNil(__)).get((x) -> x)
                    .when(headTail(any(), any())).get((x, xs) -> "head: " + x + " tail: " + xs)
                    .getMatch();

                expect.that(result).is("head: one tail: [two]");
              });

          it.should(
              "match multi-item list and consume right side", expect -> {
                Consuming consuming = new Consuming();

                // TODO: Why is intellij formatting consuming lambda continuation indents this way?
                match(twoItemList)
                    .when(nil()).then(() -> consuming.consume("nil"))
                    .when(headNil(any())).then(consuming::consume)
                    .when(headTail(any(), any())).then(
                    (x, xs) ->
                        consuming.consume("head: " + x + " tail: " + xs))
                    .doMatch();
              });
        }
    );
  }
}
