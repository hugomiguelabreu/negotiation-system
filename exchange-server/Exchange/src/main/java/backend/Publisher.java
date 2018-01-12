package backend;

import org.zeromq.ZMQ;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Publisher implements Runnable{

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
}
