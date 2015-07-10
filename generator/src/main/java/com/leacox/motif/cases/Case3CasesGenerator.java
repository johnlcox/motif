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

import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.caseclass.Case3;
import com.leacox.motif.generate.CasesGenerator;
import com.leacox.motif.generate.Match2MethodSpec;
import com.leacox.motif.generate.Match3MethodSpec;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;

/**
 * @author John Leacox
 */
final class Case3CasesGenerator {
  private Case3CasesGenerator() {
  }

  public static void main(String[] args) {
    TypeName A = TypeVariableName.get("A");
    TypeName B = TypeVariableName.get("B");
    TypeName C = TypeVariableName.get("C");
    TypeName bounds = ParameterizedTypeName.get(ClassName.get(Case3.class), A, B, C);
    TypeName t = TypeVariableName.get("T", bounds);
    TypeName clazz = ParameterizedTypeName.get(ClassName.get(Class.class), t);

    Match3MethodSpec case3Match = Match3MethodSpec.builder()
        .withName("case3").withSummaryJavadoc("Matches a case class of three elements.\n")
        .addNonMatchParam(clazz, "clazz").withMatchExtractor(Case3FieldExtractor.class, "clazz")
        .withParamA(A, "a").withParamB(B, "b").withParamC(C, "c").build();

    JavaFile case3CasesFile = CasesGenerator.newBuilder(
        "com.leacox.motif.cases", "Case3Cases", t)
        .addFileComment(Copyright.COPYRIGHT_NOTICE)
        .addJavadoc("Motif cases for matching a {@link Case3}.\n")
        .addMatch3Method(case3Match)
        .build().generate();

    try {
      case3CasesFile.writeTo(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
