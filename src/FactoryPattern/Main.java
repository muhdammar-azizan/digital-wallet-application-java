package FactoryPattern;

public class Main {
    public static void main(String[] args) {
        // Create components using the factory
        WalletComponent UserFP = WalletComponentFactory.createComponent("user", "CB23037", "Ammar", "ammar@gmail.com", "password123");
        WalletComponent WalletFP = WalletComponentFactory.createComponent("wallet", 500.0);
        WalletComponent TransactionFP = WalletComponentFactory.createComponent("transaction", "T001", 100.0, 20230101, "CB23022");
        WalletComponent RewardPointFP = WalletComponentFactory.createComponent("rewardpoint", 10);
        WalletComponent CurrencyFP = WalletComponentFactory.createComponent("currency", "C001", "USD", "US Dollar", 1.0);
        WalletComponent PaymentFP = WalletComponentFactory.createComponent("payment", "P001", 50.0, "utilities");

        // Use polymorphism to call the displayInfo method
        UserFP.displayInfo();
        WalletFP.displayInfo();
        TransactionFP.displayInfo();
        RewardPointFP.displayInfo();
        CurrencyFP.displayInfo();
        PaymentFP.displayInfo();
    }
}
