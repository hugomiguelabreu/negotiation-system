package backend;

import data.Database;
import data.OrderOuterClass.Order;
import data.OrderResponseOuterClass.OrderResponse;

import java.io.IOException;
import java.net.Socket;

public class Handler implements Runnable{

    private Socket socket;
    private Database db;
    private Publisher publisher;

    public Handler(Socket socket, Database db, Publisher publisher) {
        this.socket = socket;
        this.db = db;
        this.publisher = publisher;
    }

    @Override
    public void run() {
        try {
            System.out.println("New connection!");

            Order o = Order.parseFrom(socket.getInputStream());
            System.out.println("Received probuf message: \n" + o);

            db.getMatch(o);

            OrderResponse response = OrderResponse.newBuilder().setConfirmation(true).build();
            response.writeTo(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

