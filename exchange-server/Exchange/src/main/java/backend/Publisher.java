package backend;

import data.protobuf.OrderOuterClass;
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
        while(true){
            String send = null;
            try {
                send = toSend.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            socket.send(send.getBytes());
        }
    }

    //Y? What does it mean?
    public static void notifyUser(OrderOuterClass.Order o) {
        try {
            Socket s = new Socket("localhost", 3002);
            o.writeTo(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
