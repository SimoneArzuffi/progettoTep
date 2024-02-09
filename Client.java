import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            
            String userInput;
            

            while (true) 
            { 
                System.out.print("Inserisci: "); 
                userInput = stdIn.readLine(); 
                out.println(userInput);
                if (userInput.equals("QUIT")){
                    break; 
                }else{
                    System.out.println("Server says: " + in.readLine());
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
