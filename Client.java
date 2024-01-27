import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost"; // o l'indirizzo IP del server
        int port = 1234;

        try (Socket socket = new Socket(hostname, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Risposta del server: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto: " + hostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Impossibile ottenere I/O per la connessione a " + hostname);
            System.exit(1);
        }
    }
}
