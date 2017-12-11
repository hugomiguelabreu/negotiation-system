package data;

public class InfoOrder{

    private String user;
    private String symbol;
    private int quantity;
    private double setValue;

    public InfoOrder(String user, String symbol, int quantity, double setValue) {
        this.user = user;
        this.symbol = symbol;
        this.quantity = quantity;
        this.setValue = setValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void takeQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public String getUser() {
        return user;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getSetValue() {
        return setValue;
    }
}
