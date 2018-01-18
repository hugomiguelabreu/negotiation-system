package client_module;

import data.OrderOuterClass;
import data.OrderOuterClass.Order;
import data.OrderResponseOuterClass.OrderResponse;
import data.OrderResponseOuterClass;

import java.io.DataOutputStream;
import java.lang.instrument.Instrumentation;


import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",3000);

        //Order o = Order.newBuilder();

        Order.Builder o = Order.newBuilder();

        o.setOrderType(true);
        o.setSymbol("MCS");
        o.setQuantity(168);
        o.setPrice(5);
        o.setUser("sim");


        Order send = o.build();

        int size = send.getSerializedSize();

        DataOutputStream out=new DataOutputStream(socket.getOutputStream());
        out.write(size);

        send.writeTo(socket.getOutputStream());

        System.out.println("size = " + size);

        OrderResponse or = OrderResponse.parseFrom(socket.getInputStream());

        System.out.println(or);
    }
}
