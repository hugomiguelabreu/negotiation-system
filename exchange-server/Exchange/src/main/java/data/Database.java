package data;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import backend.Publisher;
import data.OrderOuterClass.Order;

import static data.OrderOuterClass.Order.Type.BUY;

public class Database {

    private Publisher publisher; // where to publish when a deal is done
    private Map<String, List<QueuedOrder>> buyOrders;  // contains buying orders waiting to be done
    private Map<String, List<QueuedOrder>> sellOrders; // contains buying orders waiting to be done

    public Database(Publisher publisher) {
        this.publisher = publisher;
        this.buyOrders = new ConcurrentHashMap<>();
        this.sellOrders = new ConcurrentHashMap<>();
    }

    public void getMatch(Order o){

        List<QueuedOrder> orders;

        if (o.getOrderType() == BUY)
            orders = sellOrders.get(o.getSymbol());
        else
            orders = buyOrders.get(o.getSymbol());

        //TODO: falta apagar qd acaba

        int new_qty = o.getQuantity();

        synchronized (orders){

            for(QueuedOrder qo: orders)
                new_qty -= qo.match(o);

            if (new_qty > 0)
                orders.add(QueuedOrder.create(o,new_qty, publisher));

        }
    }

}
