package FactoryPattern;

class UserFP implements WalletComponent {
    private final String userID;
    private final String name;
    private final String email;
    private final String password;

    public UserFP(String userID, String name, String email, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public void displayInfo() {
        System.out.println("User ID: " + userID + ", Name: " + name + ", Email: " + email);
    }
}
