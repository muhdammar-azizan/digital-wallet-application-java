package FactoryPattern;

class PaymentFP implements WalletComponent {
    private final String paymentID;
    private final double amount;
    private final String method;

    public PaymentFP(String paymentID, double amount, String method) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.method = method;
    }

    @Override
    public void displayInfo() {
        System.out.println("Payment ID: " + paymentID + ", Amount: $" + amount + ", Method: " + method);
    }
}
