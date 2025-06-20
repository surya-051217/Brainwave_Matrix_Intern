import java.util.*;

class ATM {
    private double balance;
    private final String cardNumber;
    private final String pin;

    public ATM(String cardNumber, String pin, double initialBalance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public boolean authenticate(String inputCard, String inputPin) {
        return cardNumber.equals(inputCard) && pin.equals(inputPin);
    }

    public double checkBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }
}

public class ATMInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, ATM> accounts = new HashMap<>();

    public static void main(String[] args) {
        // Preload one account
        accounts.put("1234567890", new ATM("1234567890", "4321", 5000));

        System.out.println("💳 Welcome to Custom ATM");
        System.out.print("Enter Card Number: ");
        String enteredCard = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        ATM userATM = accounts.get(enteredCard);

        if (userATM == null || !userATM.authenticate(enteredCard, enteredPin)) {
            System.out.println("❌ Invalid card or PIN. Exiting...");
            return;
        }

        int option;
        do {
            System.out.println("\n🔘 Select Option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Cash");
            System.out.println("3. Withdraw Cash");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("✅ Current Balance: ₹" + userATM.checkBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmt = scanner.nextDouble();
                    if (userATM.deposit(depositAmt))
                        System.out.println("✅ Amount deposited successfully.");
                    else
                        System.out.println("❌ Invalid deposit amount.");
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmt = scanner.nextDouble();
                    if (userATM.withdraw(withdrawAmt))
                        System.out.println("✅ Please collect your cash.");
                    else
                        System.out.println("❌ Insufficient balance or invalid amount.");
                    break;

                case 4:
                    System.out.println("👋 Thank you for using Custom ATM. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }

        } while (option != 4);
    }
}
