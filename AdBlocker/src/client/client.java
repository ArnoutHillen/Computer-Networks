package client;

import java.io.*;
import java.util.*;
import java.net.*;

public class Client {
	
	/**
	 * Read string from prompt and send onwards
	 *
	 * @return
	 */
	public static String getUserInput() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Insert your data here (empty line to finish): ");
		String line;
		String input = "";
		while (!(line = reader.nextLine()).isEmpty()) {
			input += line;
		}
		return input;
	}
		
	public static void main(String[] argsv) throws Exception {
		
		int port = Integer.parseInt(argsv[2]);
		URL url = new URL(argsv[1]);
		String httpCommand = argsv[0];
			
		String data = "";
		
		if (httpCommand.equals("PUT")) {
			data = getUserInput();
		}
		else if (httpCommand.equals("POST")) {
			data = getUserInput();
		}
		
		Connection connection = new Connection(url.getHost(), port);
        Request request = new Request(httpCommand, url, "1.1", data);
        Response response = connection.getResponseFromServer(request);
        System.out.println(response);
		
		if (httpCommand.equals("HEAD")) {
			
		}
		else if (httpCommand.equals("GET")) {
			
		}		
		
		connection.close();
	}
	
}

