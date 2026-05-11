package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    public Dealership getDealership(){
        Dealership dealership = null;
        try {
            FileReader filereader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(filereader);
            String line = bufferedReader.readLine(); // reads the first line from the file
            if (!line.trim().isEmpty()){ //.trim() removes leading/trailing whitespace, only process if the line it's not empty
                String[] parts = line.split("\\|");
                String name = (parts[0]);
                String address = (parts[1]);
                String phoneNumber= parts[2];
                line = bufferedReader.readLine(); // reads the next line
                dealership = new Dealership(name, address, phoneNumber); // creates a new transaction object and adds it to the list
            }
            while(line != null) {
                String[] parts = line.split("\\|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make= parts[2];
                String model = parts[3];
                String vehicleType = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);


                dealership.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                line = bufferedReader.readLine();

            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Could not load transaction file");
        }
        return dealership;
    }

    public boolean saveDealership(Dealership dealership) {
        try {
            FileWriter fileWriter = new FileWriter("inventory.csv", false); // true means append the mode, so it won't overwrite
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");

            ArrayList<Vehicle> vehicles = dealership.getAllVehicles();

            for (Vehicle vehicle : vehicles) {
                bufferedWriter.write(vehicle.toCsv());
            }

            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("Sorry, we could not read your transaction.");
            return false;
        }
    }

}
