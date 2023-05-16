package BankingApplication3;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bank bank = new Bank("JOKO");
        int option = 0 , accNumber;
        Account account = null;
        String accName , accountType;
        Double balance , amount;
        while (option !=6){
                try{
                    if (option >= 7){
                        throw new Exception("Something went wrong");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            System.out.println("Main Menu");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Open New Account");
            System.out.println("3. ClosExisting Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            option = scan.nextInt();
            scan.nextLine();
            System.out.println();

            switch (option){
                case 1:
                    bank.listAccount();
                    break;
                case 2:
                    accNumber = generateAccountNumber();
                    System.out.print("Enter Account Name: ");
                    accName = scan.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    balance = scan.nextDouble();
                    scan.nextLine();
                    System.out.print("Enter Accont (s -> Saving Account or c -> Current Account): ");
                    accountType = scan.nextLine();
                    if (accountType.toLowerCase().equals("s")){
                        account = new SavingAccount(accNumber,accName,balance);
                    } else if (accountType.toLowerCase().equals("c")) {
                        account = new CurrentAccount(accNumber,accName,balance);
                    }
                    bank.openAccount(account);
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accNumber = scan.nextInt();
                    System.out.print("Enter Accont (s -> Saving Account or c -> Current Account): ");
                    accountType = scan.nextLine();
                    if (accountType.toLowerCase().equals("s")){
                        account = bank.getAccount(accNumber , "Saving Account");
                    } else if (accountType.toLowerCase().equals("c")) {
                        account = bank.getAccount(accNumber , "Current Account");
                    }
                    bank.closeAccount(account);
                    System.out.println("Account is Deleted");
                    System.out.println();
                    break;
                case 4 :
                    System.out.print("Enter Account Number: ");
                    accNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Accont (s -> Saving Account or c -> Current Account): ");
                    accountType = scan.nextLine();
                    if (accountType.toLowerCase().equals("s")){
                        account = bank.getAccount(accNumber , "SavingAccount");
                    } else if (accountType.toLowerCase().equals("c")) {
                        account = bank.getAccount(accNumber , "CurrentAccount");
                    }
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
                    bank.depositMoney(account , amount);
                    break;
                case 5 :
                    System.out.print("Enter Account Number: ");
                    accNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Accont (s -> Saving Account or c -> Current Account): ");
                    accountType = scan.nextLine();
                    if (accountType.toLowerCase().equals("s")){
                        account = bank.getAccount(accNumber , "SavingAccount");
                    } else if (accountType.toLowerCase().equals("c")) {
                        account = bank.getAccount(accNumber , "CurrentAccount");
                    }
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
                    bank.withdrawMoney(account , amount);
                    break;
            }
            System.out.println();
        }
    }
    public static int generateAccountNumber(){
        Random random = new Random();
        int accNumber = 100000 + random.nextInt(900000);
        return accNumber;
    }
}