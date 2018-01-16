package backend;

import data.Company;
import data.OrderOuterClass.Order;
import data.OrderResponseOuterClass.OrderResponse;
import rest.RESTClient;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class Handler extends Thread{

    private Socket socket;
    private Publisher publisher;
    private Map<String, Company> companies;

    public Handler(Socket socket, Publisher publisher) {
        this.socket = socket;
        this.publisher = publisher;
        RESTClient rest = new RESTClient();

        companies.put("MERDA", new Company(publisher, rest));
        companies.put("FEZES", new Company(publisher, rest));

    }

    @Override
    public void run() {
        try {
            System.out.println("New connection!");

            Order o = Order.parseFrom(socket.getInputStream());
            System.out.println("Received probuf message: \n" + o);

            companies.get(o.getSymbol()).getMatch(o);

            OrderResponse response = OrderResponse.newBuilder().setConfirmation(true).build();
            response.writeTo(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

