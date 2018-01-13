package client_module;

import data.OrderOuterClass;
import data.OrderOuterClass.Order;
import data.OrderResponseOuterClass.OrderResponse;
import data.OrderResponseOuterClass;

import java.io.IOException;
import java.net.Socket;

import static data.OrderOuterClass.Order.Type.BUY;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",3000);

        Order o = Order.newBuilder()
                .setOrderType(BUY)
                .setSymbol("ecks")
                .setQuantity(123)
                .setSetPrice(69)
                .setUser("sim").build();

        o.writeTo(socket.getOutputStream());

        OrderResponse or = OrderResponse.parseFrom(socket.getInputStream());
        System.out.println(or);
    }
}
