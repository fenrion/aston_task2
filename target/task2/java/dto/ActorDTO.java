package dto;


import entity.Movie;
import entity.PhoneNumber;

import java.util.List;

public class ActorDTO {
    private String name;
    private List<Movie> movieList;
    private List<PhoneNumber> phoneNumberList;

    public ActorDTO() {
    }

    public ActorDTO(String name, List<Movie> movieList, List<PhoneNumber> phoneNumberList) {
        this.name = name;
        this.movieList = movieList;
        this.phoneNumberList = phoneNumberList;
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
