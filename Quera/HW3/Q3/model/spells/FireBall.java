package model.spells;

import model.Spell;
import model.User;

public class FireBall implements Spell {
    public static final int price = 80;
    private static final String type = "Fireball";

    private User owner;

    public FireBall(User owner) {
        this.owner = owner;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public User getOwner() {
        return owner;
    }
}
