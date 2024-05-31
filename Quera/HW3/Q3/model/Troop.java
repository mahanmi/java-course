package model;

public interface Troop extends Card {
    int getHealthPoint();

    int getAttackPoint();

    int getMaxHealthPoint();

    void addHP(int amount);
}
