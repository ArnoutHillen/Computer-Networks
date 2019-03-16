package client;

import java.net.URI;
import java.net.URISyntaxException;

public class Test {
    public static void main(String[] args) throws Exception {
        URI test1 = new URI("http://scouts.be/images/logo_gsb.jpg");
        System.out.println(test1.getPath());
        
        URI test2 = new URI("http://localhost/webpage/index.html");
        System.out.println(test2.getPath());
    }
}
