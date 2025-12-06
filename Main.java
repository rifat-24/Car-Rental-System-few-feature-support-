import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

class Car {
    private String car_id;
    private String car_name;
    private String car_brand;
    private double base_prize;
    private boolean isAvailable;

    Car(String car_id, String car_name, String car_brand, double base_prize) {
        this.car_id = car_id;
        this.car_name = car_name;
        this.car_brand = car_brand;
        this.base_prize = base_prize;
        isAvailable = true;
    }

    public String get_car_id() {
        return car_id;
    }

    public String get_car_name() {
        return car_name;
    }

    public String get_car_brand() {
        return car_brand;
    }

    public double get_car_base_prize() {
        return base_prize;
    }

    public void return_car() {
        isAvailable = true;
    }
     public void rent() {
        isAvailable = false;
    }


    public boolean isAvailabll() {
        return isAvailable;
    }

    public double CalculatePrise(int number_days) {
        return number_days * base_prize;
    }
}

class Customar {
    private String customar_name;
    private String customar_id;

    Customar(String customar_name, String customar_id) {
        this.customar_name = customar_name;
        this.customar_id = customar_id;
    }

    public String get_customar_name() {
        return customar_name;
    }

    public String get_customar_id() {
        return customar_id;
    }
}

class Rental {
    private Car car;
    private Customar customar;
    private int number_days;

    Rental(Car car, Customar customar, int number_days) {
        this.car = car;
        this.customar = customar;
        this.number_days = number_days;
    }

    Car get_car() {
        return car;
    }

    Customar get_customar() {
        return customar;
    }

    int get_number_days() {
        return number_days;
    }

}

class RentalSystem {
    private List<Car> cars;
    private List<Customar> customers;
    private List<Rental> rentals;

    public void addCar(Car car) {
        cars.add(car);
    }

    public RentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    void addCustomer(Customar new_customar) {
        customers.add(new_customar);
    }

    public void returnCar(Car car) {
        car.return_car();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.get_car() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);

        } else {
            System.out.println("Car was not rented.");
        }
    }

    public void rentCar(Car car, Customar customer, int days) {
        if (car.isAvailabll()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));

        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choise = sc.nextInt();
            sc.nextLine(); // Consume newline
            if (choise == 1) {
                System.out.println("\n== Rent a Car ==\n");
                System.out.print("Enter your name: ");
                String customerName = sc.nextLine();

                for (Car car : cars) {
                    if (car.isAvailabll()) {
                        System.out.println(car.get_car_id() + " - " + car.get_car_brand() + " " + car.get_car_brand());
                    }
                }
                System.out.print("\nEnter the car ID you want to rent: ");
                String carId = sc.nextLine();
                System.out.print("Enter the number of days for rental: ");
                int rentalDays = sc.nextInt();
                sc.nextLine(); // Consume newline
                Customar newCustomer = new Customar(customerName, "CUS" + (customers.size() + 1));
                addCustomer(newCustomer);
                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.get_car_id().equals(carId) && car.isAvailabll()) {
                        selectedCar = car;
                        break;
                    }
                }
                if (selectedCar != null) {
                    double totalPrice = selectedCar.CalculatePrise(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.get_customar_id());
                    System.out.println("Customer Name: " + newCustomer.get_customar_name());
                    System.out.println("Car: " + selectedCar.get_car_brand() + " " + selectedCar.get_car_brand());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar rented successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                } else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }

            } else if (choise == 2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID you want to return: ");
                String carId = sc.nextLine();
                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.get_car_id().equals(carId) && !car.isAvailabll()) {
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) {
                    Customar customer = null;
                    for (Rental rental : rentals) {
                        if (rental.get_car() == carToReturn) {
                            customer = rental.get_customar();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + customer.get_customar_name());
                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("Invalid car ID or car is not rented.");
                }
            } else if (choise == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        System.out.println("\nThank you for using the Car Rental System!");
    }
}

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        rentalSystem.menu();
    }
}
