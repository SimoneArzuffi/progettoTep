import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is listening on port 1234");
            
            while (true) {
                try (Socket socket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Server received: " + inputLine);
                        out.println("Echo: " + inputLine);
                        
                        if(inputLine.equals("QUIT")){
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Eccezione rilevata durante il tentativo di ascolto sulla porta 1234 o l'ascolto di una connessione");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Impossibile connettersi sulla porta 1234");
            System.out.println(e.getMessage());
        }
    }

    private void creaPersonaggi(){
    }
}