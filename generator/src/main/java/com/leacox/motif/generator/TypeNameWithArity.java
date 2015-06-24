package com.leacox.motif.generator;

import com.squareup.javapoet.TypeName;

/**
 * @author John Leacox
 */
final class TypeNameWithArity {
  public final TypeName typeName;
  public final int arity;

  private TypeNameWithArity(TypeName typeName, int arity) {
    this.typeName = typeName;
    this.arity = arity;
  }

  public static TypeNameWithArity of(TypeName typeName, int arity) {
    return new TypeNameWithArity(typeName, arity);
  }
}

