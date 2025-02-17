package pl.gornik.insurancecompany;

import pl.gornik.insurancecompany.enums.PaymentMethod;

public class Payment {
    private double amount;
    private PaymentMethod paymentMethod;

    public Payment(double amount, PaymentMethod paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
}
