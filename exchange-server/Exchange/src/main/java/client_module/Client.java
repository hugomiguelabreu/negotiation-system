package client_module;

import data.OrderOuterClass.Order;
import data.OrderResponseOuterClass.OrderResponse;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",3000);

        Order o = Order.newBuilder()
                .setOrderType(true)
                .setSymbol("MERDA")
                .setQuantity(123)
                .setPrice(69)
                .setUser("sim").build();

        o.writeTo(socket.getOutputStream());

        OrderResponse or = OrderResponse.parseFrom(socket.getInputStream());
        System.out.println(or);
    }
}
