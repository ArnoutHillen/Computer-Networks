package client;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Request {
	
    private String method;
    private URL url;
    private String httpVersion;
    private byte[] data;
    private Map<String, String> head = new HashMap<>();
	
    public Request(String httpCommand, URL url,
    		String httpVersion, String data) {
    	
        this.url = url;
        this.httpVersion = httpVersion;
        this.data = data.getBytes();
    	
        // Check for HTTP version. 
        // HTTP/1.1 requires the host field.
        if (this.httpVersion.equals("1.1")) {
            this.head.put("Host", this.url.getHost() + 
            		":" + this.url.getPort());
        }
        
        // Content-length required if data present.
        if (this.data.length > 0) {
            this.head.put("Content-Length", 
            		Integer.toString(this.data.length));
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
        
        if (!this.url.getPath().startsWith("/"))
            result.append("/");
        
        result.append(this.url.getPath().replace(" ", "%20"))
                .append(" ")
                .append("HTTP/")
                .append(this.httpVersion)
                .append("\r\n");
        
        appendHead(result).append("\r\n");

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
                    .append("\r\n");
        }
        return builder;
    }
    
    /**
     * Returns the header in bytes.
     * 
     * @return
     */
    public byte[] toBytes() {
        return concat(getHeader().getBytes(StandardCharsets.ISO_8859_1),
        		this.data);
    }

    /**
     *
     * @param components
     * @return
     */
    private static byte[] concat(byte[]... components) {
        int length = 0;
        for (byte[] component : components) {
            length += component.length;
        }
        byte[] result = new byte[length];

        for (int i = 0; i < components.length; i++) {
            if (i == 0) {
                System.arraycopy(components[0], 0, 
                		result, 0, components[0].length);
            } else {
                System.arraycopy(components[i], 0, 
                		result, components[i - 1].length, 
                		components[i].length);
            }
        }
        return result;
    }
    
}
