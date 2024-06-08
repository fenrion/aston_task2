package entity;

import dao.ActorDAO;

/**
 * PhoneNumber entity
 * relation:
 * Many to One (Actor)
 */
public class PhoneNumber {
    private static final ActorDAO actorDAO = ActorDAO.getInstance();
    private Integer id;
    private Actor actor;
    private String number;

    public PhoneNumber() {
    }

    public PhoneNumber(Integer id, Actor actor, String number) {
        this.id = id;
        this.actor = actor;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
