package com.github.ducknowledges.oop_low_level_design_2.inheritance_polymorphism_generics;

class General {}

class Any extends General {}

final class None extends Any {
  private static final None INSTANCE = new None();

  private None() {}

  public static None getInstance() {
    return INSTANCE;
  }
}
