import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 1234; // Scegli una porta
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server in attesa di connessioni sulla porta " + port);
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                System.out.println("Connessione accettata.");
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Messaggio ricevuto: " + inputLine);
                    out.println("Eco: " + inputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nel server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
