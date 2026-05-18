package com.pluralsight;

public class SalesContract extends Contract {
    //fields
    private boolean finance;
    private static final double SALES_TAX = 0.05; // I used static and final are constants and it will never change
    private static final int RECORDING_FEE = 100; // for constants, we use ALL_CAPS

    public SalesContract(String dateOfContract, String customerName, Vehicle vehicleSold, String customerEmail, boolean finance) {
        super(dateOfContract, customerName, vehicleSold, customerEmail);
        this.finance = finance;
    }

    public boolean isFinance() {
        return finance;
    }

    @Override
    public double getMonthlyPayment() {
        double vehiclePrice = vehicleSold.getPrice();
        double loan;
        if (!finance) {
            return 0;
        }
        if (vehiclePrice >= 10000) {
            int loanLength = 48;
            double monthlyRate = 4.25 / 100 / 12;
            loan = getTotalPrice() * (monthlyRate / (1 - Math.pow(1 + monthlyRate, -loanLength)));

        } else {
            int loanLength = 24;
            double monthlyRate = 5.25 / 100 / 12;
            loan = getTotalPrice() * (monthlyRate / (1 - Math.pow(1 + monthlyRate, -loanLength)));
        }
        return loan;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = vehicleSold.getPrice();
        double tax = vehiclePrice * SALES_TAX;
        double processingFee;
        if (vehiclePrice <= 10000) {
            processingFee = 295;
        } else {
            processingFee = 495;
        }
        return vehiclePrice + tax + RECORDING_FEE + processingFee;
    }

    public String toCsv() {
 // Headers: SALE|date|name|email|vehicleData|tax|recordingFee|processingFee|totalPrice|finance|monthlyPayment
        return String.format("SALE|%s|%s|%s|%s|%.2f|%d|%.2f|%.2f|%s|%.2f",
                dateOfContract,
                customerName,
                customerEmail,
                vehicleSold.toCsv(),
                vehicleSold.getPrice() * SALES_TAX,
                RECORDING_FEE,
                getProcessingFee(),
                getTotalPrice(),
                (finance ? "YES" : "NO"),
                getMonthlyPayment());
    }

    private Object getDate() {
        return getDateOfContract();
    }

    private Vehicle getVehicle() {
        return getVehicleSold();
    }

    public double getProcessingFee() {
        if (getVehicle().getPrice() < 10000) {
            return 295.00;
        } else {
            return 495.00;
        }
    }
}
