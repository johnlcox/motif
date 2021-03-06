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

import com.leacox.motif.generate.CasesGenerator;
import com.leacox.motif.generate.Match1MethodSpec;
import com.leacox.motif.tuple.Tuple1;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;

/**
 * Generator for {@link Tuple1} match cases.
 *
 * @author John Leacox
 */
final class Tuple1CasesGenerator {
  private Tuple1CasesGenerator() {
  }

  public static void main(String[] args) {
    TypeName A = TypeVariableName.get("A");
    TypeName t = ParameterizedTypeName.get(ClassName.get(Tuple1.class), A);

    Match1MethodSpec tuple1Match = Match1MethodSpec.builder()
        .withName("tuple1").withSummaryJavadoc("Matches a tuple of 1 element.\n")
        .withMatchExtractor(Tuple1FieldExtractor.class).withParamA(A, "a").build();

    JavaFile tuple1CasesFile = CasesGenerator.newBuilder(
        "com.leacox.motif.cases", "Tuple1Cases", t)
        .addFileComment(Copyright.COPYRIGHT_NOTICE)
        .addJavadoc("Motif cases for matching a {@link Tuple1}.\n")
        .addMatch1Method(tuple1Match)
        .build().generate();

    try {
      tuple1CasesFile.writeTo(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
