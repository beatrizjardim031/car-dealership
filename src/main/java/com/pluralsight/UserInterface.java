package com.pluralsight;

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
                                 What do you want to do?
                            1 - Find vehicles within a price range
                            2 - Find vehicles by make / model
                            3 - Find vehicles by year range
                            4 - Find vehicles by color
                            5 - Find vehicles by mileage range
                            6 - Find vehicles by type (car, truck, SUV, van)
                            7 - List ALL vehicles
                            8 - Add a vehicle
                            9 - Remove a vehicle
                            99 - Quit
                            """);
            int userChoice = input.nextInt();

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
                case 0 -> isRunning = false;

                default -> System.out.println("We don't recognize this option. Please Try again");
            }
        }
    }

    public void getByPriceRange() {
        System.out.println("Getting by price range");
    }

    public void getByMakeModel() {
        System.out.println("Getting by Make/Model");
    }

    public void getByYearRange() {
        System.out.println("Getting by year range");
    }

    public void getByColor() {
        System.out.println("Getting by color");
    }

    public void getByMileageRange() {
        System.out.println("Getting by mileage range");
    }

    public void getByType() {
        System.out.println("Getting by type");
    }

    public void getAllVehicles() {
        System.out.println("Getting all vehicles");
    }

    public void addVehicle() {
        System.out.println("Adding vehicle...");
    }

    public void removeVehicle() {
        System.out.println("Removing vehicle...");
    }
}
