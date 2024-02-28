import java.text.DecimalFormat;

public class Q2 {
  public static void main(String[] args) {
    java.util.Scanner sc = new java.util.Scanner(System.in);
    DecimalFormat df = new DecimalFormat("0.##");

    double voltage = sc.nextDouble();

    int type = sc.nextInt();

    if (type != 1 && type != 2) {
      System.out.println("Invalid Input");
      sc.close();
      return;
    }

    int n = sc.nextInt();

    double[] resistance = new double[n];

    double totalResistance = 0;

    if (type == 1) {// series
      for (int i = 0; i < n; i++) {
        resistance[i] = sc.nextDouble();
        totalResistance += resistance[i];
      }
    } else if (type == 2) {// parallel
      for (int i = 0; i < n; i++) {
        resistance[i] = sc.nextDouble();
        if (resistance[i] != 0)
          totalResistance += 1.0 / resistance[i];
        else {
          totalResistance = 0;
          break;
        }
      }
      if (totalResistance != 0)
        totalResistance = 1.0 / totalResistance;
    }

    sc.close();

    double power;
    if (totalResistance == 0 || voltage == 0)
      power = 0;
    else
      power = voltage * voltage / totalResistance * 1000;

    totalResistance /= 1000;

    System.out.println("Total Res: " + df.format(totalResistance) + " Kohm");
    if (power != 0)
      System.out.println("Total Pow: " + df.format(power) + " mW");
    else if (voltage == 0) {
      if (totalResistance == 0)
        System.out.println("Short Circuit");
      else
        System.out.println("Total Pow: " + df.format(power) + " mW");
    } else
      System.out.println("Short Circuit");

  }
}
