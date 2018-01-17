package backend;

import rest.RESTClient;
import data.Company;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Listener {

    public static void main(String[] args) throws Exception {

        //if(args.length != 1)
        //    System.out.println("Market argument is missing or incorrect.");
        //String type = args[0];

        //Exchange e = rc.getExchange(args[0]);
        //int port = Integer.parseInt(e.getAddr().split(":")[1]);
        //System.out.println("Iniciada a Exchange " + e.getName() + " na porta " + port);

        ServerSocket svSocket = new ServerSocket(3001);

        // Inicia Thread que irá publicar exch  anges efetuadas
        Publisher publisher = new Publisher();
        publisher.start();

        RESTClient rest = new RESTClient();
        HashMap<String, Company> companies = new HashMap<>();

        //rest.core.Company[] cs = rest.getCompanyNames(type);
        //for(rest.core.Company c: cs)
        //    companies.put(c.getName(), new Company(publisher, rest));

        companies.put("MERDA", new Company(publisher, rest));
        companies.put("FEZES", new Company(publisher, rest));

        boolean submited = false;

        while (true) { // TODO: introduzir horas
            while (true) {
                Socket socket = svSocket.accept();
                (new Handler(socket, publisher, companies)).start();
            }
        }


    }
}
