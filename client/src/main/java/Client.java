import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import protobuff.account.AccountOuterClass;
import protobuff.response.ResponseOuterClass;
import rest.RESTClient;
import rest.core.Company;
import rest.core.Exchange;
import rest.core.PriceInfo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

// protoc --proto_path=. --java_out=src/main/java/ account.proto

// Registo -> true
// Login -> false

public class Client {

    private static RESTClient rc;
    private static Subscriber sb;

    public static void main(String[] args) {

        Scanner stdIn = new Scanner(System.in);
        String input;
        boolean quit = false;
        boolean flag = true;
        boolean loggedIn = false;

        System.out.println("\u001B[34mBem vindo ao Negotiation System\u001B[0m");

        while (!quit){

            if(!loggedIn) {
                System.out.println("==========================================");
                System.out.println("[1] Login");
                System.out.println("[2] Registo");
                System.out.println("[0] Sair");
                System.out.println("==========================================");

                input = stdIn.nextLine();
                //TODO: workaroud no login para testar o serviço REST
                if (StringUtils.isNumeric(input))
                    switch (Integer.parseInt(input)) {
                        case 1:
                            //flag = login();
                            //if (flag) {
                                System.out.println("\u001B[32mLogin bem sucedido.\u001B[0m");
                                loggedIn = true;
                                rc = new RESTClient();
                                sb = new Subscriber();
                                sb.start();
                            //}else {
                            //    System.out.println("\u001B[31mLogin inválido.\u001B[0m");
                            //    loggedIn = false;
                            //}
                            break;

                        case 2:
                            flag = register();
                            if (flag)
                                System.out.println("\u001B[32mRegisto bem sucedido.\u001B[0m");
                            else
                                System.out.println("\u001B[31mRegisto inválido.\u001B[0m");
                            break;

                        case 0:
                            quit = true;
                            break;

                        default:
                            System.out.println("\u001B[31mOpção não reconhecida\u001B[0m");
                            break;

                    }

            }else{
                System.out.println("==========================================");
                System.out.println("[1] Ver empresas.");
                System.out.println("[2] Ver exchanges.");
                System.out.println("[3] Ver uma empresa específica.");
                System.out.println("[4] Verificar preços da empresa.");
                System.out.println("[5] Obter real time updates.");
                System.out.println("[6] Cancelar real time updates.");
                System.out.println("[0] Sair");
                System.out.println("==========================================");

                input = stdIn.nextLine();

                if (StringUtils.isNumeric(input))
                    switch (Integer.parseInt(input)){
                        case 1:
                            Company[] cs = rc.getCompanies();
                            if(cs!=null) {
                                for (Company n: cs) {
                                    System.out.println(n.getId() + "::" + n.getName());
                                }
                            }else{
                                System.out.println("Ocorreu um erro.");
                            }
                            break;
                        case 2:
                            Exchange[] es = rc.getExchanges();
                            if(es!=null) {
                                for (Exchange n: es) {
                                    System.out.println(n.getName() + "::" + n.getCity());
                                    System.out.println(n.getAddr());
                                }
                            }else{
                                System.out.println("Ocorreu um erro.");
                            }
                            break;
                        case 3:
                            System.out.println("ID da empresa:");
                            Company c = rc.getCompany(stdIn.nextLine());
                            if(c!=null) {
                                System.out.println(c.getId() + "::" + c.getName());
                                System.out.println("Exchange:");
                                System.out.println(c.getExchange().getName() + "::" + c.getExchange().getCity());
                                System.out.println(c.getExchange().getAddr());
                            }else{
                                System.out.println("Empresa não existe.");
                            }
                            break;
                        case 4:
                            System.out.println("ID da empresa:");
                            String cId = stdIn.nextLine();
                            PriceInfo pt = rc.getPrice(cId, 0); // Preço de Hoje
                            PriceInfo py = rc.getPrice(cId, 1); // Preço de Ontem
                            if(pt!=null) {
                                System.out.println("Preços de Hoje (" + pt.getTimestamp() + "):");
                                System.out.println("Abertura: " + pt.getOpen());
                                System.out.println("Fecho: " + pt.getClose());
                                System.out.println("Máximo: " + pt.getMax());
                                System.out.println("Mínimo: " + pt.getMin());
                            }else{
                                System.out.println("Preços do dia de Hoje indisponíveis");
                            }
                            if(py!=null) {
                                System.out.println("Preços de Ontem (" + py.getTimestamp() + "):");
                                System.out.println("Abertura: " + py.getOpen());
                                System.out.println("Fecho: " + py.getClose());
                                System.out.println("Máximo: " + py.getMax());
                                System.out.println("Mínimo: " + py.getMin());
                            }else{
                                System.out.println("Preços do dia de Ontem indisponíveis");
                            }
                            break;
                        case 5:
                            System.out.println("ID da empresa a subscrever:");
                            Company cSub = rc.getCompany(stdIn.nextLine());
                            if(cSub!=null) {
                                sb.addSubscription(cSub.getExchange().getAddr(), cSub.getId());
                            }else{
                                System.out.println("Empresa não existe.");
                            }
                            break;
                        case 6:
                            System.out.println("ID da empresa para remover subscrição:");
                            Company cUnSub = rc.getCompany(stdIn.nextLine());
                            if(cUnSub!=null) {
                                sb.removeSubscription(cUnSub.getId());
                            }else{
                                System.out.println("Empresa não existe.");
                            }
                            break;
                        case 0:
                            quit = true;
                            break;
                        default:
                            System.out.println("\u001B[31mOpção não reconhecida\u001B[0m");
                            break;
                    }
            }
        }

        System.out.println("\u001B[34mBye\u001B[0m");


    }

    private static boolean register() {

        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
        String username, password;

        System.out.println("---- Register ----");

        try{

            System.out.print("Username: ");
            username = stdIn.readLine();


            System.out.print("Password: ");
            password = stdIn.readLine();

            Socket socket = new Socket("localhost", 2000);

            AccountOuterClass.Account.Builder acc = AccountOuterClass.Account.newBuilder();
            acc.setUsername(username);
            acc.setPassword(password);
            // Tipo de operação (Login == false; Registo == true);
            acc.setType(true);
            AccountOuterClass.Account send = acc.build();

            send.writeTo(socket.getOutputStream());

            ResponseOuterClass.Response rep = ResponseOuterClass.Response.parseFrom(socket.getInputStream());

            boolean b = rep.getRep();

            if(b){
                System.out.println("--- Registo bem sucedido ----");
            }
            else{
                System.out.println("--- Username inválido ---");
            }


            socket.close();

            return b;

        }

        catch (Exception e){
            e.printStackTrace();
            return false;
        }



    }

    private static boolean login(){

        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
        String username, password;

        System.out.println("---- Login ----");

        try{

            System.out.print("Username: ");
            username = stdIn.readLine();


            System.out.print("Password: ");

            password = stdIn.readLine();

            Socket socket = new Socket("localhost", 2000);

            AccountOuterClass.Account.Builder acc = AccountOuterClass.Account.newBuilder();

            acc.setUsername(username);
            acc.setPassword(password);
            // Tipo de operação (Login == false; Registo == true);
            acc.setType(false);
            AccountOuterClass.Account send = acc.build();

            send.writeTo(socket.getOutputStream());

            ResponseOuterClass.Response rep = ResponseOuterClass.Response.parseFrom(socket.getInputStream());

            boolean b = rep.getRep();

            if(b){
                System.out.println("--- Login bem sucedido ---");
            }
            else{
                System.out.println("--- Username ou Password invalido ---");
            }

            socket.close();

            return b;

        }

        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

}
