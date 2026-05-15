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
    public String toCsv() {
// Headers: CONTRACT_TYPE|DATE|NAME|EMAIL|VIN|YEAR|MAKE|MODEL|TYPE|COLOR|MILES|PRICE|TAX|ENDING_VALUE|LEASE_FEE|TOTAL|FINANCE|MONTHLY
        return String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f",
                getDate(),
                getCustomerName(),
                getCustomerEmail(),
                getVehicle().getVin(),
                getVehicle().getYear(),
                getVehicle().getMake(),
                getVehicle().getModel(),
                getVehicle().getVehicleType(),
                getVehicle().getColor(),
                getVehicle().getOdometer(),
                getVehicle().getPrice(),
                getEndingValue(),
                getLeaseFee(),
                getTotalPrice(),
                getMonthlyPayment()
        );
    }

    public double getEndingValue() {
        return getVehicle().getPrice() * 0.50;
    }

    public double getLeaseFee() {
        return getVehicle().getPrice() * 0.07;
    }

    public String getDate() {
        return getDateOfContract();
    }

    public Vehicle getVehicle() {
        return getVehicleSold();
    }
}
