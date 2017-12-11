package data;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import backend.Handler;
import backend.Publisher;
import data.OrderOuterClass.Order;

public class Database {
    private Thread publisher; // where to publish when a deal is done
    private Map<String, List<InfoOrder>> buyOrders; // contains buying orders waiting to be done
    private Map<String, List<InfoOrder>> sellOrders; // contains buying orders waiting to be done

    public Database(Thread publisher) {
        this.publisher = publisher;
        this.buyOrders = new ConcurrentHashMap<>();
        this.sellOrders = new ConcurrentHashMap<>();
    }

    public void getMatch(Order o){
        switch(o.getOrderType()){
            case BUY:
                buyMatch(o);
                break;
            case SELL:
                sellMatch(o);
                break;
        }
    }

    private void buyMatch(Order newOrder) {

        int new_qty = newOrder.getQuantity();
        List<InfoOrder> orders = sellOrders.get(newOrder.getSymbol());

        synchronized (orders){
            for(InfoOrder io: orders){

                if (io.getSetValue() <= newOrder.getSetPrice()){

                    if (io.getQuantity() >= new_qty){

                        Order toNotify = Order.newBuilder()
                                .setOrderType(Order.Type.SELL)
                                .setSymbol(io.getSymbol())
                                .setQuantity(new_qty)
                                .setSetPrice((io.getSetValue() + newOrder.getSetPrice())/2)
                                .setUser(io.getUser()).build();

                        Handler.notifyUser(toNotify);

                    } else {
                        io.takeQuantity(new_qty);

                        Order toNotify = Order.newBuilder()
                                .setOrderType(Order.Type.BUY)
                                .setSymbol(io.getSymbol())
                                .setQuantity(new_qty)
                                .setSetPrice((io.getSetValue() + newOrder.getSetPrice())/2)
                                .setUser(io.getUser()).build();

                        Handler.notifyUser(toNotify);
                        break;
                    }
                }
            }

            if (new_qty > 0){
                InfoOrder io = new InfoOrder(newOrder.getUser(), newOrder.getSymbol(), new_qty, newOrder.getSetPrice());
                orders.add(io);
            }
        }
    }

    private void sellMatch(Order newOrder) {

        int new_qty = newOrder.getQuantity();
        List<InfoOrder> orders = buyOrders.get(newOrder.getSymbol());

        synchronized (orders){
            for(InfoOrder io: orders){
                if (io.getSetValue() >= newOrder.getSetPrice()){

                    if (io.getQuantity() >= new_qty){

                        Order toNotify = Order.newBuilder()
                                .setOrderType(Order.Type.BUY)
                                .setSymbol(io.getSymbol())
                                .setQuantity(new_qty)
                                .setSetPrice((io.getSetValue() + newOrder.getSetPrice())/2)
                                .setUser(io.getUser()).build();

                        Handler.notifyUser(toNotify);

                    } else {
                        io.takeQuantity(new_qty);

                        Order toNotify = Order.newBuilder()
                                .setOrderType(Order.Type.SELL)
                                .setSymbol(io.getSymbol())
                                .setQuantity(new_qty)
                                .setSetPrice((io.getSetValue() + newOrder.getSetPrice())/2)
                                .setUser(io.getUser()).build();

                        Handler.notifyUser(toNotify);
                        break;
                    }
                }
            }

            if (new_qty > 0){
                InfoOrder io = new InfoOrder(newOrder.getUser(), newOrder.getSymbol(), new_qty, newOrder.getSetPrice());
                orders.add(io);
            }
        }
    }

}
