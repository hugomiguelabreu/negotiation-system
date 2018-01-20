package backend;

import data.OrderOuterClass.Order;
import org.zeromq.ZMQ;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Publisher extends Thread{

    private BlockingQueue<String> toSend;
    private ZMQ.Context context;
    private ZMQ.Socket socket;

    public Publisher() {
        toSend = new LinkedBlockingQueue<>();
        context = ZMQ.context(1);
        socket = context.socket(ZMQ.PUB);
        socket.bind("tcp://*:3002");
        }

        public void sendNotification(String txt){
            toSend.add(txt);
        }

        @Override
        public void run() {
            String send = null;
            while(true){
                try {
                    send = toSend.take();
                } catch (InterruptedException e) {
                e.printStackTrace();
            }
            socket.send(send.getBytes());
        }
    }

    //TODO: REVERIFICAR A PORTA DE RESPOSTA
    public static void notifyUser(Order o) {
        try {
            Socket s = new Socket("localhost", 3001);
            s.getOutputStream().write(o.getSerializedSize());
            o.writeTo(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
