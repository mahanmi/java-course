package model.troops;

import model.Troop;
import model.User;

public class Archer implements Troop {
    public static final int price = 80;
    private static final int maxHP = 1900;
    private static final int attackPoint = 800;
    private static final String type = "Archer";

    private User owner;
    private int healthPoint;

    public Archer(User owner) {
        this.owner = owner;
        this.healthPoint = maxHP;
    }

    @Override
    public int getHealthPoint() {
        return healthPoint;
    }

    @Override
    public int getAttackPoint() {
        return attackPoint;
    }

    @Override
    public int getMaxHealthPoint() {
        return maxHP;
    }

    @Override
    public void addHP(int amount) {
        healthPoint += amount;
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
