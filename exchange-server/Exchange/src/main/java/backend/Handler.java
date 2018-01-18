package backend;

import data.Company;
import data.OrderOuterClass.Order;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Map;

public class Handler extends Thread{

    private Socket socket;
    private Map<String, Company> companies;

    public Handler(Socket socket, Map<String, Company> companies) {
        this.socket = socket;
        this.companies = companies;

    }

    @Override
    public void run() {
        try {
            System.out.println("\u001B[32m" + "New connection!\u001B[0m");

            LocalTime open = LocalTime.parse("09:00");
            LocalTime close = LocalTime.parse("17:00");

            if (LocalTime.now().isAfter(open) && LocalTime.now().isBefore(close)) {
                System.out.println("Market closed.");

                Order response = Order.newBuilder().setConfirmation(true).setType(false).build();
                response.writeDelimitedTo(socket.getOutputStream());

                return;
            }

            Order o = Order.parseDelimitedFrom(socket.getInputStream());
            System.out.println("Received probuf message: \n" + o);

            companies.get(o.getSymbol()).getMatch(o);

            Order response = Order.newBuilder().setConfirmation(true).setType(true).build();
            response.writeDelimitedTo(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

