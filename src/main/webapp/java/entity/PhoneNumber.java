package entity;

/**
 * PhoneNumber entity
 * relation:
 * Many to One (Actor)
 */
public class PhoneNumber {
    private int id;
    private Actor actor;
    private byte number;

    public PhoneNumber() {
    }

    public PhoneNumber(int id, Actor actor, byte number) {
        this.id = id;
        this.actor = actor;
        this.number = number;
    }
}
