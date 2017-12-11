package backend;

import org.zeromq.ZMQ;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Publisher implements Runnable{

    private ConcurrentLinkedQueue<String> toSend;
    private ZMQ.Context context;
    private ZMQ.Socket socket;

    public Publisher() {
        toSend = new ConcurrentLinkedQueue<>();
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
            String send = toSend.poll();
            socket.send(send.getBytes());
        }
    }
}
