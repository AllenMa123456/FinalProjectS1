import java.util.Scanner;
import java.lang.Thread;
public class ATM {
    private Scanner scan;
    private Account saveAccount;
    private Account checkAccount;
    private Customer user;
    private boolean access;



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
        while (access) {

        }
    }


}