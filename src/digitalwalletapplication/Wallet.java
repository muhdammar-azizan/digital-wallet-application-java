//SECTION 03B
//MUHAMMAD AMMAR BIN AZIZAN CB23037
//MUHAMMAD ALIF AIMAN BIN AZHAR CB23120
//MUHAMMAD HIZBU FARHAN BIN ALIAS CB23022


package digitalwalletapplication;

// OOP Requirement: Composition and aggregation - a Wallet owns/holds Transaction objects
class Wallet {
    private double balance;
    private final Transaction[] transactions;
    private int transactionCount;

    public Wallet(double balance) {
        this.balance = balance;
        this.transactions = new Transaction[100];
        this.transactionCount = 0;
    }

    public void addBalance(double amount) {
        balance += amount;
        System.out.println("Balance updated. Current balance: " + balance);
    }

    public void deductBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }

    public void addTransaction(Transaction transaction) {
        transactions[transactionCount++] = transaction;
        System.out.println("Transaction added successfully.");
    }

    public void displayTransactions() {
        if (transactionCount == 0) {
            System.out.println("No transactions available.");
            return;
        }

        for (int i = 0; i < transactionCount; i++) {
            transactions[i].displayTransaction();
        }
    }
}