```text
Приведите пример иерархии, которая реализует наследование вида, и объясните, почему.
```

## Наследование вида

```java
// Основной класс автомобиля
class Vehicle {
    private Engine engine;           // has-a (автомобиль содержит двигатель)
    private Transmission transmission; // has-a (автомобиль содержит коробку передач)
    private String model;
    
    public Vehicle(Engine engine, Transmission transmission, String model) {
        this.engine = engine;
        this.transmission = transmission;
        this.model = model;
    }
    
    public double getFuelConsumption() {
        return engine.getBaseFuelConsumption() * transmission.getEfficiencyFactor();
    }
    
    public String getDescription() {
        return model + " with " + engine.getEngineDescription() + " and " + transmission.getTransmissionDescription();
    }
    
    public void start() {
        engine.start();
        transmission.engage();
    }
}

// Первая иерархия: тип двигателя
abstract class Engine {
    public abstract double getBaseFuelConsumption();
    public abstract String getEngineDescription();
    public abstract void start();
}

class PetrolEngine extends Engine {
    
    public double getBaseFuelConsumption() { 
        return 8.0; 
    }
    
    public String getEngineDescription() { 
        return "Petrol Engine"; 
    }
    public void start() { 
        System.out.println("Starting petrol engine"); 
    }
}

class DieselEngine extends Engine {
    
    public double getBaseFuelConsumption() { 
        return 15.0; 
    }
    
    public String getEngineDescription() { 
        return "Diesel engine"; 
    }
    
    public void start() { 
        System.out.println("Starting diesel motor"); 
    }
}

// Вторая иерархия: тип коробки передач
abstract class Transmission {
    public abstract double getEfficiencyFactor();
    public abstract String getTransmissionDescription();
    public abstract void engage();
}

class ManualTransmission extends Transmission {
    
    public double getEfficiencyFactor() { 
        return 0.9; 
    }
    
    public String getTransmissionDescription() { 
        return "Manual Transmission"; 
    }
    
    public void engage() { 
        System.out.println("Engaging manual transmission"); 
    }
}

class AutomaticTransmission extends Transmission {
    
    public double getEfficiencyFactor() { 
        return 1.1; 
    } 
    
    public String getTransmissionDescription() { 
        return "Automatic Transmission"; 
    }
    
    public void engage() { 
        System.out.println("Engaging automatic transmission"); 
    }
}
```

Почему это наследование вида:
- Два независимых критерия: 
  - тип двигателя
  - тип коробки передач
- Естественные отношения has-a:
  - Автомобиль содержит двигатель
  - Автомобиль содержит коробку передач
- Любой двигатель может работать с любой коробкой передач
- Отдельные иерархии: 
  - можно независимо добавлять новые типы двигателей или коробки передач
- Соответствие предметной области: отражает реальную структуру автомобиля