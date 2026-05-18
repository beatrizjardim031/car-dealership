package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    static Scanner input = new Scanner(System.in);
    //field
    private Dealership dealership;

    public UserInterface() {
    }

    private void init(){
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealership();
    }

    public void display(){
        init();

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("""
                                        Menu:
                            1 - Find vehicles within a price range
                            2 - Find vehicles by make / model
                            3 - Find vehicles by year range
                            4 - Find vehicles by color
                            5 - Find vehicles by mileage range
                            6 - Find vehicles by type (car, truck, SUV, van)
                            7 - List ALL vehicles
                            8 - Add a vehicle
                            9 - Remove a vehicle
                            10 - Sell/Lease a vehicle
                            0 - Quit
                            """);
            System.out.print("What would you like to do? ");
            int userChoice;
            try {
                String userInput = input.nextLine();
                userChoice = Integer.parseInt(userInput);
            } catch (Exception e) {
                System.out.println("\nSorry, invalid option. Please try again!\n");
                continue; // skip to next loop iteration
            }

            switch (userChoice) {
                    case 1 -> getByPriceRange();
                    case 2 -> getByMakeModel();
                    case 3 -> getByYearRange();
                    case 4 -> getByColor();
                    case 5 -> getByMileageRange();
                    case 6 -> getByType();
                    case 7 -> getAllVehicles();
                    case 8 -> addVehicle();
                    case 9 -> removeVehicle();
                    case 10 -> sellOrLeaseVehicle();
                    case 0 -> isRunning = false;

                    default -> System.out.println("We don't recognize this option. Please Try again");
                }
        }
    }

    public void getByPriceRange() {
        System.out.print("Type minimum price: ");
        double userChoiceMin = Double.parseDouble(input.nextLine());
        System.out.print("Type maximum price: ");
        double userChoiceMax = Double.parseDouble(input.nextLine());
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByPrice(userChoiceMin, userChoiceMax);

        displayVehicles(vehicles);
    }

    public void getByMakeModel() {
        System.out.print("Type make: ");
        String userChoiceMake = input.nextLine();
        System.out.print("Type model: ");
        String userChoiceModel = input.nextLine();
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMakeModel(userChoiceMake, userChoiceModel);

        displayVehicles(vehicles);
    }

    public void getByYearRange() {
        System.out.print("Type minimum year: ");
        int userChoiceMin = Integer.parseInt(input.nextLine());
        System.out.print("Type maximum year: ");
        int userChoiceMax = Integer.parseInt(input.nextLine());
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(userChoiceMin, userChoiceMax);

        displayVehicles(vehicles);
    }

    public void getByColor() {
        System.out.print("Type make: ");
        String userChoiceColor = input.nextLine();
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(userChoiceColor);

        displayVehicles(vehicles);
    }

    public void getByMileageRange() {
        System.out.print("Type minimum mileage: ");
        int userChoiceMin = Integer.parseInt(input.nextLine());
        System.out.print("Type maximum mileage: ");
        int userChoiceMax = Integer.parseInt(input.nextLine());
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMileage(userChoiceMin, userChoiceMax);

        displayVehicles(vehicles);
    }

    public void getByType() {
        System.out.print("Type vehicle Type: ");
        String userChoiceType = input.nextLine();
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(userChoiceType);

        displayVehicles(vehicles);
    }

    public void getAllVehicles() {
       ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void addVehicle() {
        System.out.print("Type Vin: ");
        int vin = Integer.parseInt(input.nextLine());
        System.out.println("Type year: ");
        int year = Integer.parseInt(input.nextLine());
        System.out.println("Type make: ");
        String make = input.nextLine();
        System.out.println("Type model: ");
        String model = input.nextLine();
        System.out.println("Vehicle type: ");
        String vehicleType = input.nextLine();
        System.out.println("Type color: ");
        String color = input.nextLine();
        System.out.print("Type mileage: ");
        int mileage = Integer.parseInt(input.nextLine());
        System.out.print("Type price: ");
        double price = Double.parseDouble(input.nextLine());

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, mileage, price);
        DealershipFileManager dealershipFileManager = new DealershipFileManager();

        dealership.addVehicle(vehicle);
        dealershipFileManager.saveDealership(dealership);

    }

    public void removeVehicle() {
        System.out.print("Type Vin: ");
        int vin = Integer.parseInt(input.nextLine());

        ArrayList<Vehicle> result = dealership.getAllVehicles();
        for (Vehicle vehicle : result) {
            if(vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
            }
        }
        DealershipFileManager dealershipFileManager = new DealershipFileManager();

        dealershipFileManager.saveDealership(dealership);
    }

    public void sellOrLeaseVehicle() {
        //ask for VIN and find the vehicle
        System.out.print("Enter VIN of vehicle: ");
        int vin = Integer.parseInt(input.nextLine());

        // find the vehicle in the dealership
        Vehicle vehicle = null;
        for (Vehicle vehicle1 : dealership.getAllVehicles()) {
            if (vehicle1.getVin() == vin) {
                vehicle = vehicle1;
            }
        }
        //if vehicle not found
        if (vehicle == null) {
            System.out.println("Vehicle not found!");
            return;
        }
        System.out.print("Enter your name: ");
        String customerName = input.nextLine();

        System.out.print("Enter your email: ");
        String customerEmail = input.nextLine();

        System.out.print("Enter date (YYYYMMDD): ");
        String date = input.nextLine();

        System.out.print("Would you like to (S)ell or (L)ease? ");
        String choice = input.nextLine().toUpperCase();

        Contract contract = null;

        if (choice.equals("S")) {
            System.out.print("Would you like to finance? (yes/no): ");
            boolean finance = input.nextLine().equalsIgnoreCase("yes");
            contract = new SalesContract(date, customerName, vehicle, customerEmail, finance);
        } else if (choice.equals("L")) {
            // check vehicle age first!
            if (2026 - vehicle.getYear() > 3) {
                System.out.println("Sorry, you can only lease vehicles 3 years old or newer!");
                return;
            }
            contract = new LeaseContract(date, customerName, vehicle, customerEmail);
        }
        ContractDataManager contractDataManager = new ContractDataManager();
        contractDataManager.saveContract(contract);

        dealership.removeVehicle(vehicle);
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealership(dealership);
    }

    //helper methods
    public void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.printf("%nVin: %d | Year: %d | Make: %s | Model: %s | Vehicle Type: %s | Color: %s | Odometer: %d | Price: %.2f%n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());

        }
    }
//    public static String askForText(String prompt) {
//        System.out.print(prompt);
//        return input.nextLine();
//    }
}
