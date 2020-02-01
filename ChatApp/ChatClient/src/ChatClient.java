import java.io.*;
import java.net.*;
import java.util.Scanner;
public class ChatClient {
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private Scanner scan = new Scanner(System.in);
	
	public ChatClient(String peer, int port) throws IOException {
		socket = new Socket(peer, port);
		writer = new PrintWriter(socket.getOutputStream(), true);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void run() throws IOException {
		boolean loop = true;
		while(loop == true) {
			//read data from server
			String data;
			data = reader.readLine();
			System.out.println("Server: " + data);
			if(data.equalsIgnoreCase("bye")) {
				loop = false;
				break;
			}
			//talk to the server
			System.out.print("Send Message to Server: ");
			String clientMsg = scan.nextLine();
			writer.println(clientMsg);
			if(clientMsg.equalsIgnoreCase("bye")) {
				loop = false;
			}
		}
		//shut down the client
		System.out.println("Shutting Down Client");
		socket.close();
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ChatClient client = new ChatClient("localhost", 8080);
		client.run();

	}

}
