
/**
 * This class is part of the "ATM" application.
 *
 * @author Eric Wallace
 * @version 1.0
 */
public class Account {
    private String accountNumber;
    private double balance;
    private boolean isPendingDeposit;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.isPendingDeposit = false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean hasPendingDeposit() {
        return isPendingDeposit;
    }

    public void setPendingDeposit(boolean pending) {
        this.isPendingDeposit = pending;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
