package backend;

import data.Database;
import data.OrderOuterClass.Order;
import data.OrderResponseOuterClass.OrderResponse;

import java.io.IOException;
import java.net.Socket;

public class Handler implements Runnable{

    private Socket socket;
    private Database db;

    public Handler(Socket socket, Database db) {
        this.socket = socket;
        this.db = db;
    }

    @Override
    public void run() {
        try {
            Order o = Order.parseFrom(socket.getInputStream());
            db.getMatch(o);
            OrderResponse response = OrderResponse.newBuilder().setConfirmation(true).build();
            response.writeTo(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void notifyUser(Order o) {
        try {
            Socket s = new Socket("localhost", 3002);
            o.writeTo(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

