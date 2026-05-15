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

    @Override
    public double getTotalPrice() {
//        vehicle price
//        + sales tax (5% of vehicle price)
//        + recording fee ($100)
//        + processing fee ($295 if price < $10,000, $495 otherwise)
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }
}
