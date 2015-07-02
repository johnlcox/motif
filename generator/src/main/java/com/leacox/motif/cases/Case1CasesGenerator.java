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

import com.leacox.motif.caseclass.Case1;
import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.generate.CasesGenerator;
import com.leacox.motif.generate.Match1MethodSpec;
import com.leacox.motif.generate.Match2MethodSpec;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;

/**
 * @author John Leacox
 */
final class Case1CasesGenerator {
  private Case1CasesGenerator() {
  }

  public static void main(String[] args) {
    TypeName A = TypeVariableName.get("A");
    TypeName bounds = ParameterizedTypeName.get(ClassName.get(Case1.class), A);
    TypeName t = TypeVariableName.get("T", bounds);
    TypeName clazz = ParameterizedTypeName.get(ClassName.get(Class.class), t);

    Match1MethodSpec case1Match = Match1MethodSpec.builder()
        .withName("case1").withSummaryJavadoc("Matches a case class of one element.\n")
        .addNonMatchParam(clazz, "clazz").withMatchExtractor(Case1FieldExtractor.class, "clazz")
        .withParamA(A, "a").build();

    JavaFile cases1CasesFile = CasesGenerator.newBuilder(
        "com.leacox.motif.cases", "Case1Cases", t)
        .addFileComment(Copyright.COPYRIGHT_NOTICE)
        .addJavadoc("Motif cases for matching a {@link Case1}.\n")
        .addMatch1Method(case1Match)
        .build().generate();

    try {
      cases1CasesFile.writeTo(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
