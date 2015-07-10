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
import com.leacox.motif.generate.Match2MethodSpec;
import com.leacox.motif.tuple.Tuple2;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;

/**
 * @author John Leacox
 */
final class Tuple2CasesGenerator {
  private Tuple2CasesGenerator() {
  }

  public static void main(String[] args) {
    TypeName A = TypeVariableName.get("A");
    TypeName B = TypeVariableName.get("B");
    TypeName t = ParameterizedTypeName.get(ClassName.get(Tuple2.class), A, B);

    Match2MethodSpec tuple2Match = Match2MethodSpec.builder()
        .withName("tuple2").withSummaryJavadoc("Matches a tuple of 2 elements.\n")
        .withMatchExtractor(Tuple2FieldExtractor.class).withParamA(A, "a").withParamB(B, "b")
        .build();

    JavaFile tuple2CasesFile = CasesGenerator.newBuilder(
        "com.leacox.motif.cases", "Tuple2Cases", t)
        .addFileComment(Copyright.COPYRIGHT_NOTICE)
        .addJavadoc("Motif cases for matching a {@link Tuple2}.\n")
        .addMatch2Method(tuple2Match)
        .build().generate();

    try {
      tuple2CasesFile.writeTo(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
