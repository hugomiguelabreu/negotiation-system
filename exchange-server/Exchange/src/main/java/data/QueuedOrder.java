package data;

import backend.Publisher;

public abstract class QueuedOrder {

    protected String user;
    protected String symbol;
    protected int quantity;
    protected double price;
    protected Publisher publisher;


    public QueuedOrder(String user, String symbol, int quantity, double price, Publisher publisher) {
        this.user = user;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.publisher = publisher;
    }

    public static QueuedOrder create(OrderOuterClass.Order o, int quantity, Publisher publisher){
        if (o.getOrderType())
            return new BuyQueuedOrder(o.getUser(),o.getSymbol(),quantity,o.getPrice(), publisher);
        else
            return new SellQueuedOrder(o.getUser(),o.getSymbol(),quantity,o.getPrice(), publisher);
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

    public double getPrice() {
        return price;
    }

    public abstract int match(OrderOuterClass.Order order);
}


