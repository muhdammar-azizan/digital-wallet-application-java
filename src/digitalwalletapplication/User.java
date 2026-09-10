//SECTION 03B
//MUHAMMAD AMMAR BIN AZIZAN CB23037
//MUHAMMAD ALIF AIMAN BIN AZHAR CB23120
//MUHAMMAD HIZBU FARHAN BIN ALIAS CB23022


package digitalwalletapplication;

// OOP Requirement: Creating multiple Java classes - User is one of many entity classes (see also Wallet, Payment, RewardPoint, Currency, Transaction, etc.)
// OOP Requirement: Encapsulation - private fields only accessible via constructor/getters below
public class User {
    private String userID;
    private String name;
    private String email;
    private String password;

    public User(String userID, String name, String email, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean validatePassword(String inputPassword) {
        // OOP Requirement: String class method (.equals())
        return this.password.equals(inputPassword);
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
