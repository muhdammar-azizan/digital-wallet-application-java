//SECTION 03B
//MUHAMMAD AMMAR BIN AZIZAN CB23037
//MUHAMMAD ALIF AIMAN BIN AZHAR CB23120
//MUHAMMAD HIZBU FARHAN BIN ALIAS CB23022


package digitalwalletapplication;

// OOP Requirement: Inheritance - TrackingTransaction extends the abstract Transaction class
class TrackingTransaction extends Transaction {

    public TrackingTransaction(String transactionID, double amount, int date, String receiverID) {
        super(transactionID, amount, date, receiverID);
    }
    

    // Search function to find and display transaction details by transaction ID
public static void searchTransactionByID(TrackingTransaction[] transactions, int transactionCount, String searchTransactionID, User[] users, int userCount) {
    boolean transactionFound = false;

    for (int i = 0; i < transactionCount; i++) {
        if (transactions[i].getTransactionID().equals(searchTransactionID)) {
            transactionFound = true;
            transactions[i].displayTransaction();
            String receiverID = transactions[i].getReceiverID();

            // Find the user details associated with the receiver ID
            for (int j = 0; j < userCount; j++) {
                if (users[j].getUserID().equals(receiverID)) {
                    System.out.println("User Details:");
                    System.out.println("User ID: " + users[j].getUserID());
                    System.out.println("Name: " + users[j].getName());
                    System.out.println("Email: " + users[j].getEmail());
                    return;
                }
            }
        }
    

    // Display the message only if no transaction is found
        if (!transactionFound) {
            System.out.println("No transaction found with Transaction ID: " + searchTransactionID);
        }
    }
}

    // OOP Requirement: Polymorphism - overrides Transaction's abstract method with its own behavior
    @Override
    public void displayTransaction() {
        System.out.println("Transaction Details:");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Amount: " + amount);
        System.out.println("Date: " + date);
        System.out.println("Receiver ID: " + receiverID);
        System.out.println("Status: Completed");
    }
}



