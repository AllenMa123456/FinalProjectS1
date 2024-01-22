import java.util.ArrayList;

public class TransactionHistory {
    private String transactionIDA;
    private String transactionIDB;
    public ArrayList<String> history = new ArrayList<String>();


    public TransactionHistory() {
        this.transactionIDA = "0000";
        this.transactionIDB = "0000";
    }

    public String getTransactionIDA(){
        return transactionIDA;
    }
    public String getTransactionIDB(){
        return transactionIDB;
    }
    public void increaseTransactionIDA(){
        int i = Integer.parseInt(transactionIDA);
        i += 1;
        String s = String.valueOf(i);
        if (i < 10) {
            transactionIDA = "000" + s;
        } else if (i < 100) {
            transactionIDA = "00" + s;
        } else if (i < 1000) {
            transactionIDA = "0" + s;
        } else {
            transactionIDA = s;
        }
    }
    public void increaseTransactionIDB(){
        int i = Integer.parseInt(transactionIDB);
        i += 1;
        String s = String.valueOf(i);
        if (i < 10) {
            transactionIDB = "000" + s;
        } else if (i < 100) {
            transactionIDB = "00" + s;
        } else if (i < 1000) {
            transactionIDB = "0" + s;
        } else {
            transactionIDB = s;
        }
    }
    public void printTransactionHistory(){
        for (int i = 0; i < history.size(); i++){
            System.out.println(history.get(i));
            System.out.println();
        }
    }



}