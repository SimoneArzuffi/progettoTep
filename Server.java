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
        int numeroCasuale = numeroCasuale(personaggi.length);
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is listening on port 1234");
            
            while (true) {
                try (Socket socket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    System.out.println("Numero scelto: " + numeroCasuale);
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
                                if(paroleFrase[paroleFrase.length - 2].equals(personaggi[numeroCasuale].getGenere())){
                                    out.println("SI");
                                }else{
                                    out.println("NO");
                                }
                            }else if(inputLine.startsWith(parteiniziale2)){
                                //out.println("ricevuto capelli");
                                paroleFrase = inputLine.split(" ");
                                if(paroleFrase[paroleFrase.length - 2].equals(personaggi[numeroCasuale].getColoreCapelli())){
                                    out.println("SI");
                                }else if (paroleFrase[paroleFrase.length - 2].equals(personaggi[numeroCasuale].getColoreCapelli())){
                                    out.println("SI");
                                }else if (paroleFrase[paroleFrase.length - 2].equals(personaggi[numeroCasuale].getColoreCapelli())){
                                    out.println("SI");
                                }else if (paroleFrase[paroleFrase.length - 2].equals(personaggi[numeroCasuale].getColoreCapelli())){
                                    out.println("SI");
                                }else if (paroleFrase[paroleFrase.length - 2].equals(personaggi[numeroCasuale].getColoreCapelli())){
                                    out.println("SI");
                                }else{
                                    out.println("NO");
                                }
                            }else if(inputLine.equals("Il personaggio ha gli occhiali ?")){
                                //out.println("ricevuto occhiali");
                                if(personaggi[numeroCasuale].getOcchiali()){
                                    out.println("SI");
                                }else{
                                    out.println("NO");
                                }
                            }else if(inputLine.equals("Il personaggio ha il cappello ?")){
                                //out.println("ricevuto capello");
                                if(personaggi[numeroCasuale].getCappello()){
                                    out.println("SI");
                                }else{
                                    out.println("NO");
                                }
                            }else if(inputLine.startsWith(parteiniziale3)){
                                //out.println("ricevuto nome");
                                paroleFrase = inputLine.split(" ");
                                if(paroleFrase[paroleFrase.length - 2].equals(personaggi[numeroCasuale].getNome())){
                                    out.println("SI");
                                }else{
                                    out.println("NO");
                                }
                            }else if(inputLine.equals("Il personaggio ha la barba ?")){
                                //out.println("ricevuto barba");
                                if(personaggi[numeroCasuale].getBarba()){
                                    out.println("SI");
                                }else{
                                    out.println("NO");
                                }
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
        personaggi[0] = new Personaggio("Tommaso", "maschio", "neri", false,true, false);
        personaggi[1] = new Personaggio("Alessandro", "maschio", "neri", true,false, false);
        personaggi[2] = new Personaggio("Carlo", "maschio", "biondi", true,false, true);
        personaggi[3] = new Personaggio("Ernesto", "maschio", "biondi", false,false, true);
        personaggi[4] = new Personaggio("Guglielmo", "maschio", "biondi", true,false, false);
        personaggi[5] = new Personaggio("Maria", "femmina", "castani", false,false, true);
        personaggi[6] = new Personaggio("Roberto", "maschio", "castani", false,false, false);
        personaggi[7] = new Personaggio("Pietro", "maschio", "bianchi", false,true, false);
        personaggi[8] = new Personaggio("Anna", "femmina", "neri", false,false, false);
        personaggi[9] = new Personaggio("Giacomo", "maschio", "arancioni", false,false, false);
        personaggi[10] = new Personaggio("Davide", "maschio", "biondi", true,false, false);
        personaggi[11] = new Personaggio("Bernardo", "maschio", "neri", false,false, true);
    }
    private static int numeroCasuale(int max){
        return (int)(Math.random() * max);
    }
}