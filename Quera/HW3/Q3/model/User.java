package model;

import model.spells.FireBall;
import model.troops.Archer;

import java.util.ArrayList;

public class User implements Comparable<User> {
    private String username;
    private String password;
    private int gold;
    private int level;
    private int experience;
    private ArrayList<Card> cards;
    private ArrayList<Card> battleDeck;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.gold = 80;
        this.level = 1;
        this.experience = 0;
        this.cards = new ArrayList<>();
        this.battleDeck = new ArrayList<>();
        this.cards.add(new Archer(this));
        this.cards.add(new FireBall(this));
        this.battleDeck.add(this.cards.get(0));
        this.battleDeck.add(this.cards.get(1));
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Card> getBattleDeck() {
        return battleDeck;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public int getLevel() {
        return level;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int compareTo(User o) {
        if (o.getLevel() != level)
            return o.getLevel() - level;
        if (o.getExperience() != experience)
            return o.getExperience() - experience;
        return username.compareTo(o.getUsername());
    }

    public boolean isPasswordWrong(String password) {
        return !this.password.equals(password);
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(password))
            return;
        this.password = newPassword;
    }

    public void addCardToDeck(String type) {
        for (Card card : cards) {
            if (card.getType().equals(type))
                battleDeck.add(card);
        }
    }

    public void removeFromBattleDeck(String type) {
        for (int i = 0; i < battleDeck.size(); i++) {
            if (battleDeck.get(i).getType().equals(type)) {
                battleDeck.remove(i);
                return;
            }
        }
    }

    void addExperience(int experience) {
        this.experience += experience;
        while (this.experience >= 150 * level * level) {
            this.experience -= 150 * level * level;
            level++;
        }
    }

    void addGold(int gold) {
        this.gold += gold;
    }

    public void buyCard(String type) {
        Card card = Database.getCard(this, type);
        if (card != null)
            this.gold -= card.getPrice();
        cards.add(card);
    }

    public void sellCard(String type) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getType().equals(type)) {
                gold += (int) (0.8 * cards.get(i).getPrice());
                cards.remove(i);
                break;
            }
        }
        for (int i = 0; i < battleDeck.size(); i++) {
            if (battleDeck.get(i).getType().equals(type)) {
                battleDeck.remove(i);
                return;
            }
        }
    }

    public boolean hasCard(String type) {
        for (Card card : cards) {
            if (card.getType().equals(type))
                return true;
        }
        return false;
    }

    public boolean hasCardInBattleDeck(String type) {
        for (Card card : battleDeck) {
            if (card.getType().equals(type))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;
        return ((User) obj).getUsername().equals(this.username);
    }
}
