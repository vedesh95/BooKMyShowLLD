import java.util.List;

public class CinemaHall {
    private int id;
    private String name;
    private Location location;
    private List<Show> shows;

    public CinemaHall(int id, Location location, String name, List<Show> shows) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.shows = shows;
    }

    public void addShows(List<Show> shows){
        this.shows.addAll(shows);
    }

    public void addShow(Show show){
        this.shows.add(show);
    }

    public List<Show> getShows() {
        return shows;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CinemaHall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", shows=" + shows +
                '}';
    }
}
