import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Server {

    private static Personaggio[] personaggi = new Personaggio[12];
    
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
                        if (inputLine.equalsIgnoreCase("QUIT")) {
                            break;
                        } else {
                            processInput(inputLine, out, personaggi[numeroCasuale]);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port 1234 or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port 1234");
            System.out.println(e.getMessage());
        }
    }

    private static void processInput(String inputLine, PrintWriter out, Personaggio personaggio) {
        String lowerCaseInput = inputLine.toLowerCase();
        
        LinkedList<String> nomiPersonaggi = new LinkedList<>();
        nomiPersonaggi.add("tommaso");
        nomiPersonaggi.add("alessandro");
        nomiPersonaggi.add("carlo");
        nomiPersonaggi.add("ernesto");
        nomiPersonaggi.add("guglielmo");
        nomiPersonaggi.add("maria");
        nomiPersonaggi.add("roberto");
        nomiPersonaggi.add("pietro");
        nomiPersonaggi.add("anna");
        nomiPersonaggi.add("giacomo");
        nomiPersonaggi.add("davide");
        nomiPersonaggi.add("bernardo");
        
        if (lowerCaseInput.contains("maschio") || lowerCaseInput.contains("femmina")) {
            out.println(lowerCaseInput.contains(personaggio.getGenere().toLowerCase()) ? "SI" : "NO");
        } else if (new LinkedList<>(Arrays.asList("neri", "biondi", "castani", "bianchi", "arancioni")).stream().anyMatch(lowerCaseInput::contains)) {
            out.println(lowerCaseInput.contains(personaggio.getColoreCapelli().toLowerCase()) ? "SI" : "NO");
        } else if (lowerCaseInput.contains("occhiali")) {
            out.println(personaggio.getOcchiali() ? "SI" : "NO");
        } else if (lowerCaseInput.contains("cappello")) {
            out.println(personaggio.getCappello() ? "SI" : "NO");
        } else if (lowerCaseInput.contains("barba")) {
            out.println(personaggio.getBarba() ? "SI" : "NO");
        } else if (nomiPersonaggi.stream().anyMatch(lowerCaseInput::contains)) {
            out.println(nomiPersonaggi.stream().anyMatch(name -> lowerCaseInput.contains(name) && personaggio.getNome().equalsIgnoreCase(name)) ? "SI" : "NO");
        } else {
            out.println("Non ho capito la domanda, riprova.");
        }
    }

    private static int numeroCasuale(int max){
        return (int)(Math.random() * max);
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
}