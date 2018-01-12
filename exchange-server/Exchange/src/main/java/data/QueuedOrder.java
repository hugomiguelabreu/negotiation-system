package data;

import backend.Publisher;

import static data.OrderOuterClass.Order.Type.*;

public abstract class QueuedOrder {

    protected String user;
    protected String symbol;
    protected int quantity;
    protected double setValue;
    protected Publisher publisher;


    public QueuedOrder(String user, String symbol, int quantity, double setValue, Publisher publisher) {
        this.user = user;
        this.symbol = symbol;
        this.quantity = quantity;
        this.setValue = setValue;
        this.publisher = publisher;
    }

    public static QueuedOrder create(OrderOuterClass.Order o, int quantity, Publisher publisher){
        if (o.getOrderType() == BUY)
            return new BuyQueuedOrder(o.getUser(),o.getSymbol(),quantity,o.getSetPrice(), publisher);
        else
            return new SellQueuedOrder(o.getUser(),o.getSymbol(),quantity,o.getSetPrice(), publisher);
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

    public abstract int match(OrderOuterClass.Order order);
}


