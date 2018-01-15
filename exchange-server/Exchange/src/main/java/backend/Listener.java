package backend;

import data.Database;
import rest.RESTClient;
import rest.core.Exchange;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

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

        Database db = new Database(publisher);

        boolean submited = false;

        while (true) { // TODO: introduzir horas
            while (true) {
                Socket socket = svSocket.accept();
                (new Handler(socket, db, publisher)).start();
            }
        }


    }
}
