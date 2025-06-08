```text
Приведите словесный пример иерархии классов, и объясните, 
почему в ней нельзя ослаблять постусловия и усиливать предусловия.
```

## Предусловия не могут усиливаться

```java


class BankAccount {
    // Предусловие: amount >= 0
    // Постусловие: баланс увеличен на amount
    public void deposit(double amount) {}
}

class PremiumAccount extends BankAccount {
    @Override
    public void deposit(double amount) {
    // ОШИБКА: усиливаем предусловие - теперь требуем минимум $100
    if (amount < 100) {
        throw new IllegalArgumentException("Minimum deposit is $100");
    }
    super.deposit(amount);
}
```
```java
BankAccount account = new PremiumAccount(); // Полиморфное присваивание
account.deposit(50); // Ожидаем, что это сработает согласно спецификации BankAccount
```

Ожидается, что deposit(50) сработает, так как 50 >= 0 (предусловие базового класса выполнено). 
Но наследник усилил предусловие до 100, поэтому операция упадет с ошибкой. 
Это нарушает контракт базового класса.

## Постусловия не могу ослабляться

```java
class BankAccount {
    // Постусловие: баланс точно увеличен на amount
    public void deposit(double amount) {
        balance += amount;
    }
}

class PremiumAccount extends BankAccount {
    @Override
    public void deposit(double amount) {
        // ОШИБКА: ослабляем постусловие - теперь баланс увеличивается меньше чем на amount
        double commission = amount * 0.01; // 1% комиссия
        balance += (amount - commission);
    }
}
```

```java
BankAccount account = new PremiumAccount();
double oldBalance = account.getBalance();
account.deposit(100);
double newBalance = account.getBalance(); // текущий баланс будет меньше ожидаемого
```
Ожидается, что после deposit(100) увеличит баланс ровно на 100 (постусловие базового класса). 
Но наследник ослабил постусловие - теперь баланс увеличился только на 99. 
Это нарушает ожидания поведения, описанного в базовом классе.

Как итог потомки ломают базовую логику, предоставляемую предками.