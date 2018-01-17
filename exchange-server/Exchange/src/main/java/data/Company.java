package data;

import backend.Publisher;
import data.OrderOuterClass.Order;
import rest.RESTClient;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Company {

    private PriceStats priceStats;
    private Publisher publisher;
    private Queue<QueuedOrder> buyQueue;
    private Queue<QueuedOrder> sellQueue;

    public Company(Publisher publisher, RESTClient rest) {
        this.publisher = publisher;
        this.buyQueue = new LinkedList<>();
        this.sellQueue = new LinkedList<>();
        this.priceStats = new PriceStats(rest);
    }

    public void getMatch(Order o) {

        Queue<QueuedOrder> orders;

        if (o.getType())
            orders = sellQueue;
        else
            orders = buyQueue;

        int new_qty = o.getQuantity();

        synchronized (orders) {


            while (new_qty > 0) {
                QueuedOrder qo = orders.peek();

                if (qo == null)
                    break;

                new_qty -= qo.match(o, priceStats);

                if (new_qty > 0)
                    orders.remove();
            }

            if (new_qty > 0) {
                System.out.println("Not enough quantity sold - Adding to Queue. Quantity: " + new_qty);

                if (o.getType())
                    buyQueue.add(QueuedOrder.create(o, new_qty, publisher));
                else
                    sellQueue.add(QueuedOrder.create(o, new_qty, publisher));
            }
        }
    }

}