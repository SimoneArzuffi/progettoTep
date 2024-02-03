import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Per uscire scrivi QUIT");
            System.out.println("Il gioco dell'indovina chi");
            System.out.println("Il server pensa ad un personaggio e tu devi indovinarlo");
            System.out.println("Per indovinare il personaggio devi fare delle domande al server");
            System.out.println("Il server risponderà con SI o NO");
            System.out.println("Per esempio: \"Il personaggio ha gli occhiali?\"");
            System.out.println("Il server risponderà con SI o NO");
            System.out.println("Per uscire scrivi QUIT");
            System.out.println("P.S. le domande ammesse sono queste:");
            System.out.println("Il personaggio è maschio?");
            System.out.println("Il personaggio ha i capelli biondi?");
            System.out.println("Il personaggio ha gli occhiali?");
            System.out.println("Il personaggio ha il cappello?");
            System.out.println("Ogni domanda va scritta con queste esatte parole, si può cambiare il genere e il colore dei capelli");
            String userInput;
            while (true) 
            { 
                System.out.print("Inserisci: "); 
                userInput = stdIn.readLine(); 
                out.println(userInput);
                if (userInput.equals("QUIT")){
                    break; 
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("No I/O");
            System.out.println(e.getMessage());
        }
    }
    
}
