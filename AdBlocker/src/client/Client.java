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
		
	/**
     * The main method for the client side of this project, reading and saving the necessary information.
     * @param args
     *         COMMAND URI PORT(80) VERSION
     * @throws Exception
     */
	public static void main(String[] argsv) throws Exception {
		
		int port = Integer.parseInt(argsv[2]);
		String httpCommand = argsv[0];
		//System.out.println(httpCommand);

		URL url = new URL(argsv[1]);
		url = new URL(url.getProtocol(), url.getHost(), port, url.getFile());
		
		//System.out.println(port);
		//System.out.println(url);

		
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
        //System.out.println(response);
		
		if (httpCommand.equals("HEAD")) {
			
		}
		else if (httpCommand.equals("GET")) {
			HTMLDocument document = new HTMLDocument(response, connection);
            document.saveAll();
		}		
		
		connection.close();
	}
	
}

