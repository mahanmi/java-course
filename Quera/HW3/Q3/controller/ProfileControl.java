package controller;

import model.Card;
import model.Database;
import model.User;
import view.ProfileMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileControl {
    private Scanner scanner;
    private User currentUser;

    ProfileControl(Scanner scanner, User user) {
        this.scanner = scanner;
        currentUser = user;
    }

    public void run() {
        ProfileMenu profileMenu = new ProfileMenu(this);
        profileMenu.startingMessage();
        profileMenu.run(scanner);
        profileMenu.endingMessage();
    }

    public String changePassword(Matcher matcher) {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        if (currentUser.isPasswordWrong(oldPassword))
            return Errors.INCORRECT_PASSWORD_2.toString();
        if (LoginControl.isPasswordFormatWrong(newPassword))
            return Errors.NEW_PASSWORD_FORMAT.toString();
        currentUser.changePassword(oldPassword, newPassword);
        return Errors.PASSWORD_CHANGE_SUCCESSFUL.toString();
    }

    public String info() {
        return String.format("username: %s\npassword: %s\nlevel: %d\nexperience: %d\ngold: %d\nrank: %d\n",
                currentUser.getUsername(), currentUser.getPassword(), currentUser.getLevel(),
                currentUser.getExperience(),
                currentUser.getGold(), Database.getRankByUserName(currentUser.getUsername()));
    }

    static boolean isCardTypeWrong(String type) {
        for (String cardType : Database.allCardTypesInput) {
            if (cardType.equals(type))
                return false;
        }
        return true;
    }

    public String removeFromBattleDeck(Matcher matcher) {
        String cardType = matcher.group("card");
        if (isCardTypeWrong(cardType))
            return Errors.CARD_NAME.toString();
        if (!currentUser.hasCardInBattleDeck(cardType))
            return Errors.NOT_IN_BATTLE_DECK.toString();
        if (currentUser.getBattleDeck().size() == 1)
            return Errors.EMPTY_BATTLE_DECK.toString();
        currentUser.removeFromBattleDeck(cardType);
        return String.format("Card %s removed successfully!\n", cardType);
    }

    public String addToBattleDeck(Matcher matcher) {
        String cardType = matcher.group("card");
        if (isCardTypeWrong(cardType))
            return Errors.CARD_NAME.toString();
        if (!currentUser.hasCard(cardType))
            return Errors.NOT_HAVE_CARD.toString();
        if (currentUser.hasCardInBattleDeck(cardType))
            return Errors.ALREADY_IN_BATTLE_DECK.toString();
        if (currentUser.getBattleDeck().size() == 4)
            return Errors.FULL_BATTLE_DECK.toString();
        currentUser.addCardToDeck(cardType);
        return String.format("Card %s added successfully!\n", cardType);
    }

    public String showBattleDeck() {
        ArrayList<String> cardTypes = new ArrayList<>();
        for (Card card : currentUser.getBattleDeck()) {
            cardTypes.add(card.getType());
        }
        Collections.sort(cardTypes);
        StringBuilder builder = new StringBuilder();
        for (String cardType : cardTypes) {
            builder.append(cardType).append('\n');
        }
        return builder.toString();
    }
}
