import java.util.Scanner;
import java.util.ArrayList;

public class Main {

  static boolean isEnded = false;

  public static int findIndex(String type, String code) {
    if (type.equals("car")) {
      for (int i = 0; i < cars.size(); i++) {
        if (cars.get(i).getCode().equals(code)) {
          return i;
        }
      }
    } else if (type.equals("motorcycle")) {
      for (int i = 0; i < motorcycles.size(); i++) {
        if (motorcycles.get(i).getCode().equals(code)) {
          return i;
        }
      }
    } else if (type.equals("bike")) {
      for (int i = 0; i < bikes.size(); i++) {
        if (bikes.get(i).getCode().equals(code)) {
          return i;
        }
      }
    }
    return -1;
  }

  public static boolean checkCode(String type, String code) {

    if (type.equals("car")) {
      for (Car car : cars) {
        if (car.getCode().equals(code)) {
          return true;
        }
      }
    } else if (type.equals("motorcycle")) {
      for (Motorcycle motorcycle : motorcycles) {
        if (motorcycle.getCode().equals(code)) {
          return true;
        }
      }
    } else if (type.equals("bike")) {
      for (Bike bike : bikes) {
        if (bike.getCode().equals(code)) {
          return true;
        }
      }
    }
    return false;

  }

  public static ArrayList<Motorcycle> motorcycles = new ArrayList<Motorcycle>();
  public static ArrayList<Car> cars = new ArrayList<Car>();
  public static ArrayList<Bike> bikes = new ArrayList<Bike>();

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    while (!isEnded) {

      String[] commandParts = scanner.nextLine().trim().split("\\s+");

      if (commandParts[0].equals("end")) {
        isEnded = true;
        break;
      }

      if (commandParts[0].startsWith("add")) {
        if (commandParts[0].equals("addCar")) {

          if (!checkCode("car", commandParts[3])) {
            cars.add(new Car(commandParts[1], commandParts[2], commandParts[3]));
            System.out.println("Car was added successfully!");
          } else
            System.out.println("Car with this code already exists!");

        } else if (commandParts[0].equals("addMotorcycle")) {

          if (!checkCode("motorcycle", commandParts[3])) {
            motorcycles.add(new Motorcycle(commandParts[1], commandParts[2], commandParts[3]));
            System.out.println("Motorcycle was added successfully!");
          } else
            System.out.println("Motorcycle with this code already exists!");

        } else if (commandParts[0].equals("addBike")) {

          if (!checkCode("bike", commandParts[3])) {
            bikes.add(new Bike(commandParts[1], commandParts[2], commandParts[3], commandParts[4]));
            System.out.println("Bike was added successfully!");
          } else
            System.out.println("Bike with this code already exists!");

        }
      } else if (commandParts[0].startsWith("Set")) {
        if (commandParts[0].startsWith("SetCar")) {
          // #################### car #################### \\
          int index = findIndex("car", commandParts[1]);

          if (index == -1)
            System.out.println("No cars exist with the given code!");
          else {
            if (commandParts[0].equals("SetCarColor")) {

              cars.get(index).setColor(commandParts[2]);
              System.out.println("CarColor is " + commandParts[2] + "!");

            } else if (commandParts[0].equals("SetCarOrigin")) {

              cars.get(index).setOrigin(commandParts[2]);
              System.out.println("CarOrigin is " + commandParts[2] + "!");

            } else if (commandParts[0].equals("SetCarStrength")) {

              if (Double.parseDouble(commandParts[2]) >= 100 && Double.parseDouble(commandParts[2]) <= 500) {
                cars.get(index).setStrength(Double.parseDouble(commandParts[2]));
                System.out.println("CarStrength is " + commandParts[2] + " hp!");
              } else
                System.out.println("CarStrength is invalid!");

            } else if (commandParts[0].equals("SetCarSpeed")) {

              cars.get(index).setSpeed(Double.parseDouble(commandParts[2]));
              System.out.println("CarSpeed is " + commandParts[2] + "!");

            } else if (commandParts[0].equals("SetCarPrice")) {

              cars.get(index).setPrice(Double.parseDouble(commandParts[2]));
              System.out.println("CarPrice is " + cars.get(index).getPrice() + "$!");

            } else if (commandParts[0].equals("SetCarState")) {

              cars.get(index).setState(commandParts[2]);
              System.out.println("CarState is " + commandParts[2] + "!");
              if (commandParts[2].equals("sold"))
                cars.remove(index);

            } else if (commandParts[0].equals("SetCarDiscount")) {

              if (Double.parseDouble(commandParts[2]) >= 5 && Double.parseDouble(commandParts[2]) <= 15) {

                cars.get(index).setDiscount(Double.parseDouble(commandParts[2]));
                System.out.println("New carPrice is " + cars.get(index).getPrice() + "$!");
              } else
                System.out.println("Input for discount is invalid!");

            }
          }
        } else if (commandParts[0].startsWith("SetMotorcycle")) {
          // #################### motorcycle #################### \\

          int index = findIndex("motorcycle", commandParts[1]);

          if (index == -1)
            System.out.println("No motorcycles exist with the given code!");
          else {
            if (commandParts[0].equals("SetMotorcycleColor")) {

              motorcycles.get(index).setColor(commandParts[2]);
              System.out.println("MotorcycleColor is " + commandParts[2] + "!");

            } else if (commandParts[0].equals("SetMotorcycleOrigin")) {

              motorcycles.get(index).setOrigin(commandParts[2]);
              System.out.println("MotorcycleOrigin is " + commandParts[2] + "!");

            } else if (commandParts[0].equals("SetMotorcycleStrength")) {

              if (Double.parseDouble(commandParts[2]) >= 50 && Double.parseDouble(commandParts[2]) <= 100) {
                motorcycles.get(index).setStrength(Double.parseDouble(commandParts[2]));
                System.out.println("MotorcycleStrength is " + commandParts[2] + " hp!");
              } else
                System.out.println("MotorcycleStrength is invalid!");

            } else if (commandParts[0].equals("SetMotorcycleSpeed")) {

              motorcycles.get(index).setSpeed(Double.parseDouble(commandParts[2]));
              System.out.println("MotorcycleSpeed is " + commandParts[2] + "!");

            } else if (commandParts[0].equals("SetMotorcyclePrice")) {

              motorcycles.get(index).setPrice(Double.parseDouble(commandParts[2]));
              System.out.println("MotorcyclePrice is " + motorcycles.get(index).getPrice() + "$!");

            } else if (commandParts[0].equals("SetMotorcycleState")) {

              motorcycles.get(index).setState(commandParts[2]);
              if (commandParts[2].equals("sold"))
                motorcycles.remove(index);
              System.out.println("MotorcycleState is " + commandParts[2] + "!");

            } else if (commandParts[0].equals("SetMotorcycleDiscount")) {

              if (Double.parseDouble(commandParts[2]) >= 5 && Double.parseDouble(commandParts[2]) <= 15) {
                motorcycles.get(index).setDiscount(Double.parseDouble(commandParts[2]));
                System.out.println("New motorcyclePrice is " + motorcycles.get(index).getPrice() + "$!");
              } else
                System.out.println("Input for discount is invalid!");

            }
          }
        } else if (commandParts[0].startsWith("SetBike") || commandParts[0].equals("SetWheelSize")) {
          int index = findIndex("bike", commandParts[1]);

          if (index == -1)
            System.out.println("No bikes exist with the given code!");
          else {
            if (commandParts[0].equals("SetBikeColor")) {

              bikes.get(index).setColor(commandParts[2]);
              System.out.println("BikeColor is " + commandParts[2] + "!");

            } else if (commandParts[0].equals("SetBikeOrigin")) {

              bikes.get(index).setOrigin(commandParts[2]);
              System.out.println("BikeOrigin is " + commandParts[2] + "!");

            } else if (commandParts[0].equals("SetBikeWeight")) {

              bikes.get(index).setWeight(Double.parseDouble(commandParts[2]));
              System.out.println("BikeWeight is " + commandParts[2] + "!");

            } else if (commandParts[0].equals("SetWheelSize")) {

              if (Double.parseDouble(commandParts[2]) >= 12 && Double.parseDouble(commandParts[2]) <= 29) {
                bikes.get(index).setWheelSize(Double.parseDouble(commandParts[2]));
                System.out.println("WheelSize is " + commandParts[2] + " inches!");
              } else
                System.out.println("WheelSize is invalid!");

            } else if (commandParts[0].equals("SetBikePrice")) {

              bikes.get(index).setPrice(Double.parseDouble(commandParts[2]));
              System.out.println("BikePrice is " + bikes.get(index).getPrice() + "$!");

            } else if (commandParts[0].equals("SetBikeState")) {

              bikes.get(index).setState(commandParts[2]);
              System.out.println("BikeState is " + commandParts[2] + "!");
              if (commandParts[2].equals("sold"))
                bikes.remove(index);

            } else if (commandParts[0].equals("SetBikeDiscount")) {

              if (Double.parseDouble(commandParts[2]) >= 5 && Double.parseDouble(commandParts[2]) <= 15) {
                bikes.get(index).setDiscount(Double.parseDouble(commandParts[2]));
                System.out.println("New bikePrice is " + bikes.get(index).getPrice() + "$!");
              } else
                System.out.println("Input for discount is invalid!");

            }
          }
        }
      } else if (commandParts[0].startsWith("Compare")) {
        if (commandParts[0].equals("CompareCars")) {
          int index1 = findIndex("car", commandParts[1]);
          int index2 = findIndex("car", commandParts[2]);

          if (index1 != -1 && index2 != -1) {
            if (cars.get(index1).getSpeed() > cars.get(index2).getSpeed())
              System.out.println("CarSpeed: " + cars.get(index1).getCode());
            else
              System.out.println("CarSpeed: " + cars.get(index2).getCode());

            if (cars.get(index1).getStrength() > cars.get(index2).getStrength())
              System.out.println("CarStrength: " + cars.get(index1).getCode());
            else if (cars.get(index1).getStrength() < cars.get(index2).getStrength())
              System.out.println("CarStrength: " + cars.get(index2).getCode());

            if (cars.get(index1).getExactPrice() < cars.get(index2).getExactPrice())
              System.out.println("CarPrice: " + cars.get(index1).getCode());
            else
              System.out.println("CarPrice: " + cars.get(index2).getCode());

          } else
            System.out.println("Input is invalid!");

        } else if (commandParts[0].equals("CompareMotorcycles")) {
          int index1 = findIndex("motorcycle", commandParts[1]);
          int index2 = findIndex("motorcycle", commandParts[2]);

          if (index1 != -1 && index2 != -1) {
            if (motorcycles.get(index1).getSpeed() > motorcycles.get(index2).getSpeed())
              System.out.println("MotorcycleSpeed: " + motorcycles.get(index1).getCode());
            else
              System.out.println("MotorcycleSpeed: " + motorcycles.get(index2).getCode());

            if (motorcycles.get(index1).getStrength() > motorcycles.get(index2).getStrength())
              System.out.println("MotorcycleStrength: " + motorcycles.get(index1).getCode());
            else if (motorcycles.get(index1).getStrength() < motorcycles.get(index2).getStrength())
              System.out.println("MotorcycleStrength: " + motorcycles.get(index2).getCode());

            if (motorcycles.get(index1).getExactPrice() < motorcycles.get(index2).getExactPrice())
              System.out.println("MotorcyclePrice: " + motorcycles.get(index1).getCode());
            else
              System.out.println("MotorcyclePrice: " + motorcycles.get(index2).getCode());

          } else
            System.out.println("Input is invalid!");

        } else if (commandParts[0].equals("CompareBikes")) {
          int index1 = findIndex("bike", commandParts[1]);
          int index2 = findIndex("bike", commandParts[2]);

          if (index1 == -1 || index2 == -1)
            System.out.println("Input is invalid!");
          else if (bikes.get(index1).getType().equals(bikes.get(index2).getType())) {
            if (bikes.get(index1).weight > bikes.get(index2).weight)
              System.out.println("BikeWeight: " + bikes.get(index1).getCode());
            else
              System.out.println("BikeWeight: " + bikes.get(index2).getCode());

            if (bikes.get(index1).getExactPrice() < bikes.get(index2).getExactPrice())
              System.out.println("BikePrice: " + bikes.get(index1).getCode());
            else
              System.out.println("BikePrice: " + bikes.get(index2).getCode());

          } else
            System.out.println("Types should be the same!");

        }
      } else if (commandParts[0].startsWith("Search")) {

        boolean isFound = false;

        if (commandParts[0].equals("SearchCars")) {

          if (commandParts[1].equals("Color")) {

            for (Car car : cars) {
              if (car.getColor().equals(commandParts[2])) {
                isFound = true;
                System.out.println(car.getName() + " " + car.getCode());
              }
            }

            if (!isFound)
              System.out.println("No cars found with the given color!");

          } else if (commandParts[1].equals("Origin")) {

            for (Car car : cars) {
              if (car.getOrigin().equals(commandParts[2])) {
                isFound = true;
                System.out.println(car.getName() + " " + car.getCode());
              }
            }

            if (!isFound)
              System.out.println("No cars found with the given origin!");

          }

        } else if (commandParts[0].equals("SearchMotorcycles")) {

          if (commandParts[1].equals("Color")) {

            for (Motorcycle motorcycle : motorcycles) {
              if (motorcycle.getColor().equals(commandParts[2])) {
                isFound = true;
                System.out.println(motorcycle.getName() + " " + motorcycle.getCode());
              }
            }

            if (!isFound)
              System.out.println("No motorcycles found with the given color!");

          } else if (commandParts[1].equals("Origin")) {

            for (Motorcycle motorcycle : motorcycles) {
              if (motorcycle.getOrigin().equals(commandParts[2])) {
                isFound = true;
                System.out.println(motorcycle.getName() + " " + motorcycle.getCode());
              }
            }

            if (!isFound)
              System.out.println("No motorcycles found with the given origin!");

          }

        } else if (commandParts[0].equals("SearchBikes")) {

          if (commandParts[1].equals("Type")) {

            for (Bike bike : bikes) {
              if (bike.getType().equals(commandParts[2])) {
                isFound = true;
                System.out.println(bike.getName() + " " + bike.getCode());
              }
            }

            if (!isFound)
              System.out.println("No bikes found with the given type!");

          } else if (commandParts[1].equals("Origin")) {

            for (Bike bike : bikes) {
              if (bike.getOrigin().equals(commandParts[2])) {
                isFound = true;
                System.out.println(bike.getName() + " " + bike.getCode());
              }
            }

            if (!isFound)
              System.out.println("No bikes found with the given origin!");

          }
        }
      } else if (commandParts[0].startsWith("Least")) {

        int minPrice = Integer.MAX_VALUE;

        if (commandParts[0].equals("LeastCarPrice")) {

          Car minCar = null;

          for (Car car : cars) {
            if (car.getPrice() < minPrice) {
              minCar = car;
              minPrice = car.getPrice();
            }
          }

          System.out.println(minCar.getName() + " " + minCar.getCode() + " " + minCar.getPrice());

        } else if (commandParts[0].equals("LeastMotorcyclePrice")) {

          Motorcycle minMotorcycle = null;

          for (Motorcycle motorcycle : motorcycles) {
            if (motorcycle.getPrice() < minPrice) {
              minMotorcycle = motorcycle;
              minPrice = motorcycle.getPrice();
            }
          }

          System.out.println(minMotorcycle.getName() + " " + minMotorcycle.getCode() + " " + minMotorcycle.getPrice());

        } else if (commandParts[0].equals("LeastBikePrice")) {

          Bike minBike = null;

          for (Bike bike : bikes) {
            if (bike.getPrice() < minPrice) {
              minBike = bike;
              minPrice = bike.getPrice();
            }
          }

          System.out.println(minBike.getName() + " " + minBike.getCode() + " " + minBike.getPrice());

        }

      } else if (commandParts[0].equals("Show")) {
        if (commandParts[1].equals("CarProperties")) {

          int index1 = findIndex("car", commandParts[2]);

          if (index1 != -1) {

            System.out.println("CarBrand: " + cars.get(index1).getBrand());
            System.out.println("CarName: " + cars.get(index1).getName());
            System.out.println("CarColor: " + cars.get(index1).getColor());
            System.out.println("CarOrigin: " + cars.get(index1).getOrigin());

          } else {

            System.out.println("No cars exist with the given code!");

          }

        } else if (commandParts[1].equals("MotorcycleProperties")) {

          int index1 = findIndex("motorcycle", commandParts[2]);

          if (index1 != -1) {

            System.out.println("MotorcycleBrand: " + motorcycles.get(index1).getBrand());
            System.out.println("MotorcycleName: " + motorcycles.get(index1).getName());
            System.out.println("MotorcycleColor: " + motorcycles.get(index1).getColor());
            System.out.println("MotorcycleOrigin: " + motorcycles.get(index1).getOrigin());

          } else {
            System.out.println("No motorcycles exist with the given code!");

          }

        } else if (commandParts[1].equals("BikeProperties")) {

          int index1 = findIndex("bike", commandParts[2]);

          if (index1 != -1) {

            System.out.println("BikeBrand: " + bikes.get(index1).getBrand());
            System.out.println("BikeName: " + bikes.get(index1).getName());
            System.out.println("BikeColor: " + bikes.get(index1).getColor());
            System.out.println("BikeOrigin: " + bikes.get(index1).getOrigin());

          } else {

            System.out.println("No bikes exist with the given code!");

          }

        }
      }
    }

    scanner.close();

  }
}
