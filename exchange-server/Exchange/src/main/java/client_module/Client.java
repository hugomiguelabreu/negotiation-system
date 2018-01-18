package client_module;

import data.OrderOuterClass.Order;

import java.io.DataOutputStream;
import java.lang.instrument.Instrumentation;


import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",3001);
        (new Thread(new NotificationReceiver())).start();
//
//<<<<<<< HEAD
//        //Order o = Order.newBuilder();
//
//        Order.Builder o = Order.newBuilder();
//
//        o.setOrderType(true);
//        o.setSymbol("MCS");
//        o.setQuantity(168);
//        o.setPrice(5);
//        o.setUser("sim");
//
//
//        Order send = o.build();
//
//
//
//        OrderResponse or = OrderResponse.parseFrom(socket.getInputStream());
//
//=======

        Order o = Order.newBuilder()
                .setConfirmation(false)
                .setType(false)
                .setSymbol("MERDA")
                .setQuantity(123)
                .setPrice(55)
                .setUser("CONAÇA").build();

        int size = o.getSerializedSize();

        DataOutputStream out= new DataOutputStream(socket.getOutputStream());
        out.write(size);

        o.writeTo(socket.getOutputStream());

        System.out.println("size = " + size);

        Order or = Order.parseDelimitedFrom(socket.getInputStream());

        System.out.println(or);
    }
}
