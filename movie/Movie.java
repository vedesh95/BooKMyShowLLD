package movie;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Movie {
    private int id;
    private String name;
    private String description;
    private Time duration;
    private List<String> reviews;
    private List<String> trailers;
    private MovieGenre movieGenre;
    private MovieLanguage movieLanguage;
    private Date relaseDate;

    public Movie(String description, Time duration, int id, MovieGenre movieGenre, MovieLanguage movieLanguage, String name, Date relaseDate, List<String> reviews, List<String> trailers) {
        this.description = description;
        this.duration = duration;
        this.id = id;
        this.movieGenre = movieGenre;
        this.movieLanguage = movieLanguage;
        this.name = name;
        this.relaseDate = relaseDate;
        this.reviews = reviews;
        this.trailers = trailers;
    }

    public String getDescription() {
        return description;
    }

    public Time getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }

    public MovieGenre getMovieGenre() {
        return movieGenre;
    }

    public MovieLanguage getMovieLanguage() {
        return movieLanguage;
    }

    public String getName() {
        return name;
    }

    public Date getRelaseDate() {
        return relaseDate;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public List<String> getTrailers() {
        return trailers;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", reviews=" + reviews +
                ", trailers=" + trailers +
                ", movieGenre=" + movieGenre +
                ", movieLanguage=" + movieLanguage +
                ", relaseDate=" + relaseDate +
                '}';
    }
}
