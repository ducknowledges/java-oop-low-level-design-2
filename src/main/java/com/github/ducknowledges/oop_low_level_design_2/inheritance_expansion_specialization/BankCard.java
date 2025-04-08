package com.github.ducknowledges.oop_low_level_design_2.inheritance_expansion_specialization;

import java.util.HashMap;
import java.util.Map;

public class BankCard {

    private final String cardNumber;
    private final String holderName;

    public BankCard(String cardNumber, String holderName) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
    }

    // Базовый метод для всех банковских карт
    public void displayCardInfo() {
        System.out.println("Bank Card: " + cardNumber + ", Holder: " + holderName);
    }

}

//Специализация класса-родителя
class DebitCard extends BankCard {

    protected double balance;

    public DebitCard(String cardNumber, String holderName, double initialBalance) {
        super(cardNumber, holderName);
        this.balance = initialBalance;
    }

    // Метод, специфичный для дебетовых карт
    public double getBalance() {
        return balance;
    }

}

// Расширение класса-родителя
class MulticurrencyDebitCard extends DebitCard {

    private final Map<String, Double> balances;
    private String currentCurrency;

    public MulticurrencyDebitCard(String cardNumber,
                                  String holderName,
                                  double initialBalance,
                                  String defaultCurrency) {
        super(cardNumber, holderName, initialBalance);
        balances = new HashMap<>();
        balances.put(defaultCurrency, initialBalance);
        this.currentCurrency = defaultCurrency;
    }

    // Переопределение метода родителя для работы с мультивалютностью
    @Override
    public double getBalance() {
        return balances.get(currentCurrency);
    }

    // Новый метод, расширения функционала для мультивалютных карт
    public void switchCurrency(String newCurrency) {
        if (balances.containsKey(newCurrency)) {
            currentCurrency = newCurrency;
            this.balance = balances.get(currentCurrency);
            System.out.println("Switched to " + newCurrency + ". Current balance: " + balance);
        } else {
            System.out.println("Currency " + newCurrency + " not available on this card");
        }
    }

}

