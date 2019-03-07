package client;

import java.net.*;
import java.util.*;
import java.io.*;

public class Request {
	
    private String method;
    private URL url;
    private String httpVersion;
    private byte[] data;
    private Map<String, String> head = new HashMap<>();
	
    public void request(String method, URL url,
    		String httpVersion, String data, Map headers) {
        this.url = url;
        this.httpVersion = httpVersion;
        this.data = data.getBytes();
    	
        // Check for HTTP version. 
        // HTTP/1.1 requires the host field.
        if (this.httpVersion.equals("1.1")) {
            this.head.put("Host", this.url.getHost() + ":" + this.url.getPort());
        }
    }
    
    public String getMethod() {
        return method;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public Map<String, String> getHead() {
        return head;
    }

    public URL getUrl() {
        return url;
    }
    
    /**
     * Method that returns the header of 
     * the HTML file as a string.
     * 
     * @return
     */
    private String getHeader() {
        StringBuilder result = new StringBuilder();
        
        result.append(this.method)
                .append(" ");
        
        if (! this.url.getPath().startsWith("/"))
            result.append("/");
        
        result.append(this.url.getPath().replace(" ", "%20"))
                .append(" ")
                .append("HTTP/")
                .append(this.httpVersion)
                .append("\n");
        
        appendHead(result).append("\n");

        return result.toString();
    }
    
    /**
     * Method that builds the header of the HTML file
     * as a StringBuilder object.
     * 
     * @param builder
     * @return
     */
    private StringBuilder appendHead(StringBuilder builder) {
        for (Map.Entry<String, String> entry : head.entrySet()) {
            builder.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return builder;
    }
    
}
