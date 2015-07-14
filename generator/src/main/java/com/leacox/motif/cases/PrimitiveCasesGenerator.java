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
 * Generator for primitive match cases.
 *
 * <p>Note: This isn't currently used.
 *
 * @author John Leacox
 */
final class PrimitiveCasesGenerator {
  private PrimitiveCasesGenerator() {
  }

  public static void main(String[] args) {
    TypeName A = TypeVariableName.get("A");
    TypeName t = ParameterizedTypeName.get(ClassName.get(Tuple1.class), A);

    Match1MethodSpec byteMatch = Match1MethodSpec.builder()
        .withName("aByte").withSummaryJavadoc("Matches a byte.\n")
        .withMatchExtractor(PrimitiveFieldExtractor.class, Byte.class)
        .withParamA(TypeName.get(byte.class), "b").build();

    JavaFile primitiveCasesFile = CasesGenerator.newBuilder(
        "com.leacox.motif.cases", "PrimitiveCases", t)
        .addFileComment(Copyright.COPYRIGHT_NOTICE)
        .addJavadoc("Motif cases for matching a primitive types.\n")
        .addMatch1Method(byteMatch)
        .build().generate();

    try {
      primitiveCasesFile.writeTo(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
