package backend;

import data.Database;
import rest.RESTClient;

import java.net.ServerSocket;
import java.net.Socket;

public class Listener {

    public static void main(String[] args) throws Exception {

        ServerSocket svSocket = new ServerSocket(3001);
        //Inicia Objeto de pedidos ao servidor REST
        RESTClient rc = new RESTClient();
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
