package client_module;

import org.zeromq.ZMQ;
public class NotificationReceiver implements Runnable {

    private ZMQ.Context context;
    private ZMQ.Socket socket;

    public NotificationReceiver() {
        context = ZMQ.context(1);
        socket = context.socket(ZMQ.SUB);
        socket.connect("tcp://*:3002");
        socket.subscribe("X_MERDA".getBytes());
    }

    @Override
    public void run() {
        while(true){
            byte[] b = socket.recv();
            System.out.println(new String(b));
        }
    }
}
