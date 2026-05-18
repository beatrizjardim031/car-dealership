package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContractDataManager {
//    private

    public void saveContract(Contract contract) {
        try {
            PrintWriter writer = new PrintWriter (new FileWriter("contracts.csv", true));

            if (contract instanceof SalesContract salesContract) {
                writer.println(salesContract.toCsv());
            } else if (contract instanceof LeaseContract leaseContract) {
                writer.println(leaseContract.toCsv());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
