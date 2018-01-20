package client_module;

import data.OrderOuterClass.Order;

import java.io.DataOutputStream;
import java.lang.instrument.Instrumentation;


import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",3000);
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
                .setType(true)
                .setSymbol("AMZ")
                .setQuantity(10)
                .setPrice(55)
                .setUser("joao").build();

        int size = o.getSerializedSize();

//        DataOutputStream out= new DataOutputStream();
        socket.getOutputStream().write(size);

        o.writeTo(socket.getOutputStream());

        System.out.println("size = " + size);


        //socket.getOutputStream().close();

        //socket.close();

//        Order or = Order.parseDelimitedFrom(socket.getInputStream());
//
//        System.out.println(or);
    }
}
