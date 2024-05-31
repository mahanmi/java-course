package model.troops;

import model.Troop;
import model.User;

public class Wizard implements Troop {
    private static final int maxHP = 3300;
    private static final int attackPoint = 1400;
    public static final int price = 140;
    private static final String type = "Wizard";

    private User owner;
    private int healthPoint;

    public Wizard(User owner) {
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
