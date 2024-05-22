package controller;

import model.Database;
import model.User;
import view.ShopMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopControl {
    private Scanner scanner;
    private User currentUser;

    ShopControl(Scanner scanner, User user) {
        this.scanner = scanner;
        currentUser = user;
    }

    public void run() {
        ShopMenu shopMenu = new ShopMenu(this);
        shopMenu.startingMessage();
        shopMenu.run(scanner);
        shopMenu.endingMessage();
    }

    public String buyCard(Matcher matcher) {
        String cardType = matcher.group("card");
        if (ProfileControl.isCardTypeWrong(cardType)) return Errors.CARD_NAME.toString();
        if (Database.getPrice(cardType) > currentUser.getGold())
            return String.format(Errors.NOT_ENOUGH_GOLD.toString(), cardType);
        if (currentUser.hasCard(cardType)) return Errors.HAVE_CARD.toString();
        currentUser.buyCard(cardType);
        return String.format("Card %s bought successfully!\n", cardType);
    }

    public String sellCard(Matcher matcher) {
        String cardType = matcher.group("card");
        if (ProfileControl.isCardTypeWrong(cardType)) return Errors.CARD_NAME.toString();
        if (!currentUser.hasCard(cardType)) return Errors.NOT_HAVE_CARD.toString();
        if (currentUser.hasCardInBattleDeck(cardType)) return Errors.SELL_BATTLE_DECK.toString();
        currentUser.sellCard(cardType);
        return String.format("Card %s sold successfully!\n", cardType);
    }
}
