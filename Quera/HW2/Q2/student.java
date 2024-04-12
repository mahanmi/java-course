//package Quera.HW2.Q2;

import java.util.ArrayList;

public class student {
  private int index;
  private String firstName;
  private String lastName;
  private int id;
  private String year;
  private String phoneNumber;
  private int passedUnits;
  public int takenUnits = 0;
  public int unitLimit = 20;

  public boolean isProfileEdited = false;
  public ArrayList<course> studentCourses = new ArrayList<course>();

  public student(int index, String firstName, String lastName, int id) {
    this.index = index;
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
  }

  public void editProfile(String year, String phoneNumber, int passedUnits) {
    this.year = year;
    this.phoneNumber = phoneNumber;
    this.passedUnits = passedUnits;
  }

  public int getIndex() {
    return index;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getID() {
    return id;
  }

  public String getYear() {
    return year;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public int getPassedUnits() {
    return passedUnits;
  }

  public int getTakenUnits() {
    return takenUnits;
  }

  public void takeCourse(course c) {

    if (unitLimit - c.getUnit() < 0) {
      System.out.println("Unit Limits Error !");
      return;
    }

    for (course i : studentCourses) {
      if (Integer.parseInt(i.getCode()) / 10 == Integer.parseInt(c.getCode()) / 10) {
        System.out.println("Same Course Code Error !");
        return;
      } else if (i.getDate().equals(c.getDate()) && i.getTime().equals(c.getTime())) {
        System.out.println("Same Time Error !");
        return;
      }
    }

    c.row = studentCourses.size() + 1;
    studentCourses.add(c);
    unitLimit -= c.getUnit();
    takenUnits++;
    System.out.println("Course added successfully !");

  }
}
