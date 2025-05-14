```text
Разберитесь, какие из четырёх вариантов скрытия методов доступны в используемом вами языке 
программирования. Приведите примеры кода для каждого из доступных вариантов.
```

## 1. метод публичен в родительском классе А и публичен в его потомке B;
```java
class Parent {
    private String value;
    
    public String getValue() {
        return this.value;
    }
}

class Child extends Parent {
    @Override
    public String getValue() {
        return super.getValue();
    }
}
```

## 2. метод публичен в родительском классе А и скрыт в его потомке B;
```text
Если метод публичен в родительском классе, он должен оставаться публичным 
или стать более открытым в потомке.
```

## 3. метод скрыт в родительском классе А и публичен в его потомке B;
```java
class Parent {
    private String value;

    protected String getValue() {
        return this.value;
    }
}

class Child extends Parent {
    @Override
    public String getValue() {
        return super.getValue();
    }
}
```

## 4. метод скрыт в родительском классе А и скрыт в его потомке B.
```java
class Parent {
    private String value;

    protected String getValue() {
        return this.value;
    }
}

class Child extends Parent {
    @Override
    protected String getValue() {
        return super.getValue();
    }
}
```