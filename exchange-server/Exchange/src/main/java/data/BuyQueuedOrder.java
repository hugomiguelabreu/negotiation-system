package data;

import backend.Publisher;
import data.OrderOuterClass.Order;

import static data.OrderOuterClass.Order.Type.*;

public class BuyQueuedOrder extends QueuedOrder {

    public BuyQueuedOrder(String user, String symbol, int quantity, double setValue, Publisher publisher) {
        super(user, symbol, quantity, setValue, publisher);
    }

    private void sendNotification(int sold, OrderOuterClass.Order order){
        StringBuilder sb = new StringBuilder();
        sb.append("User ").append(user).append(" sold ").append(sold).append(" of ").append(symbol).append(".");
        publisher.sendNotification(sb.toString());

        Order o = Order.newBuilder()
                .setOrderType(BUY)
                .setQuantity(sold)
                .setSymbol(symbol)
                .setSetPrice((price + order.getSetPrice())/2)
                .setUser(user).build();
        Publisher.notifyUser(o);

        o.toBuilder()
                .setOrderType(SELL)
                .setUser(o.getUser()).build();
        Publisher.notifyUser(o);
    }


    @Override
    public int match(OrderOuterClass.Order order) {

        int quantity_sold;

        // Verifica se o preço de venda é maior que de compra. Se for, não se efetua.
        if (order.getSetPrice() > this.price || this.quantity == 0)
            return 0;

        if (order.getQuantity() > this.quantity){
            quantity_sold = this.quantity;
            this.quantity = 0;
        } else {
            quantity_sold = order.getQuantity();
            this.quantity -= quantity_sold;
        }

        sendNotification(quantity_sold, order);

        return quantity_sold;
    }
}
