package BankingApplication3;

public class CurrentAccount implements Account {
    private int accountNumber;
    private String accountName;
    private double balance;
    private double minimum;
    private final String accountType = "CurrentAccount";

    public CurrentAccount(int accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.minimum = 0;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountName() {
        return this.accountName;
    }

    @Override
    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }

    @Override
    public void withdraw(double amount) {
        this.balance = this.balance - amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getMinimum() {
        return this.minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public String getAccountType() {
        return this.accountType;
    }
}
