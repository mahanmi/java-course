package controller;

public enum Errors {
    USERNAME_FORMAT("Incorrect format for username!\n"),
    PASSWORD_FORMAT("Incorrect format for password!\n"),
    USERNAME_EXISTS("Username already exists!\n"),
    USERNAME_NOT_EXIST("Username doesn't exist!\n"),
    INCORRECT_PASSWORD("Password is incorrect!\n"),
    TURNS_COUNT("Invalid turns count!\n"),
    INCORRECT_PASSWORD_2("Incorrect password!\n"),
    NEW_PASSWORD_FORMAT("Incorrect format for new password!\n"),
    CARD_NAME("Invalid card name!\n"),
    EMPTY_BATTLE_DECK("Invalid action: your battle deck will be empty!\n"),
    FULL_BATTLE_DECK("Invalid action: your battle deck is full!\n"),
    NOT_HAVE_CARD("You don't have this card!\n"),
    ALREADY_IN_BATTLE_DECK("This card is already in your battle deck!\n"),
    NOT_ENOUGH_GOLD("Not enough gold to buy %s!\n"),
    HAVE_CARD("You have this card!\n"),
    SELL_BATTLE_DECK("You cannot sell a card from your battle deck!\n"),
    LINE_DIRECTION("Incorrect line direction!\n"),
    MOVE_DIRECTION("you can only move troops upward or downward!\n"),
    OUT_OF_MOVES("You are out of moves!\n"),
    EMPTY_CELL_FOR_YOU("You don't have any troops in this place!\n"),
    INVALID_MOVE("Invalid move!\n"),
    TROOP_NAME("Invalid troop name!\n"),
    NOT_HAVE_BATTLE_DECK("You don't have %s card in your battle deck!\n"),
    ROW_NUMBER("Invalid row number!\n"),
    NEAR_CASTLE("Deploy your troops near your castles!\n"),
    OUT_OF_DEPLOYS("You have deployed a troop or spell this turn!\n"),
    ALREADY_DESTROYED_CASTLE("This castle is already destroyed!\n"),
    NOT_IN_BATTLE_DECK("This card isn't in your battle deck!\n"),

    PASSWORD_CHANGE_SUCCESSFUL("Password changed successfully!\n"),
    ;
    String output;

    Errors(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return output;
    }
}
