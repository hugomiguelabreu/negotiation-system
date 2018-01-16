package backend;

import data.Company;
import rest.RESTClient;
import rest.core.Exchange;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Listener {

    public static void main(String[] args) throws Exception {

        //Inicia Objeto de pedidos ao servidor REST
        //RESTClient rc = new RESTClient();

        //Exchange e = rc.getExchange(args[0]);
        //int port = Integer.parseInt(e.getAddr().split(":")[1]);
        //System.out.println("Iniciada a Exchange " + e.getName() + " na porta " + port);

        ServerSocket svSocket = new ServerSocket(3001);

        // Inicia Thread que irá publicar exchanges efetuadas
        Publisher publisher = new Publisher();
        publisher.start();

        HashMap<String,Company> companies = new HashMap<>();
        RESTClient rest = new RESTClient();
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
