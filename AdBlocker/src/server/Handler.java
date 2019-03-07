package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class Handler implements Runnable
{
	private Socket clientSocket;
	  
	  Handler(Socket clientSocket) {
	    this.clientSocket = clientSocket;
	  }
	  
	@Override
	public void run() {
		
		BufferedReader inFromClient = new BufferedReader(new
				InputStreamReader (connectionSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream
				(connectionSocket.getOutputStream());
		String clientSentence = inFromClient.readLine();
		System.out.println("Received: " + clientSentence);
		String capsSentence = clientSentence.toUpperCase() + '\n';
		outToClient.writeBytes(capsSentence);
  }
}
 //TODO implement this class properly 
