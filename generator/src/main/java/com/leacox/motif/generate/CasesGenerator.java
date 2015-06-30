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

import com.google.common.collect.Lists;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

/**
 * @author John Leacox
 */
public final class CasesGenerator {
  private final String packageName;
  private final String className;
  private final List<String> javadocs;
  private final List<MethodSpec> methods;

  private CasesGenerator(Builder builder) {
    this.packageName = builder.packageName;
    this.className = builder.className;
    this.javadocs = builder.javadocs;
    this.methods = builder.getAllMethods();
  }

  public static Builder newBuilder(
      String packageName, String className, TypeName matchType) {
    return new Builder(packageName, className, matchType);
  }

  public JavaFile generate() {
    MethodSpec privateConstructor = MethodSpec.constructorBuilder()
        .addModifiers(Modifier.PRIVATE)
        .build();

    TypeSpec.Builder casesClassBuilder = TypeSpec.classBuilder(className)
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .addMethod(privateConstructor)
        .addMethods(methods);

    for (String javadoc : javadocs) {
      casesClassBuilder.addJavadoc(javadoc);
    }

    TypeSpec casesClass = casesClassBuilder.build();

    JavaFile javaFile = JavaFile.builder(packageName, casesClass).build();
    return javaFile;
  }

  public static final class Builder {
    private static final int MAX_ARITY = 3;

    private final String packageName;
    private final String className;
    private final TypeName matchType;

    private final List<String> javadocs = Lists.newArrayList();
    private final List<MethodSpec> match0Methods = Lists.newArrayList();
    private final List<MethodSpec> match1Methods = Lists.newArrayList();
    private final List<MethodSpec> match2Methods = Lists.newArrayList();
    private final List<MethodSpec> match3Methods = Lists.newArrayList();

    private Builder(String packageName, String className, TypeName matchType) {
      this.packageName = packageName;
      this.className = className;
      this.matchType = matchType;
    }

    public Builder addJavadoc(String javadoc) {
      javadocs.add(javadoc);

      return this;
    }

    public Builder addMatch0Method(Match0MethodSpec match0MethodSpec) {
      match0Methods.addAll(
          new Match0MethodPermutationBuilder(
              matchType, match0MethodSpec.matchExtractor(), match0MethodSpec.summaryJavadoc(),
              match0MethodSpec.name())
              .build());

      return this;
    }

    public Builder addMatch1Method(Match1MethodSpec match1MethodSpec) {
      match1Methods.addAll(
          new Match1MethodPermutationBuilder(
              matchType, match1MethodSpec.matchExtractor(), match1MethodSpec.summaryJavadoc(),
              match1MethodSpec.name(),
              match1MethodSpec.paramAType(), match1MethodSpec.paramAName(), MAX_ARITY)
              .build());

      return this;
    }

    public Builder addMatch2Method(Match2MethodSpec match2MethodSpec) {
      match2Methods.addAll(
          new Match2MethodPermutationBuilder(
              matchType, match2MethodSpec.matchExtractor(), match2MethodSpec.summaryJavadoc(),
              match2MethodSpec.name(),
              match2MethodSpec.paramAType(), match2MethodSpec.paramAName(),
              match2MethodSpec.paramBType(), match2MethodSpec.paramBName(), MAX_ARITY)
              .build());

      return this;
    }

    public Builder addMatch3Method(Match3MethodSpec match3MethodSpec) {
      match3Methods.addAll(
          new Match3MethodPermutationBuilder(
              matchType, match3MethodSpec.matchExtractor(), match3MethodSpec.summaryJavadoc(),
              match3MethodSpec.name(),
              match3MethodSpec.paramAType(), match3MethodSpec.paramAName(),
              match3MethodSpec.paramBType(), match3MethodSpec.paramBName(),
              match3MethodSpec.paramCType(), match3MethodSpec.paramCName(), MAX_ARITY)
              .build());

      return this;
    }

    private List<MethodSpec> getMatch0Methods() {
      return match0Methods != null ? match0Methods : Collections.emptyList();
    }

    private List<MethodSpec> getMatch1Methods() {
      return match1Methods != null ? match1Methods : Collections.emptyList();
    }

    private List<MethodSpec> getMatch2Methods() {
      return match2Methods != null ? match2Methods : Collections.emptyList();
    }

    private List<MethodSpec> getMatch3Methods() {
      return match3Methods != null ? match3Methods : Collections.emptyList();
    }

    private List<MethodSpec> getAllMethods() {
      return Stream
          .of(getMatch0Methods(), getMatch1Methods(), getMatch2Methods(), getMatch3Methods())
          .flatMap(l -> l.stream())
          .collect(Collectors.toList());
    }

    public CasesGenerator build() {
      return new CasesGenerator(this);
    }
  }
}
