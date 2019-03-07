package client;

import java.net.*;
import java.io.*;

public class Connection {

	private Socket socket;
    private String host;
    private int port;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
	
    
    /**
     * Constructor for a connection object.
     * 
     * @param host
     * @param port
     * @throws Exception
     */
    public Connection(String host, int port) 
    		throws Exception {
    	
    	this.host = host;
        this.port = port;
        this.socket = new Socket(
        		this.host, this.port);
        this.outputStream = new DataOutputStream(
        		this.socket.getOutputStream());
        this.inputStream = new DataInputStream(
        		this.socket.getInputStream());
    }
    
    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }
    
    /**
     * Method to close the client socket of the connection.
     * 
     * @throws Exception
     */
    public void close() throws Exception {
        this.socket.close();
    }
    
}
