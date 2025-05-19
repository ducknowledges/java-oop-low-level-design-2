package com.github.ducknowledges.oop_low_level_design_2.inheritance_polymorphism_generics;

import java.util.ArrayList;
import java.util.List;

class Vector<T extends Any & Addable<T>> extends Any implements Addable<Vector<T>> {

  private final List<T> elements;

  public Vector() {
    elements = new ArrayList<>();
  }

  public Vector(List<T> elements) {
    this.elements = elements;
  }

  @Override
  public Vector<T> add(Vector<T> other) {
    if (this.size() != other.size()) {
      return null;
    }

    List<T> resultElements = new ArrayList<>();

    for (int i = 0; i < size(); i++) {
      resultElements.add(this.getElement(i).add(other.getElement(i)));
    }

    return new Vector<>(resultElements);
  }

  public void append(T element) {
    elements.add(element);
  }

  public int size() {
    return elements.size();
  }

  public T getElement(int i) {
    return elements.get(i);
  }

  public String toString() {
    return String.valueOf(this.elements);
  }
}

interface Addable<T extends Any> {
  T add(T other);
}

class General {}

class Any extends General {}

final class None extends Any {
  private static final None INSTANCE = new None();

  private None() {}

  public static None getInstance() {
    return INSTANCE;
  }
}

class Number extends Any implements Addable<Number> {

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

class Example {
  public static void main(String[] args) {
    // Создание и заполнение простых векторов
    Vector<Number> v1 = new Vector<>();
    v1.append(new Number(1));
    v1.append(new Number(2));
    v1.append(new Number(3));

    Vector<Number> v2 = new Vector<>();
    v2.append(new Number(4));
    v2.append(new Number(5));
    v2.append(new Number(6));

    // Сложение векторов методом add
    Vector<Number> sum1 = v1.add(v2);
    System.out.println("v1: " + v1);
    System.out.println("v2: " + v2);
    System.out.println("v1.add(v2): " + sum1);

    // Создание и сложение векторов векторов
    Vector<Vector<Number>> vv1 = new Vector<>();
    vv1.append(v1);
    vv1.append(v2);

    Vector<Vector<Number>> vv2 = new Vector<>();
    vv2.append(v2);
    vv2.append(v1);

    Vector<Vector<Number>> vvSum = vv1.add(vv2);
    System.out.println("\nСложение векторов векторов:");
    System.out.println("vv1: " + vv1);
    System.out.println("vv2: " + vv2);
    System.out.println("vv1.add(vv2): " + vvSum);

    // Создание и сложение трехуровневых векторов
    Vector<Vector<Vector<Number>>> vvv1 = new Vector<>();
    vvv1.append(vv1);

    Vector<Vector<Vector<Number>>> vvv2 = new Vector<>();
    vvv2.append(vv2);

    Vector<Vector<Vector<Number>>> vvvSum = vvv1.add(vvv2);
    System.out.println("\nСложение трехуровневых векторов:");
    System.out.println("vvv1.add(vvv2): " + vvvSum);
  }
}
