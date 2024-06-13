package entity;

import java.util.List;

/**
 * Actor entity
 * relation:
 * Many to Many (Movie)
 * One to Many (PhoneNumber)
 */
public class Actor {
    private int id;
    private String name;
    private List<Movie> movieList;
    private List<PhoneNumber> phoneNumberList;

    public Actor() {
    }

    public Actor(int id, String name, List<Movie> movieList, List<PhoneNumber> phoneNumberList) {
        this.id = id;
        this.name = name;
        this.movieList = movieList;
        this.phoneNumberList = phoneNumberList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<PhoneNumber> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<PhoneNumber> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }
}
