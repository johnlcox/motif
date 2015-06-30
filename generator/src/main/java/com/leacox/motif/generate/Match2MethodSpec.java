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

import com.google.auto.value.AutoValue;
import com.squareup.javapoet.TypeName;

/**
 * @author John Leacox
 */
@AutoValue
public abstract class Match2MethodSpec {
  public abstract String name();

  public abstract Class<? extends FieldExtractor> matchExtractor();

  public abstract TypeName paramAType();

  public abstract String paramAName();

  public abstract TypeName paramBType();

  public abstract String paramBName();

  public static Builder builder() {
    return new AutoValue_Match2MethodSpec.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder name(String methodName);

    public abstract Builder matchExtractor(Class<? extends FieldExtractor> matchExtractor);

    public abstract Builder paramAType(TypeName aType);

    public abstract Builder paramAName(String aName);

    public abstract Builder paramBType(TypeName bType);

    public abstract Builder paramBName(String bName);

    public abstract Match2MethodSpec build();
  }
}
