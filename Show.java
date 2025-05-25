import movie.Movie;
import seat.Seat;
import seat.SeatStatus;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Show {
    private int id;
    private Movie movie;
    private CinemaHall cinemaHall;
    private List<Seat> seats;
    private Date startDate;
    private Date endDate;

    public Show(CinemaHall cinemaHall, int id, Movie movie, List<Seat> seats, Date startDate) {
        this.cinemaHall = cinemaHall;
        this.id = id;
        this.movie = movie;
        this.seats = seats;
        this.startDate = startDate;
        this.endDate = new Time(startDate.getTime() + movie.getDuration().getTime());
    }

    public List<Seat> getAvailableSeats(){
        return seats.stream().filter(Seat::isSeatAvailableToBook).toList();
    }

    public boolean checkSeatsAvailability(List<Integer> seatIds){
        return seats.stream().allMatch(x -> x.isSeatAvailableToBook());
    }

    public synchronized void changeSeatStatus(List<Integer> seatIds, SeatStatus seatStatus){
        seats.stream().filter(x -> seatIds.contains(x.getId())).forEach(x -> x.setSeatStatus(seatStatus));
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public int getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Date getstartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "Show{" +
                ", id=" + id +
                ", movie=" + movie +
                ", seats=" + seats +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
