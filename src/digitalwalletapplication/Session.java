//SECTION 03B
//MUHAMMAD AMMAR BIN AZIZAN CB23037
//MUHAMMAD ALIF AIMAN BIN AZHAR CB23120
//MUHAMMAD HIZBU FARHAN BIN ALIAS CB23022

package digitalwalletapplication;

/**
 * Simple session holder for the GUI version of the application.
 * Stores the currently logged-in user's ID so that other GUI forms
 * (WalletGUI, PaymentForm, TrackTransGUI, RewardGUI, CurrencyForm,
 * TrannsactionGUI) can identify which user is logged in.
 */
public class Session {
    private static String loggedInUserId = null;

    private Session() {
        // Prevent instantiation - this is a utility/holder class
    }

    public static void setLoggedInUserId(String userId) {
        loggedInUserId = userId;
    }

    public static String getLoggedInUserId() {
        return loggedInUserId;
    }

    public static boolean isLoggedIn() {
        return loggedInUserId != null;
    }

    public static void clearSession() {
        loggedInUserId = null;
    }
}
