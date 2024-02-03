import java.io.*;
import java.net.*;

public class Server {

    private static String parteiniziale1 = "Il personaggio è";
    private static String parteiniziale2 = "Il personaggio ha i capelli";
    private static String parteiniziale3 = "Il personaggio si chiama";
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
                        // Se l'utente scrive QUIT, il server si disconnette
                        if (inputLine.equals("QUIT")) {
                            break;
                        }else{
                            //controllo input client con domande ammesse
                            if(inputLine.startsWith(parteiniziale1)){
                                out.println("ricevuto genere");
                            }else if(inputLine.startsWith(parteiniziale2)){
                                out.println("ricevuto capelli");
                            }else if(inputLine.equals("Il personaggio ha gli occhiali?")){
                                out.println("ricevuto occhiali");
                            }else if(inputLine.equals("Il personaggio ha il cappello?")){
                                out.println("ricevuto capello");
                            }else if(inputLine.startsWith(parteiniziale3)){
                                out.println("ricevuto nome");
                            }else{
                                out.println("Domanda non ammessa");
                            }
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