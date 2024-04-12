//package Quera.HW2.Q2;

enum DaysOfWeek {
  SATURDAY_MONDAY, SUNDAY_TUESDAY;
}

enum StartTime {
  SEVEN_HALF, NINE, TEN_HALF;
}

public class course {
  private int index;
  public int row;
  private String subject;
  private int unit;
  private String teacher;
  private DaysOfWeek date;
  private StartTime time;
  private String code;
  private int group;

  public course(String input, int index) {

    this.index = index;
    this.row = index + 1;

    String[] parts = input.split(",");

    this.subject = parts[0];

    this.unit = Integer.parseInt(parts[1]);

    this.teacher = parts[2];

    int date = Integer.parseInt(parts[3]);

    if (date == 1)
      this.date = DaysOfWeek.SATURDAY_MONDAY;
    else
      this.date = DaysOfWeek.SUNDAY_TUESDAY;

    int time = Integer.parseInt(parts[4]);

    if (time == 1)
      this.time = StartTime.SEVEN_HALF;
    else if (time == 2)
      this.time = StartTime.NINE;
    else
      this.time = StartTime.TEN_HALF;

    this.code = parts[5];
    this.group = Integer.valueOf(code) / 10;

  }

  public int getIndex() {
    return index;
  }

  public int getRow() {
    return row;
  }

  public String getSubject() {
    return subject;
  }

  public int getUnit() {
    return unit;
  }

  public String getTeacher() {
    return teacher;
  }

  public String getDate() {
    if (date == DaysOfWeek.SATURDAY_MONDAY)
      return "Saturday / Monday";
    else
      return "Sunday / Tuesday";
  }

  public String getTime() {
    if (time == StartTime.SEVEN_HALF)
      return "7:30 to 9:00";
    else if (time == StartTime.NINE)
      return "9:00 to 10:30";
    else
      return "10:30 to 12:00";
  }

  public String getCode() {
    return code;
  }

  public int getGroup() {
    return group;
  }

}
