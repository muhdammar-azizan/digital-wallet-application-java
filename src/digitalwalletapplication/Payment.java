//SECTION 03B
//MUHAMMAD AMMAR BIN AZIZAN CB23037
//MUHAMMAD ALIF AIMAN BIN AZHAR CB23120
//MUHAMMAD HIZBU FARHAN BIN ALIAS CB23022


package digitalwalletapplication;

class Payment {
    private final String paymentID;
    private final double amount;
    private final String method; // utilities, tax, loan, etc.

    public Payment(String paymentID, double amount, String method) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.method = method;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }
}
