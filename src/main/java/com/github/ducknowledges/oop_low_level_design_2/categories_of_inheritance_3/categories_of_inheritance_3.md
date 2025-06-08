```text
Приведите пример кода, где выполняется наследование реализации и льготное наследование.
```

##  Наследование реализации (implementation inheritance)

```java
// Полностью реализованный базовый класс
class Counter {
    
    protected int value;
    
    Counter(int initialValue) {
        this.value = initialValue;
    }
    
    public void increment() {
        value++;
    }
    
    public void decrement() {
        value--;
    }
    
    public int getValue() {
        return value;
    }
    
    public void reset() {
        value = 0;
    }
}

// Наследуем готовую реализацию и добавляем свою абстракцию
class StepCounter extends Counter {
    
    private int step;

    public StepCounter(int step, int initialValue) {
        super(initialValue);
        this.step = step;
    }

    // Используем готовую реализацию, но меняем логику
    @Override
    public void increment() {
        value += step;
    }

    @Override
    public void decrement() {
        value -= step;
    }
    
    public void setStep(int step) {
        this.step = step;
    }
}
```

## Льготное наследование (facility inheritance)

```java
import java.time.Instant;

// Базовый класс предоставляет стандартный набор компонентов для удобства
class ApplicationException extends Exception {
    
    protected final Instant timestamp;
    protected final String errorCode;
    protected final String technicalDetails;

    public ApplicationException(String errorCode, String message, String technicalDetails) {
        super(message);
        this.timestamp = System.currentTimeMillis();
        this.errorCode = errorCode;
        this.technicalDetails = technicalDetails;
    }

    // Стандартные методы для удобства
    public String getErrorCode() {
        return errorCode;
    }

    public String getTechnicalDetails() {
        return technicalDetails;
    }

    public long getTimestamp() {
        return timestamp;
    }
    
    protected void logError() {
        System.err.println("[ERROR] " + errorCode + " at " + timestamp + ": " + technicalDetails);
    }
}

class ValidationException extends ApplicationException {
    
    private final String fieldName;
    private final String invalidValue;
    
    public ValidationException(String fieldName, String invalidValue) {
        super(
                "VALIDATION_ERROR",
                "Invalid input provided",
                "Field '" + fieldName + "' has invalid value: " + invalidValue
        );
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
        logError();
    }
    
    public String getFieldName() {
        return this.fieldName;
    }
    
    public String getInvalidValue() {
        return this.invalidValue;
    }
}

class AuthorizationException extends ApplicationException {
    
    private final String requiredPermission;

    public AuthorizationException(String requiredPermission) {
        super(
                "AUTH_ERROR",
                "Access denied",
                "Required permission: " + requiredPermission
        );
        this.requiredPermission = requiredPermission;
        logError();
    }

    public String getRequiredPermission() {
        return requiredPermission;
    }
}
```