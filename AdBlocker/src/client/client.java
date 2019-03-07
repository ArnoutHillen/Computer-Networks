package client;

import java.io.*;
import java.net.*;

public class Client {
	
	public static void main(String[] argsv) throws Exception {
		
		int port = Integer.parseInt(argsv[2]);
		URI uri = new URI(argsv[1]);
		String httpCommand = argsv[0];
		
		BufferedReader inFromUser = new BufferedReader( 
				new InputStreamReader(System.in));
		
		//Make the connection
		Socket clientSocket = new Socket(uri.getHost(), port);
		BufferedReader inFromServer = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		
		//TODO via outToServer request in right format what you need GET/HTTP1.1
		//TODO via inFromServer the requested data will come
		//TODO via inFromUser request data from the user for PUT and POST
		
		if(httpCommand == "HEAD") {
			
		}
		
		else if (httpCommand == "GET") {
			
		}
		else if (httpCommand == "PUT") {
			// Read string from prompt and send onwards
			String sentence = inFromUser.readLine(); 
			outToServer.writeBytes(sentence);
			
			
			
		}
		else if (httpCommand == "POST") {
			// Read string from prompt and send onwards
			String sentence = inFromUser.readLine(); 
			outToServer.writeBytes(sentence);
			
			
		}
		
		
		clientSocket.close();
	}
	
}

