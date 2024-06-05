package entity;

import java.util.List;

/**
 * Movie entity
 * relation:
 * Many to Many(Actor)
 */
public class Movie {
    private int id;
    private String name;
    private List<Actor> actorList;

    public Movie() {
    }

    public Movie(int id, String name, List<Actor> actorList) {
        this.id = id;
        this.name = name;
        this.actorList = actorList;
    }
}
