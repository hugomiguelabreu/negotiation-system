import org.zeromq.ZMQ;

import java.util.ArrayList;

public class Subscriber extends Thread{

    private ArrayList<String> connections;
    private ArrayList<String> subscriptions;
    private ZMQ.Context contextProc;
    private ZMQ.Socket socket;

    public Subscriber(){
        contextProc = ZMQ.context(1);
        socket = contextProc.socket(ZMQ.SUB);
        connections = new ArrayList<>();
        subscriptions = new ArrayList<>();
    }

    public void addSubscription(String addr, String company){
        //ip:port+1
        String[] aux = addr.split(":");
        String addressAux = aux[0];
        int port = Integer.parseInt(aux[1]);
        port++;

        addr = addressAux + ":" + port;

        if(!connections.contains(addr)) {
            socket.connect("tcp://" + addr);
            connections.add(addr);
        }

        if(!subscriptions.contains(company)){
            socket.subscribe((company + "_").getBytes());
            subscriptions.add(company);
        }
    }

    public void removeSubscription(String company){
        if(subscriptions.contains(company)){
            socket.unsubscribe((company + "::").getBytes());
            subscriptions.remove(company);
        }
    }

    public void run(){

        while (true) {
            byte[] b = socket.recv();
            String r = new String(b);

            System.out.println("\u001B[36m" + r.substring(r.indexOf("_")+1).trim() + "\u001B[0m");

        }

    }

}
