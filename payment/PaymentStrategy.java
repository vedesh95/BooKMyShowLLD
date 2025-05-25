package payment;

public interface PaymentStrategy {
    public PaymentStatus pay(double amount);
}
