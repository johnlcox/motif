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
package com.leacox.motif.generate;

/**
 * @author John Leacox
 */
public class GeneratorTesting {
  public static void main(String[] args) {
    // Tuple2Cases
    //TypeName A = TypeVariableName.get("A");
    //TypeName B = TypeVariableName.get("B");
    //TypeName t = ParameterizedTypeName.get(ClassName.get(Tuple2.class), A, B);
    //
    //Match2MethodSpec tuple2Match = Match2MethodSpec.builder()
    //    .name("tuple2").matchExtractor(Tuple2FieldExtractor.class).paramAType(A).paramAName("a")
    //    .paramBType(B).paramBName("b").build();
    //
    //JavaFile tuple2CasesFile = CasesGenerator.newBuilder(
    //    "com.leacox.motif.cases", "Tuple2Cases", t)
    //    .addMatch2Method(tuple2Match)
    //    .build().generate();
    //
    //try {
    //  tuple2CasesFile.writeTo(System.out);
    //} catch (IOException e) {
    //  e.printStackTrace();
    //}

    // Tuple3Cases
    //TypeName A = TypeVariableName.get("A");
    //TypeName B = TypeVariableName.get("B");
    //TypeName C = TypeVariableName.get("C");
    //TypeName t = ParameterizedTypeName.get(ClassName.get(Tuple3.class), A, B, C);
    //
    //Match3MethodSpec tuple3Match = Match3MethodSpec.builder()
    //    .name("tuple3").matchExtractor(Tuple3FieldExtractor.class).paramAType(A).paramAName("a")
    //    .paramBType(B).paramBName("b").paramCType(C).paramCName("c").build();
    //
    //JavaFile tuple2CasesFile = CasesGenerator.newBuilder(
    //    "com.leacox.motif.cases", "Tuple3Cases", t)
    //    .addMatch3Method(tuple3Match)
    //    .build().generate();
    //
    //try {
    //  tuple2CasesFile.writeTo(System.out);
    //} catch (IOException e) {
    //  e.printStackTrace();
    //}

    // List Cases
    //TypeName E = TypeVariableName.get("T");
    //TypeName l = ParameterizedTypeName.get(ClassName.get(List.class), E);
    //
    //JavaFile listCasesFile = CasesGenerator.newBuilder("com.leacox.motif.cases", "ListCases", l)
    //    .addMatch2Method("headTail", HeadTailFieldExtractor.class, E, "head", l, "tail")
    //    .build().generate();
    //
    //try {
    //  listCasesFile.writeTo(System.out);
    //} catch (IOException e) {
    //  e.printStackTrace();
    //}
  }
}
