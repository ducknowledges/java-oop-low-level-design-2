package com.github.ducknowledges.oop_low_level_design_2.Inheritance_polymorphism_generics;

public interface Addable<T extends Any> {
  T add(T other);
}
