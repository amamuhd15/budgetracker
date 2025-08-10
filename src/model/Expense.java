package model;

public class Expense implements Transaction {
    private double amount;
    private String description;
    private String date;
    private String category;

    public Expense(double amount, String description, String date, String category) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getCategory() { return category; }

    public void display() {
        System.out.printf("[EXPENSE] %s | %s | %.2f | %s\n", date, category, amount, description);
    }
}
