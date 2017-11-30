import protobuff.account.AccountOuterClass;
import protobuff.response.ResponseOuterClass;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// protoc --proto_path=. --java_out=src/main/java/ account.proto

// Registo -> true
// Login -> false

public class Client {

    public static void main(String[] args) {

        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
        String input;
        boolean flag = false;

        System.out.println("Bem vindo ao Negotiation System");

        while (!flag){

            System.out.println("[l]login");
            System.out.println("[r]registo");

            try {
                input = stdIn.readLine();

                if(input.equals("l")){
                    flag = login();
                }

                else
                if(input.equals("r")){
                    flag = register();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private static boolean register() {

        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
        String username, password;

        System.out.println("---- Register ----");

        try{

            System.out.print("username: ");
            username = stdIn.readLine();


            System.out.print("password: ");

            password = stdIn.readLine();

            Socket socket = new Socket("localhost", 2000);

            AccountOuterClass.Account.Builder acc = AccountOuterClass.Account.newBuilder();

            acc.setUsername(username);
            acc.setPassword(password);
            acc.setType(true);
            AccountOuterClass.Account send = acc.build();

            send.writeTo(socket.getOutputStream());

//            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));

//            System.out.println(in);

            ResponseOuterClass.Response rep = ResponseOuterClass.Response.parseFrom(socket.getInputStream());

            boolean b = rep.getRep();

            if(b){
                System.out.println("--- Registo bem sucedido ----");
            }
            else{
                System.out.println("--- Username invalido ---");
            }


            socket.close();

            return  false;

        }

        catch (Exception e){
            e.printStackTrace();
            return  true;
        }



    }

    private static boolean login(){

        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
        String username, password;

        System.out.println("---- Login ----");

        try{

            System.out.print("username: ");
            username = stdIn.readLine();


            System.out.print("password: ");

            password = stdIn.readLine();

            Socket socket = new Socket("localhost", 2000);

            AccountOuterClass.Account.Builder acc = AccountOuterClass.Account.newBuilder();

            acc.setUsername(username);
            acc.setPassword(password);
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
            return  true;
        }

    }

}
