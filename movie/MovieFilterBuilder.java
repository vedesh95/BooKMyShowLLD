package movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MovieFilterBuilder {
    private List<Movie> movies;

    public MovieFilterBuilder(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> filterByName(List<Object> movieNames){
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie: movies){
            if (movieNames.contains(movie.getName())){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    public List<Movie> filterByGenre(List<Object> movieGenres){
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie: movies){
            if (movieGenres.contains(movie.getMovieGenre())){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    public List<Movie> filterByLanguage(List<Object> movieLanguages){
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie: movies){
            if (movieLanguages.contains(movie.getMovieLanguage())){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    public List<Movie> filterByReleaseDate(List<Object> releaseDates){
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie: movies){
            if (releaseDates.contains(movie.getRelaseDate())){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    public List<Movie> build(HashMap<MovieFilterCriteria, List<Object>> map){
        for(MovieFilterCriteria movieFilterCriteria: map.keySet()){
            if(movieFilterCriteria.equals(MovieFilterCriteria.NAME)){
                movies = filterByName(map.get(movieFilterCriteria));
            }
            else if(movieFilterCriteria.equals(MovieFilterCriteria.GENRE)){
                movies = filterByGenre(map.get(movieFilterCriteria));
            }
            else if(movieFilterCriteria.equals(MovieFilterCriteria.LANGUAGE)){
                movies = filterByLanguage(map.get(movieFilterCriteria));
            }
            else if(movieFilterCriteria.equals(MovieFilterCriteria.RELEASEDATE)){
                movies = filterByReleaseDate(map.get(movieFilterCriteria));
            }
        }
        return movies;
    }

}
