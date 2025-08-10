package model;

public interface Transaction {
    double getAmount();
    String getDescription();
    String getDate();
    String getCategory();
    void display();  // to show details in the console
}
