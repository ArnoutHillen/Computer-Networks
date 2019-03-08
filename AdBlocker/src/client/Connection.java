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
     * Returns a response from the server to the given request
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public Response getResponseFromServer(Request request) throws Exception {
        if (!this.socket.isConnected()) {
            this.socket = new Socket(this.host, this.port);
        }
        byte[] requestBytes = request.toBytes();
        this.outputStream.write(requestBytes);
        Response response = new Response(request);

        // Read character by character because:
        //  1) inputStream.readLine() strips 
        //     the \r and \n which are necessary 
        //     for character counting
        //  2) the last line doesn't necessarily 
        //     end with a \n, so inputStream.readLine() 
        //     doesn't read it.
        while (!response.headFinished()) {
            char nextChar = (char) inputStream.read();
            String line = "";
            while (nextChar != '\n') {
                line += nextChar;
                nextChar = (char) inputStream.read();
            }
            response.interpretHead(line);
        }
        
        if (!response.isFinished()) {
            int contentLength = 
            		response.getContentLength();
            byte[] data = new byte[contentLength];
            this.inputStream.readFully(data, 0, contentLength);
            response.setData(data);
        }
        
        return response;
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
