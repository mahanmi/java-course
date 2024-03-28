import java.util.Scanner;

public class Q4 {

  static int row, col, count = 0, ANS;
  static boolean isFound = false;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    row = sc.nextInt();
    col = sc.nextInt();

    int[][] Array = new int[row][col];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        Array[i][j] = sc.nextInt();
      }
    }

    sc.close();

    navigate(Array, 0, 0);

    if (isFound)
      System.out.println(ANS - 1);
    else
      System.out.println("0");

  }

  public static boolean navigate(int[][] Array, int i, int j) {

    if (Array[i][j] == 9) {
      if (count + 1 < ANS || !isFound)
        ANS = count + 1;
      isFound = true;
      return false;
    }

    for (int x = -1; x <= 1; x += 2) { // up and down
      if (i + x < row && i + x >= 0 && Array[i + x][j] != 1) {
        Array[i][j] = 1;
        count++;
        if (!navigate(Array, i + x, j)) {
          count--;
          Array[i][j] = 0;
        }
      }
    }

    for (int y = -1; y <= 1; y += 2) { // left and right
      if (j + y < col && j + y >= 0 && Array[i][j + y] != 1) {
        Array[i][j] = 1;
        count++;
        if (!navigate(Array, i, j + y)) {
          count--;
          Array[i][j] = 0;
        }
      }
    }

    return false;
  }
}
