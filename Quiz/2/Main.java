import java.util.Scanner;
import java.util.ArrayList;

public class Main {

  public static class coordinates {

    int x;
    int y;

    public coordinates(String input) {
      this.x = Integer.parseInt(input.split(" ")[0]);
      this.y = Integer.parseInt(input.split(" ")[1]);
    }

  }

  static void printMatrix(String[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] + "   ");
      }
      System.out.println();
    }
    System.out.println();
  }

  static boolean isMine(coordinates c, ArrayList<coordinates> mines) {
    for (int i = 0; i < mines.size(); i++) {
      if (c.x == mines.get(i).x && c.y == mines.get(i).y) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {

    String[][] matrix;

    ArrayList<coordinates> mines = new ArrayList<coordinates>();

    Scanner scanner = new Scanner(System.in);

    int n, m, d;

    coordinates nm = new coordinates(scanner.nextLine().trim());

    n = nm.x;
    m = nm.y;

    d = Integer.parseInt(scanner.nextLine().trim());

    for (int i = 0; i < d; i++) {
      mines.add(new coordinates(scanner.nextLine().trim()));
    }

    matrix = new String[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix[i][j] = "?";
      }
    }

    coordinates c;

    for (int i = 0; i < m * n - mines.size(); i++) {
      printMatrix(matrix);
      c = new coordinates(scanner.nextLine().trim());
      if (isMine(c, mines)) {
        matrix[c.x - 1][c.y - 1] = "f";
        printMatrix(matrix);
        System.out.println("The Robot Failed!");
        return;
      } else {
        matrix[c.x - 1][c.y - 1] = "c";
      }
    }

    printMatrix(matrix);
    System.out.println("The Robot Succeeded!");

    scanner.close();

  }

}