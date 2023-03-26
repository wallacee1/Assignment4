import java.util.HashMap;
import java.util.Map;

/**
 * This class is part of the "ATM" application.
 *
 * @author Eric Wallace
 * @version 1.0
 */
public class Accounts {
    private Map<String, Account> accounts;

    public Accounts() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}
