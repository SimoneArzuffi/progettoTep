import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientGUI extends JFrame {
    private JTextField userInputField;
    private JTextArea messagesArea;
    private JButton sendButton;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private JPanel imagesPanel;

    public ClientGUI() {
        try {
            socket = new Socket("localhost", 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        createUI();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Imposta il frame a tutto schermo
    }

    private void createUI() {
        setTitle("Indovina Chi - Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userInputField = new JTextField();
        messagesArea = new JTextArea();
        sendButton = new JButton("Invia");

        messagesArea.setEditable(false);

        // Aggiungi le istruzioni di gioco a messagesArea
        messagesArea.append("Per uscire scrivi QUIT\n");
        messagesArea.append("Il gioco dell'indovina chi\n");
        messagesArea.append("Il server pensa ad un personaggio e tu devi indovinarlo\n");
        messagesArea.append("Per indovinare il personaggio devi fare delle domande al server\n");
        messagesArea.append("Il server risponderà con SI o NO\n");

        // Imposta le dimensioni preferite per userInputField e sendButton
        userInputField.setPreferredSize(new Dimension(getWidth(), 30));
        sendButton.setPreferredSize(new Dimension(getWidth(), 30));

        // Aggiungi ActionListener a userInputField per inviare il testo con il tasto Enter
        userInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButton.doClick(); // Simula il click sul pulsante di invio
            }
        });

        setLayout(new BorderLayout(5, 5)); // Imposta un margine tra i componenti
        add(userInputField, BorderLayout.NORTH);
        add(new JScrollPane(messagesArea), BorderLayout.CENTER); // Assicura che messagesArea utilizzi la maggior parte dello spazio
        add(sendButton, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = userInputField.getText();
                try {
                    if (!userInput.equals("QUIT")) {
                        out.println(userInput);
                        messagesArea.append("Tu: " + userInput + "\n");
                        userInputField.setText("");
                        String response = in.readLine();
                        messagesArea.append("Server: " + response + "\n");
                    } else {
                        out.println("QUIT");
                        socket.close();
                        System.exit(0);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        setVisible(true);

        imagesPanel = new JPanel(new GridLayout(3, 4, 10, 10)); // 3 righe, 4 colonne, margini tra le celle
        imagesPanel.setPreferredSize(new Dimension(800, 600)); // Dimensione preferita, adattala secondo le tue esigenze

        // Aggiungi le immagini al pannello
        for (int i = 1; i <= 12; i++) {
            ImageIcon icon = new ImageIcon("immagine" + i + ".png"); // Percorso dell'immagine
            JLabel label = new JLabel();
            label.setIcon(icon);
            imagesPanel.add(label);
        }

        // Aggiungi il pannello delle immagini al frame
        add(imagesPanel, BorderLayout.EAST); // Aggiungi il pannello delle immagini a destra nella GUI
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientGUI();
            }
        });
    }
}              