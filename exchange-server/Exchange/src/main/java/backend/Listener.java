package backend;

import rest.RESTClient;
import data.Company;
import rest.core.Exchange;

import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.HashMap;

public class Listener {

    public static void main(String[] args) throws Exception {

        if(args.length != 1)
            System.out.println("Market argument is missing or incorrect.");

        String type = "NASDAQ";

        // Inicia Thread que irá publicar exch  anges efetuadas

        Publisher publisher = new Publisher();
        publisher.start();

        RESTClient rest = new RESTClient();
        Exchange e = rest.getExchange(type);
        int port = Integer.parseInt(e.getAddr().split(":")[1]);

        HashMap<String, Company> companies = new HashMap<>();
        rest.core.Company[] cs = rest.getCompanyNames(type);
        for(rest.core.Company c: cs)
            companies.put(c.getName(), new Company(type, publisher, rest));

        System.out.println(companies.keySet());

        ServerSocket svSocket = new ServerSocket(3001);

        while (true) {
            Socket socket = svSocket.accept();
            (new Handler(socket, companies)).start();
        }
    }
}
