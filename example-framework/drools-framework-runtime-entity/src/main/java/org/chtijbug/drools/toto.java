package org.chtijbug.drools;

import java.net.MalformedURLException;
import java.net.URL;

public class toto {
     private String test = "ws://localhost:8080/kie-wb/websocket/controller/server/15ad5bfa-7532-3eea-940a-abbbbc89f1e8";
    public static void main(String[] args){
        try {
            URL toto = new URL("wss://localhost:8080/kie-wb/websocket/controller/server/15ad5bfa-7532-3eea-940a-abbbbc89f1e8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
