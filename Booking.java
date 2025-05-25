import payment.PaymentStatus;
import payment.PaymentStrategy;
import seat.Seat;
import seat.SeatStatus;

import java.util.List;
import java.util.Map;

public class Booking {
    private String id;
    private User user;
    private Show show;
    private List<Integer> seatIds;
    private double amount;
    private BOOKINGSTATUS bookingstatus;
    private PaymentStrategy paymentStrategy;

    public Booking(String id, PaymentStrategy paymentStrategy, List<Integer> seatIds, Show show, User user) {
        this.id = id;
        this.paymentStrategy = paymentStrategy;
        this.seatIds = seatIds;
        this.show = show;
        this.user = user;
        this.bookingstatus = BOOKINGSTATUS.PENDING;
        setAmount();
    }

    public PaymentStatus pay(double amount){
        return paymentStrategy.pay(amount);
    }

    public synchronized void book(){
        show.changeSeatStatus(seatIds, SeatStatus.PENDING);
        setBookingstatus(BOOKINGSTATUS.PENDING);

        PaymentStatus paymentStatus = pay(amount);
        if(paymentStatus.equals(PaymentStatus.SUCCESS)){
            show.changeSeatStatus(seatIds, SeatStatus.BOOKED);
            setBookingstatus(BOOKINGSTATUS.CONFIRMED);
            return;
        }

        show.changeSeatStatus(seatIds, SeatStatus.AVAILABLE);
        setBookingstatus(BOOKINGSTATUS.FAILED);
    }


    public void setAmount() {
        for(Seat seat: show.getSeats()){
            if(seatIds.contains(seat.getId())){
                amount += seat.getPrice();
            }
        }
    }

    public void setBookingstatus(BOOKINGSTATUS bookingstatus) {
        this.bookingstatus = bookingstatus;
    }

    public BOOKINGSTATUS getBookingstatus() {
        return bookingstatus;
    }
}
