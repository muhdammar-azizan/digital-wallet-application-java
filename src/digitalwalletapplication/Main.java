//SECTION 03B
//MUHAMMAD AMMAR BIN AZIZAN CB23037
//MUHAMMAD ALIF AIMAN BIN AZHAR CB23120
//MUHAMMAD HIZBU FARHAN BIN ALIAS CB23022


package digitalwalletapplication;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User[] users = new User[10];
        Wallet[] wallets = new Wallet[10];
        RewardPoint[] rewardPoints = new RewardPoint[10];
        Transaction[] transactions = new Transaction[100];
        TrackingTransaction[] trackingTransactions = new TrackingTransaction[100];
        Payment[] payments = new Payment[50];
        double[] transactionAmounts = new double[100];

        int userCount = 0;
        int transactionCount = 0;
        int trackingTransactionCount = 0;
        int paymentCount = 0;

        String loggedInUserId = null;

        Currency[] currencies = {
            new Currency("1", "USD", "US Dollar", 1.0),
            new Currency("2", "EUR", "Euro", 0.85),
            new Currency("3", "JPY", "Japanese Yen", 110.0),
            new Currency("4", "INR", "Indian Rupee", 74.0)
        };

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register User");
            System.out.println("2. Log In");
            System.out.println("3. Create Wallet");
            System.out.println("4. Add Balance");
            System.out.println("5. Check Balance");
            System.out.println("6. Add Transaction");
            System.out.println("7. Display All Transactions");
            System.out.println("8. Tracking Transaction");
            System.out.println("9. Add Reward Points");
            System.out.println("10. Check Reward Points Balance");
            System.out.println("11. Make Payment");
            System.out.println("12. Currency Calculator");
            System.out.println("13. Log Out");
            System.out.println("14. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (userCount < users.length) {
                        System.out.print("Enter User ID: ");
                        String userID = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Password: ");
                        String password = scanner.nextLine();

                        users[userCount++] = new User(userID, name, email, password);
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("User limit reached. Cannot register more users.");
                    }
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    String loginID = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String loginPassword = scanner.nextLine();

                    boolean loggedIn = false;
                    for (int i = 0; i < userCount; i++) {
                        if (users[i].getUserID().equals(loginID) && users[i].validatePassword(loginPassword)) {
                            loggedInUserId = users[i].getUserID();
                            System.out.println("Logged in successfully as " + users[i].getName());
                            loggedIn = true;
                            break;
                        }
                    }

                    if (!loggedIn) {
                        System.out.println("Invalid User ID or Password.");
                    }
                    break;

                case 3:
                    if (loggedInUserId != null) {
                        System.out.print("Enter deposit amount: ");
                        double balance = scanner.nextDouble();

                        int index = findUserIndex(users, userCount, loggedInUserId);
                        if (index != -1) {
                            wallets[index] = new Wallet(balance);
                            rewardPoints[index] = new RewardPoint();
                            System.out.println("Wallet created successfully!");
                        }
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 4:
                    if (loggedInUserId != null) {
                        System.out.print("Enter amount to add: ");
                        double amount = scanner.nextDouble();

                        int index = findUserIndex(users, userCount, loggedInUserId);
                        if (index != -1 && wallets[index] != null) {
                            wallets[index].addBalance(amount);
                        } else {
                            System.out.println("Wallet not found.");
                        }
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 5:
                    if (loggedInUserId != null) {
                        int index = findUserIndex(users, userCount, loggedInUserId);
                        if (index != -1 && wallets[index] != null) {
                            wallets[index].checkBalance();
                        } else {
                            System.out.println("Wallet not found.");
                        }
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 6:
                    if (loggedInUserId != null) {
                        System.out.print("Enter Transaction ID: ");
                        String transactionID = scanner.nextLine();
                        System.out.print("Enter Amount: ");
                        double transAmount = scanner.nextDouble();
                        System.out.print("Enter Date (YYYYMMDD): ");
                        int date = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Receiver ID: ");
                        String receiverID = scanner.nextLine();

                        int index = findUserIndex(users, userCount, loggedInUserId);
                        if (index != -1 && wallets[index] != null) {
                            if (wallets[index].getBalance() >= transAmount) {
                                TrackingTransaction trackingTransaction = new TrackingTransaction(transactionID, transAmount, date, receiverID);
                                trackingTransactions[trackingTransactionCount++] = trackingTransaction;
                                transactionAmounts[trackingTransactionCount - 1] = transAmount;
                                wallets[index].addTransaction(trackingTransaction);
                                rewardPoints[index].addPoint((int) transAmount / 10);
                                wallets[index].deductBalance(transAmount);
                                System.out.println("Balance after transaction: " + wallets[index].getBalance());
                            } else {
                                System.out.println("Insufficient balance for the transaction.");
                            }
                        }
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;


                case 7:
                    if (loggedInUserId != null) {
                        System.out.println("Transactions:");
                        for (int i = 0; i < trackingTransactionCount; i++) {
                            trackingTransactions[i].displayTransaction();
                        }

                        double totalTransactionAmount = 0;
                        for (int i = 0; i < trackingTransactionCount; i++) {
                            totalTransactionAmount += transactionAmounts[i];
                        }
                        System.out.println("Total transaction amount: " + totalTransactionAmount);
                    }

                    break;
                case 8:
                    System.out.print("Enter Transaction ID to search: ");
                    String searchTransactionID = scanner.nextLine();
                    TrackingTransaction.searchTransactionByID(trackingTransactions, trackingTransactionCount, searchTransactionID, users, userCount);
                    break;

                case 9:
                    if (loggedInUserId != null) {
                        System.out.print("Enter points to add: ");
                        int pointsToAdd = scanner.nextInt();

                        int index = findUserIndex(users, userCount, loggedInUserId);
                        if (index != -1 && rewardPoints[index] != null) {
                            rewardPoints[index].addPoint(pointsToAdd);
                        } else {
                            System.out.println("Reward points not found.");
                        }
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 10:
                    if (loggedInUserId != null) {
                        int index = findUserIndex(users, userCount, loggedInUserId);
                        if (index != -1 && rewardPoints[index] != null) {
                            rewardPoints[index].checkPointBalance();
                        } else {
                            System.out.println("Reward points not found.");
                        }
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 11:
                    if (loggedInUserId != null) {
                        System.out.print("Enter Payment ID: ");
                        String paymentID = scanner.nextLine();
                        System.out.print("Enter Amount: ");
                        double paymentAmount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter payment type (utilities/tax/loan): ");
                        String method = scanner.nextLine();

                        int index = findUserIndex(users, userCount, loggedInUserId);
                        if (index != -1 && wallets[index] != null) {
                            if (wallets[index].getBalance() >= paymentAmount) {
                                Payment payment = new Payment(paymentID, paymentAmount, method);
                                payments[paymentCount++] = payment;
                                wallets[index].deductBalance(paymentAmount);
                                System.out.println("Payment successful! Balance after payment: " + wallets[index].getBalance());
                            } else {
                                System.out.println("Insufficient balance for the payment.");
                            }
                        }
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 12:
                    System.out.print("Enter amount in USD: ");
                    double usdAmount = scanner.nextDouble();
                    System.out.println("Converted amounts:");
                    for (Currency currency : currencies) {
                        System.out.println(currency.getCurrencyName() + ": " + usdAmount * currency.getExchangeRate());
                    }
                    break;

                case 13:
                    loggedInUserId = null;
                    System.out.println("Logged out successfully.");
                    break;

                case 14:
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static int findUserIndex(User[] users, int userCount, String userID) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUserID().equals(userID)) {
                return i;
            }
        }
        return -1;

   }
}
