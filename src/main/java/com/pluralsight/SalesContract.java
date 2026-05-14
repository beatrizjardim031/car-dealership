package com.pluralsight;

public class SalesContract extends Contract {
    //fields
    private boolean finance;
    private static final double SALES_TAX = 0.05; // I used static and final are constants and it will never change
    private static final int RECORDING_FEE = 100; // for constants we use ALL_CAPS

    public SalesContract(String dateOfContract, String customerName, String customerEmail, String vehicleSold, boolean finance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }
}
