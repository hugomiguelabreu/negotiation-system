import data.OrderOuterClass.Order;
import protobuff.account.AccountOuterClass.Account;
import protobuff.response.ResponseOuterClass.Response;
import rest.RESTClient;
import rest.core.Company;
import rest.core.Exchange;
import rest.core.PriceInfo;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// protoc --proto_path=. --java_out=src/main/java/ account.proto

// Registo -> true
// Login -> false

public class Client {

    private static RESTClient rc;
    private static Subscriber sb;
    private static String username;

    public enum Status {
        VISITOR, LOGGED, QUIT
    }

    public static void main(String[] args) throws IOException {

        rc = new RESTClient();
        sb = new Subscriber();
        (new Thread(sb)).start();

        Status status = Status.VISITOR;
        Socket socket = new Socket("localhost"  , 2000);
        //(new Thread(new Listener(socket))).start();

        do{
            switch (status){
                case VISITOR:
                    status = visitorMenu(socket);
                    break;
                case LOGGED:
                    socket = new Socket("localhost"  , 3000);
                    (new Thread(new Listener(socket))).start();
                    status = loginMenu(socket, username);
                    break;
            }
        } while (status != Status.QUIT);
    }


    private static Status loginMenu(Socket socket, String user) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("[1] Ver empresas.");
        System.out.println("[2] Ver exchanges.");
        System.out.println("[3] Ver uma empresa específica.");
        System.out.println("[4] Verificar preços da empresa.");
        System.out.println("[5] Obter real time updates.");
        System.out.println("[6] Cancelar real time updates.");
        System.out.println("[7] Abrir posição.");
        System.out.println("[0] Sair");
        System.out.println("==========================================");

        switch (sc.nextInt()){
            case 1:
                Company[] cs = rc.getCompanies();
                if (cs != null)
                    for (Company n: cs)
                        System.out.println(n.getId() + "::" + n.getName());
                    else
                        System.out.println("Ocorreu um erro.");
                break;

            case 2:
                Exchange[] es = rc.getExchanges();
                if(es!=null)
                    for (Exchange n: es)
                        System.out.println(n.getName() + "::" + n.getCity() + '\n' + n.getAddr());
                else
                    System.out.println("Ocorreu um erro.");
                break;

            case 3:
                System.out.println("ID da empresa: ");
                Company c = rc.getCompany(sc.nextLine());

                if (c!=null) {
                    System.out.println(c.getId() + "::" + c.getName());
                    System.out.println("Exchange:");
                    System.out.println(c.getExchange().getName() + "::" + c.getExchange().getCity());
                    System.out.println(c.getExchange().getAddr());
                } else
                    System.out.println("Empresa não existe.");

                break;

            case 4:
                System.out.println("ID da empresa: ");
                String cId = sc.nextLine();
                PriceInfo pt = rc.getPrice(cId, 0); // Preço de Hoje
                PriceInfo py = rc.getPrice(cId, 1); // Preço de Ontem

                if (pt!=null) {
                    System.out.println("Preços de Hoje (" + pt.getTimestamp() + "):");
                    System.out.println("Abertura: " + pt.getOpen());
                    System.out.println("Fecho: " + pt.getClose());
                    System.out.println("Máximo: " + pt.getMax());
                    System.out.println("Mínimo: " + pt.getMin());
                } else
                    System.out.println("Preços do dia de Hoje indisponíveis");

                if (py!=null) {
                    System.out.println("Preços de Ontem (" + py.getTimestamp() + "):");
                    System.out.println("Abertura: " + py.getOpen());
                    System.out.println("Fecho: " + py.getClose());
                    System.out.println("Máximo: " + py.getMax());
                    System.out.println("Mínimo: " + py.getMin());
                }else
                    System.out.println("Preços do dia de Ontem indisponíveis");

                break;

            case 5:

                System.out.println("ID da empresa a subscrever:");
                Company cSub = rc.getCompany(sc.nextLine());

                if (cSub!=null)
                    sb.addSubscription(cSub.getExchange().getAddr(), cSub.getId());
                else
                    System.out.println("Empresa não existe.");

                break;
            case 6:
                System.out.println("ID da empresa para remover subscrição:");
                Company cUnSub = rc.getCompany(sc.nextLine());

                if (cUnSub!=null)
                    sb.removeSubscription(cUnSub.getId());
                else
                    System.out.println("Empresa não existe.");

                break;

            case 7:
                createPosition(socket,user);
                break;

            case 0:
                return Status.QUIT;


            default:
                return Status.LOGGED;
        }

        return Status.LOGGED;
    }

    private static Status visitorMenu(Socket socket) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("[1] Login");
        System.out.println("[2] Registo");
        System.out.println("[0] Sair");
        System.out.println("==========================================");

        switch (sc.nextInt()){
            case 1:
                if (login(socket))
                    return Status.LOGGED;
                else
                    return Status.VISITOR;
            case 2:
                if (register(socket))
                    return Status.LOGGED;
                else
                    return Status.VISITOR;
            case 3:
                return Status.QUIT;

            default:
                return Status.VISITOR;
        }
    }

    private static boolean register(Socket socket) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("---- Register ----");

        System.out.print("Username: ");
        String usr = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Account acc = Account.newBuilder()
                .setUsername(usr)
                .setPassword(password)
                .setType(true).build();

        socket.getOutputStream().write(acc.getSerializedSize());
        acc.writeTo(socket.getOutputStream());

        Response rep = Response.parseDelimitedFrom(socket.getInputStream());
        boolean b = rep.getRep();

        if(b) {
            System.out.println("--- Registo bem sucedido ----");
            username = usr;
        } else
            System.out.println("--- Username inválido ---");

        return b;
    }

    private static boolean login(Socket socket) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("---- Login ----");

        System.out.print("Username: ");
        String usr = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Account acc = Account.newBuilder()
                .setUsername(usr)
                .setPassword(password)
                .setType(false).build();

        socket.getOutputStream().write(acc.getSerializedSize());
        acc.writeTo(socket.getOutputStream());

        Response rep = Response.parseDelimitedFrom(socket.getInputStream());
        boolean b = rep.getRep();

        if(b) {
            System.out.println("--- Login bem sucedido ----");
            username = usr;
        } else
            System.out.println("--- Login inválido ---");

        return b;
    }

    private static void createPosition(Socket s, String user) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Empresa: ");
        String symbol = sc.nextLine();

        System.out.println("Quantidade: ");
        int quantity = sc.nextInt();

        System.out.println("Preço: ");
        double price = sc.nextDouble();

        System.out.println("Tipo [B/S]: ");
        boolean type = sc.nextLine() == "B" ? true : false;

        Order o = Order.newBuilder()
                    .setConfirmation(false)
                    .setType(type)
                    .setSymbol(symbol)
                    .setQuantity(quantity)
                    .setPrice(price)
                    .setUser(user).build();

        s.getOutputStream().write(o.getSerializedSize());
        o.writeTo(s.getOutputStream());
    }

}
