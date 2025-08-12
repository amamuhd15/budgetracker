# Budget Tracker (Java CLI)

A simple **Command-Line Budget Tracker** application built in Java to help users manage and monitor their income and expenses.  
This project was developed as part of an internship assignment, with plans to extend it into a GUI-based application using **JavaFX/Swing** or a **Spring Boot REST API**.

---

## 📌 Features
- **User Authentication**
  - Secure login system with password hashing.
- **CRUD Operations**
  - Add, view, update, and delete transactions.
- **Category-based Transactions**
  - Supports predefined categories: `Food`, `Transport`, `Entertainment`, `Bills`, `Others`.
- **Persistent Storage**
  - Transactions stored in a file for data retention between sessions.
- **Summary Reports**
  - View total income, expenses, and balance.
- **Expandable Design**
  - Can be extended to support GUI or API-based frontends.

---

## 🛠️ Technologies Used
- **Java** (Core Logic)
- **Java IO** (File storage)
- **OOP Principles** (Encapsulation, Inheritance, Abstraction)
- **Password Hashing** (Security)

---
ProjectStructure
budgettracker/
│
├── Main.java                     # Entry point
├── model/                        # Domain models (POJOs & interfaces)
│   ├── Transaction.java          # Interface
│   ├── Income.java               # Income class
│   └── Expense.java              # Expense class
│
├── manager/                      # Core business logic
│   └── BudgetManager.java
│
├── io/                           # Data persistence layer
│   └── DataStorage.java
│
└── util/                         # Helpers (optional but useful)
    └── InputValidator.java

Future Improvements
Graphical User Interface (JavaFX or Swing)

Spring Boot REST API with HTML/CSS frontend

Database Support (MySQL or PostgreSQL)

Export Reports to PDF/Excel
