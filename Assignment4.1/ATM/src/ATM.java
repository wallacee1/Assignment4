import java.util.Scanner;

/**
 * This class is part of the "ATM" application.
 *
 * @author Eric Wallace
 * @version 1.0
 */
public class ATM {
    private Accounts accounts;

    public ATM() {
        accounts = new Accounts();
    }

    public void addAccount(Account account) {
        accounts.addAccount(account);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.print("Enter account number (or type 'exit' to quit): ");
            String accountNumber = scanner.nextLine();
            if (accountNumber.equalsIgnoreCase("exit")) {
                break;
            }

            Account account = accounts.getAccount(accountNumber);
            if (account == null) {
                System.out.println("Account not found.");
                continue;
            }

            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                account.setPendingDeposit(true);
                System.out.println("Deposit successful! Amount is pending bank approval.");
            } else if (option == 2) {
                System.out.print("Enter withdrawal amount (multiples of $20): ");
                double withdrawAmount = scanner.nextDouble();
                if (withdrawAmount % 20 != 0) {
                    System.out.println("Invalid withdrawal amount. Please enter a multiple of $20.");
                } else if (account.withdraw(withdrawAmount)) {
                    System.out.println("Withdrawal successful!");
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Invalid option.");
            }
            scanner.nextLine();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        ATM atm = new ATM();

        // Add sample accounts
        atm.addAccount(new Account("123456", 1000));
        atm.addAccount(new Account("234567", 2000));

        atm.start();
    }
}
