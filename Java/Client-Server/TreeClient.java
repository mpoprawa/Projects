import java.net.*;
import java.io.*;

public class TreeClient {
 
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost", 4444); 
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Console console = System.console();
            String text;
        do {
            text = console.readLine("Input command: ");
            out.println(text);
            System.out.println(in.readLine());
        } while (!text.equals("exit"));
        socket.close();
 
        }
        catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}