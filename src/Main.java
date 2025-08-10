import manager.BudgetManager;
//import io.DataStorage;
import manager.UserManager;
import java.util.Scanner;

public class Main {

    private static final String[] INCOME_CATEGORIES = {
    "Salary", "Bonus", "Freelance", "Investment", "Gift", "Other"
};

private static final String[] EXPENSE_CATEGORIES = {
    "Groceries", "Rent", "Transportation", "Utilities", "Entertainment",
    "Dining", "Health", "Education", "Travel", "Other"
};

    public static void main(String[] args) {

        // Initialize UserManager to handle user registration and login
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        String currentUser = null;

    System.out.println("=== Welcome to Budget Tracker ===");

    //LOGIN LOOP
    while (currentUser == null) {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.print("Choose option: ");
        String option = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (option.equals("1")) {
            if (userManager.login(username, password)) {
                currentUser = username;
                System.out.println("Login successful. Welcome, " + username + "!");
            } else {
                System.out.println("Invalid credentials. Try again.");
            }
        } else if (option.equals("2")) {
            if (userManager.register(username, password)) {
                System.out.println("Registration successful. You can now log in.");
            } else {
                System.out.println("Username already exists.");
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    // Create personalized data file
    BudgetManager manager = new BudgetManager();
    String userDataFile = currentUser + "_data.txt";
    manager.setTransactions(io.DataStorage.loadTransactions(userDataFile));

        // Main application loop
        System.out.println("Welcome to your Budget Tracker, " + currentUser + "!");
        //System.out.println("Your data will be saved in: " + userDataFile);

        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. View Summary");
            System.out.println("5. View Monthly Summary");
            System.out.println("6. View Category Breakdown");
            System.out.println("7. Exit");


            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter amount: ");
                    double incomeAmount = readDouble(scanner);
                    System.out.print("Enter category: ");
                    String incomeCategory = readValidCategory(scanner, INCOME_CATEGORIES);
                    System.out.print("Enter description: ");
                    String incomeDesc = scanner.nextLine();
                    System.out.print("Enter date (e.g., 2025-07-29): ");
                    String incomeDate = scanner.nextLine();                   
                    manager.addIncome(incomeAmount, incomeDesc, incomeDate, incomeCategory);
                    System.out.println("Income added.");
                    break;

                case "2":
                    System.out.print("Enter amount: ");
                    double expenseAmount = readDouble(scanner);
                    System.out.print("Enter category: ");
                    String expenseCategory = readValidCategory(scanner, EXPENSE_CATEGORIES);
                    System.out.print("Enter description: ");
                    String expenseDesc = scanner.nextLine();
                    System.out.print("Enter date (e.g., 2025-07-29): ");
                    String expenseDate = scanner.nextLine();                    
                    manager.addExpense(expenseAmount, expenseDesc, expenseDate, expenseCategory);
                    System.out.println("Expense added.");
                    break;

                case "3":
                    System.out.println("\n--- Transaction History ---");
                    manager.showAllTransactions();
                    break;

                case "4":
                    double income = manager.getTotalIncome();
                    double expenses = manager.getTotalExpenses();
                    double balance = manager.getBalance();
                    System.out.printf("\nTotal Income: %.2f\n", income);
                    System.out.printf("Total Expenses: %.2f\n", expenses);
                    System.out.printf("Current Balance: %.2f\n", balance);
                    break;
                
                case "5":
                    System.out.print("Enter month and year (format: YYYY-MM): ");
                    String monthYear = scanner.nextLine();
                    manager.showMonthlySummary(monthYear);
                    break;
                case "6":
                    manager.showCategoryBreakdown();
                    break;
                case "7":
                    running = false;
                    io.DataStorage.saveTransactions(manager.getTransactions(), userDataFile);
                    System.out.println("Data saved. Exiting Budget Tracker. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Helper method to read valid double input
    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid amount. Enter a number: ");
            }
        }
    }


    // Helper method to read a valid category from a predefined list
    // This method ensures that the user selects a valid category from the provided options
    private static String readValidCategory(Scanner scanner, String[] allowedCategories) {
    while (true) {
        System.out.print("Enter category from the list: ");
        for (String category : allowedCategories) {
            System.out.print(category + " | ");
        }
        System.out.println();

        String input = scanner.nextLine().trim();
        for (String category : allowedCategories) {
            if (category.equalsIgnoreCase(input)) {
                return category; // return exact match (proper casing)
            }
        }

        System.out.println("Invalid category. Please try again.");
    }
}

}
// Main.java
// This is the entry point for the Budget Tracker application. It allows users to add incomes and expenses, view transactions, and see a summary of their financial status. The application uses a simple console interface for interaction.
// It utilizes the BudgetManager class to manage transactions and the Transaction interface for defining income and expense types.
// The main loop continues until the user chooses to exit, providing a straightforward way to track personal finances.
// The application is designed to be user-friendly, guiding the user through each step and validating inputs to ensure correct data entry. 
