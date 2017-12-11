package backend;

import data.Database;

import java.net.ServerSocket;
import java.net.Socket;

public class Listener {

    public static void main(String[] args) throws Exception {

        ServerSocket svSocket = new ServerSocket(3001);

        Thread publisher = (new Thread(new Publisher()));
        publisher.start();

        Database db = new Database(publisher);

        boolean submited = false;

        while (true) { // aqui estará as horas
            while (true) {
                Socket socket = svSocket.accept();
                (new Thread(new Handler(socket, db))).start();
            }
        }


    }
}
