//SECTION 03B
//MUHAMMAD AMMAR BIN AZIZAN CB23037
//MUHAMMAD ALIF AIMAN BIN AZHAR CB23120
//MUHAMMAD HIZBU FARHAN BIN ALIAS CB23022


package digitalwalletapplication;

// OOP Requirement: Abstract class - defines a contract (abstract method below) for subclasses like TrackingTransaction
abstract class Transaction {
    protected final String transactionID;
    protected final double amount;
    protected final int date; // Format: YYYYMMDD
    protected final String receiverID;

    public Transaction(String transactionID, double amount, int date, String receiverID) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.date = date;
        this.receiverID = receiverID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public double getAmount() {
        return amount;
    }

    public int getDate() {
        return date;
    }

    public String getReceiverID() {
        return receiverID;
    }

    // OOP Requirement: abstract method - must be implemented by subclasses (see TrackingTransaction)
    public abstract void displayTransaction() ;

}