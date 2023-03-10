import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Socket socketClient;
        ServerSocket socketAscolto;
        String[] titoli = {
                "Shrek",
                "Cars",
                "L'era glaciale",
                "Kung fu Panda",
                "Kung fu Panda 2",
                "Shrek 2",
                "Cars 2"
        };
        BufferedReader input;
        PrintWriter output;
        int codiceOperazione;
        int filmRichiesto;

        try {
            socketAscolto = new ServerSocket(33333);
            System.out.println("Server partito!");

            while(true){
                socketClient = socketAscolto.accept();
                input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                output = new PrintWriter(socketClient.getOutputStream());

                codiceOperazione = Integer.parseInt(input.readLine());
                filmRichiesto = Integer.parseInt(input.readLine());

                //System.out.println(codiceOperazione);
                //System.out.println(filmRichiesto);


                if(codiceOperazione == 1 && filmRichiesto >= 1 && filmRichiesto <=7){
                    output.write("1\n");
                    output.write(titoli[filmRichiesto-1] + "\n");
                    output.flush();
                }
                else{
                    output.write("0\n");
                    output.flush();
                }
                socketClient.close();

            }
        }
        catch(IOException ex){
            System.out.println("Errore in fase di input/output");
        }
    }
}