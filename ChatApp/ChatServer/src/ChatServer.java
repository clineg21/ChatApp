import java.io.*;
import java.net.*;
import java.util.Scanner;
public class ChatServer {
	private ServerSocket server;
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private Scanner scan = new Scanner(System.in);
	
	public ChatServer(int port) throws IOException {
		server = new ServerSocket(port);
		System.out.println("...Starting Chat");
	}
	
	public void run() throws IOException {
		boolean loop = true;
		socket = server.accept();
		while(loop == true) {
			writer = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//talk to the client
			System.out.print("Send Message to Client: ");
			String serverMsg = scan.nextLine();
			writer.println(serverMsg);
			if(serverMsg.equalsIgnoreCase("bye")) {
				loop = false;
				break;
			}
			
			//read data
			String data;
			data = reader.readLine();
			System.out.println("Client: " + data);
			if(data.equalsIgnoreCase("bye")) {
				loop = false;
			}
			
		}
		//shut down the server
		System.out.println("Shutting Server Down");
		server.close();
	}
	
	public static void main(String[] args) throws IOException {
		ChatServer serv = new ChatServer(8080);
		serv.run();

	}

}
