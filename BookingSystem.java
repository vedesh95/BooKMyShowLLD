import movie.Movie;
import movie.MovieFilterBuilder;
import movie.MovieFilterCriteria;
import payment.UPIPay;
import seat.Seat;

import javax.swing.event.ListDataEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BookingSystem {
    private Map<Integer, User> userMap;
    private Map<Integer, Show> showMap;
    private Map<Integer, Location> locationMap;
    private Map<Integer, Movie> movieMap;
    private Map<Integer, CinemaHall> cinemaHallMap;

    public BookingSystem() {
        userMap = new HashMap<>();
        showMap = new ConcurrentHashMap<>();
        locationMap = new HashMap<>();
        movieMap = new HashMap<>();
        cinemaHallMap = new HashMap<>();
    }
//
//    public void addMovieToLocation(Integer locationId, Movie movie){
//        if(locationMap.get(locationId) != null){
//            locationMap.get(locationId).addMovie(movie);
//        }
//    }

    public List<Movie> getMoviesForALocation(Integer locationId){
        List<Movie> movies = new ArrayList<>();
        if(locationMap.get(locationId) != null){
            movies = locationMap.get(locationId).getMovies();
        }
        return movies;
    }


    public List<Show> filterShowsAfterCurrentTime(List<Show> shows){
        return shows.stream().filter(show -> show.getstartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now())).toList();
    }

    public List<Show> getAllShowsForAMovieAfterCurrentTime(int movieId){
        List<Show> shows = showMap.values().stream().filter(show -> show.getMovie().getId() == movieId).toList();
        return filterShowsAfterCurrentTime(shows);
    }

    public List<Show> getAllShowsByCinemaAfterCurrentTime(int cinemaHallId){
        return filterShowsAfterCurrentTime(cinemaHallMap.get(cinemaHallId).getShows());
    }

    public List<Movie> filterMovies(Integer locationId, HashMap<MovieFilterCriteria, List<Object>> map){
        List<Movie> movies = locationMap.get(locationId).getMovies();
        MovieFilterBuilder builder = new MovieFilterBuilder(movies);
        return builder.build(map);
    }

    public List<Seat> getAvailableSeatsForAShow(Integer showId){
        return showMap.get(showId).getAvailableSeats();
    }


    public synchronized BOOKINGSTATUS bookTickets(User user, Integer showId, List<Integer> seatIds){
        String bookingId =  generateBookingId();
        if(!showMap.get(showId).checkSeatsAvailability(seatIds)) return BOOKINGSTATUS.FAILED;
        Booking booking = new Booking(bookingId, new UPIPay(), seatIds, showMap.get(showId), user);
        booking.book();
        return booking.getBookingstatus();
    }
    public String generateBookingId(){
        return "BMS" + LocalDate.now().toString() + LocalTime.now().toString() + LocalTime.now();
    }
    public Movie getMovieDetails(Integer movieId){
        return movieMap.get(movieId);
    }


    public void addUsers(List<User> users){
        users.forEach(user -> userMap.put(user.getId(), user));
    }

    public void addMovies(List<Movie> movies){
        movies.forEach(movie -> movieMap.put(movie.getId(), movie));
    }

    public void addLocations(List<Location> locations){
        locations.forEach(location -> locationMap.put(location.getId(), location));
    }

    public void addShows(List<Show> shows){
        shows.forEach(show -> {showMap.put(show.getId(), show);cinemaHallMap.get(show.getCinemaHall().getId()).addShow(show);});
    }

    public void addCinemaHalls(List<CinemaHall> cinemaHalls){
        cinemaHalls.forEach(cinemaHall -> cinemaHallMap.put(cinemaHall.getId(), cinemaHall));
    }

    public void addShowsToACinemaHall(int cinemaHallId, List<Show> shows){
        cinemaHallMap.get(cinemaHallId).addShows(shows);
    }

    public Show getShow(int showId){
        return showMap.get(showId);
    }
}
