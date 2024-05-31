package view;

import controller.GameControl;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private GameControl control;

    public GameMenu(GameControl gameControl) {
        this.control = gameControl;
    }

    public void startingMessage(String username) {
        System.out.println("Battle started with user " + username);
    }

    public void run(Scanner scanner) {
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;

            if (Commands.OPPONENT_HP.getMatcher(command) != null)
                System.out.print(control.opponentHP());
            else if ((matcher = Commands.SHOW_LINE.getMatcher(command)) != null)
                System.out.print(control.showLine(matcher));
            else if (Commands.CARDS_TO_PLAY.getMatcher(command) != null)
                System.out.print(control.cardsToPlay());
            else if (Commands.MOVES_LEFT.getMatcher(command) != null)
                System.out.print(control.movesLeft());
            else if ((matcher = Commands.MOVE_TROOP.getMatcher(command)) != null)
                System.out.print(control.moveTroop(matcher));
            else if ((matcher = Commands.DEPLOY_TROOP.getMatcher(command)) != null)
                System.out.print(control.deployTroop(matcher));
            else if ((matcher = Commands.DEPLOY_HEAL.getMatcher(command)) != null)
                System.out.print(control.deployHeal(matcher));
            else if ((matcher = Commands.DEPLOY_FIREBALL.getMatcher(command)) != null)
                System.out.print(control.deployFireball(matcher));
            else if (Commands.NEXT_TURN.getMatcher(command) != null) {
                String result = control.nextTurn();
                System.out.print(result);
                if (result.startsWith("Game has ended.")) return;
            } else if (Commands.SHOW_CURRENT_MENU.getMatcher(command) != null)
                System.out.println("Game Menu");
            else
                System.out.println("Invalid command!");
        }
    }
}
