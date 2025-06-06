```text
Приведите примеры кода, реализующие наследование вариаций, 
наследование с конкретизацией и структурное наследование.
```

## Наследование вариаций

```java
// Переопределение логики без изменения сигнатуры
class Logger {
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}

class FileLogger extends Logger {
    @Override
    public void log(String message) {
        writeToFile("LOG: " + message);
    }
    
    private void writeToFile(String message) {
        // Запись в файл
    }
}
```

## Наследование с конкретизацией

```java
// Абстрактный класс с отложенной реализацией
abstract class Shape {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    public abstract double calculateArea();
    
}

class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double width, height;
    
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
}
```

## Структурное наследование

```java
// Структурное свойство - возможность сравнения
interface Comparable<T> {
    int compareTo(T other);
}

// Структурное свойство - возможность итерации
interface Iterable<T> {
    Iterator<T> iterator();
}

// Класс, наследующий структурные свойства
class Student implements Comparable<Student>, Iterable<Course> {
    private String name;
    private double averageScore;
    private List<Course> courses;
    
    public Student(String name, double averageScore) {
        this.name = name;
        this.averageScore = averageScore;
        this.courses = new ArrayList<>();
    }
    
    // Реализация структурного свойства сравнения
    @Override
    public int compareTo(Student other) {
        return Double.compare(this.averageScore, other.averageScore);
    }
    
    // Реализация структурного свойства итерации
    @Override
    public Iterator<Course> iterator() {
        return courses.iterator();
    }
}
```