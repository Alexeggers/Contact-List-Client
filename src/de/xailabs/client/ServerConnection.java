package de.xailabs.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import de.xailabs.interfaces.IContact;

public class ServerConnection {
	private String hostName;
    private int portNumber;
    
    public ServerConnection(String hostName, int portNumber) {
    	this.hostName = hostName;
    	this.portNumber = portNumber;
    }
    
    
    public void startConnection() {
	    try (
	        Socket echoSocket = new Socket(hostName, portNumber);
	        ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());
	        ObjectInputStream in = new ObjectInputStream(echoSocket.getInputStream());
	    ){
	    	System.out.println("Connection established");
	    	IContact contact = new Contact("Alex", "3543514321351", "Notestest");
	    	CommandObject co = new CommandObject("new contact", contact);
	    	out.writeObject(co);
	    	out.flush();
	    } catch (UnknownHostException e) {
	        System.err.println("Don't know about host " + hostName);
	        System.exit(1);
	    } catch (IOException e) {
	        System.err.println("Couldn't get I/O for the connection to " +
	            hostName);
	        System.exit(1);
	    } 
    }
}
