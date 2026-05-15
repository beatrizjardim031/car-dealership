package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContractDataManager {
//    private

    public void saveContract(Contract contract) {
        try {
            PrintWriter writer = new PrintWriter (new FileWriter("contracts.cvs", true));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
