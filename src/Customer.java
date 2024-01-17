import java.sql.SQLOutput;

public class Customer {
    private String pin;
    private String name;


    public Customer(String pin, String name){
        this.pin = pin;
        this.name = name;


    }
    public String getPin(){
        return pin;
    }
    public void setPin(String changedPin) {
        pin = changedPin;
    }


}