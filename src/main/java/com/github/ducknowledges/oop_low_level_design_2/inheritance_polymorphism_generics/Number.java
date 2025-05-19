package com.github.ducknowledges.oop_low_level_design_2.inheritance_polymorphism_generics;

public class Number extends Any implements Addable<Number> {

  private final Integer value;

  public Number(int value) {
    this.value = value;
  }

  @Override
  public Number add(Number other) {
    return new Number(this.value + other.getValue());
  }

  public int getValue() {
    return this.value;
  }

  public String toString() {
    return String.valueOf(this.value);
  }
}
