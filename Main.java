import movie.*;
import seat.Seat;
import seat.SeatStatus;
import seat.SeatType;
import java.sql.Time;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();

        Movie movie1 = new Movie("Sooksh", new Time(2,0,0), 1, MovieGenre.HORROR, MovieLanguage.MALYALI, "movie1", null, null, null );
        Movie movie2 = new Movie("KishkindaKandam", new Time(2,30,0), 2, MovieGenre.THRILLER, MovieLanguage.MALYALI, "movie2", null, null, null );
        Movie movie3= new Movie("Devara", new Time(3,15, 0), 3, MovieGenre.FEELGOOD,MovieLanguage.TAMIL, "movie3", null, null, null );
        bookingSystem.addMovies(List.of(movie1, movie2, movie3));

        Location location1 = new Location(1, List.of(movie1, movie2), "Bengaluru", null);
        Location location2 = new Location(2, List.of(movie3), "Mumbai", null);
        bookingSystem.addLocations(List.of(location1, location2));

        CinemaHall cinemaHall1 = new CinemaHall(1, location1, "PVR", new ArrayList<>());
        CinemaHall cinemaHall2 = new CinemaHall(2, location1, "Inox", new ArrayList<>());
        CinemaHall cinemaHall3 = new CinemaHall(3, location2, "Cinemax", new ArrayList<>());
        bookingSystem.addCinemaHalls(List.of(cinemaHall1, cinemaHall2, cinemaHall3));

        Show show1 = new Show(cinemaHall1, 1, movie1, generateSeats(15, 25, 5, 200, 100), new Date(2026, 6, 5, 19, 0 , 0));
        Show show2 = new Show(cinemaHall1, 2, movie1, generateSeats(20, 15, 5, 250, 150), new Date(2026, 6, 5, 22, 0 , 0));
        Show show3 = new Show(cinemaHall2, 3, movie2, generateSeats(15, 25, 5, 200, 100), new Date(2026, 6, 6, 19, 0 , 0));
        Show show4 = new Show(cinemaHall3, 4, movie3, generateSeats(15, 25, 5, 200, 100), new Date(2026, 6, 6, 19, 0 , 0));
        Show show5 = new Show(cinemaHall3, 5, movie1, generateSeats(15, 25, 5, 200, 100), new Date(2026, 6, 5, 16, 0 , 0));
        bookingSystem.addShows(List.of(show1, show2, show3, show4, show5));

        User user1 = new User("1@1.com", 1, "1");
        User user2 = new User("2@2.com", 2, "2");
        bookingSystem.addUsers(List.of(user1, user2));

        //req-1
        bookingSystem.getMoviesForALocation(2).stream().forEach(x  -> System.out.print(x.getDescription() + " "));
        System.out.println();
        //req-2
        bookingSystem.getAllShowsForAMovieAfterCurrentTime(1).stream().forEach(x  -> System.out.print(x.getId() + " "));
        System.out.println();
        //req-3
        HashMap<MovieFilterCriteria, List<Object>> map = new HashMap<>();
        map.put(MovieFilterCriteria.GENRE, List.of(MovieGenre.COMEDY, MovieGenre.THRILLER));
        map.put(MovieFilterCriteria.LANGUAGE, List.of(MovieLanguage.MALYALI));
        bookingSystem.filterMovies(1, map).stream().forEach(x  -> System.out.print(x.getDescription() + " "));
        System.out.println();
        //req-4
        List<Show> shows = bookingSystem.getAllShowsByCinemaAfterCurrentTime(1);
        shows.forEach(x  -> System.out.print(x.getId() + " "));
        System.out.println();
        bookingSystem.getAvailableSeatsForAShow(shows.get(0).getId()).stream().forEach(x  -> System.out.print( x.getId() + " "));
        System.out.println();
        //req-5
        System.out.println("req-5");
        System.out.println(bookingSystem.bookTickets(user1, 1, List.of(2,3,4)));
        System.out.println(bookingSystem.getAvailableSeatsForAShow(1).stream().anyMatch(x ->List.of(2,3,4).contains(x.getId())));
        System.out.println();
        //req-6
        Thread t1 = new Thread(() -> {
            System.out.println(bookingSystem.bookTickets(user1, 2, List.of(1,2,3)));
        });
        Thread t2 = new Thread(() -> {
            System.out.println(bookingSystem.bookTickets(user2, 2, List.of(3,4,4)));
        });
        t1.start();
        t2.start();
        //req-7
        bookingSystem.getMovieDetails(1);
    }

    public static  List<Seat> generateSeats(int rows, int columns, int premiumRows, int premiumPrice, int standardPrice){
        List<Seat> seats= new ArrayList<>();
        for(int i=1; i<= rows; i++){
            for (int j=1; j<=columns; j++){
                if(rows -i < premiumRows) seats.add(new Seat(2*j + i, i, j, SeatStatus.AVAILABLE, SeatType.PREMIUM, premiumPrice));
                else seats.add(new Seat(2*j + i, i, j, SeatStatus.AVAILABLE, SeatType.STANDARD, standardPrice));
            }
        }
        return seats;
    }

}
