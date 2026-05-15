package com.pluralsight;

public class LeaseContract extends Contract {
    private static final double ENDING_VALUE = .5;
    private static final double LEASE_FEE = .07;

    public LeaseContract(String dateOfContract, String customerName, Vehicle vehicleSold, String customerEmail) {
        super(dateOfContract, customerName, vehicleSold, customerEmail);
    }

    @Override
    public double getMonthlyPayment() {
        int loanLength = 36;
        double monthlyRate = 4.00 / 100 / 12;

        return getTotalPrice() * (monthlyRate / (1 - Math.pow(1 + monthlyRate, -loanLength)));
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = vehicleSold.getPrice();

        return vehiclePrice + (vehiclePrice * LEASE_FEE) + (vehiclePrice * ENDING_VALUE);
    }
}
