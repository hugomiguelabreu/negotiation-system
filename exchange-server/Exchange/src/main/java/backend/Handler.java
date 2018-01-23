package backend;

import data.Company;
import data.OrderOuterClass.Order;

import java.io.DataOutputStream;
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
            System.out.println("\n\u001B[32m" + "[+] New connection!\u001B[0m");

            LocalTime open = LocalTime.parse("09:00:00");
            LocalTime close = LocalTime.parse("17:00:00");

            Order o = Order.parseDelimitedFrom(socket.getInputStream());
            System.out.print("Received probuf message: \n" + "\u001B[34m" + o + "\u001B[0m");

            if (!LocalTime.now().isAfter(open) || !LocalTime.now().isBefore(close)) {
                Order response = Order.newBuilder(o).setConfirmation(true).setType(false).build();

                this.socket = new Socket("localhost", 3001);

//                DataOutputStream out= new DataOutputStream(socket.getOutputStream());
//                out.write(o.getSerializedSize());
//                o.writeTo(socket.getOutputStream()); // escreve no socket o tamanho do pacote pq erlang

                socket.getOutputStream().write(response.getSerializedSize());
                response.writeTo(socket.getOutputStream());

                System.out.println("\u001B[41m" + "[ERROR]" + "\u001B[0m" + " Market closed. Negative confirmation sent.");
                return;
            }

            Order response = Order.newBuilder(o).setConfirmation(true).setType(true).build();

            this.socket = new Socket("localhost", 3001);

            socket.getOutputStream().write(response.getSerializedSize());
            response.writeTo(socket.getOutputStream());

            System.out.println("\u001B[42m" + "[SUCCESS]" + "\u001B[0m" + " Positive confirmation sent.");

            companies.get(o.getSymbol()).getMatch(o);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

