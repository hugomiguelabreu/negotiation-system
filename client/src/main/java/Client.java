import protobuff.account.AccountOuterClass;
import protobuff.response.ResponseOuterClass;
import rest.RESTClient;


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

    public static void main(String[] args) {

        Scanner stdIn = new Scanner(System.in);
        String input;
        boolean quit = false;
        boolean flag = true;
        boolean loggedIn = false;

        System.out.println("\u001B[34mBem vindo ao Negotiation System\u001B[0m");

        while (!quit){

            if(!loggedIn) {
                System.out.println("[1] Login");
                System.out.println("[2] Registo");
                System.out.println("[0] Sair");

                input = stdIn.nextLine();
                //TODO: workaroud no login para testar o serviço REST
                if (input.equals("1")) {
                    flag = login();
                    if (flag) {
                        System.out.println("\u001B[32mLogin bem sucedido.\u001B[0m");
                        loggedIn = true;
                        rc = new RESTClient();
                    }else {
                        System.out.println("\u001B[31mLogin inválido.\u001B[0m");
                        loggedIn = false;
                    }
                } else if (input.equals("2")) {
                    flag = register();
                    if (flag)
                        System.out.println("\u001B[32mRegisto bem sucedido.\u001B[0m");
                    else
                        System.out.println("\u001B[31mRegisto inválido.\u001B[0m");
                } else if (input.equals("0")) {
                    quit = true;
                } else {
                    System.out.println("\u001B[31mOpção não reconhecida\u001B[0m");
                }
            }else{
                System.out.println("[1] Ver empresas");
                System.out.println("[2] Ver exchanges");
                System.out.println("[3] Ver uma empresa específica");
                System.out.println("[0] Sair");

                input = stdIn.nextLine();

                if (input.equals("1")) {
                    System.out.println("ok");
                } else if (input.equals("2")) {
                    System.out.println("ok");
                } else if (input.equals("3")) {
                    System.out.println("ok");
                }else if (input.equals("0")) {
                    quit = true;
                } else {
                    System.out.println("\u001B[31mOpção não reconhecida\u001B[0m");
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
