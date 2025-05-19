package com.github.ducknowledges.oop_low_level_design_2.inheritance_composition_polymorphism;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    // Создаем разные типы транспортных средств с использованием общего базового типа
    Vehicle vehicle1 = new ElectricCar(new Battery(75));
    Vehicle vehicle2 = new PetrolCar(new Tank(60));

    // Полиморфизм: объекты разных типов в одной коллекции
    List<Vehicle> vehicles = List.of(vehicle1, vehicle2);

    // Полиморфизм: для каждого объекта вызывается своя реализация методов
    for (Vehicle vehicle : vehicles) {
      System.out.println(
          "Fuel level: " + vehicle.getFuelLevelInPercent() + "%, Type: " + vehicle.getType());
    }
  }
}

// Абстрактный базовый класс для иерархии транспортных средств
abstract class Vehicle {

  public abstract int getFuelLevelInPercent();

  public abstract String getType();
}

// Наследование: ElectricCar является (is-a) транспортным средством Vehicle
class ElectricCar extends Vehicle {

  // Композиция: электромобиль содержит (has-a) батарею
  private final Battery battery;

  public ElectricCar(Battery battery) {
    this.battery = battery;
  }

  // Полиморфизм: специфическая реализация метода базового класса
  @Override
  public int getFuelLevelInPercent() {
    return battery.getLevelInPercent();
  }

  // Полиморфизм: специфическая реализация метода базового класса
  @Override
  public String getType() {
    return "Electric Car";
  }
}

// Наследование: PetrolCar является (is-a) транспортным средством Vehicle
class PetrolCar extends Vehicle {

  // Композиция: бензиновый автомобиль содержит (has-a) топливный бак
  private final Tank tank;

  public PetrolCar(Tank tank) {
    this.tank = tank;
  }

  // Полиморфизм: специфическая реализация метода базового класса
  @Override
  public int getFuelLevelInPercent() {
    return tank.getLevelInPercent();
  }

  // Полиморфизм: специфическая реализация метода базового класса
  @Override
  public String getType() {
    return "Petrol Car";
  }
}

// Абстрактный базовый класс для хранилищ энергии
abstract class FuelStorage {

  private int levelPercent;

  public FuelStorage(int initialLevel) {
    this.levelPercent = initialLevel;
  }

  public int getLevelInPercent() {
    return levelPercent;
  }
}

// Наследование: Tank является (is-a) хранилищем энергии EnergyStorage
class Tank extends FuelStorage {

  public Tank(int initialLevel) {
    super(initialLevel);
  }
}

// Наследование: Battery является (is-a) хранилищем энергии EnergyStorage
class Battery extends FuelStorage {

  public Battery(int initialLevel) {
    super(initialLevel);
  }
}
