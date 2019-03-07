package client;

import java.io.*;
import java.net.*;

public class Client {
	
	public static void main(String[] argsv) throws Exception {
		
		int port = Integer.parseInt(argsv[2]);
		URL url = new URL(argsv[1]);
		String httpCommand = argsv[0];
		
		BufferedReader inFromUser = new BufferedReader( 
				new InputStreamReader(System.in));
		
		//Make the connection
		Socket clientSocket = new Socket(url.getHost(), port);
		
		//Make a writer
		PrintWriter outToServer = new PrintWriter(
				clientSocket.getOutputStream());
				
		//TODO via outToServer request in right format what you need GET/HTTP1.1
		//TODO via inFromServer the requested data will come
		//TODO via inFromUser request data from the user for PUT and POST
		
		if (httpCommand.equals("HEAD")) {
			outToServer.println("HEAD / HTTP/1.1");
			outToServer.println("Host: " + url.getHost());
			outToServer.println("");
			outToServer.flush();
			
			BufferedReader inFromServer = new BufferedReader(
					new InputStreamReader(
							clientSocket.getInputStream()));
			
			// Read and print input from server line by line
			String line;
			while ((line = inFromServer.readLine()) != null) {
			    System.out.println(line);
			}
		}
		
		else if (httpCommand.equals("GET")){
			outToServer.println("GET / HTTP/1.1");
			outToServer.println("Host: " + url.getHost());
			outToServer.println("");
			outToServer.flush();
			
			BufferedReader inFromServer = new BufferedReader(
					new InputStreamReader(
							clientSocket.getInputStream()));
			String line;
			while ((line = inFromServer.readLine()) != null) {
			    System.out.println(line);
			}
		}
		
		else if (httpCommand.equals("PUT")) {
			// Read string from prompt and send onwards
			System.out.println("Insert string here: ");
			String sentence = inFromUser.readLine(); 
			
			outToServer.println("PUT " + sentence + " HTTP/1.1");
			outToServer.println("Host: " + url.getHost());
			outToServer.println("");
			outToServer.flush();
			
			BufferedReader inFromServer = new BufferedReader(
					new InputStreamReader(
							clientSocket.getInputStream()));
			String line;
			while ((line = inFromServer.readLine()) != null) {
			    System.out.println(line);
			}
			
		}
		
		else if (httpCommand.equals("POST")) {
			// Read string from prompt and send onwards
			System.out.println("Insert string here: ");
			String sentence = inFromUser.readLine(); 
			
			outToServer.println("POST " + sentence + " HTTP/1.1");
			outToServer.println("Host: " + url.getHost());
			outToServer.println("");
			outToServer.flush();
			
			BufferedReader inFromServer = new BufferedReader(
					new InputStreamReader(
							clientSocket.getInputStream()));
			String line;
			while ((line = inFromServer.readLine()) != null) {
			    System.out.println(line);
			}
		}
		
		
		clientSocket.close();
	}
	
}

