import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    String nS = scanner.nextLine().trim();

    int n = Integer.parseInt(nS);

    long maxHight = 0;

    int[] hights = new int[n];

    int maxHightIndex = 0;

    long count = 0;

    for (int i = 0; i < n; i++) {
      hights[i] = scanner.nextInt();
      if (hights[i] > maxHight) {
        maxHight = hights[i];
        maxHightIndex = i;
      }
    }

    int x = 0;

    for (int i = 0; i < maxHightIndex; i++) {
      if (hights[i] > x) {
        x = hights[i];
      } else {
        count += x - hights[i];
      }
    }

    x = 0;

    for (int i = n - 1; i > maxHightIndex; i--) {
      if (hights[i] > x) {
        x = hights[i];
      } else {
        count += x - hights[i];
      }
    }

    scanner.close();

    System.out.println(count);
  }

}