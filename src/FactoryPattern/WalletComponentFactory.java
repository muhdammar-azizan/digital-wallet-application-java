package FactoryPattern;

public class WalletComponentFactory {

    public static WalletComponent createComponent(String type, Object... params) {
        switch (type.toLowerCase()) {
            case "user" -> {
                return new UserFP((String) params[0], (String) params[1], (String) params[2], (String) params[3]);
            }
            case "wallet" -> {
                return new WalletFP((Double) params[0]);
            }
            case "transaction" -> {
                return new TransactionFP((String) params[0], (Double) params[1], (Integer) params[2], (String) params[3]);
            }
            case "rewardpoint" -> {
                return new RewardPointFP();
            }
            case "currency" -> {
                return new CurrencyFP((String) params[0], (String) params[1], (String) params[2], (Double) params[3]);
            }
            case "payment" -> {
                return new PaymentFP((String) params[0], (Double) params[1], (String) params[2]);
            }
            default -> throw new IllegalArgumentException("Invalid component type: " + type);
        }
    }
}
