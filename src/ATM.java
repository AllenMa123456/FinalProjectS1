import java.util.Scanner;
import java.lang.Thread;
public class ATM {
    private Scanner scan;
    private Account saveAccount;
    private Account checkAccount;
    private TransactionHistory transactionHistory;
    private Customer user;
    private boolean access;
    private boolean quit = false;



    public ATM() {
        this.scan = new Scanner(System.in);
    }
    public void start() {
        welcoming();
    }

    public void welcoming() {
        System.out.print("Welcome to the ATM.\nIt seems that you don't have an account yet. Would you like to create one? (y/n) : ");
        String makeAccount = scan.nextLine();
        if(makeAccount.equals("y")) {
            creatingAccount();
            enterPin();

        }else if(makeAccount.equals("n")) {
            System.out.println("Goodbye.");
        } else {
            System.out.println("Please enter y or n");
            try {
                Thread.sleep(5000);
                for (int i = 0; i < 50; i++) {
                    System.out.println();
                }
            } catch (InterruptedException e) {
                System.out.println("Oops! Looks like something went wrong!");
            }
            this.welcoming();
        }
    }

    private void creatingAccount() {
        System.out.println("Please enter the following information");
        System.out.print("What is your name? : ");
        String name = scan.nextLine();
        System.out.print("What do you want your pin to be?(4 integers and no spaces) : ");
        String pin = scan.nextLine();
        while(!checkPinContents(pin)){
            System.out.print("Something went wrong! Try again. (Please only enter 4 integers and no spaces): ");
            pin = scan.nextLine();
        }
        user = new Customer(pin, name);
        saveAccount = new Account(user);
        checkAccount = new Account(user);
        transactionHistory = new TransactionHistory();
        System.out.println("Your CHECKING and SAVING accounts have successfully been made.");
    }
    private void changePin(){
        System.out.print("Enter a new Pin: ");
        String pin = scan.nextLine();
        while (user.getPin().equals(pin)){
            System.out.print("Enter a different Pin: ");
            pin = scan.nextLine();
        }
        user.setPin(pin);
        System.out.println("Changed Pin");
        String transaction = "Changed Pin" + "\nTransaction ID: S" + transactionHistory.getTransactionIDB();
        transactionHistory.increaseTransactionIDB();
        transactionHistory.history.add(transaction);
        System.out.println();
    }

    private void enterPin() {
        System.out.print("Please enter your pin to access your account (No spaces please): ");
        String pin = scan.nextLine();
        while(!user.getPin().equals(pin)){
            System.out.println("Invalid Pin");
            System.out.print("Please enter your pin to access your account (No spaces please): ");
            pin = scan.nextLine();
        }
        System.out.println("Access granted.");
        access = true;
        mainMenu();
    }
    private boolean checkPinContents(String thePin) {
        if (thePin.length() != 4 || thePin.contains("-")) {
            return false;
        }
        try {
            int contentChecker = Integer.parseInt(thePin); // Never used
        }catch (Exception e) {
            return false;
        }
        return true;
    }
    private boolean quitFunction(){
        access = false;
        System.out.print("Would you like to do anything else? (y/n): ");
        String ans = scan.nextLine();
        if (ans.equals("y")){
            enterPin();
        }
        else if (ans.equals("n")) {
            System.out.println("Thank you for being a customer " + user.getName() + "!");
            System.out.println();
            System.out.println("Goodbye!");
            access = false;
            quit = true;
            return true;
        }
        else {
            System.out.println("Please enter y or n");
            try {
                Thread.sleep(5000);
                for (int i = 0; i < 50; i++) {
                    System.out.println();
                }
            } catch (InterruptedException e) {
                System.out.println("Oops! Looks like something went wrong!");
            }
        }
        return false;
    }
    private void mainMenu() {
        int option;
        //Options need to account for negative numbers and numbers that are not options
        while (access && !quit) {
            System.out.println();
            System.out.println("Please select an option.");
            System.out.println("1. Withdraw money");
            System.out.println("2. Deposit money");
            System.out.println("3. Transfer Money between Accounts");
            System.out.println("4. Get Account Balances");
            System.out.println("5. Get Transaction History");
            System.out.println("6. Change PIN");
            System.out.println("7. Exit");
            System.out.println();
            System.out.print("Option: ");
            option = Integer.parseInt(scan.nextLine());
            if (option == 1) {
                System.out.println();
                System.out.println("Which account would you like to withdraw money from? (Enter the number)");
                System.out.println("1. Savings Account");
                System.out.println("2. Checking Account");
                System.out.println();
                System.out.print("Option: ");
                int option2 = Integer.parseInt(scan.nextLine());
                while (option2 != 1 && option2 != 2) {
                    System.out.println("Please choose a valid option");
                    System.out.println("Which account would you like to withdraw money from? (Enter the number)");
                    System.out.println("1. Savings Account");
                    System.out.println("2. Checking Account");
                    System.out.println();
                    System.out.print("Option: ");
                    option2 = Integer.parseInt(scan.nextLine());
                }
                if (option2 == 1) {
                    System.out.print("How much money would you like to withdraw from your Savings Account? (Multiple of 5): $");
                    double money = Double.parseDouble(scan.nextLine());
                    while ((money > saveAccount.getMoney() || money % 5 != 0) || (money < 0)) {
                        String transaction = "Money withdrawal of $" + money + " from Savings Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + saveAccount.getMoney() + "\nStatus: Unsuccessful";
                        System.out.println(transaction);
                        transactionHistory.increaseTransactionIDA();
                        transactionHistory.history.add(transaction);
                        System.out.println();
                        System.out.println("Reason: Invalid amount! / Insufficient Funds!");
                        System.out.print("How much money would you like to withdraw from your Savings Account? (Multiple of 5): $");
                        money = Integer.parseInt(scan.nextLine());
                    }
                    System.out.println("Please choose the type of bills you want and the amount.");
                    System.out.print("$20 Bills: ");
                    int bills20 = Integer.parseInt(scan.nextLine());
                    System.out.print("$5 Bills: ");
                    int bills5 = Integer.parseInt(scan.nextLine());
                    while (bills20 < 0 || bills5 < 0){
                        String transaction = "Money withdrawal of $" + money + " from Savings Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + saveAccount.getMoney() + "\nStatus: Unsuccessful";
                        System.out.println(transaction);
                        transactionHistory.increaseTransactionIDA();
                        transactionHistory.history.add(transaction);
                        System.out.println();
                        System.out.println("Reason: You can't withdraw a negative number of bills!");
                        System.out.println("Please choose the type of bills you want and the amount.");
                        System.out.print("$20 Bills: ");
                        bills20 = Integer.parseInt(scan.nextLine());
                        System.out.print("$5 Bills: ");
                        bills5 = Integer.parseInt(scan.nextLine());
                    }
                    System.out.println();
                    while (((bills20 * 20) + (bills5 * 5)) > saveAccount.getMoney() || ((bills20 * 20) + (bills5 * 5)) != money) {
                        String transaction = "Money withdrawal of $" + money + " from Savings Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + saveAccount.getMoney() + "\nStatus: Unsuccessful";
                        System.out.println(transaction);
                        transactionHistory.increaseTransactionIDA();
                        transactionHistory.history.add(transaction);
                        System.out.println();
                        System.out.println("Reason: Insufficient Funds! / Invalid amount of bills");
                        System.out.println("Please choose the type of bills you want and the amount.");
                        System.out.print("$20 Bills: ");
                        bills20 = Integer.parseInt(scan.nextLine());
                        System.out.print("$5 Bills: ");
                        bills5 = Integer.parseInt(scan.nextLine());
                    }
                    saveAccount.withdrawMoney(money);
                    String transaction = "Withdrew $" + money + " from Savings Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + saveAccount.getMoney() + "\nStatus: Successful";
                    System.out.println(transaction);
                    transactionHistory.increaseTransactionIDA();
                    transactionHistory.history.add(transaction);
                    System.out.println();
                    quitFunction();
                    if (!quit) {
                        enterPin();
                    }
                }
                if (option2 == 2) {
                    System.out.print("How much money would you like to withdraw from your Checking Account? (Multiple of 5): $");
                    double money = Double.parseDouble(scan.nextLine());
                    while ((money > checkAccount.getMoney() || money % 5 != 0) || (money < 0)) {
                        String transaction = "Money withdrawal of $" + money + " from Checking Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + checkAccount.getMoney() + "\nStatus: Unsuccessful";
                        System.out.println(transaction);
                        transactionHistory.increaseTransactionIDA();
                        transactionHistory.history.add(transaction);
                        System.out.println();
                        System.out.println("Reason: Invalid amount! / Insufficient Funds!");
                        System.out.print("How much money would you like to withdraw from your Savings Account? (Multiple of 5): $");
                        money = Integer.parseInt(scan.nextLine());
                    }
                    System.out.println("Please choose the type of bills you want and the amount.");
                    System.out.print("$20 Bills: ");
                    int bills20 = Integer.parseInt(scan.nextLine());
                    System.out.print("$5 Bills: ");
                    int bills5 = Integer.parseInt(scan.nextLine());
                    while (bills20 < 0 || bills5 < 0){
                        String transaction = "Money withdrawal of $" + money + " from Checking Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + checkAccount.getMoney() + "\nStatus: Unsuccessful";
                        System.out.println(transaction);
                        transactionHistory.increaseTransactionIDA();
                        transactionHistory.history.add(transaction);
                        System.out.println();
                        System.out.println("Reason: You can't withdraw a negative number of bills!");
                        System.out.println("Please choose the type of bills you want and the amount.");
                        System.out.print("$20 Bills: ");
                        bills20 = Integer.parseInt(scan.nextLine());
                        System.out.print("$5 Bills: ");
                        bills5 = Integer.parseInt(scan.nextLine());
                    }
                    System.out.println();
                    while (((bills20 * 20) + (bills5 * 5)) > checkAccount.getMoney() || ((bills20 * 20) + (bills5 * 5)) != money) {
                        String transaction = "Money withdrawal of $" + money + " from Checking Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + checkAccount.getMoney() + "\nStatus: Unsuccessful";
                        System.out.println(transaction);
                        transactionHistory.increaseTransactionIDA();
                        transactionHistory.history.add(transaction);
                        System.out.println();
                        System.out.println("Reason: Insufficient Funds! / Invalid amount of bills");
                        System.out.println("Please choose the type of bills you want and the amount.");
                        System.out.print("$20 Bills: ");
                        bills20 = Integer.parseInt(scan.nextLine());
                        System.out.print("$5 Bills: ");
                        bills5 = Integer.parseInt(scan.nextLine());
                    }
                    checkAccount.withdrawMoney(money);
                    String transaction = "Withdrew $" + money + " from Checking Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + checkAccount.getMoney() + "\nStatus: Successful";
                    System.out.println(transaction);
                    transactionHistory.increaseTransactionIDA();
                    transactionHistory.history.add(transaction);
                    System.out.println();
                    quitFunction();
                    if (!quit) {
                        enterPin();
                    }
                }
            } else if (option == 2) {
                System.out.println();
                System.out.println("Which account would you like to deposit money into? (Enter the number)");
                System.out.println("1. Savings Account");
                System.out.println("2. Checking Account");
                System.out.print("Option: ");
                int option2 = Integer.parseInt(scan.nextLine());
                while (option2 != 1 && option2 != 2) {
                    System.out.println("Please choose a valid option");
                    System.out.println("Which account would you like to deposit money into? Enter the number)");
                    System.out.println("1. Savings Account");
                    System.out.println("2. Checking Account");
                    System.out.print("Option: ");
                    option2 = Integer.parseInt(scan.nextLine());
                }
                if (option2 == 1) {
                    System.out.print("How much money would you like to deposit into your Savings Account?: $");
                    double money = Double.parseDouble(scan.nextLine());
                    while (money < 0){
                        String transaction = "Deposit of $" + money + " into Savings Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + saveAccount.getMoney() + "\nStatus: Unsuccessful";
                        System.out.println(transaction);
                        transactionHistory.increaseTransactionIDA();
                        transactionHistory.history.add(transaction);
                        System.out.println();
                        System.out.println("Reason: You can't deposit a negative amount of money");
                        System.out.print("How much money would you like to deposit into your Savings Account?: $");
                        money = Integer.parseInt(scan.nextLine());
                    }
                    saveAccount.depositMoney(money);
                    String transaction = "Deposited $" + money + " into Savings Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + saveAccount.getMoney() + "\nStatus: Successful";
                    System.out.println(transaction);
                    transactionHistory.increaseTransactionIDA();
                    transactionHistory.history.add(transaction);
                    System.out.println();
                    quitFunction();
                    if (!quit) {
                        enterPin();
                    }
                } else {
                    System.out.print("How much money would you like to deposit into your Checking Account?: $");
                    double money = Double.parseDouble(scan.nextLine());
                    while (money < 0){
                        String transaction = "Deposit of $" + money + " into Checking Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + checkAccount.getMoney() + "\nStatus: Unsuccessful";
                        System.out.println(transaction);
                        transactionHistory.increaseTransactionIDA();
                        transactionHistory.history.add(transaction);
                        System.out.println();
                        System.out.println("Reason: You can't deposit a negative amount of money");
                        System.out.print("How much money would you like to deposit into your Checking Account?: $");
                        money = Integer.parseInt(scan.nextLine());
                    }
                    checkAccount.depositMoney(money);
                    String transaction = "Deposited $" + money + " into Checking Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nAccount Balance: $" + checkAccount.getMoney() + "\nStatus: Successful";
                    System.out.println(transaction);
                    transactionHistory.increaseTransactionIDA();
                    transactionHistory.history.add(transaction);
                    System.out.println();
                    quitFunction();
                    if (!quit) {
                        enterPin();
                    }
                }
            } else if (option == 3) { //Transferring money function
                System.out.println("Please select the action you would like to perform.");
                System.out.println("1. Transfer money FROM savings account TO checking account");
                System.out.println("2. Transfer money FROM checking account TO savings account");
                System.out.print("Please select an option to continue (1 or 2): ");
                int transferOption = Integer.parseInt(scan.nextLine());
                System.out.print("Please enter the amount of money you would like to transfer: ");
                double money1 = Double.parseDouble(scan.nextLine());
                if (transferOption == 1) {
                    while (money1 > saveAccount.getMoney() || money1 < 0) {
                        String transaction = "Transfer of $" + money1 + " FROM Savings Account TO Checking Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nSavings Account Balance: $" + saveAccount.getMoney() + "\nChecking Account Balance: $" + checkAccount.getMoney() + "\nStatus: Unsuccessful";
                        System.out.println(transaction);
                        transactionHistory.increaseTransactionIDA();
                        transactionHistory.history.add(transaction);
                        System.out.println();
                        System.out.println("Reason: You don't have enough funds to transfer the specified amount / Enter a valid amount");
                        System.out.print("Please enter a valid amount of funds to transfer from your savings account: ");
                        money1 = Integer.parseInt(scan.nextLine());
                    }
                    saveAccount.withdrawMoney(money1);
                    checkAccount.depositMoney(money1);
                    System.out.println();
                    String transaction = "Transferred $" + money1 + " FROM Savings Account TO Checking Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nSavings Account Balance: $" + saveAccount.getMoney() + "\nChecking Account Balance: $" + checkAccount.getMoney() + "\nStatus: Successful";
                    System.out.println(transaction);
                    transactionHistory.increaseTransactionIDA();
                    transactionHistory.history.add(transaction);
                    System.out.println();
                    quitFunction();
                    if (!quit) {
                        enterPin();
                    }
                }
                if (transferOption == 2) {
                    while (money1 > checkAccount.getMoney() || money1 < 0) {
                        String transaction = "Transfer of $" + money1 + " FROM Checking Account TO Savings Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nSavings Account Balance: $" + saveAccount.getMoney() + "\nChecking Account Balance: $" + checkAccount.getMoney() + "\nStatus: Unsuccessful";
                        System.out.println(transaction);
                        transactionHistory.increaseTransactionIDA();
                        transactionHistory.history.add(transaction);
                        System.out.println();
                        System.out.println("You don't have enough funds to transfer the specified amount / Enter a valid amount");
                        System.out.print("Please enter a valid amount of funds to transfer from your checking account: ");
                        money1 = Integer.parseInt(scan.nextLine());
                    }
                    checkAccount.withdrawMoney(money1);
                    saveAccount.depositMoney(money1);
                    System.out.println();
                    String transaction = "Transferred $" + money1 + " FROM Checking Account TO Savings Account\n" + "Transaction ID: A" + transactionHistory.getTransactionIDA() + "\nSavings Account Balance: $" + saveAccount.getMoney() + "\nChecking Account Balance: $" + checkAccount.getMoney() + "\nStatus: Successful";
                    System.out.println(transaction);
                    transactionHistory.increaseTransactionIDA();
                    transactionHistory.history.add(transaction);
                    System.out.println();
                    quitFunction();
                    if (!quit) {
                        enterPin();
                    }
                }
            } else if (option == 4) {
                System.out.println();
                System.out.println("Savings Account: $" + saveAccount.getMoney());
                System.out.println("Checking Account: $" + checkAccount.getMoney());
                String transaction = "Checked Account Balances" + "Transaction ID: S" + transactionHistory.getTransactionIDB() + "\nSavings Account: $" + saveAccount.getMoney() + "\nChecking Account: " + checkAccount.getMoney();
                System.out.println("Checked Account Balances: Successful" + "\nTransaction ID: S" + transactionHistory.getTransactionIDB());
                transactionHistory.increaseTransactionIDB();
                transactionHistory.history.add(transaction);
                System.out.println();
                quitFunction();
                if (!quit) {
                    enterPin();
                }
            }
            else if (option == 5) {
                System.out.println();
                System.out.println("----------Transaction History ---------------");
                System.out.println();
                String transaction = "Checked Transaction History" + "\nTransaction ID: S" + transactionHistory.getTransactionIDB();
                transactionHistory.increaseTransactionIDB();
                transactionHistory.history.add(transaction);
                transactionHistory.printTransactionHistory();
                System.out.println("---------------------------------------------");
                quitFunction();
                if (!quit) {
                    enterPin();
                };
            }
            else if (option == 6) {
                changePin();
                quitFunction();
                if (!quit) {
                    enterPin();
                }
            }
            else if (option == 7){
                quit = true;
            }
        }
    }
}