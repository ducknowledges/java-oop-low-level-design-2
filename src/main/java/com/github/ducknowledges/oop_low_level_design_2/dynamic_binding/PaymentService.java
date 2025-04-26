package com.github.ducknowledges.oop_low_level_design_2.dynamic_binding;

import java.util.EnumMap;
import java.util.Map;

public class PaymentService {
    private final Map<PaymentMethod, PaymentProcessor> processors;

    public PaymentService() {
        // Инициализация процессоров - каждый тип платежа связывается со своим процессором
        processors = new EnumMap<>(PaymentMethod.class);
        processors.put(PaymentMethod.CREDIT_CARD, new CreditCardProcessor());
        processors.put(PaymentMethod.PAYPAL, new PayPalProcessor());
    }

    public void processPayment(PaymentRequest request) {
        // Получаем конкретный процессор на основе метода оплаты
        PaymentProcessor processor = getProcessor(request.getPaymentMethod());

        // ДИНАМИЧЕСКОЕ СВЯЗЫВАНИЕ ПРОИСХОДИТ ЗДЕСЬ:
        // На этапе компиляции Java не знает, какой конкретно метод processPayment
        // будет вызван - CreditCardProcessor или PayPalProcessor.
        // Фактический метод определяется во время выполнения на основе
        // реального типа объекта processor.
        processor.processPayment(request);
    }

    private PaymentProcessor getProcessor(PaymentMethod method) {
        // Получаем процессор из мапы - тип возвращаемого значения PaymentProcessor,
        // но фактический объект будет CreditCardProcessor или PayPalProcessor
        PaymentProcessor processor = processors.get(method);
        if (processor == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
        return processor;
    }
}

// Базовый класс для всех способов оплаты
abstract class PaymentProcessor {
    // Абстрактный метод, который будет реализован в каждом конкретном процессоре
    // Этот метод демонстрирует полиморфизм и динамическое связывание
    public abstract void processPayment(PaymentRequest request);
}

// Конкретная реализация для обработки банковских карт
class CreditCardProcessor extends PaymentProcessor {
    @Override
    public void processPayment(PaymentRequest request) {
        // Специфичная логика обработки платежа по карте
        // Этот метод будет вызван, когда processor.processPayment() вызывается
        // для объекта CreditCardProcessor
        System.out.println("CreditCardProcessor.processPayment");
    }
}

// Конкретная реализация для обработки PayPal
class PayPalProcessor extends PaymentProcessor {
    @Override
    public void processPayment(PaymentRequest request) {
        // Специфичная логика обработки платежа через PayPal
        // Этот метод будет вызван, когда processor.processPayment() вызывается
        // для объекта PayPalProcessor
        System.out.println("PayPalProcessor.processPayment");
    }
}

enum PaymentMethod {
    CREDIT_CARD, PAYPAL
}

class PaymentRequest {
    private PaymentMethod paymentMethod;

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}