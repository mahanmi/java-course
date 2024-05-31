package controller;

import model.Game;
import model.User;
import view.GameMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameControl {
    private Scanner scanner;
    private User host;
    private User guest;
    private Game game;

    GameControl(Scanner scanner, User host, User guest, int maxTurns) {
        this.scanner = scanner;
        this.host = host;
        this.guest = guest;
        game = new Game(host, guest, maxTurns);
    }

    public void run() {
        GameMenu gameMenu = new GameMenu(this);
        gameMenu.startingMessage(guest.getUsername());
        gameMenu.run(scanner);
    }

    public String opponentHP() {
        String middleCastle = String.format("middle castle: %d\n",
                game.getOpponentCastlesHP()[1] > 0 ? game.getOpponentCastlesHP()[1] : -1);
        String leftCastle = String.format("left castle: %d\n",
                game.getOpponentCastlesHP()[0] > 0 ? game.getOpponentCastlesHP()[0] : -1);
        String rightCastle = String.format("right castle: %d\n",
                game.getOpponentCastlesHP()[2] > 0 ? game.getOpponentCastlesHP()[2] : -1);
        return middleCastle + leftCastle + rightCastle;
    }

    public String showLine(Matcher matcher) {
        String lineDirection = matcher.group("line");
        if (isInvalidLineDirection(lineDirection))
            return Errors.LINE_DIRECTION.toString();
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s line:\n", lineDirection));
        for (int row = 0; row < game.getLine(lineDirection).length; row++) {
            for (int i = 0; i < game.getLine(lineDirection)[row].getCards().size(); i++) {
                builder.append(String.format("row %d: %s: %s\n", row + 1,
                        game.getLine(lineDirection)[row].getCards().get(i).getType(),
                        game.getLine(lineDirection)[row].getCards().get(i).getOwner().getUsername()));
            }
        }
        return builder.toString();
    }

    private boolean isInvalidLineDirection(String lineDirection) {
        return !lineDirection.equals("middle") && !lineDirection.equals("left") && !lineDirection.equals("right");
    }

    private String capitalize(String input) {
        char[] letters = input.toCharArray();
        if (letters[0] >= 'a' && letters[0] <= 'z')
            letters[0] += 'A' - 'a';
        return String.valueOf(letters);
    }

    public String cardsToPlay() {
        return String.format("You can play %d cards more!\n", game.getDeploysLeft());
    }

    public String movesLeft() {
        return String.format("You have %d moves left!\n", game.getMovesLeft());
    }

    public String moveTroop(Matcher matcher) {
        String line = matcher.group("line");
        String direction = matcher.group("direction");
        int row = Integer.parseInt(matcher.group("row"));
        if (isInvalidLineDirection(line))
            return Errors.LINE_DIRECTION.toString();
        if (row < 1 || row > 15)
            return Errors.ROW_NUMBER.toString();
        if (!direction.equals("upward") && !direction.equals("downward"))
            return Errors.MOVE_DIRECTION.toString();
        if (game.getMovesLeft() == 0)
            return Errors.OUT_OF_MOVES.toString();
        String result = game.moveTroop(line, row, direction.equals("upward") ? 1 : -1);
        if (result == null)
            return Errors.EMPTY_CELL_FOR_YOU.toString();
        if (result.equals(""))
            return Errors.INVALID_MOVE.toString();
        if (direction.equals("downward") && row <= 1)
            return Errors.INVALID_MOVE.toString();
        if (direction.equals("upward"))
            return String.format("%s moved successfully to row %d in line %s\n",
                    capitalize(result), row + 1, line);
        else
            return String.format("%s moved successfully to row %d in line %s\n",
                    capitalize(result), row - 1, line);
    }

    public String deployTroop(Matcher matcher) {
        String cardType = matcher.group("card");
        String line = matcher.group("line");
        int row = Integer.parseInt(matcher.group("row"));
        if (isInvalidTroopName(cardType))
            return Errors.TROOP_NAME.toString();
        if (!game.playerToPlay().hasCardInBattleDeck(cardType))
            return String.format(Errors.NOT_HAVE_BATTLE_DECK.toString(), cardType);
        if (isInvalidLineDirection(line))
            return Errors.LINE_DIRECTION.toString();
        if (row > 15 || row < 1)
            return Errors.ROW_NUMBER.toString();
        if (game.playerToPlay().equals(host) && row > 4)
            return Errors.NEAR_CASTLE.toString();
        if (game.playerToPlay().equals(guest) && row < 12)
            return Errors.NEAR_CASTLE.toString();
        if (game.getDeploysLeft() == 0)
            return Errors.OUT_OF_DEPLOYS.toString();
        game.deployTroop(cardType, row, line);
        return String.format("You have deployed %s successfully!\n", cardType);
    }

    public String deployHeal(Matcher matcher) {
        String line = matcher.group("line");
        int row = Integer.parseInt(matcher.group("row"));
        String cardType = "Heal";
        if (isInvalidLineDirection(line))
            return Errors.LINE_DIRECTION.toString();
        if (!game.playerToPlay().hasCardInBattleDeck(cardType))
            return String.format(Errors.NOT_HAVE_BATTLE_DECK.toString(), cardType);
        if (row > 15 || row < 1)
            return Errors.ROW_NUMBER.toString();
        if (game.getDeploysLeft() == 0)
            return Errors.OUT_OF_DEPLOYS.toString();
        game.deployTroop(cardType, row, line);
        return String.format("You have deployed %s successfully!\n", cardType);
    }

    private boolean isInvalidTroopName(String cardType) {
        return !cardType.equals("Wizard") && !cardType.equals("Archer") && !cardType.equals("Dragon");
    }

    public String deployFireball(Matcher matcher) {
        String line = matcher.group("line");
        if (isInvalidLineDirection(line))
            return Errors.LINE_DIRECTION.toString();
        if (!game.playerToPlay().hasCardInBattleDeck("Fireball"))
            return String.format(Errors.NOT_HAVE_BATTLE_DECK.toString(), "Fireball");
        if (game.getDeploysLeft() == 0)
            return Errors.OUT_OF_DEPLOYS.toString();
        if (!game.useFireball(line))
            return Errors.ALREADY_DESTROYED_CASTLE.toString();
        return "You have deployed Fireball successfully!\n";
    }

    public String nextTurn() {
        game.nextTurn();
        if (game.playerToPlay().equals(host) && game.hasGameEnded()) {
            User winner = game.getWinner();
            game.givePrize();
            if (winner != null)
                return String.format("Game has ended. Winner: %s\n", winner.getUsername());
            return "Game has ended. Result: Tie\n";
        }
        return String.format("Player %s is now playing!\n", game.playerToPlay().getUsername());
    }
}
