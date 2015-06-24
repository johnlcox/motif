package com.leacox.motif.generator;

import com.leacox.motif.extraction.FieldExtractor;

import com.google.auto.value.AutoValue;
import com.squareup.javapoet.TypeName;

/**
 * @author John Leacox
 */
@AutoValue
abstract class Match3MethodSpec {
  abstract String name();

  abstract Class<? extends FieldExtractor> matchExtractor();

  abstract TypeName paramAType();

  abstract String paramAName();

  abstract TypeName paramBType();

  abstract String paramBName();

  abstract TypeName paramCType();

  abstract String paramCName();

  public static Builder builder() {
    return new AutoValue_Match3MethodSpec.Builder();
  }

  @AutoValue.Builder
  abstract static class Builder {
    abstract Builder name(String methodName);

    abstract Builder matchExtractor(Class<? extends FieldExtractor> matchExtractor);

    abstract Builder paramAType(TypeName aType);

    abstract Builder paramAName(String aName);

    abstract Builder paramBType(TypeName bType);

    abstract Builder paramBName(String bName);

    abstract Builder paramCType(TypeName cType);

    abstract Builder paramCName(String cName);

    abstract Match3MethodSpec build();
  }
}
