package FactoryPattern;

class RewardPointFP implements WalletComponent {
    private final int points;

    public RewardPointFP() {
        this.points = 10;
    }

    @Override
    public void displayInfo() {
        System.out.println("Reward Points: " + points);
    }
}
