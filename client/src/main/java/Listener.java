import java.io.IOException;
import java.net.Socket;
import data.OrderOuterClass.Order;

public class Listener implements Runnable{

    private Socket socket;

    public Listener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while(true){
            try {

                Order o = Order.parseDelimitedFrom(socket.getInputStream());
                if (o.getConfirmation())
                    System.out.println("Order for " + o.getSymbol() + " registered successfully.");
                else
                    System.out.println("Transition made:\n"+ o);

            } catch (IOException e) {
                System.out.println("Server connection Error!");
                System.exit(-1);
            }
        }
    }
}
