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
import com.leacox.motif.generate.Match2MethodSpec;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.List;

/**
 * @author John Leacox
 */
final class ListConsCasesGenerator {
  private ListConsCasesGenerator() {
  }

  public static void main(String[] args) {
    TypeName E = TypeVariableName.get("T");
    TypeName l = ParameterizedTypeName.get(ClassName.get(List.class), E);

    Match0MethodSpec nilMatch = Match0MethodSpec.builder()
        .name("nil").matchExtractor(ListConsNilFieldExtractor.class).build();

    Match1MethodSpec headNilMatch = Match1MethodSpec.builder()
        .name("headNil").matchExtractor(ListConsHeadFieldExtractor.class).paramAType(E)
        .paramAName("head").build();

    Match2MethodSpec headTailMatch = Match2MethodSpec.builder()
        .name("headTail").matchExtractor(ListConsHeadTailFieldExtractor.class).paramAType(E)
        .paramAName("head").paramBType(l).paramBName("tail").build();

    JavaFile listCasesFile = CasesGenerator.newBuilder("com.leacox.motif.cases", "ListConsCases", l)
        .addMatch0Method(nilMatch)
        .addMatch1Method(headNilMatch)
        .addMatch2Method(headTailMatch)
        .build().generate();

    try {
      listCasesFile.writeTo(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
