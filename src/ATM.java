import java.util.Scanner;
import java.lang.Thread;
public class ATM {
    private Scanner scan;
    private Account saveAccount;
    private Account checkAccount;
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
            System.out.println("Something went wrong! Try again. (Please only enter 4 integers and no spaces) ");
            pin = scan.nextLine();
        }
        user = new Customer(pin, name);
        saveAccount = new Account(user);
        checkAccount = new Account(user);
        System.out.println("Your CHECKING and SAVING accounts have successfully been made.");
    }




    private void enterPin() {
        System.out.print("Please enter a pin to access your account.(No spaces please): ");
        String pin = scan.nextLine();
        while(!user.getPin().equals(pin)){
            System.out.println("Invalid Pin");
            pin = scan.nextLine();
        }
        System.out.println("Access granted.");
        access = true;
        mainMenu();

    }
    private boolean checkPinContents(String thePin) {
        if(thePin.length() != 4) {
            return false;
        }
        try {
            int contentChecker = Integer.parseInt(thePin);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    private void mainMenu() {
        int option;
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
            option = scan.nextInt();
            if (option == 1) {
                // Still need to implement $5 and $20 function
                System.out.println();
                System.out.println("Which account would you like to withdraw money from? (Enter the number)");
                System.out.println("1. Savings Account");
                System.out.println("2. Checking Account");
                int option2 = scan.nextInt();
                if (option2 == 1) {
                    System.out.print("How much money would you like to withdraw from your Savings Account?: $");
                    int money = scan.nextInt();
                    saveAccount.withdrawMoney(money);
                } else {
                    System.out.print("How much money would you like to withdraw from your Checking Account?: $");
                    int money = scan.nextInt();
                    checkAccount.withdrawMoney(money);
                }
            } else if (option == 2) {
                System.out.println();
                System.out.println("Which account would you like to deposit money into? (Enter the number)");
                System.out.println("1. Savings Account");
                System.out.println("2. Checking Account");
                int option2 = scan.nextInt();
                if (option2 == 1) {
                    System.out.print("How much money would you like to deposit into your Savings Account?: $");
                    int money = scan.nextInt();
                    saveAccount.depositMoney(money);
                } else {
                    System.out.print("How much money would you like to deposit into your Checking Account?: $");
                    int money = scan.nextInt();
                    checkAccount.depositMoney(money);
                }
            } else if (option == 3) {

            } else if (option == 4) {
                System.out.println();
                System.out.println("Savings Account: " + saveAccount.getMoney());
                System.out.println("Checking Account: " + checkAccount.getMoney());
            }
        }
    }


}