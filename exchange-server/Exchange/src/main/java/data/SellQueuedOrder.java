package data;

import backend.Publisher;
import data.OrderOuterClass.Order;

public class SellQueuedOrder extends QueuedOrder{

    public SellQueuedOrder(String user, String symbol, int quantity, double setValue, Publisher publisher) {
        super(user, symbol, quantity, setValue, publisher);
    }


    private void sendNotification(int sold, Order order){

        StringBuilder sb = new StringBuilder();
        sb.append(symbol).append("_User ").append(user).append(" sold ").append(sold).append(" of ").append(symbol).append(" to ").append(order.getUser());
        publisher.sendNotification(sb.toString());

        OrderOuterClass.Order o = OrderOuterClass.Order.newBuilder()
                .setConfirmation(false)
                .setType(false)
                .setQuantity(sold)
                .setSymbol(symbol)
                .setPrice((price + order.getPrice())/2)
                .setUser(user).build();
        Publisher.notifyUser(o);

        o.toBuilder()
                .setType(true)
                .setUser(order.getUser()).build();
        Publisher.notifyUser(o);

    }

    @Override
    public int match(Order order, PriceStats priceStats) {

        int quantity_sold;

        // Verifica se o preço de venda é maior que de compra. Se for, não se efetua.
        if (order.getPrice() < this.price || this.quantity == 0)
            return 0;

        if (order.getQuantity() > this.quantity){
            quantity_sold = this.quantity;
            this.quantity = 0;
        } else {
            quantity_sold = order.getQuantity();
            this.quantity -= quantity_sold;
        }

        sendNotification(quantity_sold, order);
        priceStats.checkValue((price + order.getPrice())/2);

        return quantity_sold;
    }
}
