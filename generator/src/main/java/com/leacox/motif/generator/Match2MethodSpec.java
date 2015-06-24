package com.leacox.motif.generator;

import com.leacox.motif.extraction.FieldExtractor;

import com.google.auto.value.AutoValue;
import com.squareup.javapoet.TypeName;

/**
 * @author John Leacox
 */
@AutoValue
abstract class Match2MethodSpec {
  abstract String name();

  abstract Class<? extends FieldExtractor> matchExtractor();

  abstract TypeName paramAType();

  abstract String paramAName();

  abstract TypeName paramBType();

  abstract String paramBName();

  public static Builder builder() {
    return new AutoValue_Match2MethodSpec.Builder();
  }

  @AutoValue.Builder
  abstract static class Builder {
    abstract Builder name(String methodName);

    abstract Builder matchExtractor(Class<? extends FieldExtractor> matchExtractor);

    abstract Builder paramAType(TypeName aType);

    abstract Builder paramAName(String aName);

    abstract Builder paramBType(TypeName bType);

    abstract Builder paramBName(String bName);

    abstract Match2MethodSpec build();
  }
}
