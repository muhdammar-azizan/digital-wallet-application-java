package FactoryPattern;

class WalletFP implements WalletComponent {
    private final double balance;

    public WalletFP(double balance) {
        this.balance = balance;
    }

    @Override
    public void displayInfo() {
        System.out.println("Wallet Balance: $" + balance);
    }
}
