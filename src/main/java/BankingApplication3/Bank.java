package BankingApplication3;

import java.sql.*;

public class Bank {
    private String bankName;

    public Bank(){

    }
    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void listAccount(){
        Connection con = BankConnection.connect();
        try {
            Statement statement = con.createStatement();
            String sql = "Select * from account";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2)
                        + " " + resultSet.getString(3) + " " + resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void openAccount(Account account){
        Connection con = BankConnection.connect();
        String sql = "insert into account(accID,accName,accBalance,accType)" +
                "values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getAccountName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4, account.getAccountType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeAccount(Account account){
        Connection con = BankConnection.connect();
        String sql = "delete from account where accID = ? and accType = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,account.getAccountNumber());
            preparedStatement.setString(2,account.getAccountType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void depositMoney(Account account , Double amount){
        account.deposit(amount);
        Connection con = BankConnection.connect();
        String sql = "update account set accBalance = ? where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void withdrawMoney(Account account , Double amount){
        account.withdraw(amount);
        Connection con = BankConnection.connect();
        String sql = "update account set accBalance = ? where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Account getAccount(int accNumber , String accountType){
        Connection con = BankConnection.connect();
        String sql = "Select * from account where accID = ? and accType = ?";
        Account account = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, accNumber);
            preparedStatement.setString(2, accountType);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            String accountName = resultSet.getString(2);
            Double balance = resultSet.getDouble(3);
            String accounttype = resultSet.getString(4);
            if(accounttype.equals("SavingAccount")){
                account = new SavingAccount(accNumber,accountName,balance);
            } else if (accounttype.equals("CurrentAccount")) {
                account = new CurrentAccount(accNumber,accountName,balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }
}
