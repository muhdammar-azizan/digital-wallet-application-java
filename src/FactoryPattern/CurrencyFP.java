package FactoryPattern;

class CurrencyFP implements WalletComponent {
    private final String currencyID;
    private final String currencyCode;
    private final String currencyName;
    private final double exchangeRate;

    public CurrencyFP(String currencyID, String currencyCode, String currencyName, double exchangeRate) {
        this.currencyID = currencyID;
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public void displayInfo() {
        System.out.println("Currency Code: " + currencyCode + ", Name: " + currencyName + ", Exchange Rate: " + exchangeRate);
    }
}
