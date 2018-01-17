package client_module;

import data.OrderOuterClass.Order;
import data.OrderResponseOuterClass.OrderResponse;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",3001);
        (new Thread(new NotificationReceiver())).start();

        Order o = Order.newBuilder()
                .setConfirmation(false)
                .setType(false)
                .setSymbol("MERDA")
                .setQuantity(123)
                .setPrice(55)
                .setUser("CONAÇA").build();

        o.writeDelimitedTo(socket.getOutputStream());

        Order or = Order.parseDelimitedFrom(socket.getInputStream());
        System.out.println(or);
    }
}
