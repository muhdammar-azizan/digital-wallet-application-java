//SECTION 03B
//MUHAMMAD AMMAR BIN AZIZAN CB23037
//MUHAMMAD ALIF AIMAN BIN AZHAR CB23120
//MUHAMMAD HIZBU FARHAN BIN ALIAS CB23022


package digitalwalletapplication;

class Currency {
    private String currencyID;
    private String currencyCode;
    private String currencyName;
    private double exchangeRate; // Relative to USD

    public Currency(String currencyID, String currencyCode, String currencyName, double exchangeRate) {
        this.currencyID = currencyID;
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
    }

    public String getCurrencyID() {
        return currencyID;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}
