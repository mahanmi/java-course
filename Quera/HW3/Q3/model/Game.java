package model;

public class Game {
    private User host;
    private User guest;
    private int[] hostCastlesHP;
    private int[] guestCastlesHP;
    private int maxTurns;
    private int currentTurns;
    private Cell[][] board;
    private int movesLeft;
    private int deploysLeft;
    private boolean hostTurn;

    public Game(User host, User guest, int maxTurns) {
        this.host = host;
        this.guest = guest;
        hostCastlesHP = new int[] { 2200 * host.getLevel(), 3400 * host.getLevel(), 2200 * host.getLevel() };
        guestCastlesHP = new int[] { 2200 * guest.getLevel(), 3400 * guest.getLevel(), 2200 * guest.getLevel() };
        this.maxTurns = maxTurns;
        currentTurns = 1;
        startBoard();
        movesLeft = 3;
        deploysLeft = 1;
        hostTurn = true;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public int getDeploysLeft() {
        return deploysLeft;
    }

    public int[] getOpponentCastlesHP() {
        if (playerToPlay().equals(host))
            return guestCastlesHP;
        return hostCastlesHP;
    }

    public Cell[] getLine(String lineDirection) {
        return board[lineDirectionToLine(lineDirection)];
    }

    private void startBoard() {
        board = new Cell[3][15];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public User playerToPlay() {
        if (hostTurn)
            return host;
        return guest;
    }

    public boolean hasGameEnded() {
        if (currentTurns > maxTurns)
            return true;
        boolean isHostDead = true;
        boolean isGuestDead = true;
        for (int hp : hostCastlesHP) {
            if (hp > 0) {
                isHostDead = false;
                break;
            }
        }
        for (int hp : guestCastlesHP) {
            if (hp > 0) {
                isGuestDead = false;
                break;
            }
        }
        return isGuestDead || isHostDead;
    }

    private int lineDirectionToLine(String direction) {
        switch (direction) {
            case "left":
                return 0;
            case "middle":
                return 1;
            case "right":
                return 2;
            default:
                return -1;
        }
    }

    public void deployTroop(String type, int row, String direction) {
        row--;
        board[lineDirectionToLine(direction)][row].addCard(Database.getCard(playerToPlay(), type));
        deploysLeft--;
    }

    public String moveTroop(String lineDirection, int row, int direction) {
        row--;
        int line = lineDirectionToLine(lineDirection);
        Card card = board[line][row].removeFirstCard(playerToPlay());
        if (card == null)
            return null;
        if (row + direction > 14 || row + direction < 0) {
            board[line][row].addCard(card);
            return "";
        }
        board[line][row + direction].addCard(card);
        movesLeft--;
        return card.getType();
    }

    public boolean useFireball(String direction) {
        if (getOpponentCastlesHP()[lineDirectionToLine(direction)] <= 0)
            return false;
        getOpponentCastlesHP()[lineDirectionToLine(direction)] -= 1400;
        deploysLeft--;
        return true;
    }

    private void attack(Troop card1, Troop card2) {
        if (card1.getOwner().equals(card2.getOwner()))
            return;
        if (card1.getAttackPoint() > card2.getAttackPoint())
            card2.addHP(card2.getAttackPoint() - card1.getAttackPoint());
        else
            card1.addHP(card1.getAttackPoint() - card2.getAttackPoint());
    }

    private void attackCastle() {
        for (int i = 0; i < 3; i++) {
            if (hostCastlesHP[i] <= 0)
                continue;
            for (int j = 0; j < board[i][0].getCards().size(); j++) {
                Card card = board[i][0].getCards().get(j);
                if (card.getOwner().equals(guest) && card instanceof Troop) {
                    ((Troop) card).addHP(-500 * host.getLevel());
                    hostCastlesHP[i] -= ((Troop) card).getAttackPoint();
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (guestCastlesHP[i] <= 0)
                continue;
            for (int j = 0; j < board[i][14].getCards().size(); j++) {
                Card card = board[i][14].getCards().get(j);
                if (card.getOwner().equals(host) && card instanceof Troop) {
                    ((Troop) card).addHP(-500 * guest.getLevel());
                    guestCastlesHP[i] -= ((Troop) card).getAttackPoint();
                }
            }
        }
    }

    public void nextTurn() {
        deploysLeft = 1;
        movesLeft = 3;
        if (hostTurn) {
            hostTurn = false;
            return;
        }
        attackCastle();
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                for (int i = 0; i < cell.getCards().size(); i++) {
                    Card card1 = cell.getCards().get(i);
                    for (int j = i + 1; j < cell.getCards().size(); j++) {
                        Card card2 = cell.getCards().get(j);
                        attack((Troop) card1, (Troop) card2);
                    }
                }
            }
        }
        currentTurns++;
        hostTurn = true;
        cleanBoard();
    }

    private void cleanBoard() {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                cell.removeDeadCards();
                cell.setHP();
            }
        }
        for (int i = 0; i < hostCastlesHP.length; i++) {
            if (hostCastlesHP[i] <= 0)
                hostCastlesHP[i] = 0;
            if (guestCastlesHP[i] <= 0)
                guestCastlesHP[i] = 0;
        }
    }

    public User getWinner() {
        int hostHP = 0, guestHP = 0;
        for (int i = 0; i < 3; i++) {
            hostHP += hostCastlesHP[i];
            guestHP += guestCastlesHP[i];
        }
        if (hostHP > guestHP)
            return host;
        if (hostHP < guestHP)
            return guest;
        return null;
    }

    public void givePrize() {
        int hostHP = 0, guestHP = 0;
        int countDestroyedHost = 0, countDestroyedGuest = 0;
        for (int i = 0; i < 3; i++) {
            if (hostCastlesHP[i] <= 0)
                countDestroyedHost++;
            else
                hostHP += hostCastlesHP[i];

            if (guestCastlesHP[i] <= 0)
                countDestroyedGuest++;
            else
                guestHP += guestCastlesHP[i];
        }
        host.addExperience(hostHP);
        guest.addExperience(guestHP);
        host.addGold(countDestroyedGuest * 20);
        guest.addGold(countDestroyedHost * 20);
    }
}
