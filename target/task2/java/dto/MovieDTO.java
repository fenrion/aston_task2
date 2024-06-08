package dto;

import entity.Actor;

import java.util.List;

public class MovieDTO {
    private String name;
    private List<ActorDTO> actorList;

    public MovieDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ActorDTO> getActorList() {
        return actorList;
    }

    public void setActorList(List<ActorDTO> actorList) {
        this.actorList = actorList;
    }
}
