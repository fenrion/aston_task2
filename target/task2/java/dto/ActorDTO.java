package dto;


import java.util.List;

public class ActorDTO {
    private String name;
    private List<MovieDTO> movieList;
    private List<PhoneNumberPostDTO> phoneNumberList;

    public ActorDTO() {
    }

    public ActorDTO(String name, List<MovieDTO> movieList, List<PhoneNumberPostDTO> phoneNumberList) {
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

    public List<MovieDTO> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieDTO> movieList) {
        this.movieList = movieList;
    }

    public List<PhoneNumberPostDTO> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<PhoneNumberPostDTO> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Actor's name: ").append(name).append("\n").append("movies: \n");
        for(MovieDTO movie:movieList){
            sb.append(movie.getName()).append("\n");
        }
        sb.append("phone numbers: \n");
        for(PhoneNumberPostDTO phoneNumber  : phoneNumberList){
            sb.append(phoneNumber.getNumber()).append("\n");
        }
        return sb.toString();
    }
}
