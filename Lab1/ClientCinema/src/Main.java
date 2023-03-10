import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Socket socket;
        InetAddress indirizzoServer;
        int portaServer;
        Scanner tastiera = new Scanner(System.in);
        int giornoCinema;
        BufferedReader input;
        PrintWriter output;

        try{
            System.out.print("Inserire l'indirizzo del server: ");
            indirizzoServer = InetAddress.getByName(tastiera.nextLine());
            System.out.print("Inserire la porta del server: ");
            portaServer = tastiera.nextInt();
            socket = new Socket(indirizzoServer, portaServer);

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream());

            System.out.println("Che giorno vuoi andare al cinema?");
            giornoCinema = tastiera.nextInt();

            String messaggio = "1\n" + Integer.toString(giornoCinema) + "\n";
            output.write(messaggio);
            output.flush();

            while(true){
                String inputRisposta = input.readLine();
                if(inputRisposta != null && inputRisposta.equals("1")){
                    System.out.println(input.readLine());
                    break;
                }
                else if(inputRisposta != null && inputRisposta.equals("0")){
                    System.out.println("operazione non riuscita");
                    break;
                }
            }

            input.close();
            output.close();
            socket.close();

        }
        catch (IOException e) {
            System.out.println("Connessione non riuscita");
        }
    }
}