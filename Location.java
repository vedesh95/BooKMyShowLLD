import movie.Movie;

import java.util.List;

public class Location {
    private int id;
    private String name;
    private List<Movie> movies;
    private List<Show> shows;

    public Location(int id, List<Movie> movies, String name, List<Show> shows) {
        this.id = id;
        this.movies = movies;
        this.name = name;
        this.shows = shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }



    public int getId() {
        return id;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                ", shows=" + shows +
                '}';
    }
}
