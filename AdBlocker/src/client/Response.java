package client;

import java.util.*;

/**
 * Class representing a response received at 
 * the client side.
 * 
 * @author arnouthillen
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * A class representing the response on the client's side.
 */
public class Response {

    private final Request request;

    private boolean headerEnded = false;
    private boolean messageEnded = false;
    private boolean success;
    private int responseCode; // The response code, 
                              // for example 200
    private String reasonPhrase; // The phrase after the 
    							 // response code, for example OK
    private byte[] data;
    private Map<String, String> headers = new HashMap<>();
    private int contentLength;

    /**
     * The constructor for the request class. 
     * information to the headers.
     * 
     * @param request
     */
    public Response(Request request) {
        this.request = request;
    }

    /**
     * A method that interprets the given line from the connection.
     * @param line
     */
    public void interpretHead(String line) {

        if (this.messageEnded || this.headerEnded)
            return;

        // if the reasonPhrase is still null, the first line still has to be read.
        if (this.reasonPhrase == null) {
            String[] components = line.split(" ");
            // System.out.println(components[0]);
            this.responseCode = Integer.parseInt(components[1].trim());
            this.reasonPhrase = components[2].trim();
        } else {
            if (line.trim().equals("")) {
                this.headerEnded = true;

                // If the method was HEAD, there isn't any data, so the message has ended.
                if (this.request.getMethod() == "HEAD")
                    this.messageEnded = true;

                // If there isn't a header "content-length", there is no body, so the message has ended.
                if (!this.headers.containsKey("content-length")) {
                    this.contentLength = 0;
                    this.messageEnded = true;
                }
                // Else, if the content-length equals 0, there is no body, so the message has ended.
                else if (Integer.parseInt(this.headers.get("content-length")) == 0) {
                    this.messageEnded = true;
                    this.contentLength = 0;
                }

                if (this.getResponseCode() != 200) {
                    if (this.getResponseCode() == 404) {
                    	// System.out.println("Failed to load resource; the server responded with 404 for file " + this.getUrl());
                    }
                    this.success = false;
                } else {
                    this.success = true;
                }

                // If the message hasn't ended yet, set the content length to the header value.
                if (!this.messageEnded) {
                    this.contentLength = Integer.parseInt(this.headers.get("content-length"));
                }
            } else {
                // Add header
                line = line.trim();
                String[] components = line.split(":", 2);
                headers.put(components[0].toLowerCase(), components[1].trim());
            }
        }
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public boolean isFinished() {
        return this.messageEnded;
    }

    public boolean headFinished() {
        return this.headerEnded;
    }

    public int getContentLength() {
        return contentLength;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public URL getUrl() {
        return this.request.getUrl();
    }

    public boolean isSuccess() {
        return success;
    }
    
    
    
    
	
}
