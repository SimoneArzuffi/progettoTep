import java.io.*;
import java.net.*;

public class Server {

    private static String parteiniziale1 = "Il personaggio è";
    private static String parteiniziale2 = "Il personaggio ha i capelli";
    private static String parteiniziale3 = "Il personaggio si chiama";
    private static Personaggio[] personaggi = new Personaggio[12];
    private static String[] paroleFrase;
       public static void main(String[] args) {
        creaPersonaggi();
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
                                //out.println("ricevuto genere");
                                paroleFrase = inputLine.split(" ");
                                if(paroleFrase[paroleFrase.length - 1].equals("maschio")){
                                    out.println("SI");
                                }else{
                                    out.println("NO");
                                }
                            }else if(inputLine.startsWith(parteiniziale2)){
                                //out.println("ricevuto capelli");
                                paroleFrase = inputLine.split(" ");
                            }else if(inputLine.equals("Il personaggio ha gli occhiali?")){
                                //out.println("ricevuto occhiali");
                            }else if(inputLine.equals("Il personaggio ha il cappello?")){
                                //out.println("ricevuto capello");
                            }else if(inputLine.startsWith(parteiniziale3)){
                                //out.println("ricevuto nome");
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

    private static void creaPersonaggi(){
        personaggi[0] = new Personaggio("tommaso", "maschio", "neri", false,true, false);
        personaggi[1] = new Personaggio("alessandro", "maschio", "neri", true,false, false);
        personaggi[2] = new Personaggio("carlo", "maschio", "biondi", true,false, true);
        personaggi[3] = new Personaggio("ernesto", "maschio", "neri", false,true, false);//to finish
    }
}