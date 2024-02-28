import java.util.Arrays;
import java.util.Scanner;

public class Q3 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int[][] sudoku = new int[6][6];
    int unsolved = 0;

    for (int i = 0; i < 6; i++) {
      String line = sc.nextLine();
      String[] numbers = line.split(" ");
      for (int j = 0; j < 6; j++) {
        if (numbers[j].equals("x")) {
          sudoku[i][j] = 0;
          unsolved++;
        } else
          sudoku[i][j] = Integer.parseInt(numbers[j]);
      }
    }

    sc.close();

    while (unsolved > 0) {
      for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
          if (sudoku[i][j] == 0) {
            Boolean[] possibleNumbers = new Boolean[6];
            Arrays.fill(possibleNumbers, true);
            for (int k = 0; k < 6; k++) {
              if (sudoku[i][k] != 0)
                possibleNumbers[sudoku[i][k] - 1] = false;
              if (sudoku[k][j] != 0)
                possibleNumbers[sudoku[k][j] - 1] = false;
            }
            int row = i / 2, column = j / 3;
            for (int k = row * 2; k < row * 2 + 2; k++) {
              for (int l = column * 3; l < column * 3 + 3; l++) {
                if (sudoku[k][l] != 0)
                  possibleNumbers[sudoku[k][l] - 1] = false;
              }
            }
            int possibleNumbersCount = 0;
            int ANS = 0;
            for (int k = 0; k < 6; k++) {
              if (possibleNumbers[k]) {
                possibleNumbersCount++;
                ANS = k + 1;
              }
            }
            if (possibleNumbersCount == 1) {
              sudoku[i][j] = ANS;
              unsolved--;
            }
          }
        }
      }
    }

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        System.out.print(sudoku[i][j] + " ");
      }
      System.out.println();
    }

  }
}
