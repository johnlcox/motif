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
package com.leacox.motif.cases;

import static com.leacox.motif.MatchesAny.any;
import static com.leacox.motif.MatchesExact.eq;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.OptionalCases.some;
import static com.leacox.motif.cases.Tuple2Cases.tuple2;
import static com.leacox.motif.cases.Tuple3Cases.tuple3;
import static org.assertj.core.api.Assertions.assertThat;

import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;

import org.junit.Test;

import java.util.Optional;

/**
 * @author John Leacox
 */
public class Tuple3CasesTests {
  @Test
  public void testDecomposeFirstTo0AndMatchSecondAndMatchThird() {
    Tuple3<Optional<String>, String, String> tuple3 =
        Tuple3.of(Optional.of("A"), "B", "C");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), "B", "C")).get(() -> "Yep")
        .getMatch();

    assertThat(result).isEqualTo("Yep");
  }

  @Test
  public void testDecomposeFirstTo0AndMatchSecondAndExtractThird() {
    Tuple3<Optional<String>, String, String> tuple3 =
        Tuple3.of(Optional.of("A"), "B", "C");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), "B", any())).get(c -> c)
        .getMatch();

    assertThat(result).isEqualTo("C");
  }

  @Test
  public void testDecomposeFirstTo0AndMatchSecondAndDecomposeThirdTo0() {
    Tuple3<Optional<String>, String, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), "B", some(eq("C")))).get(() -> "Yep")
        .getMatch();

    assertThat(result).isEqualTo("Yep");
  }

  @Test
  public void testDecomposeFirstTo0AndMatchSecondAndDecomposeThirdTo1() {
    Tuple3<Optional<String>, String, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), "B", some(any()))).get(c -> c)
        .getMatch();

    assertThat(result).isEqualTo("C");
  }

  @Test
  public void testDecomposeFirstTo0AndMatchSecondAndDecomposeThirdTo2() {
    Tuple3<Optional<String>, String, Tuple2<String, String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Tuple2.of("C", "D"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), "B", tuple2(any(), any()))).get((c, d) -> c + d)
        .getMatch();

    assertThat(result).isEqualTo("CD");
  }

  @Test
  public void testDecomposeFirstTo0AndMatchSecondAndDecomposeThirdTo3() {
    Tuple3<Optional<String>, String, Tuple3<String, String, String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Tuple3.of("C", "D", "E"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), "B", tuple3(any(), any(), any()))).get((c, d, e) -> c + d + e)
        .getMatch();

    assertThat(result).isEqualTo("CDE");
  }

  @Test
  public void testDecomposeFirstTo0AndExtractSecondAndMatchThird() {
    Tuple3<Optional<String>, String, String> tuple3 =
        Tuple3.of(Optional.of("A"), "B", "C");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), any(), "C")).get(b -> b)
        .getMatch();

    assertThat(result).isEqualTo("B");
  }

  @Test
  public void testDecomposeFirstTo0AndExtractSecondAndExtractThird() {
    Tuple3<Optional<String>, String, String> tuple3 =
        Tuple3.of(Optional.of("A"), "B", "C");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), any(), any())).get((b, c) -> b + c)
        .getMatch();

    assertThat(result).isEqualTo("BC");
  }

  @Test
  public void testDecomposeFirstTo0AndExtractSecondAndDecomposeThirdTo0() {
    Tuple3<Optional<String>, String, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), any(), some(eq("C")))).get(b -> b)
        .getMatch();

    assertThat(result).isEqualTo("B");
  }

  @Test
  public void testDecomposeFirstTo0AndExtractSecondAndDecomposeThirdTo1() {
    Tuple3<Optional<String>, String, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), any(), some(any()))).get((b, c) -> b + c)
        .getMatch();

    assertThat(result).isEqualTo("BC");
  }

  @Test
  public void testDecomposeFirstTo0AndExtractSecondAndDecomposeThirdTo2() {
    Tuple3<Optional<String>, String, Tuple2<String, String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Tuple2.of("C", "D"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), any(), tuple2(any(), any()))).get((b, c, d) -> b + c + d)
        .getMatch();

    assertThat(result).isEqualTo("BCD");
  }

  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b, C c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b, MatchesAny c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder0<C> c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder1<C, C1> c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder2<C, C1, C2> c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder3<C, C1, C2, C3> c

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo0AndMatchThird() {
    Tuple3<Optional<String>, Optional<String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), "C");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(eq("B")), "C")).get(() -> "Yep")
        .getMatch();

    assertThat(result).isEqualTo("Yep");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo0AndExtractThird() {
    Tuple3<Optional<String>, Optional<String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), "C");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(eq("B")), any())).get(c -> c)
        .getMatch();

    assertThat(result).isEqualTo("C");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo0AndDecomposeThirdTo0() {
    Tuple3<Optional<String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(eq("B")), some(eq("C")))).get(() -> "Yep")
        .getMatch();

    assertThat(result).isEqualTo("Yep");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo0AndDecomposeThirdTo1() {
    Tuple3<Optional<String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(eq("B")), some(any()))).get(c -> c)
        .getMatch();

    assertThat(result).isEqualTo("C");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo0AndDecomposeThirdTo2() {
    Tuple3<Optional<String>, Optional<String>, Tuple2<String, String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Tuple2.of("C", "D"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(eq("B")), tuple2(any(), any()))).get((c, d) -> c + d)
        .getMatch();

    assertThat(result).isEqualTo("CD");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo0AndDecomposeThirdTo3() {
    Tuple3<Optional<String>, Optional<String>, Tuple3<String, String, String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Tuple3.of("C", "D", "E"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(eq("B")), tuple3(any(), any(), any()))).get(
            (c, d, e) -> c + d + e)
        .getMatch();

    assertThat(result).isEqualTo("CDE");
  }

  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b, C c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b, MatchesAny c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder0<C> c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder1<C> c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder2<C> c

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo1AndMatchThird() {
    Tuple3<Optional<String>, Optional<String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), "C");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(any()), "C")).get(b -> b)
        .getMatch();

    assertThat(result).isEqualTo("B");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo1AndExtractThird() {
    Tuple3<Optional<String>, Optional<String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), "C");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(any()), any())).get((b, c) -> b + c)
        .getMatch();

    assertThat(result).isEqualTo("BC");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo1AndDecomposeThirdTo0() {
    Tuple3<Optional<String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(any()), some(eq("C")))).get(b -> b)
        .getMatch();

    assertThat(result).isEqualTo("B");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo1AndDecomposeThirdTo1() {
    Tuple3<Optional<String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(any()), some(any()))).get((b, c) -> b + c)
        .getMatch();

    assertThat(result).isEqualTo("BC");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo1AndDecomposeThirdTo2() {
    Tuple3<Optional<String>, Optional<String>, Tuple2<String, String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Tuple2.of("C", "D"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), some(any()), tuple2(any(), any()))).get((b, c, d) -> b + c + d)
        .getMatch();

    assertThat(result).isEqualTo("BCD");
  }

  // DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b, C c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b, MatchesAny c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b, DecomposableMatchBuilder0<C> c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b, DecomposableMatchBuilder1<C> c

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo2AndMatchThird() {
    Tuple3<Optional<String>, Tuple2<String, String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Tuple2.of("B", "C"), "D");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), tuple2(any(), any()), "D")).get((b, c) -> b + c)
        .getMatch();

    assertThat(result).isEqualTo("BC");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo2AndExtractThird() {
    Tuple3<Optional<String>, Tuple2<String, String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Tuple2.of("B", "C"), "D");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), tuple2(any(), any()), any())).get((b, c, d) -> b + c + d)
        .getMatch();

    assertThat(result).isEqualTo("BCD");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo2AndDecomposeThirdTo0() {
    Tuple3<Optional<String>, Tuple2<String, String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Tuple2.of("B", "C"), Optional.of("D"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), tuple2(any(), any()), some(eq("D")))).get((b, c) -> b + c)
        .getMatch();

    assertThat(result).isEqualTo("BC");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo2AndDecomposeThirdTo1() {
    Tuple3<Optional<String>, Tuple2<String, String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Tuple2.of("B", "C"), Optional.of("D"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), tuple2(any(), any()), some(any()))).get((b, c, d) -> b + c + d)
        .getMatch();

    assertThat(result).isEqualTo("BCD");
  }

  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder3<B, B1, B2, B3> b, C c
  //DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder3<B, B1, B2, B3> b, DecomposableMatchBuilder0<C> c

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo3AndMatchThird() {
    Tuple3<Optional<String>, Tuple3<String, String, String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Tuple3.of("B", "C", "D"), "E");

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), tuple3(any(), any(), any()), "E")).get((b, c, d) -> b + c + d)
        .getMatch();

    assertThat(result).isEqualTo("BCD");
  }

  @Test
  public void testDecomposeFirstTo0AndDecomposeSecondTo3AndDecomposeThirdTo0() {
    Tuple3<Optional<String>, Tuple3<String, String, String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Tuple3.of("B", "C", "D"), Optional.of("E"));

    String result = match(tuple3)
        .when(tuple3(some(eq("A")), tuple3(any(), any(), any()), some(eq("E")))).get((b, c, d) -> b + c + d)
        .getMatch();

    assertThat(result).isEqualTo("BCD");
  }

  //DecomposableMatchBuilder1<A, A1> a, B b, C c
  //DecomposableMatchBuilder1<A, A1> a, B b, MatchesAny c
  //DecomposableMatchBuilder1<A, A1> a, B b, DecomposableMatchBuilder0<C> c
  //DecomposableMatchBuilder1<A, A1> a, B b, DecomposableMatchBuilder1<C, C1> c
  //DecomposableMatchBuilder1<A, A1> a, B b, DecomposableMatchBuilder2<C, C1, C2> c

  @Test
  public void testDecomposeFirstTo1AndMatchSecondAndMatchThird() {
    Tuple3<Optional<String>, String, String> tuple3 =
        Tuple3.of(Optional.of("A"), "B", "C");

    String result = match(tuple3)
        .when(tuple3(some(any()), "B", "C")).get(a -> a)
        .getMatch();

    assertThat(result).isEqualTo("A");
  }

  @Test
  public void testDecomposeFirstTo1AndMatchSecondAndExtractThird() {
    Tuple3<Optional<String>, String, String> tuple3 =
        Tuple3.of(Optional.of("A"), "B", "C");

    String result = match(tuple3)
        .when(tuple3(some(any()), "B", any())).get((a, c) -> a + c)
        .getMatch();

    assertThat(result).isEqualTo("AC");
  }

  @Test
  public void testDecomposeFirstTo1AndMatchSecondAndDecomposeThirdTo0() {
    Tuple3<Optional<String>, String, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(any()), "B", some(eq("C")))).get(a -> a)
        .getMatch();

    assertThat(result).isEqualTo("A");
  }

  @Test
  public void testDecomposeFirstTo1AndMatchSecondAndDecomposeThirdTo1() {
    Tuple3<Optional<String>, String, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(any()), "B", some(any()))).get((a, c) -> a + c)
        .getMatch();

    assertThat(result).isEqualTo("AC");
  }

  @Test
  public void testDecomposeFirstTo1AndMatchSecondAndDecomposeThirdTo2() {
    Tuple3<Optional<String>, String, Tuple2<String, String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Tuple2.of("C", "D"));

    String result = match(tuple3)
        .when(tuple3(some(any()), "B", tuple2(any(), any()))).get((a, c, d) -> a + c + d)
        .getMatch();

    assertThat(result).isEqualTo("ACD");
  }

  // DecomposableMatchBuilder1<A, A1> a, MatchesAny b, C c
  // DecomposableMatchBuilder1<A, A1> a, MatchesAny b, MatchesAny c
  // DecomposableMatchBuilder1<A, A1> a, MatchesAny b, DecomposableMatchBuilder0<C> c
  // DecomposableMatchBuilder1<A, A1> a, MatchesAny b, DecomposableMatchBuilder1<C, C1> c

  @Test
  public void testDecomposeFirstTo1AndExtractSecondAndMatchThird() {
    Tuple3<Optional<String>, String, String> tuple3 =
        Tuple3.of(Optional.of("A"), "B", "C");

    String result = match(tuple3)
        .when(tuple3(some(any()), any(), "C")).get((a, b) -> a + b)
        .getMatch();

    assertThat(result).isEqualTo("AB");
  }

  @Test
  public void testDecomposeFirstTo1AndExtractSecondAndExtractThird() {
    Tuple3<Optional<String>, String, String> tuple3 =
        Tuple3.of(Optional.of("A"), "B", "C");

    String result = match(tuple3)
        .when(tuple3(some(any()), any(), any())).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  @Test
  public void testDecomposeFirstTo1AndExtractSecondAndDecomposeThirdTo0() {
    Tuple3<Optional<String>, String, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(any()), any(), some(eq("C")))).get((a, b) -> a + b)
        .getMatch();

    assertThat(result).isEqualTo("AB");
  }

  @Test
  public void testDecomposeFirstTo1AndExtractSecondAndDecomposeThirdTo1() {
    Tuple3<Optional<String>, String, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), "B", Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(any()), any(), some(any()))).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  // DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b, C c
  // DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b, MatchesAny c
  // DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder0<C> c
  // DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder1<C> c
  // DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder2<C> c

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo0AndMatchThird() {
    Tuple3<Optional<String>, Optional<String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), "C");

    String result = match(tuple3)
        .when(tuple3(some(any()), some(eq("B")), "C")).get(a -> a)
        .getMatch();

    assertThat(result).isEqualTo("A");
  }

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo0AndExtractThird() {
    Tuple3<Optional<String>, Optional<String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), "C");

    String result = match(tuple3)
        .when(tuple3(some(any()), some(eq("B")), any())).get((a, c) -> a + c)
        .getMatch();

    assertThat(result).isEqualTo("AC");
  }

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo0AndDecomposeThirdTo0() {
    Tuple3<Optional<String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(any()), some(eq("B")), some(eq("C")))).get(a -> a)
        .getMatch();

    assertThat(result).isEqualTo("A");
  }

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo0AndDecomposeThirdTo1() {
    Tuple3<Optional<String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(any()), some(eq("B")), some(any()))).get((a, c) -> a + c)
        .getMatch();

    assertThat(result).isEqualTo("AC");
  }

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo0AndDecomposeThirdTo2() {
    Tuple3<Optional<String>, Optional<String>, Tuple2<String, String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Tuple2.of("C", "D"));

    String result = match(tuple3)
        .when(tuple3(some(any()), some(eq("B")), tuple2(any(), any()))).get((a, c, d) -> a + c + d)
        .getMatch();

    assertThat(result).isEqualTo("ACD");
  }

  //DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b, C c
  //DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b, MatchesAny c
  //DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder0<C> c
  //DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder1<C, C1> c

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo1AndMatchThird() {
    Tuple3<Optional<String>, Optional<String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), "C");

    String result = match(tuple3)
        .when(tuple3(some(any()), some(any()), "C")).get((a, b) -> a + b)
        .getMatch();

    assertThat(result).isEqualTo("AB");
  }

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo1AndExtractThird() {
    Tuple3<Optional<String>, Optional<String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), "C");

    String result = match(tuple3)
        .when(tuple3(some(any()), some(any()), any())).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo1AndDecomposeThirdTo0() {
    Tuple3<Optional<String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(any()), some(any()), some(eq("C")))).get((a, b) -> a + b)
        .getMatch();

    assertThat(result).isEqualTo("AB");
  }

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo1AndDecomposeThirdTo1() {
    Tuple3<Optional<String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Optional.of("B"), Optional.of("C"));

    String result = match(tuple3)
        .when(tuple3(some(any()), some(any()), some(any()))).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  //DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder2<B, B1, B2> b, C c
  //DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder2<B, B1, B2> b, DecomposableMatchBuilder0<C> c

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo2AndMatchThird() {
    Tuple3<Optional<String>, Tuple2<String, String>, String> tuple3 =
        Tuple3.of(Optional.of("A"), Tuple2.of("B", "C"), "D");

    String result = match(tuple3)
        .when(tuple3(some(any()), tuple2(any(), any()), "D")).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  @Test
  public void testDecomposeFirstTo1AndDecomposeSecondTo2AndDecomposeThirdTo0() {
    Tuple3<Optional<String>, Tuple2<String, String>, Optional<String>> tuple3 =
        Tuple3.of(Optional.of("A"), Tuple2.of("B", "C"), Optional.of("D"));

    String result = match(tuple3)
        .when(tuple3(some(any()), tuple2(any(), any()), some(eq("D")))).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  //DecomposableMatchBuilder2<A, A1, A2> a, B b, C c
  //DecomposableMatchBuilder2<A, A1, A2> a, B b, MatchesAny c
  //DecomposableMatchBuilder2<A, A1, A2> a, B b, DecomposableMatchBuilder0<C> c
  //DecomposableMatchBuilder2<A, A1, A2> a, B b, DecomposableMatchBuilder1<C, C1> c

  @Test
  public void testDecomposeFirstTo2AndMatchSecondAndMatchThird() {
    Tuple3<Tuple2<String, String>, String, String> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), "C", "D");

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), "C", "D")).get((a, b) -> a + b)
        .getMatch();

    assertThat(result).isEqualTo("AB");
  }

  @Test
  public void testDecomposeFirstTo2AndMatchSecondAndExtractThird() {
    Tuple3<Tuple2<String, String>, String, String> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), "C", "D");

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), "C", any())).get((a, b, d) -> a + b + d)
        .getMatch();

    assertThat(result).isEqualTo("ABD");
  }

  @Test
  public void testDecomposeFirstTo2AndMatchSecondAndDecomposeThirdTo0() {
    Tuple3<Tuple2<String, String>, String, Optional<String>> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), "C", Optional.of("D"));

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), "C", some(eq("D")))).get((a, b) -> a + b)
        .getMatch();

    assertThat(result).isEqualTo("AB");
  }

  @Test
  public void testDecomposeFirstTo2AndMatchSecondAndDecomposeThirdTo1() {
    Tuple3<Tuple2<String, String>, String, Optional<String>> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), "C", Optional.of("D"));

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), "C", some(any()))).get((a, b, d) -> a + b + d)
        .getMatch();

    assertThat(result).isEqualTo("ABD");
  }

  //DecomposableMatchBuilder2<A, A1, A2> a, MatchesAny b, C c
  //DecomposableMatchBuilder2<A, A1, A2> a, MatchesAny b, DecomposableMatchBuilder0<C> c

  @Test
  public void testDecomposeFirstTo2AndExtractSecondAndMatchThird() {
    Tuple3<Tuple2<String, String>, String, String> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), "C", "D");

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), any(), "D")).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  @Test
  public void testDecomposeFirstTo2AndExtractSecondAndDecomposeThirdTo0() {
    Tuple3<Tuple2<String, String>, String, Optional<String>> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), "C", Optional.of("D"));

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), any(), some(eq("D")))).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  //DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b, C c
  //DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b, MatchesAny c
  //DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder0<C> c
  //DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder1<C, C1> c

  @Test
  public void testDecomposeFirstTo2AndDecomposeSecondTo0AndMatchThird() {
    Tuple3<Tuple2<String, String>, Optional<String>, String> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), Optional.of("C"), "D");

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), some(eq("C")), "D")).get((a, b) -> a + b)
        .getMatch();

    assertThat(result).isEqualTo("AB");
  }

  @Test
  public void testDecomposeFirstTo2AndDecomposeSecondTo0AndExtractThird() {
    Tuple3<Tuple2<String, String>, Optional<String>, String> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), Optional.of("C"), "D");

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), some(eq("C")), any())).get((a, b, d) -> a + b + d)
        .getMatch();

    assertThat(result).isEqualTo("ABD");
  }

  @Test
  public void testDecomposeFirstTo2AndDecomposeSecondTo0AndDecomposeThirdTo0() {
    Tuple3<Tuple2<String, String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), Optional.of("C"), Optional.of("D"));

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), some(eq("C")), some(eq("D")))).get((a, b) -> a + b)
        .getMatch();

    assertThat(result).isEqualTo("AB");
  }

  @Test
  public void testDecomposeFirstTo2AndDecomposeSecondTo0AndDecomposeThirdTo1() {
    Tuple3<Tuple2<String, String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), Optional.of("C"), Optional.of("D"));

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), some(eq("C")), some(any()))).get((a, b, d) -> a + b + d)
        .getMatch();

    assertThat(result).isEqualTo("ABD");
  }

  //DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder1<B, B1> b, C c
  //DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder0<C> c

  @Test
  public void testDecomposeFirstTo2AndDecomposeSecondTo1AndMatchThird() {
    Tuple3<Tuple2<String, String>, Optional<String>, String> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), Optional.of("C"), "D");

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), some(any()), "D")).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  @Test
  public void testDecomposeFirstTo2AndDecomposeSecondTo1AndDecomposeThirdTo0() {
    Tuple3<Tuple2<String, String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Tuple2.of("A", "B"), Optional.of("C"), Optional.of("D"));

    String result = match(tuple3)
        .when(tuple3(tuple2(any(), any()), some(any()), some(eq("D")))).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  //DecomposableMatchBuilder3<A, A1, A2, A3> a, B b, C c
  //DecomposableMatchBuilder3<A, A1, A2, A3> a, B b, DecomposableMatchBuilder0<C> c
  //DecomposableMatchBuilder3<A, A1, A2, A3> a, DecomposableMatchBuilder0<B> b, C c
  //DecomposableMatchBuilder3<A, A1, A2, A3> a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder0<C> c

  @Test
  public void testDecomposeFirstTo3AndMatchSecondAndMatchThird() {
    Tuple3<Tuple3<String, String, String>, String, String> tuple3 =
        Tuple3.of(Tuple3.of("A", "B", "C"), "D", "E");

    String result = match(tuple3)
        .when(tuple3(tuple3(any(), any(), any()), "D", "E")).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  @Test
  public void testDecomposeFirstTo3AndMatchSecondAndDecomposeThirdTo0() {
    Tuple3<Tuple3<String, String, String>, String, Optional<String>> tuple3 =
        Tuple3.of(Tuple3.of("A", "B", "C"), "D", Optional.of("E"));

    String result = match(tuple3)
        .when(tuple3(tuple3(any(), any(), any()), "D", some(eq("E")))).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  @Test
  public void testDecomposeFirstTo3AndDecomposeSecondTo0AndMatchThird() {
    Tuple3<Tuple3<String, String, String>, Optional<String>, String> tuple3 =
        Tuple3.of(Tuple3.of("A", "B", "C"), Optional.of("D"), "E");

    String result = match(tuple3)
        .when(tuple3(tuple3(any(), any(), any()), some(eq("D")), "E")).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }

  @Test
  public void testDecomposeFirstTo3AndDecomposeSecondTo0AndDecomposeThirdTo0() {
    Tuple3<Tuple3<String, String, String>, Optional<String>, Optional<String>> tuple3 =
        Tuple3.of(Tuple3.of("A", "B", "C"), Optional.of("D"), Optional.of("E"));

    String result = match(tuple3)
        .when(tuple3(tuple3(any(), any(), any()), some(eq("D")), some(eq("E")))).get((a, b, c) -> a + b + c)
        .getMatch();

    assertThat(result).isEqualTo("ABC");
  }
}
