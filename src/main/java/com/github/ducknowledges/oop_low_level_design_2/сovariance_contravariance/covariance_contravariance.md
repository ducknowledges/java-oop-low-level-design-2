## КОВАРИАНТНОСТЬ В JAVA

### 1. Ковариантность возвращаемых типов методов

```java
// Базовый класс для финансовой транзакции
class Transaction {
    private String id;
    private double amount;

    public Transaction(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }
    
    public String getId() {
        return id;
    }
    
    public double getAmount() {
        return amount;
    }
}
```

```java
// Специализированный класс для платежей
class PaymentTransaction extends Transaction {
    private final String recipientAccount;

    public PaymentTransaction(String id, double amount, String recipientAccount) {
        super(id, amount);
        this.recipientAccount = recipientAccount;
    }
    
    public String getRecipientAccount() {
        return recipientAccount;
    }
}
```

```java
//Фабрики для создания транзакций
class TransactionFactory {
// Возвращает базовый тип Transaction
    public Transaction createTransaction(String id, double amount) {
        return new Transaction(id, amount);
    }
}
```

```java
// Ковариантность: переопределенный метод возвращает более специализированный тип
class PaymentFactory extends TransactionFactory {
    @Override
    public Payment createTransaction(String id, double amount) {
        return new Payment(id, amount, "default-account");
    }
}
```
### 2. Ковариантность с использованием generic wildcards
```java
class FinancialReportProcessor {
    // Ковариантный метод - может принимать списки любых подтипов Transaction
    public double calculateTotal(List<? extends Transaction> transactions) {
        double total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }
}
```
## КОНТРАВАРИАНТНОСТЬ В JAVA


### 1. Контравариантность с использованием generic wildcards
```java
// Обработчик транзакций
class TransactionProcessor {
    // Контравариантный метод - может обрабатывать Transaction и любые супертипы
    public void processTransactions(List<? super Payment> transactions, Payment payment) {
        // Можно добавлять элементы типа Payment в список
        transactions.add(payment);
    }
}
```

### 2. Контравариантность с компараторами

```java
// Компаратор для базового класса Transaction
class TransactionAmountComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return Double.compare(t1.getAmount(), t2.getAmount());
    }
}
```

```java
// Класс для сортировки платежей
class PaymentTransactionSorter {
    // Метод принимает компаратор для Transaction, хотя сортирует Payment
    // Это контравариантность - компаратор для суперкласса может обрабатывать подклассы
    public void sortPaymentsByAmount(List<Payment> payments, Comparator<Transaction> comparator) {
        payments.sort(comparator);
    }
}
```