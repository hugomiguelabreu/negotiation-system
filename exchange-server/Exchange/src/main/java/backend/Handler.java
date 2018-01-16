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

    public Handler(Socket socket, Publisher publisher, Map<String, Company> companies) {
        this.socket = socket;
        this.publisher = publisher;
        this.companies = companies;

    }

    @Override
    public void run() {
        try {
            System.out.println("New connection!");

            Order o = Order.parseDelimitedFrom(socket.getInputStream());
            System.out.println("Received probuf message: \n" + o);

            companies.get(o.getSymbol()).getMatch(o);

            OrderResponse response = OrderResponse.newBuilder().setConfirmation(true).build();
            response.writeDelimitedTo(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

