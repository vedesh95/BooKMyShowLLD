package payment;

import static java.lang.Thread.sleep;

public class UPIPay implements PaymentStrategy{
    @Override
    public PaymentStatus pay(double amount){
        // payment logic goes here
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
            return PaymentStatus.FAILURE;
        }
        return PaymentStatus.SUCCESS;
    }
}
