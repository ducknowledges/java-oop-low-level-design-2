package com.github.ducknowledges.oop_low_level_design_2.covariance;

import java.util.List;

public class Covariation {

    public static void main(String[] args) {
        // ПОЛИМОРФНЫЙ ВЫЗОВ
        A elementB = new B();
        elementB.doSomething();
        A elementC = new C();
        elementC.doSomething();

        // КОВАРИАНТНЫЙ ВЫЗОВ
        List<B> elementsB = List.of(new B());
        A.process(elementsB);

        List<C> elementsC = List.of(new C());
        A.process(elementsC);
    }

}

class A {

    public static <T extends A> void process(List<T> elements) {
        // Логика параметризована типом T
        for (T element : elements) {
            element.doSomething();
            System.out.println("Processing: " + element.getClass().getSimpleName());
        }
    }

    public void doSomething() {
        System.out.println("Doing something A");
    }
}

class B extends A {
    @Override
    public void doSomething() {
        System.out.println("Doing something B");
    }
}

class C extends A {
    @Override
    public void doSomething() {
        System.out.println("Doing something C");
    }
}