import java.io.*;
import java.net.*;
 
/**Klasa serwera */
public class TreeServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            System.out.println("Server active");
            //Laczenie klienta
            while (true) {
                Socket socket = serverSocket.accept();
                new TreeThread(socket).start();
                System.out.println("New client connected");
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}