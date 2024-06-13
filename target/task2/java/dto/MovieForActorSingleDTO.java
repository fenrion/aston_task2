package dto;

public class MovieForActorSingleDTO {
    private String name;


    public MovieForActorSingleDTO() {
    }

    public MovieForActorSingleDTO(String name, String actorName) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MovieForActorSingleDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
