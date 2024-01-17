public class Account {
    private double money;
    private Customer user;

    public Account (Customer user){
        this.money = 0.0;
        this.user = user;
    }
    public double getMoney(){
        return this.money;
    }
    public void setMoney(double amount){
        this.money = amount;
    }

    public void depositMoney(double amount){
        this.setMoney(this.getMoney() + amount);
    }
    public void withdrawMoney(double amount){
        this.setMoney(this.getMoney() - amount);
    }


}