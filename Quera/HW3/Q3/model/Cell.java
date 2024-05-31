package model;

import java.util.ArrayList;

public class Cell {
    private ArrayList<Card> cards;

    Cell() {
        cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    void addCard(Card card) {
        cards.add(card);
    }

    Card removeFirstCard(User owner) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card != null && card.getOwner().equals(owner) && card instanceof Troop) {
                cards.remove(i);
                return card;
            }
        }
        return null;
    }

    void removeDeadCards() {
        int size = 0;
        while (size != cards.size()) {
            size = cards.size();
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i) instanceof Troop && ((Troop) cards.get(i)).getHealthPoint() <= 0) {
                    cards.remove(i);
                    break;
                }
            }
        }
    }

    void setHP() {
        for (Card card : cards) {
            if (card instanceof Troop && ((Troop) card).getHealthPoint() > ((Troop) card).getMaxHealthPoint())
                ((Troop) card).addHP(((Troop) card).getHealthPoint() - ((Troop) card).getMaxHealthPoint());
        }
    }
}
