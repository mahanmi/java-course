import java.util.Scanner;

public class Q1 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    char[] cityArray = sc.next().toCharArray();
    int b = sc.nextInt();
    int t = sc.nextInt();
    if (b > t) {
      int temp = b;
      b = t;
      t = temp;
    }
    int count = 0;
    sc.close();
    for (int i = b - 1; i < t; i++) {
      int S = 0;

      while (cityArray[i] == 'S' && i < t) {
        S++;
        i++;
      }

      if (S > 0) {
        int binary = Integer.parseInt(Integer.toBinaryString(S));
        while (binary > 0) {
          count += binary % 10;
          binary /= 10;
        }
      }
    }
    System.out.println(count);
  }
}
