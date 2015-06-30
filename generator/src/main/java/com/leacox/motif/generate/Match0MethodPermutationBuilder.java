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

import com.leacox.motif.extract.FieldExtractor;

import com.google.common.collect.ImmutableList;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

/**
 * @author John Leacox
 */
public class Match0MethodPermutationBuilder extends BaseMatchMethodPermutationBuilder {
  private final TypeName input;
  private final TypeName fieldExtractor;
  private final String methodName;

  Match0MethodPermutationBuilder(
      TypeName input, Class<? extends FieldExtractor> inputExtractor, String methodName) {
    this.input = input;
    this.fieldExtractor = TypeName.get(inputExtractor);
    this.methodName = methodName;
  }

  public List<MethodSpec> build() {
    return getMethodPermutations(input, methodName);
  }

  private List<MethodSpec> getMethodPermutations(TypeName inputType, String methodName) {

    return ImmutableList.of(
        MethodSpec.methodBuilder(methodName)
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(getReturnType(inputType))
            .addTypeVariables(getTypeVariables(inputType))
            .addStatement(getMatcherStatement(), getMatcherStatementArgs(0))
            .addStatement(getReturnStatement(), getReturnStatementArgs(inputType))
            .build());
  }

  private TypeName getReturnType(TypeName inputType) {
    ClassName unparameterizedType = ClassName.get(getDecomposableBuilderByArity(0));

    TypeName[] typeVariables = ImmutableList.of(inputType).stream().toArray(s -> new TypeName[s]);

    ParameterizedTypeName t = ParameterizedTypeName.get(unparameterizedType, typeVariables);
    return t;
  }

  private String getMatcherStatement() {
    return "$T matchers = $T.of()";
  }

  private Object[] getReturnStatementArgs(TypeName inputType) {

    TypeName[] typeVariables = ImmutableList.of(inputType).stream().toArray(s -> new TypeName[s]);

    TypeName returnType = ParameterizedTypeName
        .get(
            ClassName.get(getDecomposableBuilderByArity(typeVariables.length - 1)),
            typeVariables);

    List<Object> statementArgs = new ArrayList<>();
    statementArgs.add(returnType);
    statementArgs.add(fieldExtractor);

    return statementArgs.stream().toArray(s -> new Object[s]);
  }

  private String getReturnStatement() {
    return "return new $T(matchers, new $T<>())";
  }
}
