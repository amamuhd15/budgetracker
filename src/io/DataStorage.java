package io;

import model.Income;
import model.Expense;
import model.Transaction;
//import manager.BudgetManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * DataStorage class handles saving and loading transactions to/from a file.
 */

public class DataStorage {
    //private static final String FILE_NAME = "budget_data.txt";

    public static void saveTransactions(List<Transaction> transactions, String filename) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
        for (Transaction t : transactions) {
            String type = (t instanceof Income) ? "Income" : "Expense";
            writer.printf("%s;%f;%s;%s;%s\n",
                    type,
                    t.getAmount(),
                    t.getDescription(),
                    t.getDate(),
                    t.getCategory());
        }
    } catch (IOException e) {
        System.out.println("Error saving data: " + e.getMessage());
    }
}
    public static List<Transaction> loadTransactions(String filename) {
    List<Transaction> transactions = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length == 5) {
                String type = parts[0];
                double amount = Double.parseDouble(parts[1]);
                String description = parts[2];
                String date = parts[3];
                String category = parts[4];

                if (type.equalsIgnoreCase("Income")) {
                    transactions.add(new Income(amount, description, date, category));
                } else if (type.equalsIgnoreCase("Expense")) {
                    transactions.add(new Expense(amount, description, date, category));
                }
            }
        }
    } catch (FileNotFoundException e) {
        // No data file yet
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error loading data: " + e.getMessage());
    }

    return transactions;
}


    
}

