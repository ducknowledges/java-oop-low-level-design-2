## Плохой подход (с полем-перечислением)

```java
enum Gender {
    MALE, FEMALE, NON_BINARY, //...
}

class Person extends Any {
    private String name;
    private Gender gender;

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.gender = gender;
    }
    
    public String getTitle() {
        if (gender == Gender.MALE) {
            return "Mr. " + name;
        } else if (gender == Gender.FEMALE) {
            return "Ms. " + name;
        } else if (gender == Gender.NON_BINARY) {
            return "Mx. " + name;
        }
        return name;
    }

    public String getPronouns() {
        if (gender == Gender.MALE) {
            return "he/him";
        } else if (gender == Gender.FEMALE) {
            return "she/her";
        } else if (gender == Gender.NON_BINARY) {
            return "they/them";
        }
        return "none";
    }
}
```

Хороший подход(с решением конфликта таксономии)
```java
abstract class Person extends Any {
    
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public abstract String getTitle();
    public abstract String getPronouns();
}

class MalePerson extends Person {
    
    public MalePerson(String name, int age) {
        super(name, age);
    }

    @Override
    public String getTitle() {
        return "Mr. " + name;
    }

    @Override
    public String getPronouns() {
        return "he/him";
    }
}

class FemalePerson extends Person {
    
    public FemalePerson(String name) {
        super(name);
    }

    @Override
    public String getTitle() {
        return "Ms. " + name;
    }

    @Override
    public String getPronouns() {
        return "she/her";
    }
}

class NonBinaryPerson extends Person {
    
    public NonBinaryPerson(String name) {
        super(name);
    }

    @Override
    public String getTitle() {
        return "Mx. " + name;
    }

    @Override
    public String getPronouns() {
        return "they/them";
    }
}
```