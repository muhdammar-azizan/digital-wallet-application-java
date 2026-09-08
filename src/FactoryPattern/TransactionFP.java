package FactoryPattern;

class TransactionFP implements WalletComponent {
    private final String transactionID;
    private final double amount;
    private final int date; // Format: YYYYMMDD
    private final String receiverID;

    public TransactionFP(String transactionID, double amount, int date, String receiverID) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.date = date;
        this.receiverID = receiverID;
    }

    @Override
    public void displayInfo() {
        System.out.println("Transaction ID: " + transactionID + ", Amount: $" + amount + ", Date: " + date + ", Receiver ID: " + receiverID);
    }
}
