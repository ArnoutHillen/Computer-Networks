package server;

import java.io.*;
import java.net.*;

public class Server {
		
	public static void main(String argv[]) throws Exception {
	
		// Only the port number should be provided
		if (argv.length != 1) {
			throw new IllegalArgumentException(); 
		}

		int portNumber = Integer.parseInt(argv[0]);
		ServerSocket socket = new ServerSocket(portNumber);
		System.out.println("Listening on port " + portNumber);
		
		// Launch new thread for every client
		while (true) {
			Socket connectionSocket = socket.accept();
			if (connectionSocket != null) {
				Handler request = new Handler(connectionSocket);
				Thread thread = new Thread(request);
				thread.start();
			}
		}
	
	}
}

