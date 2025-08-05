package manager;

import model.Transaction;
import model.Income;
import model.Expense;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class BudgetManager {
    private List<Transaction> transactions;

    public BudgetManager() {
        transactions = new ArrayList<>();
    }

    public void addIncome(double amount, String description, String date, String category) {
        Income income = new Income(amount, description, date, category);
        transactions.add(income);
    }

    public void addExpense(double amount, String description, String date, String category) {
        Expense expense = new Expense(amount, description, date, category);
        transactions.add(expense);
    }

    public void showAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactions) {
                t.display();
            }
        }
    }

    public double getTotalIncome() {
        double total = 0;
        for (Transaction t : transactions) {
            if (t instanceof Income) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Transaction t : transactions) {
            if (t instanceof Expense) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpenses();
    }

    // Getter for transactions, useful for saving to file
    // This allows the manager to retrieve transactions for saving
    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Setter for transactions, useful for loading from file
    // This allows the manager to set transactions after loading from storage
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    //Method for monthly summary
    public void showMonthlySummary(String monthYear) {
    double income = 0;
    double expenses = 0;

    System.out.println("\n--- Monthly Summary for " + monthYear + " ---");

    for (Transaction t : transactions) {
        if (t.getDate().startsWith(monthYear)) {
            t.display();
            if (t instanceof Income) income += t.getAmount();
            if (t instanceof Expense) expenses += t.getAmount();
        }
    }

    double balance = income - expenses;

    System.out.printf("\nIncome: %.2f | Expenses: %.2f | Balance: %.2f\n", income, expenses, balance);
}
    // Method for yearly summary
    /***public void showYearlySummary(String year) {
        double income = 0;
        double expenses = 0;

        System.out.println("\n--- Yearly Summary for " + year + " ---");

        for (Transaction t : transactions) {
            if (t.getDate().startsWith(year)) {
                t.display();
                if (t instanceof Income) income += t.getAmount();
                if (t instanceof Expense) expenses += t.getAmount();
            }
        }

        double balance = income - expenses;

        System.out.printf("\nIncome: %.2f | Expenses: %.2f | Balance: %.2f\n", income, expenses, balance);
    }*/

    public void showCategoryBreakdown() {
    Map<String, Double> incomeByCategory = new HashMap<>();
    Map<String, Double> expenseByCategory = new HashMap<>();

    for (Transaction t : transactions) {
        String category = t.getCategory();
        double amount = t.getAmount();

        if (t instanceof Income) {
            incomeByCategory.put(category, incomeByCategory.getOrDefault(category, 0.0) + amount);
        } else {
            expenseByCategory.put(category, expenseByCategory.getOrDefault(category, 0.0) + amount);
        }
    }

    System.out.println("\n--- Category Breakdown ---");

    System.out.println("\nIncome:");
    for (String category : incomeByCategory.keySet()) {
        System.out.printf("%s: %.2f\n", category, incomeByCategory.get(category));
    }

    System.out.println("\nExpenses:");
    for (String category : expenseByCategory.keySet()) {
        System.out.printf("%s: %.2f\n", category, expenseByCategory.get(category));
    }
}
}
// BudgetManager.java
// This class manages the budget by allowing users to add incomes and expenses, view all transactions,

