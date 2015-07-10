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
import com.leacox.motif.generate.Match0MethodSpec;
import com.leacox.motif.generate.Match1MethodSpec;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.Optional;

/**
 * @author John Leacox
 */
final class OptionalCasesGenerator {
  private OptionalCasesGenerator() {
  }

  public static void main(String[] args) {
    TypeName T = TypeVariableName.get("T");
    TypeName o = ParameterizedTypeName.get(ClassName.get(Optional.class), T);

    Match0MethodSpec noneMatch = Match0MethodSpec.builder()
        .withName("none").withSummaryJavadoc("Matches an empty {@link Optional}.\n")
        .withMatchExtractor(OptionalNoneFieldExtractor.class).build();

    Match1MethodSpec someMatch = Match1MethodSpec.builder()
        .withName("some").withSummaryJavadoc("Matches a non-empty {@link Optional}.\n")
        .withMatchExtractor(OptionalFieldExtractor.class).withParamA(T, "t").build();

    JavaFile optionalCasesFile = CasesGenerator.newBuilder("com.leacox.motif.cases", "OptionalCases", o)
        .addFileComment(Copyright.COPYRIGHT_NOTICE)
        .addJavadoc("Motif cases for matching an {@link Optional}.\n")
        .addMatch0Method(noneMatch)
        .addMatch1Method(someMatch)
        .build().generate();

    try {
      optionalCasesFile.writeTo(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
