package com.pluralsight;

public abstract class Contract {
    //fields
    protected String dateOfContract;
    protected String customerName;
    protected String customerEmail;
    protected Vehicle vehicleSold;

    public Contract(String dateOfContract, String customerName, Vehicle vehicleSold, String customerEmail) {
        this.dateOfContract = dateOfContract;
        this.customerName = customerName;
        this.vehicleSold = vehicleSold;
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public String getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(String dateOfContract) {
        this.dateOfContract = dateOfContract;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();
}
