//package Quera.HW2.Q2;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

  public static int studentIndex(String firstName, String lastName, int id) {
    for (student s : studentsList)
      if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName) && s.getID() == id) {
        return s.getIndex();
      }
    return -1;
  }

  public static boolean checkCourseCode(String code) {
    for (course c : coursesList)
      if (c.getCode().equals(code))
        return true;
    return false;
  }

  public static ArrayList<course> coursesList = new ArrayList<course>();
  public static ArrayList<student> studentsList = new ArrayList<student>();

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    int n = Integer.parseInt(input.nextLine());

    for (int i = 0; i < n; i++)
      coursesList.add(new course(input.nextLine(), coursesList.size()));

    int studentIndex = -1;

    boolean isEnded = false;

    while (!isEnded) {

      String[] command = input.nextLine().trim().split("\\s+");

      if (command[0].equals("-end")) {
        isEnded = true;
        break;
      } else if (command[0].equals("-signin") && command.length == 4) {

        if (!command[1].matches("[A-Z]+.*")) {
          System.out.println("First name first character should be uppercase !");
          continue;
        }

        if (!command[2].matches("[A-Z]+.*")) {
          System.out.println("Last name first character should be uppercase !");
          continue;
        }

        if (!command[3].matches("[0-9]{9}")) {
          System.out.println("The ID number contains characters other than numbers or is too short !");
          continue;
        }

        if (studentIndex(command[1], command[2], Integer.parseInt(command[3])) == -1) {
          studentsList.add(new student(studentsList.size(), command[1], command[2], Integer.parseInt(command[3])));
          System.out.println("Student with name \"" + command[1] + " " + command[2] + "\" and ID number \"" + command[3]
              + "\" created !");
        } else {
          System.out.println("A student with this name exist !");
        }

      } else if (command[0].equals("-login") && command.length == 4) {

        int index = studentIndex(command[1], command[2], Integer.parseInt(command[3]));

        if (index != -1) {
          studentIndex = index;
          System.out.println("Welcome \"" + studentsList.get(studentIndex).getFirstName() + "\"");
        } else
          System.out.println("There is no student with this name and ID !");

      } else if (command[0].equals("-show") && command[1].equals("department") && command[2].equals("list")
          && command.length == 3) {

        System.out.println("Department List is :");

        for (int i = 1; i <= coursesList.size(); i++)
          System.out.println(i + ". " + coursesList.get(i - 1).getSubject() + " - " + coursesList.get(i - 1).getCode()
              + " - " + coursesList.get(i - 1).getTeacher() + " - Time : " + coursesList.get(i - 1).getTime()
              + " - Day : " + coursesList.get(i - 1).getDate());

      } else if (studentIndex != -1) {

        if (command[0].equals("-logout") && command.length == 1) {

          studentIndex = -1;
          System.out.println("Logged out successfully !");

        } else if (command[0].equals("-edit") && command[1].equals("profile") && command.length == 5) {

          if (!command[2].matches("[0-9]+")) {
            System.out.println("Year must be a number !");
            continue;
          } else if (!command[2].matches("1401|1402")) {
            System.out.println("Year must be 1401 or 1402 !");
            continue;
          }

          if (!command[3].matches("[0-9]{10}")) {
            System.out.println("Phone must be a number and it's length should be 10 !");
            continue;
          }

          int units = command[4].matches("[0-9]+") ? Integer.parseInt(command[4]) : -1;

          if (units == -1) {
            System.out.println("Units must be a number !");
            continue;
          } else if (units <= 10 || units >= 90) {
            System.out.println("Units must be between 10 and 90 !");
            continue;
          }

          studentsList.get(studentIndex).editProfile(command[2], command[3], Integer.parseInt(command[4]));

          studentsList.get(studentIndex).isProfileEdited = true;

          System.out.println("Profile edited successfully !");

        } else if (command[0].equals("-show") && command[1].equals("my") && command[2].equals("list")
            && command.length == 3) {

          if (studentsList.get(studentIndex).studentCourses.size() == 0)
            System.out.println("List is empty !");
          else {

            System.out.println("My List is :");

            for (int i = 1; i <= studentsList.get(studentIndex).studentCourses.size(); i++)
              System.out
                  .println(i + ". " + studentsList.get(studentIndex).studentCourses.get(i - 1).getSubject() + " - "
                      + studentsList.get(studentIndex).studentCourses.get(i - 1).getCode());

          }

        } else if (command[0].equals("-select") && command.length == 2) {

          if (!studentsList.get(studentIndex).isProfileEdited)
            System.out.println("Please first edit your profile !");

          else {
            int index = -1;

            for (course c : coursesList)
              if (c.getCode().equals(command[1]))
                index = c.getIndex();

            if (index == -1)
              System.out.println("Invalid Code !");
            else
              studentsList.get(studentIndex).takeCourse(coursesList.get(index));
          }
        } else if (command[0].equals("-delete") && command[1].equals("course") && command.length == 3) {

          boolean isDeleted = false;

          for (course c : studentsList.get(studentIndex).studentCourses)
            if (c.getCode().equals(command[2])) {
              studentsList.get(studentIndex).unitLimit += c.getUnit();
              studentsList.get(studentIndex).takenUnits--;
              studentsList.get(studentIndex).studentCourses.remove(c);
              isDeleted = true;
              System.out.println("Course deleted successfully !");
              break;
            }

          if (!isDeleted)
            System.out.println("Course with this code does not exist in your list !");

        } else if (command[0].equals("-show") && command[1].equals("profile") && command.length == 2) {

          if (!studentsList.get(studentIndex).isProfileEdited)
            System.out.println("Please first edit your profile !");
          else {
            System.out.println("First name : " + studentsList.get(studentIndex).getFirstName());
            System.out.println("Last name : " + studentsList.get(studentIndex).getLastName());
            System.out.println("Student ID : " + studentsList.get(studentIndex).getID());
            System.out.println("Entrance year : " + studentsList.get(studentIndex).getYear());
            System.out.println("Phone number : " + studentsList.get(studentIndex).getPhoneNumber());
            System.out.println("Passed units : " + studentsList.get(studentIndex).getPassedUnits());
            System.out.println("Units limit : " + studentsList.get(studentIndex).unitLimit);
            System.out.println("Courses number : " + studentsList.get(studentIndex).getTakenUnits());
          }

        } else if (command[0].equals("-select") && command[1].equals("all") && command.length > 2) {

          if (!studentsList.get(studentIndex).isProfileEdited)
            System.out.println("Please first edit your profile !");
          else {

            ArrayList<String> Invalid = new ArrayList<String>();
            ArrayList<String> UnitLimit = new ArrayList<String>();
            ArrayList<String> SameCourse = new ArrayList<String>();
            ArrayList<String> SameTime = new ArrayList<String>();

            for (int i = 2; i < command.length; i++) {

              if (studentsList.get(studentIndex).takenUnits >= 20) {
                System.out.println("You have selected 20 units already !");
                break;
              }

              boolean isFound = false;

              for (course c : coursesList) {
                if (c.getCode().equals(command[i])) {
                  isFound = true;

                  if (studentsList.get(studentIndex).unitLimit - c.getUnit() < 0) {
                    UnitLimit.add(command[i]);
                    break;
                  }

                  boolean shouldAdd = true;

                  for (course j : studentsList.get(studentIndex).studentCourses) {
                    if (c.getGroup() == j.getGroup()) {
                      SameCourse.add(command[i]);
                      shouldAdd = false;
                      break;
                    }
                  }

                  if (shouldAdd) {
                    for (course j : studentsList.get(studentIndex).studentCourses) {
                      if (j.getDate().equals(c.getDate()) && j.getTime().equals(c.getTime())) {
                        SameTime.add(command[i]);
                        shouldAdd = false;
                        break;
                      }
                    }
                  }

                  if (shouldAdd) {
                    c.row = studentsList.get(studentIndex).studentCourses.size() + 1;
                    studentsList.get(studentIndex).studentCourses.add(c);
                    studentsList.get(studentIndex).unitLimit -= c.getUnit();
                    studentsList.get(studentIndex).takenUnits++;
                    System.out.println("Course " + c.getCode() + " added successfully !");
                  }

                }
              }

              if (!isFound) {
                Invalid.add(command[i]);
                continue;
              }

            }

            if (Invalid.size() > 0) {
              System.out.print("Invalid Codes ->");
              for (String s : Invalid)
                System.out.print(" " + s);
              System.out.println();
            }

            if (UnitLimit.size() > 0) {
              System.out.print("Unit Limit Codes ->");
              for (String s : UnitLimit)
                System.out.print(" " + s);
              System.out.println();
            }

            if (SameCourse.size() > 0) {
              System.out.print("Same Course Codes ->");
              for (String s : SameCourse)
                System.out.print(" " + s);
              System.out.println();
            }

            if (SameTime.size() > 0) {
              System.out.print("Same Time Codes ->");
              for (String s : SameTime)
                System.out.print(" " + s);
              System.out.println();
            }
          }
        } else
          System.out.println("Invalid command !");
      } else
        System.out.println("Invalid command !");
    }

    input.close();

  }

}
