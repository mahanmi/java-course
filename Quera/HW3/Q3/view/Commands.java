package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    SHOW_CURRENT_MENU("show current menu"),
    EXIT("Exit"),
    REGISTER("register username (?<username>.+) password (?<password>.+)"),
    LOGIN("login username (?<username>.+) password (?<password>.+)"),
    LOGOUT("logout"),
    LIST_OF_USERS("list of users"),
    SCOREBOARD("scoreboard"),
    PROFILE_MENU("profile menu"),
    SHOP_MENU("shop menu"),
    START_GAME("start game turns count (?<turns>-?\\d+) username (?<username>.+)"),
    BACK("back"),
    CHANGE_PASSWORD("change password old password (?<oldPassword>.+) new password (?<newPassword>.+)"),
    INFO("Info"),
    REMOVE_FROM_BATTLE_DECK("remove from battle deck (?<card>.+)"),
    ADD_TO_BATTLE_DECK("add to battle deck (?<card>.+)"),
    SHOW_BATTLE_DECK("show battle deck"),
    BUY_CARD("buy card (?<card>.+)"),
    SELL_CARD("sell card (?<card>.+)"),
    OPPONENT_HP("show the hitpoints left of my opponent"),
    SHOW_LINE("show line info (?<line>.+)"),
    CARDS_TO_PLAY("number of cards to play"),
    MOVES_LEFT("number of moves left"),
    MOVE_TROOP("move troop in line (?<line>.+) and row (?<row>-?\\d+) (?<direction>.+)"),
    DEPLOY_TROOP("deploy troop (?<card>.+) in line (?<line>.+) and row (?<row>-?\\d+)"),
    DEPLOY_HEAL("deploy spell Heal in line (?<line>.+) and row (?<row>-?\\d+)"),
    DEPLOY_FIREBALL("deploy spell Fireball in line (?<line>.+)"),
    NEXT_TURN("next turn");

    private String regex;

    Commands(String regex) {
        this.regex = regex;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
