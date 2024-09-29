package Advanced;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
	static Set<ClientHander> client=new HashSet<>();
	public static void main(String[] args) {
		try(ServerSocket serverSocket=new ServerSocket(12345)){
			System.out.println("Server Started........");
			while(true) {
				Socket socket=serverSocket.accept();
				System.out.println("Client Connected.......");
				ClientHander ch=new ClientHander(socket);
				client.add(ch);
				new Thread(ch).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	static void broadcastMsg(String message,ClientHander sender) {
		for(ClientHander client:client) {
			if(client!=sender) {
				client.sendMessage(message);
			}
		}
	}
	static void removeClient(ClientHander ch) {
		client.remove(ch);
	}

}
class ClientHander implements Runnable{
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	public ClientHander(Socket socket) {
		this.socket=socket;
	}
	public void run() {
		try {
			out=new PrintWriter(socket.getOutputStream(),true);
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message;
			while((message=in.readLine())!=null) {
				System.out.println("Received: "+message);
				ChatServer.broadcastMsg(message,this);
			}}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				socket.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			ChatServer.removeClient(this);
		}
	}
	void sendMessage(String message) {
		out.println(message);
	}
}
