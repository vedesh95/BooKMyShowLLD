package seat;

public class Seat {
    private int id;
    private int row;
    private int column;
    private SeatStatus seatStatus;
    private SeatType seatType;
    private double price;

    public Seat(int id, int row, int column, SeatStatus seatStatus, SeatType seatType, double price) {
        this.column = column;
        this.id = id;
        this.price = price;
        this.row = row;
        this.seatStatus = seatStatus;
        this.seatType = seatType;
    }

    public boolean isSeatAvailableToBook(){
        return seatStatus.equals(SeatStatus.AVAILABLE);
    }

    public int getColumn() {
        return column;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getRow() {
        return row;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "column=" + column +
                ", id=" + id +
                ", row=" + row +
                ", seatStatus=" + seatStatus +
                ", seatType=" + seatType +
                ", price=" + price +
                '}';
    }
}
