package Advanced;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 12345);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

       System.out.println("Connected to the chat server");

       // Read messages from the server
       Thread receiveMessages = new Thread(() -> 
       {
           String serverMessage;
           try {
               while ((serverMessage = in.readLine()) != null) {
                   System.out.println(serverMessage);
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       });

       receiveMessages.start();

       // Send messages to the server
       Scanner scanner = new Scanner(System.in);
       while (true) {
           String message = scanner.nextLine();
           out.println(message);
       }
   } catch (IOException e) {
       e.printStackTrace();
   }
}


	}


