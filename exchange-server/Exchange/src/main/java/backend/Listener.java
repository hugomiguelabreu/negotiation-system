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
            System.out.println("\u001B[31m[ERROR] Market argument is missing or incorrect.\u001B[0m");

        String type = "NASDAQ";

        // Inicia Thread que irá publicar exch  anges efetuadas


        System.out.print("> Connecting to the REST Server...");
        RESTClient rest = new RESTClient();
        System.out.print("\u001B[32m DONE!\u001B[0m\n");

        Exchange e = rest.getExchange(type);
        int port = Integer.parseInt(e.getAddr().split(":")[1]);
        int port_pub = port + 1;

        System.out.print("> Starting publisher on port " + port_pub + "...");
        Publisher publisher = new Publisher(port + 1);
        publisher.start();
        System.out.print("\u001B[32m DONE!\u001B[0m\n");

        HashMap<String, Company> companies = new HashMap<>();
        rest.core.Company[] cs = rest.getCompanyNames(type);
        for(rest.core.Company c: cs)
            companies.put(c.getId(), new Company(c.getId(), publisher, rest));
        System.out.println("> Market: " +  type + " |  Companies: " + companies.keySet());

        ServerSocket svSocket = new ServerSocket(port);
        System.out.println("> Now listening for connections on port " + port);

        while (true) {
            Socket socket = svSocket.accept();
            (new Handler(socket, companies)).start();
        }
    }
}
