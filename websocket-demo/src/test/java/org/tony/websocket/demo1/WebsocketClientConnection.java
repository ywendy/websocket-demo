package org.tony.websocket.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class WebsocketClientConnection {
	
	

	 public Session session;  
	   
	    protected void start()  
	             {  
	   
	            WebSocketContainer container = ContainerProvider.getWebSocketContainer();  
	   
	            String uri = "ws://127.0.0.1:8080/websocket-demo/websocket.ws/12345";  
	            System.out.println("Connecting to " + uri);  
	            try {  
	                session = container.connectToServer(WebsocketClient.class, URI.create(uri));  
	            } catch (DeploymentException e) {  
	                e.printStackTrace();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }               
	   
	    }  
	    public static void main(String args[]){  
	    	WebsocketClientConnection client = new WebsocketClientConnection();  
	        client.start();  
	   
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
	        String input = "";  
	        try {  
	            do{  
	                input = br.readLine();  
	                if(!input.equals("exit"))  
	                    client.session.getBasicRemote().sendText(input);  
	   
	            }while(!input.equals("exit"));  
	   
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    }  


}
